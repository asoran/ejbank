package com.ejbank.api;

import com.ejbank.api.payload.ServerPayload;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserServer {

    @GET
    @Path("/{id}")
    public ServerPayload getStatus(@PathParam("age") int id) {
        return new ServerPayload(true);
    }

}

