package com.olivaw.codegraph.scraper.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    @Value("${storage.basePath}")
    private static String basePath;

    public static Path createTargetDirectory(String targetPath) throws IOException {
        String newPath = (targetPath == null || targetPath.isEmpty()) ? "" : targetPath;
        Path targetDirectory = Paths.get(basePath, newPath);
        Files.createDirectories(targetDirectory.getParent());
        return targetDirectory;
    }
}
