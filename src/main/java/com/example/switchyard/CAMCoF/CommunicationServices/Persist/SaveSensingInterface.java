package com.example.switchyard.CAMCoF.CommunicationServices.Persist;

import com.entities.SensingData;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.SensorService;

public interface SaveSensingInterface {
	
	public boolean verifySensor(SensorService sensorService);
	public SensorService addNewSensor(SensorService sensorService);
	public SensingData addSensingData(SensorService sensorService);
}
