package com.example.switchyard.CAMCoF.CommunicationServices;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;

import com.entities.ProfileHome;
import com.entities.SensingData;
import com.entities.SensorHome;
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
    private SensorHome sensorHome;
	
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
		
		System.out.println(dataObject.getId() + " " + dataObject.getType() + " " + dataObject.getData());
		
		if(existSensorService(dataObject.getId(), dataObject.getType())){
			
			//gravar dados na BD
			SensorService sensorServ = getSensorService(dataObject.getId(), dataObject.getType());
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
		
		
		if(existSensorService(sensorService)){
			serviceResponse = new SensorServiceResponse("409", "Conflict - Service already exists"); 
			System.out.println("conflict 409");
		}
		else{
			serviceResponse = new SensorServiceResponse("200", "islab.di.uminho.pt/CAMCoF/send/" + sensorService.getId() + "/" + sensorService.getType());
			
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
			//String targetURL = "http://127.0.0.1:8080/CAMCoF/send/teste";

			try {

				URL targetUrl = new URL(targetURL);
				HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
				httpConnection.setDoOutput(true);
				httpConnection.setRequestMethod("POST");
				httpConnection.setRequestProperty("Content-Type", "application/json");
				httpConnection.setConnectTimeout(10000);
				httpConnection.setReadTimeout(10000);
			
				StatusRequest statusRequest = new StatusRequest(serviceList.get(k).getId(), serviceList.get(k).getType(), "status");
			
				OutputStream outputStream = httpConnection.getOutputStream();
			
				outputStream.write(statusRequest.toString().getBytes());
				outputStream.flush();

				if (httpConnection.getResponseCode() != 200) {
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
					System.out.println(statusResponse.getId() + " " + statusResponse.getType() + " " + statusResponse.getResponse());
				}

				httpConnection.disconnect();
				
				if(!statusResponse.getResponse().equals("200")){
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
	
	
	//resposta temporaria ao ping
	@Override
	public StatusResponse camcofPongProvisorio(StatusRequest statusRequest){

		System.out.println(statusRequest.getId() + " " + statusRequest.getStatus()  + " " + statusRequest.getType());
		return new StatusResponse(statusRequest.getId(), statusRequest.getType(), "200");
		
	}
	//resposta temporaria ao ping
	@Override
	public StatusResponse camcofPongProvisorio1(StatusRequest statusRequest){

		System.out.println(statusRequest.getId() + " " + statusRequest.getStatus()  + " " + statusRequest.getType());
		return new StatusResponse(statusRequest.getId(), statusRequest.getType(), "201");
		
	}
	
	//Funcoes auxiliares
	
	
	//Compara servicos mac address e tipo de sensor com existentes
	public boolean existSensorService(SensorService sensorService){
		for (SensorService s : serviceList) {
	        if (s.getId().equals(sensorService.getId()) && s.getType().equals(sensorService.getType())) {
	            return true;
	        }
	    }
		return false;		
	}
	
	//Compara servicos mac address e tipo de sensor com existentes
	public boolean existSensorService(String id, String type){
		for (SensorService s : serviceList) {
	        if (s.getId().equals(id) && s.getType().equals(type)) {
	            return true;
	        }
	    }
		return false;		
	}
	
	//get sensor service by id and type
	public SensorService getSensorService(String id, String type){
		for (SensorService s : serviceList) {
	        if (s.getId().equals(id) && s.getType().equals(type)) {
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
