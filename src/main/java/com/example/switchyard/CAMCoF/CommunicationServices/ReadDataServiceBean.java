
package com.example.switchyard.CAMCoF.CommunicationServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.example.switchyard.CAMCoF.CommunicationServices.Objects.*;

import org.switchyard.component.bean.Service;

@Service(ReadDataService.class)
public class ReadDataServiceBean implements ReadDataService {
	
	private ArrayList<SensorService> serviceList = new ArrayList<SensorService>();

	@Override
	public DataResponse receiveData(DataObject dataObject){
		DataResponse response;
		
		System.out.println(dataObject.getId() + " " + dataObject.getType() + " " + dataObject.getData());
		
		if(existSensorService(dataObject.getId(), dataObject.getType())){
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
			serviceList.add(sensorService);
			printServicesList();

		}
		return serviceResponse;
	}
	
	
	
	//periodicamente verifica quem está connectado
	@Override
	public void camcofPing(){

		String targetURL = "http://127.0.0.1:8080/CAMCoF/send/teste";

		//METER AQUI O CICLO PARA PERCORRER TODOS OS SERVIÇOS
		//E meter o que recebe tb num objecto e validar a resposta, eliminando serviços inactivos
		
		try {

			URL targetUrl = new URL(targetURL);

			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");

			StatusRequest statusRequest = new StatusRequest("id", "type", "request");
			
			
			OutputStream outputStream = httpConnection.getOutputStream();

			
			
			outputStream.write(statusRequest.toString().getBytes());
			outputStream.flush();
			

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
					(httpConnection.getInputStream())));

			
			String output;
			System.out.println("Output from Server:");
			while ((output = responseBuffer.readLine()) != null) {
				System.out.println(output);
			}

			httpConnection.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }

	}	 	
	
	
	//resposta temporaria ao ping
	@Override
	public StatusResponse camcofPongProvisorio(StatusRequest statusRequest){

		System.out.println(statusRequest.getId() + " " + statusRequest.getStatus()  + " " + statusRequest.getType());
		return new StatusResponse(statusRequest.getId(), statusRequest.getType(), "200");
		
	}
	
	
	//Funções auxiliares
	
	
	//Compara serviços mac address e tipo de sensor com existentes
	public boolean existSensorService(SensorService sensorService){
		for (SensorService s : serviceList) {
	        if (s.getId().equals(sensorService.getId()) && s.getType().equals(sensorService.getType())) {
	            return true;
	        }
	    }
		return false;		
	}
	
	//Compara serviços mac address e tipo de sensor com existentes
	public boolean existSensorService(String id, String type){
		for (SensorService s : serviceList) {
	        if (s.getId().equals(id) && s.getType().equals(type)) {
	            return true;
	        }
	    }
		return false;		
	}
	
	public void printServicesList(){
		System.out.println("lista de servicos");
		for(int i = 0; i<serviceList.size(); i++){
			System.out.println(serviceList.get(i).getId());
		}
		
	}
		
	
	

}
