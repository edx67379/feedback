package ru.omsu.imit.main.daoImpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.main.dao.ScheduleDAO;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.Employee;
import ru.omsu.imit.main.models.Schedule;
import ru.omsu.imit.main.models.Weekday;
import ru.omsu.imit.main.utils.ErrorCode;

import java.time.LocalTime;
import java.util.List;

public class ScheduleDAOImpl extends BaseDAOImpl implements ScheduleDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDAOImpl.class);

    @Override
    public Schedule insert(Employee employee, Schedule schedule) {
        LOGGER.debug("DAO Insert Schedule {}", schedule);
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).insert(employee, schedule);
                employee.getScheduleList().add(schedule);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't insert Schedule {} {}", schedule, ex);
                sqlSession.rollback();
                throw new MyException(ErrorCode.EMPTY_PARAM);
            }
            sqlSession.commit();
        }
        return schedule;
    }

    @Override
    public Schedule getById(int id) {
        try (SqlSession sqlSession = getSession()) {
            return getScheduleMapper(sqlSession).getById(id);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Schedule by Id {} {}", id, ex);
            throw new MyException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
    }

    @Override
    public List<Schedule> getByEmployee(Employee employee) {
        try (SqlSession sqlSession = getSession()) {
            return getScheduleMapper(sqlSession).getByEmployee(employee);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Schedule by Employee {} {}", employee, ex);
            throw ex;
        }
    }

    @Override
    public List<Schedule> getByTimeStart(LocalTime timeStart) {
        try (SqlSession sqlSession = getSession()) {
            return getScheduleMapper(sqlSession).getByTimeStart(timeStart);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Schedule by TimeStart {} {}", timeStart, ex);
            throw ex;
        }
    }

    @Override
    public List<Schedule> getByTimeEnd(LocalTime timeEnd) {
        try (SqlSession sqlSession = getSession()) {
            return getScheduleMapper(sqlSession).getByTimeEnd(timeEnd);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Schedule by TimeEnd {} {}", timeEnd, ex);
            throw ex;
        }
    }

    @Override
    public List<Schedule> getByWeekday(Weekday weekday) {
        try (SqlSession sqlSession = getSession()) {
            return getScheduleMapper(sqlSession).getByWeekday(weekday);
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get Schedule by Weekday {} {}", weekday.getText(), ex);
            throw ex;
        }
    }

    @Override
    public List<Schedule> getAll() {
        try (SqlSession sqlSession = getSession()) {
            return getScheduleMapper(sqlSession).getAll();
        }
        catch (RuntimeException ex) {
            LOGGER.debug("Can't get All {}", ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All Schedules {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete all Schedules {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(Schedule schedule) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).delete(schedule);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't delete Schedule {} {}", schedule, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void changeTimeStart(Schedule schedule, LocalTime timeStart) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).changeTimeStart(schedule, timeStart);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Schedule TimeStart {} {} ", schedule, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void changeTimeEnd(Schedule schedule, LocalTime timeEnd) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).changeTimeEnd(schedule, timeEnd);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Schedule TimeEnd {} {} ", schedule, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void changeWeekday(Schedule schedule, Weekday weekday) {
        try (SqlSession sqlSession = getSession()) {
            try {
                getScheduleMapper(sqlSession).changeWeekday(schedule, weekday);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can't change Schedule Weekday {} {} ", schedule, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
