package com.example.switchyard.CAMCoF.CommunicationServices.Persist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;

import org.switchyard.component.bean.Service;

import com.entities.Metrics;
import com.entities.MetricsHome;
import com.entities.MidlevelInformation;
import com.entities.MidlevelInformationHome;
import com.entities.SensingData;
import com.entities.SensingDataHome;
import com.entities.SensingDataId;
import com.entities.SensingDataValue;
import com.entities.SensingDataValueHome;
import com.entities.TransformationLevelHome;
import com.example.switchyard.CAMCoF.CommunicationServices.Objects.DataObject;

@Service(SaveDataInterface.class)
public class SaveDataBean implements SaveDataInterface {

	@EJB
	private SensingDataValueHome sensingDataValueHome;
	
	@EJB
	private SensingDataHome sensingDataHome;

	@EJB
	private MidlevelInformationHome midlevelInformationHome;

	@EJB
	private MetricsHome metricsHome;
	
	@EJB
	private TransformationLevelHome transformationLevelHome;
	
	
	@Override
	public DataObject persistData(DataObject dataObject) {
		
		SensingDataValue sensingDataValue = new SensingDataValue();
		//get id sensing data respectivo
		SensingDataId sensingDataId = dataObject.getSensingDataId();
		SensingData sensingData = sensingDataHome.findById(sensingDataId);

		sensingDataValue.setSensingData(sensingData);
		sensingDataValue.setValue(dataObject.getData());
		sensingDataValue.setValueAddress(sensingData.getResourceAddress());

		sensingDataValueHome.merge(sensingDataValue);
		//value units?!
		
		return dataObject;
	}


	
	@Override
	public DataObject processData(DataObject dataObject) {
		
		//get id sensing data respectivo
		SensingDataId sensingDataId = dataObject.getSensingDataId();
		SensingData sensingData = sensingDataHome.findById(sensingDataId);	
		
		List<Metrics> metricsList = metricsHome.findByType(sensingData.getSensorNode().getSensor().getType());

		Map<String, String> metricsResult = new HashMap<String, String>();
		
		//get de todos os sensing data values
		String sensingDataValues = "";

		for(String st : sensingDataValueHome.getDataValues(sensingDataId)){
			sensingDataValues = sensingDataValues.concat(st.concat("\n"));
		}
		
		for(Metrics metric : metricsList){
		
			try {
	
				URL targetUrl = new URL(metric.getUrl());
				HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
				httpConnection.setDoOutput(true);
				httpConnection.setRequestMethod("POST");
				httpConnection.setRequestProperty("Content-Type", "text/plain");
				httpConnection.setConnectTimeout(10000);
				httpConnection.setReadTimeout(10000);
			
				OutputStream outputStream = httpConnection.getOutputStream();
				
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
				
				metricsResult.put(metric.getIdMetrics().toString(), output);
				dataObject.setMetricsResults(metricsResult);
	
				} catch (MalformedURLException e) {
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			
		}
		
		return dataObject;

	}


	@Override
	public void persistProcessedData(DataObject dataObject) {
		
		//get id sensing data respectivo
		SensingDataId sensingDataId = dataObject.getSensingDataId();
		SensingData sensingData = sensingDataHome.findById(sensingDataId);
		
		Set<MidlevelInformation> midlevelInformations = sensingData.getMidlevelInformations();
		
		for(MidlevelInformation midlevelinformation : midlevelInformations){
			midlevelinformation.setDescription(midlevelinformation.getMetrics().getDescription());
			midlevelinformation.setFeature(dataObject.getMetricsResults().get(midlevelinformation.getMetrics().getIdMetrics().toString()));
		}
		sensingData.setMidlevelInformations(midlevelInformations);
		sensingData.setTransformationLevel(transformationLevelHome.findById(2));
		
		sensingData = sensingDataHome.merge(sensingData);
		
	}
	
	
	

}
