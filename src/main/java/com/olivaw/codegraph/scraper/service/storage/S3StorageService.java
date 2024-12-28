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
//        try {
//            s3Client.putObject(
//                    PutObjectRequest.builder()
//                            .bucket(bucketName)
//                            .key(targetPath)
//                            .build(),
//                    new ByteArrayInputStream(data),
//                    data.length
//            );
//        } catch (Exception e) {
//            throw new StorageException("Failed to store data to S3 at " + targetPath, e);
//        }
    }

    @Override
    public byte[] retrieve(String targetPath) throws StorageException {
//        try {
//            var response = s3Client.getObject(
//                    GetObjectRequest.builder()
//                            .bucket(bucketName)
//                            .key(targetPath)
//                            .build()
//            );
//            return response.readAllBytes();
//        } catch (Exception e) {
//            throw new StorageException("Failed to retrieve data from S3 at " + targetPath, e);
//        }
        return null;
    }

    @Override
    public void delete(String targetPath) throws StorageException {
//        try {
//            s3Client.deleteObject(
//                    DeleteObjectRequest.builder()
//                            .bucket(bucketName)
//                            .key(targetPath)
//                            .build()
//            );
//        } catch (Exception e) {
//            throw new StorageException("Failed to delete data from S3 at " + targetPath, e);
//        }
    }
}
