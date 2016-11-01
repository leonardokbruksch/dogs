package com.paulograbin.domain.notes.Update;


import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import com.paulograbin.persistence.InMemoryNotesRepository;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class UpdateNoteUseCaseTest {

    private UpdateNoteRequest request;
    private UpdateNoteResponse response;
    private NotesRepository repository;

    @Before
    public void setUp() {
        request = new UpdateNoteRequest();
        response = new UpdateNoteResponse();
        repository = new InMemoryNotesRepository();
    }

    @Test
    public void givenValidInput__noteMustBeUpdated() {
        Note n = new Note(3, "olá");
        repository.save(n);

        request.setId(3);
        request.setText("olá marilene");

        new UpdateNoteUseCase(repository, request, response).execute();

        assertEquals("olá marilene", n.getText());
        assertTrue(response.success);
    }

    @Test
    public void givenValidInput__afterUpdateNoteMustHaveChangedDate() {
        Note n = new Note(77, "Test text");
        assertNull(n.getLastChangedDate());
        repository.save(n);

        request.setId(77);
        request.setText("Some new random text");
        new UpdateNoteUseCase(repository, request, response).execute();

        n = repository.getById(n.getId());
        assertNotNull(n.getLastChangedDate());
    }

    @Test
    public void givenValidInputButInvalidEntity__mustReturnError() {
        request.setId(3);
        request.setText("olá");

        new UpdateNoteUseCase(repository, request, response).execute();

        assertTrue(response.entityNotFound);
    }

    @Test
    public void givenInvalidId__mustReturnError() {
        Note n = new Note(3, "olá");
        repository.save(n);

        request.setId(null);
        request.setText("olá marilene");

        new UpdateNoteUseCase(repository, request, response).execute();

        assertEquals("olá", n.getText());
        assertTrue(response.invalidId);
    }

    @Test
    public void givenInvalidText__mustReturnError() {
        Note n = new Note(3, "olá");
        repository.save(n);

        request.setId(3);
        request.setText("");

        new UpdateNoteUseCase(repository, request, response).execute();

        assertEquals("olá", n.getText());
        assertTrue(response.invalidText);
    }
}