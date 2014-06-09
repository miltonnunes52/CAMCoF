package com.example.switchyard.CAMCoF.CommunicationServices.Persist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.EJB;

import org.codehaus.jackson.map.ObjectMapper;
import org.switchyard.component.bean.Service;

import com.entities.MidlevelInformation;
import com.entities.MidlevelInformationHome;
import com.entities.SensingData;
import com.entities.SensingDataHome;
import com.entities.SensingDataId;
import com.entities.SensingDataValue;
import com.entities.SensingDataValueHome;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.DataObject;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.StatusRequest;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.StatusResponse;

@Service(SaveDataInterface.class)
public class SaveDataBean implements SaveDataInterface {

	@EJB
	private SensingDataValueHome sensingDataValueHome;
	
	@EJB
	private SensingDataHome sensingDataHome;

	@EJB
	private MidlevelInformationHome midlevelInformationHome;
	
	@Override
	public DataObject persistData(DataObject dataObject) {
		
		SensingDataValue sensingDataValue = new SensingDataValue();
		//get id sensing data respectivo
		SensingDataId sensingDataId = dataObject.getSensingDataId();
		SensingData sensingData = sensingDataHome.findById(sensingDataId);

		sensingDataValue.setSensingData(sensingData);
		sensingDataValue.setValue(dataObject.getData());
		sensingDataValue.setValueAddress("valueaddress");

		sensingDataValueHome.merge(sensingDataValue);
		//value units?!	de
		
		return dataObject;
	}


	
	@Override
	public DataObject processData(DataObject dataObject) {
		String targetURL = "http://islab.di.uminho.pt:36001/MetricsReport/rest/Metrics/";
		

		
		if(dataObject.getType().equals("keyboard")){
			targetURL = targetURL.concat("keydowntime");
		}
		else if(dataObject.getType().equals("mouse")){
			targetURL = targetURL.concat("mousevelocity");
		}
		
		
		try {

			URL targetUrl = new URL(targetURL);
			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "text/plain");
			httpConnection.setConnectTimeout(10000);
			httpConnection.setReadTimeout(10000);
		
		
			OutputStream outputStream = httpConnection.getOutputStream();
		
			
			//get de todos os sensing data values
			String sensingDataValues = "";
			SensingDataId sensingDataId = dataObject.getSensingDataId();
			SensingData sensingData = sensingDataHome.findById(sensingDataId);
			for(SensingDataValue sensingDataValue : sensingData.getSensingDataValues()){
				sensingDataValues = sensingDataValues.concat(sensingDataValue.getValue()).concat("\n");
			}
			
			System.out.println("input server: " + sensingDataValues);
			outputStream.write(sensingDataValues.getBytes());
			outputStream.flush();

			

			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
				(httpConnection.getInputStream())));

		
			String output;
			System.out.println("Output from Server:");
			output = responseBuffer.readLine();
			System.out.println(output);
			
			httpConnection.disconnect();
			
			dataObject.setData(output);
			return dataObject;
			

			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	}


	@Override
	public void persistProcessedData(DataObject dataObject) {
		
		//get id sensing data respectivo
		SensingDataId sensingDataId = dataObject.getSensingDataId();
		SensingData sensingData = sensingDataHome.findById(sensingDataId);
	
		MidlevelInformation midLevelInformation = sensingData.getMidlevelInformation();
		
		midLevelInformation.setDescription(dataObject.getData());
		
		midlevelInformationHome.merge(midLevelInformation);
		
	}
	
	
	

}
