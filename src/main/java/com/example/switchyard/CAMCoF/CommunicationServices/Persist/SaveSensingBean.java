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
		
		/*************/
		
		Sensor sensor = sensorHome.findById(13);

		
		System.out.println("sensor : " + sensor.getIdSensor()+ sensor.getType());
		
		SensorNode sensorNode1 = new SensorNode();
		sensorNode1.setAddress(sensorService.getIp());
		sensorNode1.setDescription("descriptionSensorNode");
		sensorNode1.setSensor(sensor);
		sensorNode1.setIdSensorNode(Integer.parseInt(sensorService.getType()));
		
		
		Set<SensingData> sensingDatas = new HashSet<SensingData>(0);
		sensorNode1.setSensingDatas(sensingDatas);
		
//		System.out.println("sensorNode : " + sensorNode1.getIdSensorNode()+ " " + sensorNode1.getAddress() + " " +sensorNode1.getDescription()+ " " + sensorNode1.getSensor().getIdSensor());

		

		
		sensorNode1 = sensorNodeHome.merge(sensorNode1);
		

		
		System.out.println("sensor node id: " + sensorNode1.getIdSensorNode());
		
		System.out.println("sensor node adicionado");
		/**********/
		
		
		
		SensingData sensingData = new SensingData();
		
		//valores definidos por default
		ClassificationTags cltags = classificationTagsHome.findById(1);
		sensingData.setClassificationTags(cltags);
		DataContext dt = dataContextHome.findById(1);
		sensingData.setDataContext(dt);
		MidlevelInformation mli = midlevelInformationHome.findById(1);
		sensingData.setMidlevelInformation(mli);
		//SensorNode sensorNode = sensorNodeHome.findById(Integer.parseInt(sensorService.getId()));
		sensingData.setSensorNode(sensorNode1);
		TransformationLevel tl = transformationLevelHome.findById(1);
		sensingData.setTransformationLevel(tl);
		UserProfile up = userProfileHome.findById(1);
		System.out.println("UserProfile: " + up.getIdUserProfile());
		sensingData.setUserProfile(up);
				
		
		sensingData.setId(new SensingDataId(Integer.parseInt(sensorService.getId()),sensorNode1.getIdSensorNode()));
		sensingData.setDescription("descriptionSensingData");
		sensingData.setResourceAddress(sensorNode1.getAddress());
		sensingData.setTimeCreation("horaadefinir");
				
		sensingDataHome.merge(sensingData);
		
		System.out.println("sensing data adicionado");
		
		return sensingData;
				
		
	}

//	@Override
//	public SensorNode addSensorNode(SensorService sensorService) {
//		Sensor sensor = sensorHome.findById(Integer.parseInt(sensorService.getId()));
//		
//		SensorNode sensorNode = new SensorNode();
//		sensorNode.setAddress(sensorService.getIp());
//		sensorNode.setDescription("descriptionSensorNode");
//		sensorNode.setSensor(sensor);
//		sensorNodeHome.merge(sensorNode);
//		return sensorNode;
//	}
//	
	
	
	

}
