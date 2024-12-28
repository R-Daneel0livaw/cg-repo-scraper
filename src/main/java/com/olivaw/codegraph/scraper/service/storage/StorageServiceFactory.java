package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.model.request.ResultDestinationType;

public class StorageServiceFactory {

    public static StorageService getService(ResultDestinationType storageType) {
        return switch (storageType) {
            case LOCAL_STORAGE -> new LocalFileSystemStorageService();
            case EXTERNAL_STORAGE -> new S3StorageService();
//                    new S3StorageService(AwsClientProvider.getS3Client(), "bucket-name");
            case API_RESPONSE -> new APIResponseStorageService();
        };
    }
}
