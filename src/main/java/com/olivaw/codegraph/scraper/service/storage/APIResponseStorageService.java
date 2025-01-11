package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;
import com.olivaw.codegraph.scraper.model.StorageData;
import com.olivaw.codegraph.scraper.model.StorageResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("apiStorageService")
public class APIResponseStorageService implements StorageService<Void> {
    @Override
    public StorageResult store(StorageData<Void> storageData) throws StorageException {
       return new StorageResult("Files successfully retrieved", storageData.getFiles());
    }

    @Override
    public StorageResult retrieve(StorageData<Void> storageData) throws StorageException {
        return store(storageData);
    }

    @Override
    public StorageResult delete(StorageData<Void> storageData) throws StorageException {
        throw new UnsupportedOperationException("Delete operation is not applicable for API Response Storage.");
    }
}
