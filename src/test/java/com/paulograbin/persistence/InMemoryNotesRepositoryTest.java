package com.paulograbin.persistence;


import com.paulograbin.domain.EntityNotFoundException;
import com.paulograbin.domain.Repository;
import com.paulograbin.domain.notes.Note;
import org.junit.Test;

import static org.junit.Assert.*;

public class InMemoryNotesRepositoryTest extends RepositoryTest<Note> {

    @Override
    protected Repository makeRepository() {
        return new InMemoryNotesRepository();
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

        assertEquals("olá marilene", n.getText());
    }
}
