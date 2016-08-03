package com.paulograbin.domain.notes.Read;

import com.paulograbin.domain.notes.Create.CreateNoteRequest;
import com.paulograbin.domain.notes.Create.CreateNoteResponse;
import com.paulograbin.domain.notes.Create.CreateNoteUseCase;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;
import com.paulograbin.persistence.InMemoryNotesDAO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class ReadNoteUseCaseTest {

    private NotesDAO dao;
    private Collection<Note> response;

    @Before
    public void setUp() throws Exception {
        dao = new InMemoryNotesDAO();
        response = new ArrayList<>();
    }

    @Test
    public void WithNoNotes__mustReturnEmptyCollection() {
        whenReadingAllNotes();
        thenSizeMustBe(0);
    }

    private void whenReadingAllNotes() {
        new ReadNotesUseCase(dao, response).execute();
    }

    @Test
    public void WithOneNote__mustReturnOne() {
        givenANote("Olá Marilene");
        whenReadingAllNotes();
        thenSizeMustBe(1);
    }

    private void thenSizeMustBe(int expected) {
        assertEquals(expected, response.size());
    }

    private void givenANote(String text) {
        CreateNoteRequest request = new CreateNoteRequest();
        request.setText(text);

        CreateNoteResponse res = new CreateNoteResponse();
        new CreateNoteUseCase(dao, request, res).execute();
    }
}