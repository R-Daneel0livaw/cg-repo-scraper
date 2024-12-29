package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;
import com.olivaw.codegraph.scraper.model.StorageData;
import com.olivaw.codegraph.scraper.model.StorageResult;

public interface StorageService {

    StorageResult store(StorageData storageData) throws StorageException;

    StorageResult retrieve(StorageData storageData) throws StorageException;

    StorageResult delete(StorageData storageData) throws StorageException;
}
