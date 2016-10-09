package com.paulograbin.persistence;

import java.io.File;


public class FileSystemRepositoryCreator {

    File repository;

    public FileSystemRepositoryCreator(File repository) {
        this.repository = repository;
    }

    public void create() {
        try {
            repository.mkdir();
            repository.setWritable(true);
        } catch(SecurityException e) {
            throw new RuntimeException("Couldn't create the repository directory");
        }
    }

    public boolean isRepositoryReady() {
        return repository.exists() && repository.canWrite();
    }
}
