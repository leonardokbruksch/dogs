package com.paulograbin.domain.notes.Create;

import com.paulograbin.domain.notes.NotesDAO;
import com.paulograbin.persistence.InMemoryNotesDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CreateNoteUseCaseTest {

    private CreateNoteRequest request;
    private CreateNoteResponse response;
    private NotesDAO notesDAO;

    @Before
    public void setUp() throws Exception {
        notesDAO = new InMemoryNotesDAO();
        response = new CreateNoteResponse();
    }

    @Test
    public void givenAllInputIsValid__NoteMustBeCreated() {
        request = new CreateNoteRequest();
        request.text = "Olá";

        new CreateNoteUseCase(notesDAO, request, response).execute();
        Assert.assertTrue(response.success);
    }

    @Test
    public void givenTextIsInvalid__NoteIsNotCreated() {
        request = new CreateNoteRequest();

        new CreateNoteUseCase(notesDAO, request, response).execute();
        Assert.assertTrue(response.noText);
    }
}
