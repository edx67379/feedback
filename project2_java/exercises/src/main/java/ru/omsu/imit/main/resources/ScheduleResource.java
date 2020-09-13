package ru.omsu.imit.main.resources;

import ru.omsu.imit.main.service.ScheduleService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api")
public class ScheduleResource {
    private static ScheduleService service = new ScheduleService();

    @POST
    @Path("/schedules")
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertSchedule(String json) {
        return service.insertSchedule(json);
    }

    @GET
    @Path("/schedules/{id}")
    @Produces("application/json")
    public Response getById(@PathParam(value = "id") int id) {
        return service.getById(id);
    }

    @GET
    @Path("/schedules/by/{weekday}")
    @Produces("application/json")
    public Response getByWeekday(@PathParam(value = "weekday") String weekday) {
        return service.getByWeekday(weekday);
    }

    @GET
    @Path("/schedules/")
    @Produces("application/json")
    public Response getAll() {
        return service.getAll();
    }

    @PUT
    @Path("/schedules/{id}")
    @Produces("application/json")
    public Response editById(@PathParam(value = "id") int id, String json) {
        return service.editById(id, json);
    }

    @DELETE
    @Path("/schedules/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam(value = "id") int id) {
        return service.deleteById(id);
    }
}
