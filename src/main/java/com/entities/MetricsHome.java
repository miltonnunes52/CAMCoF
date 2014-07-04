package com.entities;
// default package
// Generated 20/Jun/2014 17:25:12 by Hibernate Tools 3.6.0

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Metrics.
 * @see .Metrics
 * @author Hibernate Tools
 */
@Stateless
public class MetricsHome {

	private static final Log log = LogFactory.getLog(MetricsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Metrics transientInstance) {
		log.debug("persisting Metrics instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Metrics persistentInstance) {
		log.debug("removing Metrics instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Metrics merge(Metrics detachedInstance) {
		log.debug("merging Metrics instance");
		try {
			Metrics result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Metrics findById(Integer id) {
		log.debug("getting Metrics instance with id: " + id);
		try {
			Metrics instance = entityManager.find(Metrics.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Metrics> findByType(String type) {
		
		List<Metrics> resultlist =  entityManager.createQuery("FROM Metrics WHERE type = :type")
				.setParameter("type", type).getResultList();

			return resultlist;
	}
}
