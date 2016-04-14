package com.enviance.bean;

import java.util.Collection;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.enviance.api.common.UserImpl;
import com.enviance.service.EnvianceService;

@Named(value = "userPageBean")
@ViewScoped
public class UsersPageBean {
	@Inject
	@Named("envianceService")
	private EnvianceService envianceService;

	private Collection<UserImpl> allUsers;
	private Collection<UserImpl> usersByName;

	private UserImpl selectedUser;

	public UserImpl getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserImpl selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Collection<UserImpl> getAllUsers() {

		if (allUsers == null) {
			allUsers = envianceService.getAllUsers();
		}

		return allUsers;
	}
	
	public Collection<UserImpl>getUsersByName(String name){
		if (usersByName == null){
			usersByName=envianceService.getUsersByName(name);
		}
		return usersByName;
	}

	public void updateUsers() {
		allUsers = null;
	}
}
