package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

import java.util.HashSet;
import java.util.Set;

import com.entities.SensingDataId;
import com.entities.SensingDataValue;

public class SensorService {
	
	private String id;
	private String ip;
	private String type;
	private String sensorid;
	private int period;
	private String typedata;
	private String description;
	private SensingDataId sensingDataId;
	
	public SensorService(String id, String ip, String type, String sensorid, int period,
			String typedata, String description) {
		this.id = id;
		this.ip = ip;
		this.type = type;
		this.sensorid = sensorid;
		this.period = period;
		this.typedata = typedata;
		this.description = description;
		this.setSensingDataId(null);
	}

	public SensorService(){
		
	}
	
	public SensorService(SensorService service) {
		this.id = service.getId();
		this.ip = service.getIp();
		this.type = service.getType();
		this.sensorid = service.getSensorid();
		this.period = service.getPeriod();
		this.typedata = service.getTypedata();
		this.description = service.getDescription();
		this.sensingDataId = service.getSensingDataId();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getTypedata() {
		return typedata;
	}

	public void setTypedata(String typedata) {
		this.typedata = typedata;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SensingDataId getSensingDataId() {
		return sensingDataId;
	}

	public void setSensingDataId(SensingDataId sensingDataId) {
		this.sensingDataId = sensingDataId;
	}

	

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	
	
	

}
