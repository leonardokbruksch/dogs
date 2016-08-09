package com.paulograbin.persistence;


import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class InMemoryNotesDAOTest {

    InMemoryNotesDAO repository;

    @Test
    public void withEmptyRepository__mustReturnEmpty() {
        repository = new InMemoryNotesDAO();

        Assert.assertEquals(0, repository.list().size());
    }

    @Test
    public void afterEntityIsAdded__dataRemainsIfNotSaved() {
        repository = new InMemoryNotesDAO();

        Note aNote = new Note(15, "olá");

        repository.save(aNote);


    }

    @Test
    public void givenANote_mustReturnIt() {
        repository = new InMemoryNotesDAO();

        repository.save(new Note());

        Assert.assertEquals(1, repository.list().size());
    }

    @Test
    public void givenEntityWithNoId__afterBeingSaveEntityMustHaveId() {
        repository = new InMemoryNotesDAO();
        Note aNote = new Note();

        repository.save(aNote);

        assertNotNull(aNote.getId());
    }

    @Test
    public void givenTwoEntitiesWithoutId__afterSaveTheirIdCannotBeTheSame() {
        repository = new InMemoryNotesDAO();

        Note firstNote = new Note();
        Note secondNote = new Note();

        repository.save(firstNote);
        repository.save(secondNote);

        assertNotEquals(firstNote.getId(), secondNote.getId());
    }

    @Test
    public void givenEntityWithId__afterSaveMustHaveIdPreserved() {
        repository = new InMemoryNotesDAO();
        Note aNote = new Note(5, "olá");

        repository.save(aNote);

        assertEquals(5, aNote.getId().intValue());
    }
}
