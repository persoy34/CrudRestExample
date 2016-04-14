package com.enviance.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.enviance.api.common.UserImpl;
import com.enviance.service.EnvianceService;

@Path("/user")
public class UserRestServiceRoot {
	@Inject
	@Named("envianceService")
	private EnvianceService envianceService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserImpl getUserById(@PathParam("id") Integer userId) {
		return envianceService.getUserById(userId);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserImpl> getUsersByName(@QueryParam("name") String name) {
		return envianceService.getUsersByName(name);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUser(UserImpl user) {
		envianceService.updateUser(user);
	}
}
