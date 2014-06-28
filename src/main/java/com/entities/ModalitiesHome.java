package com.entities;
// default package
// Generated 20/Jun/2014 17:25:12 by Hibernate Tools 3.6.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Modalities.
 * @see .Modalities
 * @author Hibernate Tools
 */
@Stateless
public class ModalitiesHome {

	private static final Log log = LogFactory.getLog(ModalitiesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Modalities transientInstance) {
		log.debug("persisting Modalities instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Modalities persistentInstance) {
		log.debug("removing Modalities instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Modalities merge(Modalities detachedInstance) {
		log.debug("merging Modalities instance");
		try {
			Modalities result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Modalities findById(Integer id) {
		log.debug("getting Modalities instance with id: " + id);
		try {
			Modalities instance = entityManager.find(Modalities.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
