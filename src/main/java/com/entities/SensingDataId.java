package com.entities;
// default package
// Generated 5/Mai/2014 15:56:04 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SensingDataId generated by hbm2java
 */
@Embeddable
public class SensingDataId implements java.io.Serializable {

	private int idSensing;
	private int sensorNodeIdSensorNode;

	public SensingDataId() {
	}

	public SensingDataId(int idSensing, int sensorNodeIdSensorNode) {
		this.idSensing = idSensing;
		this.sensorNodeIdSensorNode = sensorNodeIdSensorNode;
	}

	@Column(name = "idSensing", nullable = false)
	public int getIdSensing() {
		return this.idSensing;
	}

	public void setIdSensing(int idSensing) {
		this.idSensing = idSensing;
	}

	@Column(name = "Sensor_node_idSensor_node", nullable = false)
	public int getSensorNodeIdSensorNode() {
		return this.sensorNodeIdSensorNode;
	}

	public void setSensorNodeIdSensorNode(int sensorNodeIdSensorNode) {
		this.sensorNodeIdSensorNode = sensorNodeIdSensorNode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SensingDataId))
			return false;
		SensingDataId castOther = (SensingDataId) other;

		return (this.getIdSensing() == castOther.getIdSensing())
				&& (this.getSensorNodeIdSensorNode() == castOther
						.getSensorNodeIdSensorNode());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdSensing();
		result = 37 * result + this.getSensorNodeIdSensorNode();
		return result;
	}

}
