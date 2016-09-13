package com.paulograbin.persistence;


import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InMemoryNotesRepositoryTest {

    InMemoryNotesRepository repository;


    @Before
    public void setUp() {
        repository = new InMemoryNotesRepository();
    }

    @Test
    public void withEmptyRepository__mustReturnZero() {
        repository = new InMemoryNotesRepository();

        assertEquals(0, repository.list().size());
    }

    /*
     * SAVE
     */
    @Test
    public void givenANoteIsSaved__sizeMustBeOne() {
        repository.save(new Note());

        assertEquals(1, repository.list().size());
    }

    @Test
    public void givenEntityWithNoId__afterBeingSaveEntityMustHaveId() {
        Note aNote = new Note();

        repository.save(aNote);

        assertNotNull(aNote.getId());
    }

    @Test
    public void givenTwoEntitiesWithoutId__afterSaveTheirIdCannotBeTheSame() {
        Note firstNote = new Note();
        Note secondNote = new Note();

        repository.save(firstNote);
        repository.save(secondNote);

        assertNotEquals(firstNote.getId(), secondNote.getId());
    }

    @Test
    public void givenEntityWithId__afterSaveMustHaveIdPreserved() {
        Note aNote = new Note(5, "olá");

        repository.save(aNote);

        assertEquals(5, aNote.getId().intValue());
    }

    /*
     * GET BY ID
     */
    @Test
    public void givenValidId__entityMustBeReturned() {
        Note n = new Note(3, "Olá");

        repository.save(n);

        Note returnedFromDatabase = repository.getById(3);

        assertEquals(n, returnedFromDatabase);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenNonExistingId__noEntityIsReturned() {
        repository.getById(3);
        fail();
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenNullId__noEntityIsReturned() {
        repository.getById(null);
        fail();
    }

    /*
     * DELETE
     */
    @Test
    public void givenValidId__noteShouldBeDeleted() {
        Note n = new Note(1, "olá");

        repository.save(n);

        assertFalse(n.isDeleted());

        repository.delete(1);

        assertTrue(n.isDeleted());
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidId__noNoteIsDeleted() {
        repository.save(new Note());
        repository.save(new Note());

        int noteCount = repository.list().size();

        repository.delete(Integer.MAX_VALUE);

        assertEquals(noteCount, repository.list().size());
    }
    
    /*
     * UPDATE
     */
    @Test
    public void givenValidInput__noteIsUpdated() {
        Note n = new Note(3, "olá");

        repository.save(n);
        assertEquals(1, repository.list().size());

        Note another = new Note(3, "olá marilene");
        repository.update(another);

        assertEquals("olá marilene", n.getText());
    }
}
