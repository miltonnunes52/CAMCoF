package com.example.switchyard.CAMCoF.CommunicationServices.Persist;

import javax.ejb.EJB;

import org.switchyard.component.bean.Service;

import com.entities.SensorHome;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.SensorService;

@Service(VerifyInterface.class)
public class VerifyBean implements VerifyInterface {

	

	@EJB
    private SensorHome sensorHome;
	
	@Override
	public boolean verifySensor(SensorService sensorService) {
		String id = sensorService.getId();
		if(sensorHome.existByID(Integer.parseInt(id))){
			return true;
			
		}
		return false;
	}


}
