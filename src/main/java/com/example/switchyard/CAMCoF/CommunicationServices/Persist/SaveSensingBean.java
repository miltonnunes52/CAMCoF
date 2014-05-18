package com.example.switchyard.CAMCoF.CommunicationServices.Persist;


import javax.ejb.EJB;

import org.switchyard.component.bean.Service;

import com.entities.ClassificationTags;
import com.entities.ClassificationTagsHome;
import com.entities.DataContext;
import com.entities.DataContextHome;
import com.entities.MidlevelInformation;
import com.entities.MidlevelInformationHome;
import com.entities.SensingData;
import com.entities.SensingDataHome;
import com.entities.SensingDataId;
import com.entities.Sensor;
import com.entities.SensorHome;
import com.entities.SensorNode;
import com.entities.SensorNodeHome;
import com.entities.TransformationLevel;
import com.entities.TransformationLevelHome;
import com.entities.UserProfile;
import com.entities.UserProfileHome;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.SensorService;

@Service(SaveSensingInterface.class)
public class SaveSensingBean implements SaveSensingInterface {

	@EJB
    private SensorHome sensorHome;
	
	@EJB
	private SensorNodeHome sensorNodeHome;
	
	@EJB
	private ClassificationTagsHome classificationTagsHome;
	
	@EJB
	private DataContextHome dataContextHome;
	
	@EJB
	private MidlevelInformationHome midlevelInformationHome;
	
	@EJB
	private TransformationLevelHome transformationLevelHome;
	
	@EJB
	private UserProfileHome userProfileHome;
	
	@EJB
	private SensingDataHome sensingDataHome;
	

	@Override
	public SensingData addSensingData(SensorService sensorService) {
		
		SensingData sensingData = new SensingData();
		
		//get sensor node
		int idSensorNode = sensorService.getSensingDataId().getSensorNodeIdSensorNode();
		SensorNode sensorNode = sensorNodeHome.findById(idSensorNode);
		
		//valores definidos por default temporariamente
		ClassificationTags cltags = classificationTagsHome.findById(1);
		sensingData.setClassificationTags(cltags);
		DataContext dt = dataContextHome.findById(1);
		sensingData.setDataContext(dt);
		MidlevelInformation mli = midlevelInformationHome.findById(1);
		sensingData.setMidlevelInformation(mli);
		sensingData.setSensorNode(sensorNode);
		TransformationLevel tl = transformationLevelHome.findById(1);
		sensingData.setTransformationLevel(tl);
		UserProfile up = userProfileHome.findById(1);
		System.out.println("UserProfile: " + up.getIdUserProfile());
		sensingData.setUserProfile(up);
				
		sensingData.setId(new SensingDataId(sensorNode.getIdSensorNode()));
		sensingData.setDescription("descriptionSensingData");
		sensingData.setResourceAddress(sensorNode.getAddress());
		sensingData.setTimeCreation("horaadefinir");
		
		sensingData = sensingDataHome.merge(sensingData);
		
		//get e actualiza o idsensing
		sensingData.getId().setIdSensing(sensingDataHome.getIdSensing(sensorNode));
		
		return sensingData;
		
	}

	@Override
	public boolean verifySensor(SensorService sensorService) {
		
		String id = sensorService.getId();
		if(sensorHome.existByID(Integer.parseInt(id))){
			return true;
			
		}
		return false;
	}
	
	@Override
	public SensorService addNewSensor(SensorService sensorService) {
		Sensor sensor = new Sensor();
		sensor.setDataPeriodicity(Integer.toString(sensorService.getPeriod()));
		sensor.setLocation("location");
		sensor.setType(sensorService.getType());
		
		sensor = sensorHome.merge(sensor);
		
		//atualiza id do sensor no sensorService
		sensorService.setSensorId(sensor.getIdSensor());
		
		return sensorService;
				
	}

	@Override
	public SensorService addSensorNode(SensorService sensorService) {
		
		//get sensor
		Sensor sensor = sensorHome.findById(sensorService.getSensorId());
		
		SensorNode sensorNode = new SensorNode();
		sensorNode.setAddress(sensorService.getIp());
		sensorNode.setDescription("descriptionSensorNode");
		sensorNode.setSensor(sensor);
		
		sensorNode = sensorNodeHome.merge(sensorNode);
		
		//atualiza valor do sensor node no sensorService
		sensorService.setSensingDataId(new SensingDataId(sensorNode.getIdSensorNode()));
		
		return sensorService;
	}

}
