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
import com.paulograbin.web.crypto.EtagGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;

@CrossOrigin
@RestController
@RequestMapping(value = "/notes", produces = "application/json; charset=utf-8")
public class NotesController {

    @Inject
    NotesRepository repository;
    private Gson converter = new Gson();


    @RequestMapping(method = RequestMethod.POST)
    public String save(CreateNoteRequest request, HttpServletResponse res) {
        CreateNoteResponse response = new CreateNoteResponse();
        new CreateNoteUseCase(repository, request, response).execute();

        String responseJSON = converter.toJson(response);

        res.setHeader("location", "/notes/" + response.entity.getId());
        res.setStatus(HttpStatus.CREATED.value());

        return responseJSON;
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
}