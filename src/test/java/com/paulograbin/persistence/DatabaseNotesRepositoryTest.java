package com.paulograbin.persistence;

import com.paulograbin.domain.notes.NotesRepository;


public class DatabaseNotesRepositoryTest extends RepositoryTest {

    @Override
    protected NotesRepository makeRepository() {
        return new DatabaseNotesRepository();
    }
}
