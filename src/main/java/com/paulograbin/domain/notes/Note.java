package com.paulograbin.domain.notes;

import com.paulograbin.domain.Entity;
import com.paulograbin.domain.texts.Text;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@javax.persistence.Entity
@Table(name = "Notes")
public class Note extends Entity {


    private boolean deleted;

    @OneToMany
    private List<Text> texts;

    public Note() {
        this(null, "");
    }

    public Note(Integer id, String text) {
        super(id);

        this.deleted = false;
        this.texts = new ArrayList();
    }

    public void setText(Text text) {
        texts.add(text);
    }

    public Text getText() {
        if(texts.size() == 0)
            return null;
        else
            return texts.get(0);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = true;
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
        if (!creationDate.equals(note.creationDate)) return false;
        return lastChangedDate != null ? lastChangedDate.equals(note.lastChangedDate) : note.lastChangedDate == null;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", creationDate=" + creationDate +
                ", lastChangedDate=" + lastChangedDate +
                "} ";
    }
}
