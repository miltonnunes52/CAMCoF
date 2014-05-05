package com.entities;
// default package
// Generated 5/Mai/2014 15:56:05 by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.services.task.annotations.Local;

/**
 * Home object for domain model class ValueUnits.
 * @see .ValueUnits
 * @author Hibernate Tools
 */
@Stateless
public class ValueUnitsHome {

	private static final Log log = LogFactory.getLog(ValueUnitsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ValueUnits transientInstance) {
		log.debug("persisting ValueUnits instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ValueUnits persistentInstance) {
		log.debug("removing ValueUnits instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ValueUnits merge(ValueUnits detachedInstance) {
		log.debug("merging ValueUnits instance");
		try {
			ValueUnits result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ValueUnits findById(Integer id) {
		log.debug("getting ValueUnits instance with id: " + id);
		try {
			ValueUnits instance = entityManager.find(ValueUnits.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
