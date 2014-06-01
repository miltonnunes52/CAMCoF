package com.entities;
// default package
// Generated 5/Mai/2014 15:56:05 by Hibernate Tools 4.0.0

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class SensingData.
 * @see .SensingData
 * @author Hibernate Tools
 */
@Stateless
public class SensingDataHome {

	private static final Log log = LogFactory.getLog(SensingDataHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SensingData transientInstance) {
		log.debug("persisting SensingData instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SensingData persistentInstance) {
		log.debug("removing SensingData instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SensingData merge(SensingData detachedInstance) {
		log.debug("merging SensingData instance");
		try {
			SensingData result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SensingData findById(SensingDataId id) {
		log.debug("getting SensingData instance with id: " + id);
		try {
			SensingData instance = entityManager.find(SensingData.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public Integer getIdSensing(SensorNode idSensorNode){
		
		List<SensingDataId> resultlist =  entityManager.createQuery("SELECT id FROM SensingData WHERE sensorNode = :idSensorNode")
			.setParameter("idSensorNode", idSensorNode).getResultList();
		
		return resultlist.get(0).getIdSensing();
	}
	
	
}
