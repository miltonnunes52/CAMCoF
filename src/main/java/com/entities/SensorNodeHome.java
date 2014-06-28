package com.entities;
// default package
// Generated 20/Jun/2014 17:25:12 by Hibernate Tools 3.6.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SensorNode.
 * @see .SensorNode
 * @author Hibernate Tools
 */
@Stateless
public class SensorNodeHome {

	private static final Log log = LogFactory.getLog(SensorNodeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SensorNode transientInstance) {
		log.debug("persisting SensorNode instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SensorNode persistentInstance) {
		log.debug("removing SensorNode instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SensorNode merge(SensorNode detachedInstance) {
		log.debug("merging SensorNode instance");
		try {
			SensorNode result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SensorNode findById(Integer id) {
		log.debug("getting SensorNode instance with id: " + id);
		try {
			SensorNode instance = entityManager.find(SensorNode.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
