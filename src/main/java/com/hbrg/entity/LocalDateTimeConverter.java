package com.hbrg.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd";

    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dateTimeString) {
        LocalDate date = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        return LocalDateTime.of(date, LocalTime.MIDNIGHT);
    }
}