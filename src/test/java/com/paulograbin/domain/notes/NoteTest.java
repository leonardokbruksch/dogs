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

    @Test
    public void testNoteIsEqualToItself() {
        Note n = new Note();

        assertTrue(n.equals(n));
    }

    @Test
    public void testNotesWithSameContentAreEqual() {
        LocalDateTime now = LocalDateTime.now();

        Note n = new Note(13, "Some random test");
        n.setLastChangedDate(now);

        Note m = new Note(13, "Some random test");
        m.setLastChangedDate(now);

        assertTrue(n.equals(m));
    }

    @Override
    protected Note makeOne() {
        return new Note();
    }
}
