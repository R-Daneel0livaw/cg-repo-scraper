package com.olivaw.codegraph.scraper.service.storage;

import com.olivaw.codegraph.scraper.exception.StorageException;

public interface StorageService {

    void store(String targetPath, byte[] data) throws StorageException;

    byte[] retrieve(String targetPath) throws StorageException;

    void delete(String targetPath) throws StorageException;
}
