package com.zimaku.zimaku.domain.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class InstantDateMapperFormatter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy")
            .withZone(ZoneId.systemDefault());

    public String formatInstant(Instant instant) {
        return instant != null ? FORMATTER.format(instant) : null;
    }

}
