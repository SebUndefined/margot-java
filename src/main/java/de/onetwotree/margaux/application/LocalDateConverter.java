package de.onetwotree.margaux.application;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by SebUndefined on 25/07/17.
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {
    private final DateTimeFormatter formatter;

    public LocalDateConverter(String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public LocalDate convert(String source) {
        if (source == null || source.isEmpty()) {
            System.out.println("Va à null");
            return null;
        }
        System.out.println(source);
        LocalDate dateFormated = LocalDate.parse(source, formatter);
        System.out.println(dateFormated.getDayOfMonth());
        return dateFormated;
    }
}
