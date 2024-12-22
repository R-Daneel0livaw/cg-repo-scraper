package com.olivaw.codegraph.scraper.service.storage;

public class StorageServiceFactory {

    public static StorageService getService(String storageType) {
        return switch (storageType.toLowerCase()) {
            case "local" -> new LocalFileSystemStorageService();
            case "s3" -> new S3StorageService();
//                    new S3StorageService(AwsClientProvider.getS3Client(), "bucket-name");
            default -> throw new IllegalArgumentException("Unsupported storage type: " + storageType);
        };
    }
}
