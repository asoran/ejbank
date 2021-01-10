package com.ejbank.api;


import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ejbank.api.payload.UserPayload;
import com.ejbank.entities.User;
import com.ejbank.haricots.UserBean;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserServer {
	
	@EJB
	private UserBean userBean;
	
    @GET
    @Path("/{user_id}")
    public UserPayload getUserInfo(@PathParam("age") int id) {
    	Optional<User> usr= userBean.getUserById(id);
    	  if ( !usr.isPresent() ) {
              return new UserPayload(" unknown ", " user");
          }

    	return new UserPayload(usr.get().getFirstname(), usr.get().getLastname());
    }

}

