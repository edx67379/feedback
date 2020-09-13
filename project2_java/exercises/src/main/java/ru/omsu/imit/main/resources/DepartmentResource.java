package ru.omsu.imit.main.resources;

import ru.omsu.imit.main.service.DepartmentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api")
public class DepartmentResource {
    private static DepartmentService service = new DepartmentService();

    @POST
    @Path("/departments")
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertDepartment(String json) {
        return service.insertDepartment(json);
    }

    @GET
    @Path("/departments/{id}")
    @Produces("application/json")
    public Response getById(@PathParam(value = "id") int id) {
        return service.getById(id);
    }

    @GET
    @Path("/departments/")
    @Produces("application/json")
    public Response getAll() {
        return service.getAll();
    }

    @PUT
    @Path("/departments/{id}")
    @Produces("application/json")
    public Response editById(@PathParam(value = "id") int id, String json) {
        return service.editById(id, json);
    }

    @DELETE
    @Path("/departments/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam(value = "id") int id) {
        return service.deleteById(id);
    }
}
