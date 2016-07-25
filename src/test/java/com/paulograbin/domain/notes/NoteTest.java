package com.paulograbin.domain.notes;


import org.junit.Assert;
import org.junit.Test;

public class NoteTest {

    @Test
    public void testEmptyNote() {
        Note note = new Note();

        Assert.assertEquals(null, note.getText());
    }
}
