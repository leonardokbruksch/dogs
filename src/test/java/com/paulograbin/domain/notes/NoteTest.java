package com.paulograbin.domain.notes;

import com.paulograbin.domain.EntityTest;
import com.paulograbin.domain.texts.Text;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.*;


public class NoteTest extends EntityTest<Note> {

    @Test
    public void NoteIsCreatedWithCreationDate() {
        Note note = new Note();
        assertNotNull(note.getCreationDate());
    }

    @Test
    public void creationDateMustBeCloseToCurrentTime() throws InterruptedException {
        Note note = new Note();
        LocalDateTime noteCreationDate = note.getCreationDate();

        LocalDateTime currentTime = ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();

        assertTimeDifferenceIsNegligible(noteCreationDate, currentTime);
    }

    private void assertTimeDifferenceIsNegligible(LocalDateTime noteCreationDate, LocalDateTime currentTime) {
        Duration d = Duration.between(noteCreationDate, currentTime);

        assertEquals(0, d.toDays());
        assertEquals(0, d.toHours());
        assertEquals(0, d.toMinutes());
        assertEquals(0, d.getSeconds());
    }

    @Test
    public void noteIsCreatedWithLastChangedDateNull() {
        Note note = new Note();

        assertNull(note.getLastChangedDate());
    }

    @Test
    public void noteIsCreatedNotDeleted() {
        Note note = new Note();

        assertFalse(note.isDeleted());
    }

    @Test
    public void givenNoText__thenNoteIsCreatedWithoutText() {
        Note note = new Note(5);

        assertEquals(note.getTexts(), Lists.emptyList());
    }

    @Test
    public void givenAText__thenNoteIsCreatedWithText() {
        Note note = new Note(6, "test text");

        assertEquals(note.getLatestText().getText(), "test text");
    }

    @Test
    public void afterTextIsAdded_NoteMustHaveText() {
        Note note = new Note();
        note.setText(new Text(null, "abc"));

        assertTrue(note.getTexts().size() > 0);
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

    @Test
    public void givenNoteWithoutText__whenGettingLattestText__thenMustReturnNull() {
        Note n = new Note(5);

        assertEquals(null, n.getLatestText());
    }

    @Override
    protected Note makeOne() {
        return new Note();
    }
}
