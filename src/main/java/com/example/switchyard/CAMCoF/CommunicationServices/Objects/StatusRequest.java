package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

public class StatusRequest {
	
	
	private String id;
	private String type;
	private String status;
	
	
	public StatusRequest() {

	}
	
	
	public StatusRequest(String id, String type, String status) {
		this.id = id;
		this.type = type;
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"type\":\"" + type + "\",\"status\":\""  + status + "\"}";
	}
	
	
}
