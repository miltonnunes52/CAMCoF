package com.entities;
// default package
// Generated 7/Jun/2014 22:44:51 by Hibernate Tools 3.6.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Transformation.
 * @see .Transformation
 * @author Hibernate Tools
 */
@Stateless
public class TransformationHome {

	private static final Log log = LogFactory.getLog(TransformationHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Transformation transientInstance) {
		log.debug("persisting Transformation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Transformation persistentInstance) {
		log.debug("removing Transformation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Transformation merge(Transformation detachedInstance) {
		log.debug("merging Transformation instance");
		try {
			Transformation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Transformation findById(Integer id) {
		log.debug("getting Transformation instance with id: " + id);
		try {
			Transformation instance = entityManager.find(Transformation.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
