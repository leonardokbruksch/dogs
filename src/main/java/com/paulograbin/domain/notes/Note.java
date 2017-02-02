package com.paulograbin.domain.notes;

import com.paulograbin.domain.Entity;
import com.paulograbin.domain.texts.Text;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@javax.persistence.Entity
@Table(name = "Notes")
public class Note extends Entity {

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Text> texts;
    private boolean deleted;


    public Note() {
        this(null);
    }

    public Note(Integer id) {
        super(id);

        this.deleted = false;
        this.texts = new ArrayList<>();
    }

    public Note(Integer id, String text) {
        super(id);

        this.deleted = false;
        this.texts = new ArrayList();

        Text t = Text.EMPTY;
        t.setText(text);

        texts.add(t);
    }


    public Text getLatestText() {
        if (texts.size() == 0)
            return null;
        else {
            Comparator<Text> cmp = Comparator.comparing(Text::getCreationDate);

            return texts.stream().max(cmp).get();
        }
    }

    public void addText(Text text) {
        texts.add(text);
    }

    public List<Text> getTexts() {
        return texts;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        super.setId(id);
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
