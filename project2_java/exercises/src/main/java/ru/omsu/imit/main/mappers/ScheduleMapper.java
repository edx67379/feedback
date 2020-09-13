package ru.omsu.imit.main.mappers;

import org.apache.ibatis.annotations.*;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Schedule;
import ru.omsu.imit.main.models.Weekday;

import java.time.LocalTime;
import java.util.List;

public interface ScheduleMapper {
    @Insert("INSERT INTO schedule (employee_id, timestart, timeend, weekday) " +
            "VALUES ( #{employee.id}, " +
            "#{schedule.timeStart}, " +
            "#{schedule.timeEnd}," +
            "#{schedule.weekday})")
    @Options(useGeneratedKeys = true, keyProperty = "schedule.id")
    public Integer insert(@Param("employee") Employee employee, @Param("schedule") Schedule schedule);

    @Select("SELECT * FROM schedule WHERE id = #{id}")
    public Schedule getById(int id);

    @Select("SELECT * FROM schedule WHERE employee_id = #{employee.id}")
    public List<Schedule> getByEmployee(@Param("employee") Employee employee);

    @Select("SELECT * FROM schedule WHERE timestart = #{timeStart}")
    public List<Schedule> getByTimeStart(LocalTime timeStart);

    @Select("SELECT * FROM schedule WHERE timeend = #{timeEnd}")
    public List<Schedule> getByTimeEnd(LocalTime timeEnd);

    @Select("SELECT * FROM schedule WHERE weekday = #{weekday}")
    public List<Schedule> getByWeekday(Weekday weekday);

    @Select("SELECT * FROM schedule")
    public List<Schedule> getAll();

    @Delete("DELETE FROM schedule")
    public void deleteAll();

    @Delete("DELETE FROM schedule WHERE id = #{schedule.id}")
    public void delete(@Param("schedule") Schedule schedule);

    @Update("UPDATE schedule SET timestart = #{timeStart} WHERE id = #{schedule.id} ")
    public void changeTimeStart(@Param("schedule") Schedule schedule, @Param("timeStart") LocalTime timeStart);

    @Update("UPDATE schedule SET timeend = #{timeEnd} WHERE id = #{schedule.id} ")
    public void changeTimeEnd(@Param("schedule") Schedule schedule, @Param("timeEnd") LocalTime timeEnd);

    @Update("UPDATE schedule SET weekday = #{weekday} WHERE id = #{schedule.id} ")
    public void changeWeekday(@Param("schedule") Schedule schedule, @Param("weekday") Weekday weekday);
}
