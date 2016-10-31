package com.paulograbin.domain.notes.Delete;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteNoteUseCaseTest {

    private DeleteNoteRequest request;
    private DeleteNoteResponse response;
    private NotesRepository mockedRepository;

    @Before
    public void setUp() {
        response = new DeleteNoteResponse();
        mockedRepository = mock(NotesRepository.class);
    }

    @Test
    public void givenValidInput__noteMustBeDeleted() {
        Note n = new Note();
        mockedRepository.save(n);
        verify(mockedRepository).save(n);

        assertFalse(n.isDeleted());

        request = new DeleteNoteRequest();
        request.setIdToDelete(n.getId());

        new DeleteNoteUseCase(mockedRepository, request, response).execute();

        assertTrue(n.isDeleted());
        assertTrue(response.success);
    }

    @Test
    public void givenNonExistingId__noNoteIsDeleted() {
        request = new DeleteNoteRequest();
        request.setIdToDelete(3);

        new DeleteNoteUseCase(mockedRepository, request, response).execute();

        assertTrue(response.entityNotFound);
    }

    @Test
    public void givenInvalidInput__errorIsReturned() {
        request = new DeleteNoteRequest();
        request.setIdToDelete(null);

        new DeleteNoteUseCase(mockedRepository, request, response).execute();

        assertTrue(response.invalidId);
    }
}
