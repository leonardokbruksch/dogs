package com.paulograbin.web.controllers;

import com.google.gson.Gson;
import com.paulograbin.domain.notes.Create.CreateNoteRequest;
import com.paulograbin.domain.notes.Create.CreateNoteResponse;
import com.paulograbin.domain.notes.Create.CreateNoteUseCase;
import com.paulograbin.domain.notes.Delete.DeleteNoteRequest;
import com.paulograbin.domain.notes.Delete.DeleteNoteResponse;
import com.paulograbin.domain.notes.Delete.DeleteNoteUseCase;
import com.paulograbin.domain.notes.NotesRepository;
import com.paulograbin.domain.notes.Read.ReadNotesResponse;
import com.paulograbin.domain.notes.Read.ReadNotesUseCase;
import com.paulograbin.domain.notes.ResponseWrapper;
import com.paulograbin.domain.notes.Update.UpdateNoteRequest;
import com.paulograbin.domain.notes.Update.UpdateNoteResponse;
import com.paulograbin.domain.notes.Update.UpdateNoteUseCase;
import com.paulograbin.domain.texts.TextsRepository;
import com.paulograbin.web.crypto.EtagGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping(value = "/notes", produces = "application/json; charset=utf-8")
public class NotesController {

    @Inject
    private NotesRepository repository;

    private Gson converter = new Gson();


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(CreateNoteRequest request, HttpServletResponse res) {
        CreateNoteResponse response = new CreateNoteResponse();
        new CreateNoteUseCase(repository, request, response).execute();

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/notes/")
                .body(converter.toJson(response));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        ReadNotesResponse response = new ReadNotesResponse();
        new ReadNotesUseCase(repository, response).execute();

        return FillResponseWithGETWrapper(response);
    }

    private String FillResponseWithGETWrapper(ResponseWrapper responseWrapper) {
        ResponseData responseData = new ResponseData(responseWrapper);
        ResponseMetadata metadata = new ResponseMetadata(new EtagGenerator(), responseData);

        return converter.toJson(metadata);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        DeleteNoteRequest request = new DeleteNoteRequest();
        request.setIdToDelete(id);

        DeleteNoteResponse response = new DeleteNoteResponse();
        new DeleteNoteUseCase(repository, request, response).execute();

        String responseJSON = converter.toJson(response);
        System.out.println(responseJSON);

        return responseJSON;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public String update(UpdateNoteRequest request) {
        UpdateNoteResponse response = new UpdateNoteResponse();
        new UpdateNoteUseCase(repository, request, response).execute();

        String responseJSON = converter.toJson(response);
        System.out.println(responseJSON);

        return responseJSON;
    }

    @Transactional
    @RequestMapping(value="/deleteAll", method = RequestMethod.GET)
    public void deleteAll() {
        repository.deleteAll();
    }
}