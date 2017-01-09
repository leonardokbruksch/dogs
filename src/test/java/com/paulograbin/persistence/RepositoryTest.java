package com.paulograbin.persistence;


import com.paulograbin.domain.EntityNotFoundException;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public abstract class RepositoryTest {

    protected NotesRepository repository;


    protected abstract NotesRepository makeRepository();


    @Before
    public void setUp() {
        repository = makeRepository();
    }

    @Test
    public void withEmptyRepository__noteCountMustBeZero() {
        repository = makeRepository();

        assertNoteCountIs(0);
    }


    /*
     * GET ALL
     */
    @Test
    public void givenEmptyRepository__mustNotHaveNext() {
        assertFalse(repository.getAll().iterator().hasNext());
    }

    @Test
    public void name() {
        String noteText = "some random text";

        repository.save(new Note(3, noteText));

        assertEquals(repository.getAll().iterator().next().getTexts(), noteText);
    }

    /*
     * EXISTS
     */
    @Test
    public void givenValidInput__noteMustExist() {
        Note n = new Note(3, "somw random test");
        repository.save(n);

        assertTrue(repository.exists(n.getId()));
    }

    @Test
    public void givenInvalidInput__noteMustNotExist() {
        assertFalse(repository.exists(4));
    }

    /*
     * SAVE
     */
    @Test
    public void givenANoteIsSaved__noteCountMustBeOne() {
        repository.save(new Note());

        assertNoteCountIs(1);
    }

    @Test
    public void givenEntityWithNoId__afterBeingSaveEntityMustHaveId() {
        Note aNote = new Note();

        repository.save(aNote);

        assertNotNull(repository.exists(aNote.getId()));
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
        Note m = new Note(4, "Oi");
        repository.save(n);
        repository.save(m);

        Note returnedFromDatabase = repository.getById(3);

        assertEquals(n, returnedFromDatabase);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenNonExistingId__noEntityIsReturned() {
        repository.getById(3);
        fail();
    }


    @Test(expected = IllegalArgumentException.class)
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

        n = repository.getById(n.getId());
        assertTrue(n.isDeleted());
    }

    @Test
    public void givenDeleteAllNotes__sizeMustBeZero() {
        repository.save(new Note());
        repository.save(new Note());
        assertNoteCountIs(2);

        repository.deleteAll();
        assertNoteCountIs(0);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenNonExistingId__noNoteIsDeleted() {
        repository.save(new Note());

        repository.delete(Integer.MAX_VALUE);

        assertNoteCountIs(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullId__noNoteIsDeleted() {
        repository.save(new Note());
        repository.save(new Note());

        repository.delete(null);

        assertNoteCountIs(2);
    }

    /*
     * UPDATE
     */
    @Test
    public void givenValidInput__noteIsUpdated() {
        Note n = new Note(3, "olá");

        repository.save(n);
        assertNoteCountIs(1);

        Note another = new Note(3, "olá marilene");
        repository.update(another);

        n = repository.getById(n.getId());

        assertEquals("olá marilene", n.getLatestText().getText());
    }


    protected void assertNoteCountIs(int expectedSize) {
        assertEquals(expectedSize, repository.count());
    }
}
