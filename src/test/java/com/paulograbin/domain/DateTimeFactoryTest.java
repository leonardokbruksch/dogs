package com.paulograbin.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DateTimeFactoryTest {

    DateTimeFactory factory;

    @Before
    public void setUp() {
        factory = new DateTimeFactory();
    }

    @Test
    public void testDateIsVeryCloseToNow() throws InterruptedException {
        LocalDateTime dateFromFactory = factory.getCurrentUTCTime();
        LocalDateTime now = ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();

        verifyTwoDatesAreSimilarUpToSeconds(dateFromFactory, now);
    }

    private boolean verifyTwoDatesAreSimilarUpToSeconds(LocalDateTime aDate, LocalDateTime anotherDate) {
        System.out.println(aDate);
        System.out.println(anotherDate);

        assertEquals(aDate.getYear(), anotherDate.getYear());
        assertEquals(aDate.getMonth(), anotherDate.getMonth());
        assertEquals(aDate.getDayOfYear(), anotherDate.getDayOfYear());
        assertEquals(aDate.getHour(), anotherDate.getHour());
        assertEquals(aDate.getMinute(), anotherDate.getMinute());
        assertEquals(aDate.getSecond(), anotherDate.getSecond());

        return true;
    }
}
