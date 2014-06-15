package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

public class StatusRequest {
	
	
	private String id;
	private String sensorid;
	private String status;
	
	
	public StatusRequest() {

	}
	
	
	public StatusRequest(String id, String sensorid, String status) {
		this.id = id;
		this.sensorid = sensorid;
		this.status = status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"sensorid\":\"" + sensorid + "\",\"status\":\""  + status + "\"}";
	}
	
	
}
