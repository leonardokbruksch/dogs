package com.paulograbin.web.controllers;

import com.google.gson.Gson;
import com.paulograbin.domain.notes.Create.CreateNoteRequest;
import com.paulograbin.domain.notes.Create.CreateNoteResponse;
import com.paulograbin.domain.notes.Create.CreateNoteUseCase;
import com.paulograbin.domain.notes.Delete.DeleteNoteRequest;
import com.paulograbin.domain.notes.Delete.DeleteNoteResponse;
import com.paulograbin.domain.notes.Delete.DeleteNoteUseCase;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepositoriy;
import com.paulograbin.domain.notes.Read.ReadNotesUseCase;
import com.paulograbin.domain.notes.Update.UpdateNoteRequest;
import com.paulograbin.domain.notes.Update.UpdateNoteResponse;
import com.paulograbin.domain.notes.Update.UpdateNoteUseCase;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/notes")
public class NotesController {

    @Resource
    NotesRepositoriy repository;
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
        Collection<Note> notes = new ArrayList<>();
        new ReadNotesUseCase(repository, notes).execute();

        String responseJSON = converter.toJson(notes);
        System.out.println(responseJSON);

        return responseJSON;
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