package com.example.switchyard.CAMCoF.CommunicationServices.Persist;

import javax.ejb.EJB;

import org.switchyard.component.bean.Service;

import com.entities.Sensor;
import com.entities.SensorHome;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.SensorService;

@Service(SaveSensorInterface.class)
public class SaveSensorBean implements SaveSensorInterface {

	
	@EJB
    private SensorHome sensorHome;
	
	@Override
	public SensorService addNewSensor(SensorService sensorService) {
		Sensor sensor = new Sensor();
		
		
		//temp id do sensor e o ip
		sensor.setIdSensor(Integer.parseInt(sensorService.getId()));
		sensor.setDataPeriodicity(Integer.toString(sensorService.getPeriod()));
		sensor.setLocation("location");
		sensor.setType(sensorService.getType());
		
		
		sensor = sensorHome.merge(sensor);
		
		
		/*******/
		
		System.out.println(sensor.getIdSensor() + sensor.getType());
		/******/
		
		return sensorService;
				
	}

}
