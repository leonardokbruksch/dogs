package com.paulograbin.persistence;

import com.paulograbin.domain.notes.NotesRepository;


public class InMemoryNotesRepositoryTest extends RepositoryTest {

    @Override
    protected NotesRepository makeRepository() {
        return new InMemoryNotesRepository();
    }
}
