package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;
import com.olivaw.codegraph.scraper.model.StorageData;
import com.olivaw.codegraph.scraper.model.StorageResult;

public class APIResponseStorageService implements StorageService {
    @Override
    public StorageResult store(StorageData storageData) throws StorageException {
        throw new UnsupportedOperationException("Store operation is not applicable for API Response Storage.");
    }

    @Override
    public StorageResult retrieve(StorageData storageData) throws StorageException {
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
    public StorageResult delete(StorageData storageData) throws StorageException {
        throw new UnsupportedOperationException("Delete operation is not applicable for API Response Storage.");
    }
}
