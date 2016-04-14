package com.enviance.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.enviance.api.common.UserImpl;
import com.enviance.dao.UserDao;

@Named("envianceService")
@Singleton
public class EnvianceService {
	@Inject
	@Named("HibernateUserDao")
	private UserDao userDao;
	final static Logger logger = Logger.getLogger(EnvianceService.class);
	public UserImpl getUserById(Integer id) {
		logger.info("START EnvianceService.getUserById()");
		return userDao.getUserById(id);
	}

	public void updateUser(UserImpl user) {
		logger.info("START EnvianceService.updateUser()");
		userDao.updateUser(user);
	}

	public Collection<UserImpl> getAllUsers() {
		logger.info("START EnvianceService.getAllUsers()");
		return userDao.getAllUsers();
	}

	public List<UserImpl> getUsersByName(String name) {
		logger.info("START EnvianceService.getUsersByName()");
		return userDao.getUsersByName(name);
	}
}
