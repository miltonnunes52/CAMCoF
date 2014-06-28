package com.entities;
// default package
// Generated 20/Jun/2014 17:25:12 by Hibernate Tools 3.6.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class TransformationLevel.
 * @see .TransformationLevel
 * @author Hibernate Tools
 */
@Stateless
public class TransformationLevelHome {

	private static final Log log = LogFactory
			.getLog(TransformationLevelHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TransformationLevel transientInstance) {
		log.debug("persisting TransformationLevel instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TransformationLevel persistentInstance) {
		log.debug("removing TransformationLevel instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public TransformationLevel merge(TransformationLevel detachedInstance) {
		log.debug("merging TransformationLevel instance");
		try {
			TransformationLevel result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TransformationLevel findById(Integer id) {
		log.debug("getting TransformationLevel instance with id: " + id);
		try {
			TransformationLevel instance = entityManager.find(
					TransformationLevel.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
