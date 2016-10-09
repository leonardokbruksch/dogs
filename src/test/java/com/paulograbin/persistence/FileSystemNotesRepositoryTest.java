package com.paulograbin.persistence;

import com.paulograbin.domain.notes.NotesRepository;


public class FileSystemNotesRepositoryTest extends RepositoryTest {

    @Override
    protected NotesRepository makeRepository() {
        FileSystemNotesRepository repository = new FileSystemNotesRepository();
        repository.deleteAll();

        return repository;
    }
}