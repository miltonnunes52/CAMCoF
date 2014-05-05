package com.entities;
// default package
// Generated 5/Mai/2014 15:56:05 by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SensingDataValue.
 * @see .SensingDataValue
 * @author Hibernate Tools
 */
@Stateless
public class SensingDataValueHome {

	private static final Log log = LogFactory
			.getLog(SensingDataValueHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SensingDataValue transientInstance) {
		log.debug("persisting SensingDataValue instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SensingDataValue persistentInstance) {
		log.debug("removing SensingDataValue instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SensingDataValue merge(SensingDataValue detachedInstance) {
		log.debug("merging SensingDataValue instance");
		try {
			SensingDataValue result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SensingDataValue findById(Integer id) {
		log.debug("getting SensingDataValue instance with id: " + id);
		try {
			SensingDataValue instance = entityManager.find(
					SensingDataValue.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
