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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/notes")
public class NotesController {

    @Resource
    NotesRepository repository;
    private Gson converter = new Gson();


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String save(CreateNoteRequest request) {
        CreateNoteResponse response = new CreateNoteResponse();
        new CreateNoteUseCase(repository, request, response).execute();

        String responseJSON = converter.toJson(response);
        System.out.println(responseJSON);

        return responseJSON;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String list() {
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
    public @ResponseBody String delete(@PathVariable("id") int id) {
        DeleteNoteRequest request = new DeleteNoteRequest();
        request.setIdToDelete(id);

        DeleteNoteResponse response = new DeleteNoteResponse();
        new DeleteNoteUseCase(repository, request, response).execute();

        String responseJSON = converter.toJson(response);
        System.out.println(responseJSON);

        return responseJSON;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public @ResponseBody String update(UpdateNoteRequest request) {
        UpdateNoteResponse response = new UpdateNoteResponse();
        new UpdateNoteUseCase(repository, request, response).execute();

        String responseJSON = converter.toJson(response);
        System.out.println(responseJSON);

        return responseJSON;
    }
}