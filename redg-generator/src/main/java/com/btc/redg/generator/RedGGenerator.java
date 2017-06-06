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

package com.btc.redg.generator;

import com.btc.redg.generator.exceptions.RedGGenerationException;
import com.btc.redg.generator.extractor.DatabaseManager;
import com.btc.redg.generator.extractor.MetadataExtractor;
import com.btc.redg.generator.extractor.TableExtractor;
import com.btc.redg.generator.extractor.conveniencesetterprovider.ConvenienceSetterProvider;
import com.btc.redg.generator.extractor.datatypeprovider.DataTypeProvider;
import com.btc.redg.generator.extractor.explicitattributedecider.ExplicitAttributeDecider;
import com.btc.redg.generator.extractor.nameprovider.NameProvider;
import com.btc.redg.generator.utils.FileUtils;
import com.btc.redg.models.TableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schemacrawler.schema.Catalog;
import schemacrawler.schemacrawler.IncludeAll;
import schemacrawler.schemacrawler.InclusionRule;
import schemacrawler.schemacrawler.SchemaCrawlerException;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * The main class for the RedG generator.
 */
public class RedGGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(RedGGenerator.class);


    /**
     * This is a convenience method that combines the whole metadata extraction and code generation process into a single method while still offering
     * most of the customizability.
     * <p>
     * First, this method will use the provided JDBC connection to run SchemaCrawler over the database and extract the metadata. The {@code schemaRule} and
     * {@code tableRule} will be passed on to SchemaCrawler and can be used to specify the tables for which metadata should be extracted.
     * <p>Afterwards the {@link MetadataExtractor} gets called and extracts the relevant data into a list of {@link TableModel}s.
     * <p>The third step generates the code and writes it into the source files at the specified location.
     *
     * @param connection                 The established JDBC connection that will be used as the data source for the analysis
     * @param schemaRule                 The rule used for inclusion/exclusion of database schemas. Use {@code null} or {@link IncludeAll} for no filtering.
     * @param tableRule                  The rule used for inclusion/Exclusion of database tables. Use {@code null} or {@link IncludeAll} for no filtering.
     * @param targetPackage              The java package for the generated code. May not be default package. If {@code null},
     *                                   defaults to {@link TableExtractor#DEFAULT_TARGET_PACKAGE}
     * @param classPrefix                The prefix for the generated java class names
     * @param targetDirectory            The directory to put the generated source files. The package directory structure will be generated automatically
     * @param dataTypeProvider           The data type provider for customization of the SQL type to java type mapping. If {@code null},
     *                                   a {@link com.btc.redg.generator.extractor.datatypeprovider.DefaultDataTypeProvider} will be used.
     * @param nameProvider               The {@link NameProvider} used to determine the names in the generated code
     * @param convenienceSetterProvider  A provider that determines convenience setters
     * @param explicitAttributeDecider   The {@link ExplicitAttributeDecider} that will be used to determine whether a attribute / foreign key should be treated
     *                                   as explicitly required
     * @param enableVisualizationSupport If {@code true}, the RedG visualization features will be enabled for the generated code. This will result in a small
     *                                   performance hit and slightly more memory usage if activated.
     * @param shouldCloseConnection      Indicates whether the JDBC connection should be closed after the database analysis
     */
    public static void generateCode(final Connection connection,
                                    final InclusionRule schemaRule,
                                    final InclusionRule tableRule,
                                    String targetPackage,
                                    String classPrefix,
                                    final Path targetDirectory,
                                    final DataTypeProvider dataTypeProvider,
                                    final NameProvider nameProvider,
                                    final ExplicitAttributeDecider explicitAttributeDecider,
                                    final ConvenienceSetterProvider convenienceSetterProvider,
                                    final boolean enableVisualizationSupport,
                                    final boolean shouldCloseConnection) {
        Objects.requireNonNull(connection, "RedG requires a JDBC connection to a database to perform an analysis");
        targetPackage = targetPackage != null ? targetPackage : TableExtractor.DEFAULT_TARGET_PACKAGE;
        classPrefix = classPrefix != null ? classPrefix : TableExtractor.DEFAULT_CLASS_PREFIX;
        final TableExtractor tableExtractor = new TableExtractor(classPrefix, targetPackage, dataTypeProvider, nameProvider, explicitAttributeDecider,
                convenienceSetterProvider);
        Objects.requireNonNull(targetDirectory, "RedG needs a target directory for the generated source code");

        LOG.info("Starting the RedG all-in-one code generation.");

        Catalog databaseCatalog = crawlDatabase(connection, schemaRule, tableRule, shouldCloseConnection);
        final List<TableModel> tables = extractTableModel(tableExtractor, databaseCatalog);
        Path targetWithPkgFolders = createPackageFolderStructure(targetDirectory, targetPackage);
        new CodeGenerator().generate(tables, targetWithPkgFolders, enableVisualizationSupport);
    }

    private static Catalog crawlDatabase(Connection connection, InclusionRule schemaRule, InclusionRule tableRule, boolean shouldCloseConnection) {
        Catalog database;
        try {
            LOG.info("Crawling the database for metadata...");
            database = DatabaseManager.crawlDatabase(connection, schemaRule, tableRule);
            LOG.info("Crawling done. Metadata completely assembled.");
        } catch (SchemaCrawlerException e) {
            LOG.error("Crawling failed with an exception.", e);
            throw new RedGGenerationException("Crawling failed", e);
        } finally {
            if (shouldCloseConnection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.warn("Closing the JDBC connection failed. Code generation will continue.");
                }
            }
        }
        return database;
    }

    private static List<TableModel> extractTableModel(TableExtractor tableExtractor, Catalog database) {
        LOG.info("Extracting the required information from the metadata...");
        final List<TableModel> tables = MetadataExtractor.extract(database, tableExtractor);
        LOG.info("Extraction done.");

        if (tables.size() == 0) {
            LOG.error("No tables extracted! The database is either empty or everything is excluded by rules");
            throw new RedGGenerationException("No tables to process!");
        }
        return tables;
    }

    private static Path createPackageFolderStructure(Path targetDirectory, String targetPackage) {
        Path targetWithPkgFolders;
        try {
            LOG.info("Creating package folder structure...");
            targetWithPkgFolders = FileUtils.generateJavaPackageStructure(targetDirectory, targetPackage);
            LOG.info("Creation successful.");
        } catch (IOException e) {
            LOG.error("Creation failed.", e);
            throw new RedGGenerationException("Creation of package folder structure failed", e);
        }
        return targetWithPkgFolders;
    }

}
