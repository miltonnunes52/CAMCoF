package com.example.switchyard.CAMCoF.CommunicationServices;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;

import com.entities.ProfileHome;
import com.entities.SensingData;
import com.entities.SensingDataHome;
import com.entities.SensingDataId;
import com.entities.SensorHome;
import com.entities.UserProfileHome;
import com.example.switchyard.CAMCoF.CommunicationServices.ReadDataService;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.*;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

@Stateless
@Service(ReadDataService.class)
public class ReadDataServiceBean implements ReadDataService {
	
	@EJB
    private ProfileHome profileHome;
	
	@EJB
    private UserProfileHome userProfileHome;
	
	@EJB
    private SensorHome sensorHome;
	
	@EJB
    private SensingDataHome sensingDataHome;
	
	@Inject
	@Reference
	private SaveDataService saveDataService;
	
	@Inject
	@Reference
	private SaveSensingService saveSensingService;

	
	private ArrayList<SensorService> serviceList = new ArrayList<SensorService>();

	
	@Override
	public DataResponse receiveData(DataObject dataObject){
		DataResponse response;
		
		System.out.println(dataObject.getId() + " " + dataObject.getSensorid() + " " + dataObject.getData());
		
		if(existSensorService(dataObject.getId(), dataObject.getSensorid())){
			
			//gravar dados na BD
			SensorService sensorServ = getSensorService(dataObject.getId(), dataObject.getSensorid());
			dataObject.setSensingDataId(sensorServ.getSensingDataId());
			saveDataService.saveData(dataObject);

			response = new DataResponse("200 - Accepted");	
		}
		else{
			response = new DataResponse("403 - Forbidden");
		}
		
		return response;		
	}

	

	@Override
	public SensorServiceResponse connectService(SensorService sensorService) {
		SensorServiceResponse serviceResponse;
		
		if(!userProfileHome.existByID(sensorService.getId())){
			serviceResponse = new SensorServiceResponse("409", "Conflict - Invalid username"); 	
		}
		else if(existSensorService(sensorService)){
			serviceResponse = new SensorServiceResponse("409", "Conflict - Service already exists"); 
			System.out.println("conflict 409");
		}
		else{
			serviceResponse = new SensorServiceResponse("200", "islab.di.uminho.pt:36001/CAMCoF/send/" + sensorService.getId() + "/" + sensorService.getSensorid());
			
			//gravar dados na BD	
			SensingData sensingData = saveSensingService.saveData(sensorService);
			sensorService.setSensingDataId(sensingData.getId());
			
			serviceList.add(sensorService);
			printServicesList();
			
		}
		return serviceResponse;
	}
	
	
	
	//periodicamente verifica quem esta connectado
	@Override
	public void camcofPing(){

		for(int k=0; k<serviceList.size(); k++) {
		
			String targetURL = serviceList.get(k).getIp();

			try {

				URL targetUrl = new URL(targetURL);
				HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
				httpConnection.setDoOutput(true);
				httpConnection.setRequestMethod("POST");
				httpConnection.setRequestProperty("Content-Type", "application/json");
				httpConnection.setConnectTimeout(10000);
				httpConnection.setReadTimeout(10000);
			
				StatusRequest statusRequest = new StatusRequest(serviceList.get(k).getId(), serviceList.get(k).getSensorid(), "status");
			
				OutputStream outputStream = httpConnection.getOutputStream();
			
				outputStream.write(statusRequest.toString().getBytes());
				outputStream.flush();

				if (httpConnection.getResponseCode() != 200) {
					SensingDataId sensingdataid = serviceList.get(k).getSensingDataId();
					SensingData sensingData = sensingDataHome.findById(sensingdataid);
					sensingData.setTimeEnd(new Date().toString());
					sensingDataHome.merge(sensingData);
					
					serviceList.remove(serviceList.get(k));	
					k--;
					System.out.println("servico eliminado " + "Failed : HTTP error code : "
							+ httpConnection.getResponseCode());
					continue;
					
				}

				BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
					(httpConnection.getInputStream())));

			
				String output;
				StatusResponse statusResponse = new StatusResponse();
				System.out.println("Output from Server:");
				while ((output = responseBuffer.readLine()) != null) {
					statusResponse = new ObjectMapper().readValue(output, StatusResponse.class);
					System.out.println(statusResponse.getId() + " " + statusResponse.getSensorid() + " " + statusResponse.getResponse());
				}

				httpConnection.disconnect();
				
				if(!statusResponse.getResponse().equals("200")){
					SensingDataId sensingdataid = serviceList.get(k).getSensingDataId();
					SensingData sensingData = sensingDataHome.findById(sensingdataid);
					sensingData.setTimeEnd(new Date().toString());
					sensingDataHome.merge(sensingData);
					
					serviceList.remove(serviceList.get(k));		
					System.out.println("servico eliminado");
					k--;
				}

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}	 	
	
	
	//resposta temporaria ao ping - apenas para testar
	@Override
	public StatusResponse camcofPongProvisorio(StatusRequest statusRequest){

		System.out.println(statusRequest.getId() + " " + statusRequest.getStatus()  + " " + statusRequest.getSensorid());
		return new StatusResponse(statusRequest.getId(), statusRequest.getSensorid(), "200");
		
	}
	//resposta temporaria ao ping - apenas para testar
	@Override
	public StatusResponse camcofPongProvisorio1(StatusRequest statusRequest){

		System.out.println(statusRequest.getId() + " " + statusRequest.getStatus()  + " " + statusRequest.getSensorid());
		return new StatusResponse(statusRequest.getId(), statusRequest.getSensorid(), "201");
		
	}
	
	//Funcoes auxiliares
	
	
	//Compara servicos mac address e id de sensor com existentes
	public boolean existSensorService(SensorService sensorService){
		for (SensorService s : serviceList) {
	        if (s.getId().equals(sensorService.getId()) && s.getSensorid().equals(sensorService.getSensorid())) {
	            return true;
	        }
	    }
		return false;		
	}
	
	//Compara servicos mac address e id de sensor com existentes
	public boolean existSensorService(String id, String sensorid){
		for (SensorService s : serviceList) {
	        if (s.getId().equals(id) && s.getSensorid().equals(sensorid)) {
	            return true;
	        }
	    }
		return false;		
	}
	
	//get sensor service by id and id sensor
	public SensorService getSensorService(String id, String sensorid){
		for (SensorService s : serviceList) {
	        if (s.getId().equals(id) && s.getSensorid().equals(sensorid)) {
	            return s;
	        }
	    }
		return null;		
	}
	
	public void printServicesList(){
		System.out.println("lista de servicos");
		for(int i = 0; i<serviceList.size(); i++){
			System.out.println(serviceList.get(i).getId());
		}
		
	}
		
	
	

}
