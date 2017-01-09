package com.paulograbin.persistence;

import com.paulograbin.domain.notes.Note;
import com.paulograbin.domain.notes.NotesRepository;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseNotesRepositoryTest extends RepositoryTest {

    @Inject
    ApplicationContext context;

    @Inject
    NotesRepository repo;

    @Override
    protected NotesRepository makeRepository() {
        NotesRepository bean = (NotesRepository) context.getBean("databaseNotesRepository");
//        for(String s : context.getBeanDefinitionNames()) {
//            System.out.println(s);
//        }

        bean.save(new Note());
        System.out.println(bean.count());

        DatabaseNotesRepository teste = new DatabaseNotesRepository();
        return teste;
    }
}
