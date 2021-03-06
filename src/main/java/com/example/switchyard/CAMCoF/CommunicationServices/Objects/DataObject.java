package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

import java.util.HashMap;
import java.util.Map;

import com.entities.SensingDataId;

public class DataObject {
	
	private String id;
	private String sensorid;
	private String data;
	private SensingDataId sensingDataId;
	private Map<String, String> metricsResults = new HashMap<String, String>(0);
	
	public DataObject(String id, String sensorid, String data) {
		this.id = id;
		this.sensorid = sensorid;
		this.data = data;
		this.setSensingDataId(null);
	}

	public DataObject() {
		
	}
	
	public DataObject(DataObject dataObject){
		this.id = dataObject.getId();
		this.sensorid = dataObject.getSensorid();
		this.data = dataObject.getData();
		this.sensingDataId = dataObject.getSensingDataId();
		
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public SensingDataId getSensingDataId() {
		return sensingDataId;
	}

	public void setSensingDataId(SensingDataId sensingDataId) {
		this.sensingDataId = sensingDataId;
	}

	public Map<String, String> getMetricsResults() {
		return metricsResults;
	}

	public void setMetricsResults(Map<String, String> metricsResults) {
		this.metricsResults = metricsResults;
	}
	
	
}
