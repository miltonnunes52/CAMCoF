package com.example.switchyard.CAMCoF;

public class JSONObject {
	
	private String request;
	private boolean status;
	private int error_code;
	private String error_message;
	private String data;
	private String timestamp;
	
	public JSONObject(String request, boolean status, int error_code,
			String error_message, String data, String timestamp) {
		this.request = request;
		this.status = status;
		this.error_code = error_code;
		this.error_message = error_message;
		this.data = data;
		this.timestamp = timestamp;
	}

	public JSONObject() {
		
	}
	
	public JSONObject(JSONObject jsonObject){
		this.request = jsonObject.getRequest();
		this.status = jsonObject.isStatus();
		this.error_code = jsonObject.getError_code();
		this.error_message = jsonObject.getError_message();
		this.data = jsonObject.getData();
		this.timestamp = jsonObject.getTimestamp();
		
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
