package com.paulograbin.domain.texts;

import com.paulograbin.domain.Entity;
import com.paulograbin.domain.notes.Note;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Table(name = "Texts")
public class Text extends Entity {

    public final static Text EMPTY = new Text("");

    @ManyToOne
    private Note note;
    private String text;


    public Text(String text) {
        super(null);

        this.text = text == null ? "" : text.trim();
    }

    public Text(Integer id, String text) {
        super(id);

        this.text = text.trim();
    }


    public LocalDateTime getCreationDate() {
        return super.getCreationDate();
    }

    public Integer getId() {
        return super.getId();
    }

    public void setId(Integer id) {
        super.setId(id);
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

    public String toString() {
        return "Text{" +
                "id='" + super.getId() + '\'' +
                ", text='" + text + '\'' +
                "} ";
    }
}
