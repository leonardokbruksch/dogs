package com.paulograbin.domain.notes;


public class Note {
    private String text;
    private boolean deleted;


    public Note() {
        this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "Note{" +
                "text='" + text + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
