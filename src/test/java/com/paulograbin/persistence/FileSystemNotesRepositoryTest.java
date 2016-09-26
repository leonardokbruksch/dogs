package com.paulograbin.persistence;

import com.paulograbin.domain.Repository;
import com.paulograbin.domain.notes.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FileSystemNotesRepositoryTest extends RepositoryTest<Note> {

    @Override
    protected Repository makeRepository() {
        FileSystemNotesRepository repository = new FileSystemNotesRepository("/Users/i841059/Desktop");
        repository.deleteAll();

        return repository;
    }
}