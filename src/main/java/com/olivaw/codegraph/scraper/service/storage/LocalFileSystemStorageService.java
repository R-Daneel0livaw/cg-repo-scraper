package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalFileSystemStorageService implements StorageService {
    @Override
    public void store(String targetPath, byte[] data) throws StorageException {
        try {
            Path path = Paths.get(targetPath);
            Files.createDirectories(path.getParent());
            Files.write(path, data);
        } catch (IOException e) {
            throw new StorageException("Failed to store data locally at " + targetPath, e);
        }
    }

    @Override
    public byte[] retrieve(String targetPath) throws StorageException {
        try {
            return Files.readAllBytes(Paths.get(targetPath));
        } catch (IOException e) {
            throw new StorageException("Failed to retrieve data from local storage at " + targetPath, e);
        }
    }

    @Override
    public void delete(String targetPath) throws StorageException {
        try {
            Files.deleteIfExists(Paths.get(targetPath));
        } catch (IOException e) {
            throw new StorageException("Failed to delete file at " + targetPath, e);
        }
    }
}
