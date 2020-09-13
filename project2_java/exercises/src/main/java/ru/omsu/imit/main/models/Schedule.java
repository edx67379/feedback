package ru.omsu.imit.main.models;

import java.time.LocalTime;
import java.util.Objects;

public class Schedule {
    private int id;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Weekday weekday;

    public Schedule(int id, LocalTime timeStart, LocalTime timeEnd, Weekday weekday) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.weekday = weekday;
    }

    public Schedule(LocalTime timeStart, LocalTime timeEnd, Weekday weekday) {
        this(0, timeStart, timeEnd, weekday);
    }

    public Schedule() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id &&
                Objects.equals(timeStart, schedule.timeStart) &&
                Objects.equals(timeEnd, schedule.timeEnd) &&
                weekday == schedule.weekday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeStart, timeEnd, weekday);
    }
}
