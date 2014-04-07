package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

public class StatusResponse {
	
	private String id;
	private String type;
	private String response;
	
	public StatusResponse(String id, String type, String response) {
		super();
		this.id = id;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"type\":\"" + type + "\",\"response\":\""  + response + "\"}";
	}

}
