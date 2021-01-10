package com.ejbank.api;

import com.ejbank.api.payload.ServerPayload;
import com.ejbank.entities.User;
import com.ejbank.haricots.UserBean;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserServer {
	
	@EJB
	private UserBean userBean;
	
    @GET
    @Path("/{user_id}")
    public User getUserInfo(@PathParam("age") int id) {
    	return userBean.getUserById(id);
    }

}

