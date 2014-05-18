package com.example.switchyard.CAMCoF.CommunicationServices;

import com.entities.SensingData;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.SensorService;

public interface SaveSensingService {
	
	public SensingData saveData(SensorService sensorService);

}
