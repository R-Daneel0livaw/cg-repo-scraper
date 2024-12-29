package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;
import com.olivaw.codegraph.scraper.model.StorageData;
import com.olivaw.codegraph.scraper.model.StorageResult;

public class APIResponseStorageService implements StorageService {
    @Override
    public StorageResult store(StorageData storageData) throws StorageException {
       return new StorageResult("Files successfully retrieved", storageData.getFiles());
    }

    @Override
    public StorageResult retrieve(StorageData storageData) throws StorageException {
        return store(storageData);
    }

    @Override
    public StorageResult delete(StorageData storageData) throws StorageException {
        throw new UnsupportedOperationException("Delete operation is not applicable for API Response Storage.");
    }
}
