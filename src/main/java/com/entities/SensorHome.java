package com.entities;
// default package
// Generated 5/Mai/2014 15:56:05 by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Sensor.
 * @see .Sensor
 * @author Hibernate Tools
 */
@Stateless
public class SensorHome {

	private static final Log log = LogFactory.getLog(SensorHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Sensor transientInstance) {
		log.debug("persisting Sensor instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Sensor persistentInstance) {
		log.debug("removing Sensor instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Sensor merge(Sensor detachedInstance) {
		log.debug("merging Sensor instance");
		try {
			Sensor result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sensor findById(Integer id) {
		log.debug("getting Sensor instance with id: " + id);
		try {
			Sensor instance = entityManager.find(Sensor.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Sensor findByType(String type) {
		log.debug("getting Sensor instance with type: " + type);
		try {
			Sensor instance = entityManager.find(Sensor.class, type);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public boolean existByID(Integer id) {
		log.debug("getting Sensor instance with id: " + id);
		try {
			Sensor instance = entityManager.find(Sensor.class, id);
			log.debug("get successful");
			return true;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			return false;
		}
	}
}
