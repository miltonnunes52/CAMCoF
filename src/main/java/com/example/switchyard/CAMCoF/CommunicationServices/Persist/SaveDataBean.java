package com.example.switchyard.CAMCoF.CommunicationServices.Persist;

import javax.ejb.EJB;

import org.switchyard.component.bean.Service;

import com.entities.SensingData;
import com.entities.SensingDataHome;
import com.entities.SensingDataId;
import com.entities.SensingDataValue;
import com.entities.SensingDataValueHome;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.DataObject;

@Service(SaveDataInterface.class)
public class SaveDataBean implements SaveDataInterface {

	@EJB
	private SensingDataValueHome sensingDataValueHome;
	
	@EJB
	private SensingDataHome sensingDataHome;

	
	@Override
	public void persistData(DataObject dataObject) {
		
		SensingDataValue sensingDataValue = new SensingDataValue();
		//get id sensing data respectivo
		SensingDataId sensingDataId = dataObject.getSensingDataId();
		SensingData sensingData = sensingDataHome.findById(sensingDataId);
	
		sensingDataValue.setSensingData(sensingData);
		sensingDataValue.setValue(dataObject.getData());
		sensingDataValue.setValueAddress("valueaddress");
		
		sensingDataValueHome.merge(sensingDataValue);
		//value units?!	
	}

}
