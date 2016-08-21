package com.paulograbin.domain.notes;

import com.paulograbin.domain.EntityTest;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class NoteTest extends EntityTest<Note> {

    @Test
    public void NoteIsCreatedWithCreationDate() {
        Note note = new Note();
        assertNotNull(note.getCreationDate());
    }

    @Test
    public void noteIsCreatedWithLastChangedDateNull() {
        Note note = new Note();

        assertNull(note.getLastChangedDate());
    }

    @Test
    public void afterTextIsUpdatedLastChangedDateIsSet() {
        Note note = new Note();

        LocalDateTime creationDate = note.getCreationDate();

        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        note.setText("olá");
        LocalDateTime changedDate = note.getLastChangedDate();

        assertNotEquals(creationDate, changedDate);
    }

    @Test
    public void noteIsCreatedWithoutTextAndNotDeleted() {
        Note note = new Note();

        assertEquals("", note.getText());
        assertEquals(false, note.isDeleted());
    }

    @Test
    public void afterTextIsAdded_NoteMustHaveText() {
        Note note = new Note();
        note.setText("abc");

        assertEquals("abc", note.getText());
    }

    @Test
    public void afterNoteIsDeleted_NoteMustBeDeleted() {
        Note note = new Note();
        note.setDeleted();

        assertTrue(note.isDeleted());
    }

    @Override
    protected Note makeOne() {
        return new Note();
    }
}
