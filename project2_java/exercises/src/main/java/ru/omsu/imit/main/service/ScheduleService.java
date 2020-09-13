package ru.omsu.imit.main.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.main.dao.ScheduleDAO;
import ru.omsu.imit.main.daoImpl.ScheduleDAOImpl;
import ru.omsu.imit.main.exception.MyException;
import ru.omsu.imit.main.models.*;
import ru.omsu.imit.main.rest.request.RequestValidator;
import ru.omsu.imit.main.rest.request.ScheduleRequest;
import ru.omsu.imit.main.rest.response.EmptySuccessResponse;
import ru.omsu.imit.main.rest.response.ScheduleResponse;
import ru.omsu.imit.main.utils.ErrorCode;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ru.omsu.imit.main.service.ScheduleService.class);
    private static final Gson GSON = new GsonBuilder().create();
    private ScheduleDAO scheduleDAO = new ScheduleDAOImpl();

    public Response insertSchedule(String json) {
        LOGGER.debug("Insert schedule " + json);
        try {
            ScheduleRequest request = ru.omsu.imit.main.utils.RestUtils.getClassInstanceFromJson(GSON, json, ScheduleRequest.class);
            RequestValidator.scheduleRequestValidate(request);
            Employee employee = new Employee(request.getIdEmployee(), "employee",
                    new Department());
            Schedule schedule = new Schedule(LocalTime.parse(request.getTimeStart()),
                    LocalTime.parse(request.getTimeEnd()), Weekday.valueOf(request.getWeekday()));
            Schedule insertedSchedule = scheduleDAO.insert(employee, schedule);
            String response = GSON.toJson(new ScheduleResponse(insertedSchedule.getId(), insertedSchedule.getTimeStart().format(DateTimeFormatter.ISO_TIME),
                    insertedSchedule.getTimeEnd().format(DateTimeFormatter.ISO_TIME), schedule.getWeekday().getText()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response getById(int id) {
        LOGGER.debug("Get schedule by id " + id);
        try {
            Schedule schedule = scheduleDAO.getById(id);
            if(schedule == null) {
                throw new MyException(ErrorCode.SCHEDULE_NOT_FOUND);
            }
            String response = GSON.toJson(new ScheduleResponse(schedule.getId(), schedule.getTimeStart().format(DateTimeFormatter.ISO_TIME),
                    schedule.getTimeEnd().format(DateTimeFormatter.ISO_TIME), schedule.getWeekday().getText()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response getByWeekday(String weekday) {
        LOGGER.debug("Get schedule by weekday " + weekday);
        try {
            List<Schedule> scheduleList = scheduleDAO.getByWeekday(Weekday.valueOf(weekday));
            List<ru.omsu.imit.main.rest.response.ScheduleResponse> responseList = new ArrayList<>();
            for (Schedule schedule : scheduleList)
                responseList.add(new ScheduleResponse(schedule.getId(), schedule.getTimeStart().format(DateTimeFormatter.ISO_TIME),
                        schedule.getTimeEnd().format(DateTimeFormatter.ISO_TIME), schedule.getWeekday().getText()));
            String response = GSON.toJson(responseList);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response getAll() {
        LOGGER.debug("Get all schedules ");
        try {
            List<Schedule> scheduleList = scheduleDAO.getAll();
            List<ru.omsu.imit.main.rest.response.ScheduleResponse> responseList = new ArrayList<>();
            for (Schedule schedule : scheduleList)
                responseList.add(new ScheduleResponse(schedule.getId(), schedule.getTimeStart().format(DateTimeFormatter.ISO_TIME),
                        schedule.getTimeEnd().format(DateTimeFormatter.ISO_TIME), schedule.getWeekday().getText()));
            String response = GSON.toJson(responseList);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response editById(int id, String json) {
        LOGGER.debug("Edit schedule by id " + id);
        try {
            ru.omsu.imit.main.rest.request.ScheduleRequest request = ru.omsu.imit.main.utils.RestUtils.getClassInstanceFromJson(GSON, json, ru.omsu.imit.main.rest.request.ScheduleRequest.class);
            Schedule schedule = scheduleDAO.getById(id);
            if(schedule == null) {
                throw new MyException(ErrorCode.SCHEDULE_NOT_FOUND);
            }
            scheduleDAO.changeTimeStart(schedule, LocalTime.parse(request.getTimeStart()));
            scheduleDAO.changeTimeEnd(schedule, LocalTime.parse(request.getTimeEnd()));
            scheduleDAO.changeWeekday(schedule, Weekday.valueOf(request.getWeekday()));
            String response = GSON.toJson(new EmptySuccessResponse());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }

    public Response deleteById(int id) {
        LOGGER.debug("Delete schedule by id " + id);
        try {
            Schedule schedule = scheduleDAO.getById(id);
            if(schedule == null) {
                throw new MyException(ErrorCode.SCHEDULE_NOT_FOUND);
            }
            scheduleDAO.delete(schedule);
            String response = GSON.toJson(new EmptySuccessResponse());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ru.omsu.imit.main.exception.MyException ex) {
            return ru.omsu.imit.main.utils.RestUtils.failureResponse(ex);
        }
    }
}
