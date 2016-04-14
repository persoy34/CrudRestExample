package com.enviance.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.enviance.api.common.UserImpl;
import com.enviance.dao.UserDao;

@Named("inMemoryUserDao")
@Stateless
public class InMemoryUserDao implements UserDao {

	private Map<Integer, UserImpl> users;

	@PostConstruct
	public void init() {
		users = new HashMap<>();
		users.put(1, new UserImpl(1, "Johnny", "Depp", "Hollywood 1", "555 123", "Actor"));
		users.put(2, new UserImpl(2, "Leonardo", "DiCaprio", "Hollywood 2", "555 124", "Actor"));
		users.put(4, new UserImpl(4, "Leonardo", "Da Vinci", "Florence 2", "555 126", "Artist"));
		users.put(3, new UserImpl(3, "Sharon", "Stone", "Hollywood 3", "555 125", "Actress"));
	}

	@Override
	public UserImpl getUserById(Integer id) {
		return users.get(id);
	}

	@Override
	public void updateUser(UserImpl user) {
		UserImpl oldUser = users.get(user);
		oldUser.setName(user.getName());
		oldUser.setSurname(user.getSurname());
		oldUser.setAddress(user.getAddress());
		oldUser.setOccupation(user.getOccupation());
		oldUser.setPhone(user.getPhone());
	}

	@Override
	public Collection<UserImpl> getAllUsers() {
		return users.values();
	}

	@Override
	public List<UserImpl> getUsersByName(String name) {
		List<UserImpl> result = new ArrayList<>(getAllUsers());

		for (Iterator<UserImpl> userIt = result.iterator(); userIt.hasNext();) {
			UserImpl user = userIt.next();

			if (!user.getName().equals(name)) {
				userIt.remove();
			}
		}

		return result;
	}
}
