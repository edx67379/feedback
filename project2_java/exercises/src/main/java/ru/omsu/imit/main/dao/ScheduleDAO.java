package ru.omsu.imit.main.dao;

import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Schedule;
import ru.omsu.imit.main.models.Weekday;

import java.time.LocalTime;
import java.util.List;

public interface ScheduleDAO {
    public Schedule insert(Employee employee, Schedule schedule);

    public Schedule getById(int id);

    public List<Schedule> getByEmployee(Employee employee);

    public List<Schedule> getByTimeStart(LocalTime timeStart);

    public List<Schedule> getByTimeEnd(LocalTime timeEnd);

    public List<Schedule> getByWeekday(Weekday weekday);

    public List<Schedule> getAll();

    public void deleteAll();

    public void delete(Schedule schedule);

    public void changeTimeStart(Schedule schedule, LocalTime timeStart);

    public void changeTimeEnd(Schedule schedule, LocalTime timeEnd);

    public void changeWeekday(Schedule schedule, Weekday weekday);
}
