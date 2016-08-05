package com.paulograbin.domain.notes;

import com.paulograbin.domain.EntityTest;
import com.paulograbin.web.controllers.NotesController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Calendar;

import static org.junit.Assert.*;

public class NoteTest extends EntityTest<Note> {

    @Test
    public void NoteIsCreatedWithCreationDate() {
        Note note = new Note();
        assertNotNull(note.getCreationDate());
    }

    @Test
    public void NoteIsCreatedWithoutTextAndNotDeleted() {
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
