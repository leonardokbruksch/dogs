package com.paulograbin.domain.texts;

import com.paulograbin.domain.Entity;
import com.paulograbin.domain.notes.Note;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "Texts")
public class Text extends Entity {

    @ManyToOne
    private Note note;

    private String text;


    public Text() {
        this(null, "");
    }

    public Text(Integer id, String text) {
        super(id);

        this.text = text;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
