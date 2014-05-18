package com.example.switchyard.CAMCoF.CommunicationServices.Persist;



import java.util.HashSet;
import java.util.Set;

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
		
		//new sensor node
		
		System.out.println("SaveSesingBean!!!!!!!!!!!   add sensor node");
		
		
		
		Sensor sensor = sensorHome.findById(Integer.parseInt(sensorService.getId()));
		System.out.println("sensor : " + sensor.getIdSensor()+ sensor.getType());
		
		SensorNode sensorNode = new SensorNode();
		sensorNode.setAddress(sensorService.getIp());
		sensorNode.setDescription("descriptionSensorNode");
		sensorNode.setSensor(sensor);
		
		
		
		sensorNode = sensorNodeHome.merge(sensorNode);
		
		
		
		//new sensing data
		
		SensingData sensingData = new SensingData();
		
		//valores definidos por default
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
		
		//get idsensing e actualiza no idsensing
		sensingData.getId().setIdSensing(sensingDataHome.getIdSensing(sensorNode));
		
		
		
		
		System.out.println("sensing data id no bean: " + sensingData.getId().getIdSensing() + " " + sensingData.getId().getSensorNodeIdSensorNode());
		
		
		System.out.println("sensing data adicionado");
		
		return sensingData;
				
		
	}







	@Override
	public boolean verifySensor(SensorService sensorService) {
		
		System.out.println("SaveSesingBean!!!!!!!!!!! verify sensor");
		
		String id = sensorService.getId();
		if(sensorHome.existByID(Integer.parseInt(id))){
			return true;
			
		}
		return false;
	}







	@Override
	public SensorService addNewSensor(SensorService sensorService) {
		
		System.out.println("SaveSesingBean!!!!!!!!!!! add new sensor");
		
		Sensor sensor = new Sensor();
		
		
		//temp id do sensor e o ip - ISTE ID NAO ESTA BEM !!!!!!!!!!!
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
