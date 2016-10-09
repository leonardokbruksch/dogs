package com.paulograbin.domain.notes;


import com.paulograbin.domain.DateTimeFactory;

import java.time.*;

public class Note extends Entity {

    private String text;
    private boolean deleted;
    private final LocalDateTime creationDate;
    private LocalDateTime lastChangedDate;


    public Note() {
        this(null, "");
    }

    public Note(Integer id, String text) {
        super(id);

        this.id = id;
        this.text = text;
        this.creationDate = new DateTimeFactory().getCurrentUTCTime();
        this.lastChangedDate = null;
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
                ", lastChangedDate=" + lastChangedDate +
                "} ";
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(LocalDateTime lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (!id.equals(note.id)) return false;
        if (deleted != note.deleted) return false;
        if (text != null ? !text.equals(note.text) : note.text != null) return false;
        if (!creationDate.equals(note.creationDate)) return false;
        return lastChangedDate != null ? lastChangedDate.equals(note.lastChangedDate) : note.lastChangedDate == null;
    }
}
