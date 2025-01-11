package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;
import com.olivaw.codegraph.scraper.model.StorageData;
import com.olivaw.codegraph.scraper.model.StorageResult;

public interface StorageService<T> {

    StorageResult store(StorageData<T> storageData) throws StorageException;

    StorageResult retrieve(StorageData<T> storageData) throws StorageException;

    StorageResult delete(StorageData<T> storageData) throws StorageException;
}
