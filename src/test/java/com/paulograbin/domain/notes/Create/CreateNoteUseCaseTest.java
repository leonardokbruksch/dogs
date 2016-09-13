package com.paulograbin.domain.notes.Create;

import com.paulograbin.domain.notes.NotesRepository;
import com.paulograbin.persistence.InMemoryNotesRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CreateNoteUseCaseTest {

    private CreateNoteRequest request;
    private CreateNoteResponse response;
    private NotesRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryNotesRepository();
        response = new CreateNoteResponse();
    }

    @Test
    public void givenAllInputIsValid__NoteMustBeCreated() {
        request = new CreateNoteRequest();
        request.text = "Olá";

        new CreateNoteUseCase(repository, request, response).execute();
        Assert.assertTrue(response.success);
    }

    @Test
    public void givenTextIsInvalid__NoteIsNotCreated() {
        request = new CreateNoteRequest();

        new CreateNoteUseCase(repository, request, response).execute();
        Assert.assertTrue(response.noText);
    }
}
