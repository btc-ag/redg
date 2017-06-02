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

package com.btc.redg.generator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Contains utility methods for file/file-system operations
 */
public class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public static Path generateJavaPackageStructure(final Path start, final String packageName) throws IOException {
        final String[] subFolders = packageName.split("\\.");
        Path path = start.toAbsolutePath();
        for (String folder : subFolders) {
            path = path.resolve(folder);
        }
        Files.createDirectories(path);
        return path;
    }

    public static void writeCodeFile(final Path targetWithPkgFolders, final String className, final String code) throws IOException {
        final Path targetFile = targetWithPkgFolders.resolve(className+ ".java");
        LOG.debug("Writing code to file {}", targetFile.toAbsolutePath().toString());
        Files.write(targetFile, code.getBytes());
    }
}
