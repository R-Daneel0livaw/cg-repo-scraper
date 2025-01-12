package com.olivaw.codegraph.scraper.service.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.util.List;

public class StorageServiceFactory {
    @Autowired
    @Qualifier("fileStorageService")
    private StorageService<List<File>> fileStorageService;

    @Autowired
    @Qualifier("stringStorageService")
    private StorageService<List<String>> stringStorageService;

//    public static StorageService getService(ResultDestinationType storageType) {
//        return switch (storageType) {
//            case LOCAL_STORAGE -> new LocalFileSystemStorageService();
//            case EXTERNAL_STORAGE -> new S3StorageService();
////                    new S3StorageService(AwsClientProvider.getS3Client(), "bucket-name");
//            case API_RESPONSE -> new APIResponseStorageService();
//        };
//    }


    public <T> StorageService<T> getService(String dataType) {
        if ("file".equals(dataType)) {
            return (StorageService<T>) fileStorageService; // Returning the File implementation
        } else if ("string".equals(dataType)) {
            return (StorageService<T>) stringStorageService; // Returning the String implementation
        }
        throw new IllegalArgumentException("Unsupported data type: " + dataType);
    }
}
