package com.paulograbin.domain.notes.Create;

import com.paulograbin.domain.notes.NotesRepository;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class CreateNoteUseCaseTest {

    private CreateNoteRequest request;
    private CreateNoteResponse response;
    private NotesRepository repository;

    @Before
    public void setUp() throws Exception {
        response = new CreateNoteResponse();
        repository = mock(NotesRepository.class);
    }

    @Test
    public void givenAllInputIsValid__NoteMustBeCreated() {
        request = new CreateNoteRequest();
        request.text = "Olá";

        new CreateNoteUseCase(repository, request, response).execute();
        assertTrue(response.success);
    }

    @Test
    public void givenTextIsInvalid__NoteIsNotCreated() {
        request = new CreateNoteRequest();

        new CreateNoteUseCase(repository, request, response).execute();
        assertTrue(response.noText);
    }
}
