package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;
import com.olivaw.codegraph.scraper.model.StorageData;
import com.olivaw.codegraph.scraper.model.StorageResult;
import com.olivaw.codegraph.scraper.utils.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Qualifier("localFileStorageService")
public class LocalFileSystemStorageService implements StorageService<List<File>> {

    @Override
    public StorageResult store(StorageData<List<File>> storageData) throws StorageException {
        Path targetDirectory;

        try {
            targetDirectory = FileUtils.createTargetDirectory(storageData.getTargetPath());
        } catch (IOException e) {
            throw new StorageException("Failed to create target directory: " + storageData.getTargetPath(), e);
        }

        for (File file : storageData.getData()) {
            if (file.isFile()) {
                Path sourcePath = file.toPath();
                Path targetPath = targetDirectory.resolve(file.getName());

                try {
                    Files.copy(sourcePath, targetPath);
                } catch (IOException e) {
                    throw new StorageException("Unable to place file " + sourcePath + " at location " + targetPath, e);
                }
            }
        }
        return new StorageResult("Files successfully uploaded to local storage", storageData.getTargetPath());
    }

    @Override
    public StorageResult retrieve(StorageData<List<File>> storageData) throws StorageException {
        try {
            Files.readAllBytes(Paths.get(storageData.getTargetPath()));
        } catch (IOException e) {
            throw new StorageException("Failed to retrieve data from local storage at " + storageData.getTargetPath(), e);
        }
        return null;
    }

    @Override
    public StorageResult delete(StorageData<List<File>> storageData) throws StorageException {
        try {
            Files.deleteIfExists(Paths.get(storageData.getTargetPath()));
        } catch (IOException e) {
            throw new StorageException("Failed to delete file at " + storageData.getTargetPath(), e);
        }
        return null;
    }
}
