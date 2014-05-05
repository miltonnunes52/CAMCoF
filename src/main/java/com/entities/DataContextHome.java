package com.entities;
// default package
// Generated 5/Mai/2014 15:56:05 by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class DataContext.
 * @see .DataContext
 * @author Hibernate Tools
 */
@Stateless
public class DataContextHome {

	private static final Log log = LogFactory.getLog(DataContextHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(DataContext transientInstance) {
		log.debug("persisting DataContext instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(DataContext persistentInstance) {
		log.debug("removing DataContext instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public DataContext merge(DataContext detachedInstance) {
		log.debug("merging DataContext instance");
		try {
			DataContext result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DataContext findById(Integer id) {
		log.debug("getting DataContext instance with id: " + id);
		try {
			DataContext instance = entityManager.find(DataContext.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
