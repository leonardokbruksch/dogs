package com.paulograbin.persistence;

import com.paulograbin.domain.notes.NotesRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DatabaseNotesRepositoryTest extends RepositoryTest {

    @Override
    protected NotesRepository makeRepository() {
        return new DatabaseNotesRepository();
    }
}
