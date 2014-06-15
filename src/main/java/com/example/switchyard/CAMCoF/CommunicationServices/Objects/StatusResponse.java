package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

public class StatusResponse {
	
	private String id;
	private String sensorid;
	private String response;
	
	public StatusResponse(String id, String sensorid, String response) {
		super();
		this.id = id;
		this.sensorid = sensorid;
		this.response = response;
	}
	
	public StatusResponse() {
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

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"sensorid\":\"" + sensorid + "\",\"response\":\""  + response + "\"}";
	}

}
