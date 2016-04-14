package com.enviance.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.enviance.api.common.UserImpl;
import com.enviance.dao.UserDao;
import com.enviance.service.EnvianceService;

@Named("HibernateUserDao")
@Stateless
public class HibernateUserDao implements UserDao {
	final static Logger logger = Logger.getLogger(HibernateUserDao.class);
	@PersistenceContext(unitName = "jboss-embedded-pu")
	private EntityManager em;

	@PostConstruct
	public void init() {
		logger.info("START HibernateUserDao.init()");
		em.merge(new UserImpl(1, "Johnny", "Depp", "Hollywood 11", "555 221", "Actor"));
		em.merge(new UserImpl(2, "Leonardo", "Dicaprio", "Hollywood 12", "555 222", "Actor"));
		em.merge(new UserImpl(4, "Leonardo", "Da Vinci", "Florence 14", "555 224", "Artist"));
		em.merge(new UserImpl(3, "Sharon", "Stone", "Hollywood 13", "555 223", "Actress"));
	}

	@Override
	public UserImpl getUserById(Integer id) {
		logger.info("START HibernateUserDao.getUserById()");
		return em.find(UserImpl.class, id);
	}

	@Override
	public void updateUser(UserImpl user) {
		logger.info("START HibernateUserDao.updateUser()");
		em.merge(user);
	}

	@Override
	public Collection<UserImpl> getAllUsers() {
		logger.info("START HibernateUserDao.getAllUsers()");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserImpl> cq = cb.createQuery(UserImpl.class);
		Root<UserImpl> rootEntry = cq.from(UserImpl.class);
		CriteriaQuery<UserImpl> all = cq.select(rootEntry);
		TypedQuery<UserImpl> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	@Override
	public List<UserImpl> getUsersByName(String name) {
		logger.info("START HibernateUserDao.getUsersByName()");
		List<UserImpl> result = em.createNamedQuery("User.getUsersByName", UserImpl.class).setParameter("name", name)
				.getResultList();

		return result;
	}

}
