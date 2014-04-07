package com.example.switchyard.CAMCoF.CommunicationServices;

import com.example.switchyard.CAMCoF.CommunicationServices.Objects.*;


public interface ReadDataService {
	
	
	public DataResponse receiveData(DataObject dataObject);
	public SensorServiceResponse connectService(SensorService sensorService);
	public void camcofPing();
	public StatusResponse camcofPongProvisorio(StatusRequest statusRequest);

}
