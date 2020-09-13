package ru.omsu.imit.main.resources;

import ru.omsu.imit.main.service.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api")
public class EmployeeResource {
    private static EmployeeService service = new EmployeeService();

    @POST
    @Path("/employees")
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertEmployee(String json) {
        return service.insertEmployee(json);
    }

    @GET
    @Path("/employees/{id}")
    @Produces("application/json")
    public Response getById(@PathParam(value = "id") int id) {
        return service.getById(id);
    }

    @GET
    @Path("/employees/")
    @Produces("application/json")
    public Response getAll() {
        return service.getAll();
    }

    @PUT
    @Path("/employees/{id}")
    @Produces("application/json")
    public Response editById(@PathParam(value = "id") int id, String json) {
        return service.editById(id, json);
    }

    @DELETE
    @Path("/employees/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam(value = "id") int id) {
        return service.deleteById(id);
    }
}
