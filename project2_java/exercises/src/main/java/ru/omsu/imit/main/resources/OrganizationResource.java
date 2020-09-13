package ru.omsu.imit.main.resources;

import ru.omsu.imit.main.service.OrganizationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api")
public class OrganizationResource {

    private static OrganizationService service = new OrganizationService();

    @POST
    @Path("/organizations")
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertOrganization(String json) {
    	return service.insertOrganization(json);
    }
    
    @GET
    @Path("/organizations/{id}")
    @Produces("application/json")
    public Response getById(@PathParam(value = "id") int id) {
    	return service.getById(id);
    }
    
    @GET
    @Path("/organizations/")
    @Produces("application/json")
    public Response getAll() {
    	return service.getAll();
    }

    @PUT
    @Path("/organizations/{id}")
    @Produces("application/json")
    public Response editById(@PathParam(value = "id") int id, String json) {
    	return service.editById(id, json);
    }

    @DELETE
    @Path("/organizations/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam(value = "id") int id) {
    	return service.deleteById(id);
    }
}

