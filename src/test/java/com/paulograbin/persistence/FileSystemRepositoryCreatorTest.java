package com.paulograbin.persistence;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FileSystemRepositoryCreatorTest {

    private String path;
    private final String directoryName = "/OEditor_uploads/";
    private FileSystemRepositoryCreator creator;
    private File repositoryDirectory;

    @Before
    public void setUp() {
        path = System.getProperty("user.home");

        repositoryDirectory = new File(path + directoryName);
        creator = new FileSystemRepositoryCreator(repositoryDirectory);
    }

    @Test
    public void givenNonExistingPath__mustCreateIt() {
        repositoryDirectory = new File(path + directoryName);

        if(repositoryDirectory.exists())
            deleteRepositoryAndAllItsFiles();
        assertFalse(repositoryDirectory.exists());

        creator.create();
        assertTrue(repositoryDirectory.exists());
    }

    @Test
    public void givenNonExistingPath__repoMustNotBeReady() {
        repositoryDirectory = new File("/randomPath/here");
        creator = new FileSystemRepositoryCreator(repositoryDirectory);

        assertFalse(creator.isRepositoryReady());
    }

    @Test
    public void givenExistingPath__mustBeReady() {
        File repoPath = new File(path + directoryName);
        creator.create();
        assertTrue(repoPath.exists());

        creator.create();
        assertTrue(creator.isRepositoryReady());
    }

    @Ignore
    @Test(expected = RuntimeException.class)
    public void givenPathNotAllowedToBeWritten__mustThrowException() {
        // TODO: Implement it
    }

    private void deleteRepositoryAndAllItsFiles() {
        deleteAllRepositoryFiles();
        deleteRepository();
    }

    private void deleteRepository() {
        repositoryDirectory.delete();
    }

    private void deleteAllRepositoryFiles() {
        File[] filesInDirectory = repositoryDirectory.listFiles();

        for (File aFile : filesInDirectory)
            aFile.delete();
    }
}
