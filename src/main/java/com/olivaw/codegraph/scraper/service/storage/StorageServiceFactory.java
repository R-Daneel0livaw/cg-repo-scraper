package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.model.request.ResultDestinationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.util.List;

public class StorageServiceFactory {
    @Autowired
    @Qualifier("localFileStorageService")
    private StorageService<List<File>> localFileStorageService;

    @Autowired
    @Qualifier("apiStorageService")
    private StorageService<Void> apiStorageService;

    @Autowired
    @Qualifier("s3StorageService")
    private StorageService<Void> s3StorageService;

    public <T> StorageService<T> getService(ResultDestinationType storageType) {
        return switch (storageType) {
            case LOCAL_STORAGE -> (StorageService<T>) localFileStorageService;
            case EXTERNAL_STORAGE -> (StorageService<T>) s3StorageService;
            case API_RESPONSE -> (StorageService<T>) apiStorageService;
        };
    }
}
