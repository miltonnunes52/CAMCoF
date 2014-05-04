package com.entities;
// default package
// Generated 3/Mai/2014 21:44:14 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sensor generated by hbm2java
 */
@Entity
@Table(name = "sensor", catalog = "camcof")
public class Sensor implements java.io.Serializable {

	private Integer idSensor;
	private String dataPeriodicity;
	private String type;
	private String location;
	private Set<SensorNode> sensorNodes = new HashSet<SensorNode>(0);
	private Set<Modalities> modalitieses = new HashSet<Modalities>(0);

	public Sensor() {
	}

	public Sensor(String dataPeriodicity, String type, String location,
			Set<SensorNode> sensorNodes, Set<Modalities> modalitieses) {
		this.dataPeriodicity = dataPeriodicity;
		this.type = type;
		this.location = location;
		this.sensorNodes = sensorNodes;
		this.modalitieses = modalitieses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idSensor", unique = true, nullable = false)
	public Integer getIdSensor() {
		return this.idSensor;
	}

	public void setIdSensor(Integer idSensor) {
		this.idSensor = idSensor;
	}

	@Column(name = "dataPeriodicity", length = 45)
	public String getDataPeriodicity() {
		return this.dataPeriodicity;
	}

	public void setDataPeriodicity(String dataPeriodicity) {
		this.dataPeriodicity = dataPeriodicity;
	}

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "location", length = 45)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sensor")
	public Set<SensorNode> getSensorNodes() {
		return this.sensorNodes;
	}

	public void setSensorNodes(Set<SensorNode> sensorNodes) {
		this.sensorNodes = sensorNodes;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "modalities_has_sensor", catalog = "camcof", joinColumns = { @JoinColumn(name = "Sensor_idSensor", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Modalities_idModalities", nullable = false, updatable = false) })
	public Set<Modalities> getModalitieses() {
		return this.modalitieses;
	}

	public void setModalitieses(Set<Modalities> modalitieses) {
		this.modalitieses = modalitieses;
	}

}