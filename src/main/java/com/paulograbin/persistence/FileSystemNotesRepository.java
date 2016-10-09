package com.paulograbin.persistence;

import com.google.gson.Gson;
import com.paulograbin.domain.EntityNotFoundException;
import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class FileSystemNotesRepository implements NotesRepository {

    private final String DIRECTORY_NAME = "/OEditor_uploads/";
    private final String FILE_EXTENSION = ".txt";

    private final File repository;
    private boolean ready;
    private int lastId;

    private Gson converter;


    public FileSystemNotesRepository() {
        converter = new Gson();

        String basePath = System.getProperty("user.home");
        repository = new File(basePath + DIRECTORY_NAME);

        FileSystemRepositoryCreator repoCreator = new FileSystemRepositoryCreator(repository);

        if(repoCreator.isRepositoryReady()) {
            ready = true;
        } else {
            repoCreator.create();
        }
    }

    @Override
    public void save(Note entity) {
        if(entity.getId() == null)
            entity.setId(getNextId());

        writeContentToFile(entity);
    }

    private void writeContentToFile(Note entity) {
        String filePath = makeNewNoteFileName(entity.getId());
        File newNoteFile = new File(filePath);

        try {
            if(!newNoteFile.exists())
                newNoteFile.createNewFile();

            FileWriter fw = new FileWriter(newNoteFile);
            fw.write(converter.toJson(entity));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String makeNewNoteFileName(Integer noteId) {
        Path path = Paths.get(repository.getPath(), String.valueOf(noteId));
        return path.toString() + FILE_EXTENSION;
    }

    private Integer getNextId() {
        return lastId++;
    }

    @Override
    public Collection<Note> list() {
        List<Note> notes = new ArrayList<>();

        for (File file : getAllNoteFilesFromRepositoryDirectory()) {
            try {
                notes.add(getNoteFromFile(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return notes;
    }

    private Note getNoteFromFile(File file) throws IOException {
        String noteContent = readFileContent(file);

        return converter.fromJson(noteContent, Note.class);
    }

    private File[] getAllNoteFilesFromRepositoryDirectory() {
        return repository.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(FILE_EXTENSION);

            }
        });
    }

    @Override
    public Note getById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException();

        String noteFileName = id + FILE_EXTENSION;

        File[] file = repository.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.equals(noteFileName);

            }
        });

        if(file.length == 1) {
            try {
                return getNoteFromFile(file[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new EntityNotFoundException();
    }

    private String readFileContent(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String s;
        while((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }

    @Override
    public void delete(Integer idToDelete) {
        if(idToDelete == null)
            throw new IllegalArgumentException();

        File noteFileName = new File(repository.getPath() + "/" + idToDelete + FILE_EXTENSION);

        if(!noteFileName.exists())
            throw new EntityNotFoundException();

        Note n = getById(idToDelete);
        n.setDeleted();

        writeContentToFile(n);
    }

    @Override
    public void deleteAll() {
        for(File f : getAllNoteFilesFromRepositoryDirectory())
            f.delete();
    }

    @Override
    public void update(Note entity) {
        Note noteBeingUpdated = getById(entity.getId());

        noteBeingUpdated.setText(entity.getText());
        noteBeingUpdated.setLastChangedDate(LocalDateTime.now());

        writeContentToFile(noteBeingUpdated);
    }
}
