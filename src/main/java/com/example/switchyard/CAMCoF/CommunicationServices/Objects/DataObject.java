package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

public class DataObject {
	
	private String id;
	private String data;
	
	public DataObject(String id, String data) {
		this.id = id;
		this.data = data;
	}

	public DataObject() {
		
	}
	
	public DataObject(DataObject dataObject){
		this.id = dataObject.getId();
		this.data = dataObject.getData();
		
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
	
	
	
}
