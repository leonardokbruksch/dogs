package com.paulograbin.domain.notes;


import java.time.LocalDateTime;

public class Note extends Entity {

    private String text;
    private boolean deleted;
    private LocalDateTime creationDate;


    public Note() {
        this(null, "");
    }

    public Note(Integer id, String text) {
        super(id);

        this.id = id;
        this.text = text;
        this.creationDate = LocalDateTime.now();
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
                "id=" + id +
                ", text='" + text +
                ", deleted=" + deleted +
                ", creationDate=" + creationDate +
                "} ";
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

}
