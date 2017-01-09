package com.paulograbin.persistence;

import com.paulograbin.domain.DateTimeFactory;
import com.paulograbin.domain.EntityNotFoundException;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import com.paulograbin.domain.texts.Text;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


//@Repository
public class InMemoryNotesRepository implements NotesRepository {

    private final Map<Integer, Note> notes;
    private int lastNoteId;
    private int lastTextId;

    public InMemoryNotesRepository() {
        lastNoteId = 0;
        lastTextId = 0;

        this.notes = new HashMap<>();
    }

    @Override
    public long count() {
        return notes.size();
    }

    @Override
    public boolean exists(Integer id) {
        return notes.containsKey(id);
    }

    @Override
    public void save(Note entity) {
        if (entity.getId() == null) {
            entity.setId(fetchNextNoteId());
        }

        for(Text t : entity.getTexts()) {
            if(t.getId() == null)
                t.setId(fetchNextTextId());
        }

        System.out.println(entity.getTexts().size());

        notes.put(entity.getId(), entity);
        System.out.println("Inserting note " + entity);
    }

    @Override
    public Iterable<Note> getAll() {
        return notes.values();
    }

    private int fetchNextNoteId() {
        return lastNoteId++;
    }

    private int fetchNextTextId() {
        return lastTextId++;
    }

    @Override
    public void delete(Integer idToDelete) {
        Note n = getById(idToDelete);

        n.setDeleted();
    }

    @Override
    public Note getById(Integer id) {
        if(id == null)
            throw new IllegalArgumentException();

        Note n = notes.get(id);

        if (n != null)
            return n;
        else
            throw new EntityNotFoundException();
    }

    @Override
    public void update(Note note) {

        note.setLastChangedDate(new DateTimeFactory().getCurrentUTCTime());

        notes.put(note.getId(), note);
    }

    @Override
    public void deleteAll() {
        notes.clear();
    }

}
