package com.example.switchyard.CAMCoF.CommunicationServices.Objects;

public class SensorService {
	
	private String id;
	private String ip;
	private String type;
	private int period;
	private String typedata;
	
	public SensorService(String id, String ip, String type, int period,
			String typedata) {
		this.id = id;
		this.ip = ip;
		this.type = type;
		this.period = period;
		this.typedata = typedata;
	}

	public SensorService(){
		
	}
	
	public SensorService(SensorService service) {
		this.id = service.getId();
		this.ip = service.getIp();
		this.type = service.getType();
		this.period = service.getPeriod();
		this.typedata = service.getTypedata();
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
	
	

}
