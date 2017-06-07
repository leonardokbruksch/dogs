package com.leonardobruksch.domain;

import java.time.*;


public class DateTimeFactory {

    public LocalDateTime getCurrentUTCTime() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC);

        return dateTime.toLocalDateTime();
    }

}
