package com.paulograbin.domain.notes.Delete;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import com.paulograbin.persistence.InMemoryNotesRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteNoteUseCaseTest {

    private DeleteNoteRequest request;
    private DeleteNoteResponse response;
    private NotesRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryNotesRepository();
        response = new DeleteNoteResponse();
    }

    @Test
    public void givenValidInput__noteMustBeDeleted() {
        Note n = new Note();
        repository.save(n);

        assertFalse(n.isDeleted());

        request = new DeleteNoteRequest();
        request.setIdToDelete(n.getId());

        new DeleteNoteUseCase(repository, request, response).execute();

        assertTrue(n.isDeleted());
        assertTrue(response.success);
    }

    @Test
    public void givenNonExistingId__noNoteIsDeleted() {
        request = new DeleteNoteRequest();
        request.setIdToDelete(3);

        new DeleteNoteUseCase(repository, request, response).execute();

        assertTrue(response.entityNotFound);
    }

    @Test
    public void givenInvalidInput__errorIsReturned() {
        request = new DeleteNoteRequest();
        request.setIdToDelete(null);

        new DeleteNoteUseCase(repository, request, response).execute();

        assertTrue(response.invalidId);
    }
}
