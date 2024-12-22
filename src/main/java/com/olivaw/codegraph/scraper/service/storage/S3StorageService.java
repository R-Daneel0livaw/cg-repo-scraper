package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;

public class S3StorageService implements StorageService {

//    private final S3Client s3Client;
//    private final String bucketName;
//
//    public S3StorageService(S3Client s3Client, String bucketName) {
//        this.s3Client = s3Client;
//        this.bucketName = bucketName;
//    }

    @Override
    public void store(String targetPath, byte[] data) throws StorageException {

    }

    @Override
    public void delete(String targetPath) throws StorageException {

    }
}
