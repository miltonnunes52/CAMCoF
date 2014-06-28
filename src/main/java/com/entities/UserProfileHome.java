package com.entities;
// default package
// Generated 20/Jun/2014 17:25:12 by Hibernate Tools 3.6.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UserProfile.
 * @see .UserProfile
 * @author Hibernate Tools
 */
@Stateless
public class UserProfileHome {

	private static final Log log = LogFactory.getLog(UserProfileHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(UserProfile transientInstance) {
		log.debug("persisting UserProfile instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserProfile persistentInstance) {
		log.debug("removing UserProfile instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserProfile merge(UserProfile detachedInstance) {
		log.debug("merging UserProfile instance");
		try {
			UserProfile result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserProfile findById(Integer id) {
		log.debug("getting UserProfile instance with id: " + id);
		try {
			UserProfile instance = entityManager.find(UserProfile.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public UserProfile findByUsername(String username) {
		log.debug("getting UserProfile instance with username: " + username);
		try {
			UserProfile instance = (UserProfile) entityManager.createQuery("FROM UserProfile WHERE username = :username")
					.setParameter("username", username).getSingleResult();
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public boolean existByID(String username) {
		Long i =  (Long) entityManager.createQuery("SELECT COUNT(*) FROM UserProfile WHERE username = :username")
				.setParameter("username", username).getSingleResult();

		if (i == 0) return false;
		return true;
	}


}
