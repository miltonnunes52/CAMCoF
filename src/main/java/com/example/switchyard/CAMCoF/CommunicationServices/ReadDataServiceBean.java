
package com.example.switchyard.CAMCoF.CommunicationServices;

import java.util.ArrayList;

import com.example.switchyard.CAMCoF.CommunicationServices.Objects.*;

import org.switchyard.component.bean.Service;

@Service(ReadDataService.class)
public class ReadDataServiceBean implements ReadDataService {
	
	private ArrayList<SensorService> serviceList = new ArrayList<SensorService>();

	@Override
	public DataResponse receiveData(DataObject dataObject){
		
		//um if para confirmar que os dados vem de um serviço connectado
		//else resposta a mandar foder
		System.out.println(dataObject.getId() + " " + dataObject.getData());
		
		DataResponse response = new DataResponse("200");
		return response;		
	}

	

	//Alterar comparação do serviço recebido com os existentes
	@Override
	public SensorServiceResponse connectService(SensorService sensorService) {
		SensorServiceResponse serviceResponse;
		
		
		if(existSensorService(sensorService)){
			serviceResponse = new SensorServiceResponse("409", "Conflict - Service already exists"); 
			System.out.println("conflict 409");
		}
		else{
			serviceResponse = new SensorServiceResponse("200", "islab.di.uminho.pt/CAMCoF/send/data");
			serviceList.add(sensorService);
			printServicesList();

		}
		return serviceResponse;
	}
	
	
	
	//periodicamente verifica quem está connectado
	@Override
	public void camcofPing(){
		System.out.println("ping");
		
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
	
	
	public void printServicesList(){
		System.out.println("lista de servicos");
		for(int i = 0; i<serviceList.size(); i++){
			System.out.println(serviceList.get(i).getId());
		}
		
	}
		
	
	

}
