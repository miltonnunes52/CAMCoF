package com.entities;
// default package
// Generated 5/Mai/2014 15:56:05 by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Real.
 * @see .Real
 * @author Hibernate Tools
 */
@Stateless
public class RealHome {

	private static final Log log = LogFactory.getLog(RealHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Real transientInstance) {
		log.debug("persisting Real instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Real persistentInstance) {
		log.debug("removing Real instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Real merge(Real detachedInstance) {
		log.debug("merging Real instance");
		try {
			Real result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Real findById(Integer id) {
		log.debug("getting Real instance with id: " + id);
		try {
			Real instance = entityManager.find(Real.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
