package com.paulograbin.domain.notes.Delete;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesDAO;
import com.paulograbin.persistence.InMemoryNotesDAO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteNoteUseCaseTest {

    private DeleteNoteRequest request;
    private DeleteNoteResponse response;
    private NotesDAO notesDAO;

    @Before
    public void setUp() {
        notesDAO = new InMemoryNotesDAO();
        response = new DeleteNoteResponse();
    }

    @Test
    public void givenValidInput__noteMustBeDeleted() {
        Note n = new Note();
        notesDAO.save(n);
        assertFalse(n.isDeleted());

        request = new DeleteNoteRequest();
        request.setIdToDelete(n.getId());

        new DeleteNoteUseCase(notesDAO, request, response).execute();

        assertTrue(n.isDeleted());
        assertTrue(response.success);
    }

    @Test
    public void givenNonExistingId__noNoteIsDeleted() {
        request = new DeleteNoteRequest();
        request.setIdToDelete(3);

        new DeleteNoteUseCase(notesDAO, request, response).execute();

        assertTrue(response.entityNotFound);
    }

    @Test
    public void givenInvalidInput__errorIsReturned() {
        request = new DeleteNoteRequest();
        request.setIdToDelete(null);

        new DeleteNoteUseCase(notesDAO, request, response).execute();

        assertTrue(response.invalidId);
    }
}
