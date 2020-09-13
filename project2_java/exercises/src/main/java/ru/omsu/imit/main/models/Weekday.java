package ru.omsu.imit.main.models;

import java.util.Arrays;
import java.util.List;

public enum Weekday {
    Monday("Monday"),
    Tuesday("Tuesday"),
    Wednesday("Wednesday"),
    Thursday("Thursday"),
    Friday("Friday");

    private String text;

    Weekday(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> weekdays() {
        return Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
    }
}
