package com.paulograbin.domain.notes;

import com.paulograbin.web.controllers.NotesController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Calendar;

import static org.junit.Assert.*;

public class NoteTest {

    @Test
    public void testEmptyNote() {
        Note note = new Note();

        assertEquals("", note.getText());
        assertEquals(false, note.isDeleted());
    }

    @Test
    public void testAddText() {
        Note note = new Note();

        note.setText("abc");

        assertEquals("abc", note.getText());
    }

    @Test
    public void testDeleted() {
        Note note = new Note();

        note.setDeleted();

        assertTrue(note.isDeleted());
    }
}
