package com.example.switchyard.CAMCoF.CommunicationServices.Persist;

import com.entities.SensingData;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.SensorService;

public interface SaveSensingInterface {
	
	public SensingData addSensingData(SensorService sensorService);
	//public SensorNode addSensorNode(SensorService sensorService);
}
