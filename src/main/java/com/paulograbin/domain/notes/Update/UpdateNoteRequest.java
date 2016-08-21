package com.paulograbin.domain.notes.Update;


public class UpdateNoteRequest {

    public Integer id;
    public String text;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}
