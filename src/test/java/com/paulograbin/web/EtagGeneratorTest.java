package com.paulograbin.web;


import com.paulograbin.web.crypto.EtagGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EtagGeneratorTest {

    private EtagGenerator generator;

    @Before
    public void setUp() {
        generator = new EtagGenerator();
    }

    @Test
    public void testWithEmptyString() {
        String generatedTag = generator.calculateEtag("");

        Assert.assertEquals("d41d8cd98f00b204e9800998ecf8427e", generatedTag);
    }

    @Test
    public void testWithOneLetter() {
        String generatedTag = generator.calculateEtag("a");

        Assert.assertEquals("cc175b9c0f1b6a831c399e269772661", generatedTag);
    }

    @Test
    public void testWithNumberSequence() {
        String generatedTag = generator.calculateEtag("123");

        Assert.assertEquals("202cb962ac59075b964b07152d234b70", generatedTag);
    }
}
