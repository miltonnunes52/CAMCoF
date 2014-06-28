package com.entities;
// default package
// Generated 20/Jun/2014 17:25:12 by Hibernate Tools 3.6.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Study.
 * @see .Study
 * @author Hibernate Tools
 */
@Stateless
public class StudyHome {

	private static final Log log = LogFactory.getLog(StudyHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Study transientInstance) {
		log.debug("persisting Study instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Study persistentInstance) {
		log.debug("removing Study instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Study merge(Study detachedInstance) {
		log.debug("merging Study instance");
		try {
			Study result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Study findById(Integer id) {
		log.debug("getting Study instance with id: " + id);
		try {
			Study instance = entityManager.find(Study.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
