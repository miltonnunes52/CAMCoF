package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

import com.entities.SensingDataId;

public class DataObject {
	
	private String id;
	private String type;
	private String data;
	private SensingDataId sensingDataId;
	
	public DataObject(String id, String type, String data) {
		this.id = id;
		this.type = type;
		this.data = data;
		this.setSensingDataId(null);
	}

	public DataObject() {
		
	}
	
	public DataObject(DataObject dataObject){
		this.id = dataObject.getId();
		this.type = dataObject.getType();
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.id = type;
	}

	public SensingDataId getSensingDataId() {
		return sensingDataId;
	}

	public void setSensingDataId(SensingDataId sensingDataId) {
		this.sensingDataId = sensingDataId;
	}
	
	
}
