package com.entities;
// default package
// Generated 20/Jun/2014 17:25:12 by Hibernate Tools 3.6.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class HighlevelInformation.
 * @see .HighlevelInformation
 * @author Hibernate Tools
 */
@Stateless
public class HighlevelInformationHome {

	private static final Log log = LogFactory
			.getLog(HighlevelInformationHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(HighlevelInformation transientInstance) {
		log.debug("persisting HighlevelInformation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(HighlevelInformation persistentInstance) {
		log.debug("removing HighlevelInformation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public HighlevelInformation merge(HighlevelInformation detachedInstance) {
		log.debug("merging HighlevelInformation instance");
		try {
			HighlevelInformation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public HighlevelInformation findById(Integer id) {
		log.debug("getting HighlevelInformation instance with id: " + id);
		try {
			HighlevelInformation instance = entityManager.find(
					HighlevelInformation.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
