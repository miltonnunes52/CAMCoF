package com.example.switchyard.CAMCoF.CommunicationServices;


import com.example.switchyard.CAMCoF.CommunicationServices.Objects.*;


public interface EndPointService {
	
	SensorServiceResponse connectService(SensorService sensorService);
	DataResponse receiveData(DataObject dataObject);
	void camcofPing();
	StatusResponse camcofPongProvisorio(StatusRequest statusRequest);

	
	

}
