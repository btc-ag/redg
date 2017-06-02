/*
 * Copyright 2017 BTC Business Technology AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.btc.redg.plugin;

import com.btc.redg.generator.RedGGenerator;
import com.btc.redg.generator.exceptions.RedGGenerationException;
import com.btc.redg.generator.extractor.DatabaseManager;
import com.btc.redg.generator.extractor.TableExtractor;
import com.btc.redg.generator.extractor.conveniencesetterprovider.ConvenienceSetterProvider;
import com.btc.redg.generator.extractor.conveniencesetterprovider.DefaultConvenienceSetterProvider;
import com.btc.redg.generator.extractor.conveniencesetterprovider.xml.XmlFileConvenienceSetterProvider;
import com.btc.redg.generator.extractor.datatypeprovider.DataTypeProvider;
import com.btc.redg.generator.extractor.datatypeprovider.DefaultDataTypeProvider;
import com.btc.redg.generator.extractor.datatypeprovider.NoPrimitiveTypesDataTypeProviderWrapper;
import com.btc.redg.generator.extractor.datatypeprovider.json.JsonFileDataTypeProvider;
import com.btc.redg.generator.extractor.datatypeprovider.xml.XmlFileDataTypeProvider;
import com.btc.redg.generator.extractor.explicitattributedecider.DefaultExplicitAttributeDecider;
import com.btc.redg.generator.extractor.explicitattributedecider.ExplicitAttributeDecider;
import com.btc.redg.generator.extractor.explicitattributedecider.JsonFileExplicitAttributeDecider;
import com.btc.redg.generator.extractor.nameprovider.MultiProviderNameProvider;
import com.btc.redg.jpa.JpaMetamodelRedGProvider;
import com.btc.redg.generator.extractor.nameprovider.json.JsonFileNameProvider;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import schemacrawler.schemacrawler.RegularExpressionInclusionRule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The MOJO class for the RedG maven plugin
 */
@Mojo(name = "redg", defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES)
public class RedGGeneratorMojo extends AbstractMojo {

    @Parameter(property = "redg.connectionString", defaultValue = "jdbc:h2:mem:redg;DB_CLOSE_DELAY=-1")
    private String connectionString;

    @Parameter(property = "redg.username")
    private String username = "";

    @Parameter(property = "redg.password")
    private String password = "";

    @Parameter(property = "redg.jdbcDriver", defaultValue = "org.h2.Driver")
    private String jdbcDriver;

    @Parameter(property = "redg.enableVisualization")
    private boolean enableVisualizationSupport = false;

    @Parameter
    private File[] sqlScripts;

    @Parameter(defaultValue = ".*")
    private String tablesRegex;

    @Parameter(defaultValue = ".*")
    private String schemaRegex;

    @Parameter(defaultValue = "target/generated-test-sources/redg")
    private File outputDirectory;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Parameter
    private File customTypeMappings;

    @Parameter
    private File explicitAttributesConfig;

    @Parameter
    private File customNameMappings;

    @Parameter
    private File convenienceSetterConfig;

    @Parameter
    private JpaProviderConfig jpaProviderConfig;

    @Parameter
    private boolean allowPrimitiveTypes = false;

    @Parameter
    private String targetPackage = TableExtractor.DEFAULT_TARGET_PACKAGE;

    @Parameter
    private String classPrefix = TableExtractor.DEFAULT_CLASS_PREFIX;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Connection connection;
        try {
            connection = DatabaseManager.connectToDatabase(jdbcDriver, connectionString, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new MojoFailureException("Could not connect to extractor: " + e.toString(), e);
        }
        try {
            DatabaseManager.executePreparationScripts(connection, sqlScripts);
        } catch (IOException | SQLException e) {
            throw new MojoFailureException("Could not execute SQL scripts: " + e.toString(), e);
        }
        // Clean-up old code files
        cleanFolder(outputDirectory);

        JpaMetamodelRedGProvider jpaProvider = null;
        if (jpaProviderConfig != null && jpaProviderConfig.getPersistenceUnitName() != null) {
            jpaProvider = JpaMetamodelRedGProvider.fromPersistenceUnit(jpaProviderConfig.getPersistenceUnitName());
        }

        DataTypeProvider dataTypeProvider = new DefaultDataTypeProvider();
        if (jpaProvider != null && jpaProviderConfig.isUseAsDataTypeProvider()) {
            jpaProvider.setFallbackDataTypeProvider(dataTypeProvider);
            dataTypeProvider = jpaProvider;
        }
        if (customTypeMappings != null) {
            try {
                if (customTypeMappings.getName().endsWith(".json")) {
                    dataTypeProvider = new JsonFileDataTypeProvider(customTypeMappings, dataTypeProvider);
                } else {
                    dataTypeProvider = new XmlFileDataTypeProvider(
                            new InputStreamReader(new FileInputStream(customTypeMappings), "UTF-8"), dataTypeProvider);
                }
            } catch (IOException e) {
                throw new MojoFailureException("Could not read custom type mappings", e);
            }
        }
        if (!allowPrimitiveTypes) {
            dataTypeProvider = new NoPrimitiveTypesDataTypeProviderWrapper(dataTypeProvider);
        }

        ExplicitAttributeDecider explicitAttributeDecider;
        if (explicitAttributesConfig != null) {
            try {
                explicitAttributeDecider = new JsonFileExplicitAttributeDecider(explicitAttributesConfig);
            } catch (IOException e) {
                throw new MojoFailureException("Could not read explicit attribute decider config", e);
            }
        } else {
            explicitAttributeDecider = new DefaultExplicitAttributeDecider();
        }

        MultiProviderNameProvider nameProvider = new MultiProviderNameProvider();
        if (customNameMappings != null) {
            try {
                nameProvider.appendProvider(new JsonFileNameProvider(customNameMappings));
            } catch (IOException e) {
                throw new MojoFailureException("Could not read custom name mappings", e);
            }
        }
        if (jpaProvider != null && jpaProviderConfig.isUseAsNameProvider()) {
            nameProvider.appendProvider(jpaProvider);
        }


        ConvenienceSetterProvider convenienceSetterProvider;
        if (convenienceSetterConfig != null) {
            try {
                convenienceSetterProvider =
                        new XmlFileConvenienceSetterProvider(new InputStreamReader(new FileInputStream(convenienceSetterConfig), "UTF-8"));
            } catch (IOException e) {
                throw new MojoFailureException("Could not read convenience setter config.", e);
            }
        } else {
            convenienceSetterProvider = new DefaultConvenienceSetterProvider();
        }

        try {
            RedGGenerator.generateCode(connection,
                    new RegularExpressionInclusionRule(this.schemaRegex),
                    new RegularExpressionInclusionRule(this.tablesRegex),
                    targetPackage,
                    classPrefix,
                    outputDirectory.toPath(),
                    dataTypeProvider,
                    nameProvider,
                    explicitAttributeDecider,
                    convenienceSetterProvider,
                    this.enableVisualizationSupport,
                    true);
        } catch (RedGGenerationException e) {
            throw new MojoFailureException("Code generation failed", e);
        }
        getLog().info("Code generation successful. Adding code to compilation");
        project.addTestCompileSourceRoot(outputDirectory.getPath());


    }

    private void cleanFolder(final File folder) {
        final File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    cleanFolder(f);
                }
                if (!f.delete()) {
                    getLog().warn("Could not delete file " + f.getAbsolutePath() + ". This could cause problems when using the generated classes");
                }
            }
        }
    }
}
