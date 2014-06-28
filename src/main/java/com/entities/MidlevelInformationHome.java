package com.entities;
// default package
// Generated 20/Jun/2014 17:25:12 by Hibernate Tools 3.6.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class MidlevelInformation.
 * @see .MidlevelInformation
 * @author Hibernate Tools
 */
@Stateless
public class MidlevelInformationHome {

	private static final Log log = LogFactory
			.getLog(MidlevelInformationHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MidlevelInformation transientInstance) {
		log.debug("persisting MidlevelInformation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MidlevelInformation persistentInstance) {
		log.debug("removing MidlevelInformation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MidlevelInformation merge(MidlevelInformation detachedInstance) {
		log.debug("merging MidlevelInformation instance");
		try {
			MidlevelInformation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MidlevelInformation findById(Integer id) {
		log.debug("getting MidlevelInformation instance with id: " + id);
		try {
			MidlevelInformation instance = entityManager.find(
					MidlevelInformation.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
