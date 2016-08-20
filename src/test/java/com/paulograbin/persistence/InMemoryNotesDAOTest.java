package com.paulograbin.persistence;


import com.paulograbin.domain.notes.Note;
import org.junit.Test;

import static org.junit.Assert.*;

public class InMemoryNotesDAOTest {

    InMemoryNotesDAO repository;

    @Test
    public void withEmptyRepository__mustReturnZero() {
        repository = new InMemoryNotesDAO();

        assertEquals(0, repository.list().size());
    }

    /*
     * SAVE
     */
    @Test
    public void givenANoteIsSaved__sizeMustBeOne() {
        repository = new InMemoryNotesDAO();

        repository.save(new Note());

        assertEquals(1, repository.list().size());
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

    /*
     * GET BY ID
     */
    @Test
    public void givenValidId__entityMustBeReturned() {
        repository = new InMemoryNotesDAO();
        Note n = new Note(3, "Olá");

        repository.save(n);

        Note returnedFromDatabase = repository.getById(3);

        assertEquals(n, returnedFromDatabase);
    }

    @Test(expected = RuntimeException.class)
    public void givenNonExistingId__noEntityIsReturned() {
        repository = new InMemoryNotesDAO();

        repository.getById(3);
        fail();
    }

    @Test(expected = RuntimeException.class)
    public void givenNullId__noEntityIsReturned() {
        repository = new InMemoryNotesDAO();

        repository.getById(null);
        fail();
    }

    /*
     * DELETE
     */
    @Test
    public void givenValidId__noteShouldBeDeleted() {
        Note n = new Note(1, "olá");

        repository = new InMemoryNotesDAO();
        repository.save(n);

        assertFalse(n.isDeleted());

        repository.delete(1);

        assertTrue(n.isDeleted());
    }

    @Test(expected = RuntimeException.class)
    public void givenInvalidId__noNoteIsDeleted() {
        repository = new InMemoryNotesDAO();

        repository.save(new Note());
        repository.save(new Note());

        int noteCount = repository.list().size();

        repository.delete(Integer.MAX_VALUE);

        assertEquals(noteCount, repository.list().size());
    }
}
