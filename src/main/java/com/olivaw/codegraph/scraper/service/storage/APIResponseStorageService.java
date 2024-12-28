package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class APIResponseStorageService implements StorageService {
    @Override
    public void store(String targetPath, byte[] data) throws StorageException {
        throw new UnsupportedOperationException("Store operation is not applicable for API Response Storage.");
    }

    @Override
    public byte[] retrieve(String targetPath) throws StorageException {
//        List<FileData> apiResponseData = new ArrayList<>();
//        for (File file : files) {
//            try {
//                byte[] fileData = Files.readAllBytes(file.toPath());
//                apiResponseData.add(new FileData(file.getName(), fileData));
//            } catch (IOException e) {
//                throw new StorageException("Failed to read file: " + file.getAbsolutePath(), e);
//            }
//        }
//        return apiResponseData;
        return null;
    }

    @Override
    public void delete(String targetPath) throws StorageException {
        throw new UnsupportedOperationException("Delete operation is not applicable for API Response Storage.");
    }

    public static class FileData {
        private final String fileName;
        private final byte[] data;

        public FileData(String fileName, byte[] data) {
            this.fileName = fileName;
            this.data = data;
        }

        public String getFileName() {
            return fileName;
        }

        public byte[] getData() {
            return data;
        }
    }
}
