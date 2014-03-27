package com.example.switchyard.CAMCoF;

import org.switchyard.component.bean.Service;

@Service(ReadDataService.class)
public class ReadDataServiceBean implements ReadDataService {

	@Override
	public JSONResponse ReceiveCommunication(JSONObject jsonObject) {
		
		
		return SendResponse();
		
	}

	@Override
	public JSONResponse SendResponse() {
		
		JSONResponse response = new JSONResponse("200");
		return response;
		
	}
	
	

}
