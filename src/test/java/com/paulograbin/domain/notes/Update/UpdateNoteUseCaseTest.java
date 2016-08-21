package com.paulograbin.domain.notes.Update;


import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;
import com.paulograbin.persistence.InMemoryNotesDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdateNoteUseCaseTest {

    private UpdateNoteRequest request;
    private UpdateNoteResponse response;
    private NotesDAO dao;

    @Before
    public void setUp() {
        request = new UpdateNoteRequest();
        response = new UpdateNoteResponse();
        dao = new InMemoryNotesDAO();
    }

    @Test
    public void givenValidInput__noteMustBeUpdated() {
        Note n = new Note(3, "olá");
        dao.save(n);

        request.id = 3;
        request.text = "olá marilene";

        new UpdateNoteUseCase(dao, request, response).execute();

        assertEquals("olá marilene", n.getText());
        assertTrue(response.success);
    }

    @Test
    public void givenValidInputButInvalidEntity__mustReturnError() {
        request.id = 3;
        request.text = "olá";

        new UpdateNoteUseCase(dao, request, response).execute();

        assertTrue(response.entityNotFound);
    }

    @Test
    public void givenInvalidId__mustReturnError() {
        Note n = new Note(3, "olá");
        dao.save(n);

        request.id = null;
        request.text = "olá marilene";

        new UpdateNoteUseCase(dao, request, response).execute();

        assertEquals("olá", n.getText());
        assertTrue(response.invalidId);
    }

    @Test
    public void givenInvalidText__mustReturnError() {
        Note n = new Note(3, "olá");
        dao.save(n);

        request.id = 3;
        request.text = "";

        new UpdateNoteUseCase(dao, request, response).execute();

        assertEquals("olá", n.getText());
        assertTrue(response.invalidText);
    }
}