package com.enviance.dao;

import java.util.Collection;
import java.util.List;

import com.enviance.api.common.UserImpl;

public interface UserDao {

	UserImpl getUserById(Integer id);

	void updateUser(UserImpl user);

	Collection<UserImpl> getAllUsers();

	List<UserImpl> getUsersByName(String name);
}
