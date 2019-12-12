package com.everis.auto.techtalk.quarkus.resource;

import com.everis.auto.techtalk.quarkus.service.GreetingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/greeting")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @POST
    @Path("/{content}")
    public Response createAndSave(@PathParam("content") final String content) {
        return Response.status(Response.Status.CREATED).entity(greetingService.createAndSave(content)).build();
    }

    @GET
    public Response getAll() {
        return Response.ok(greetingService.getAll()).build();
    }
}
