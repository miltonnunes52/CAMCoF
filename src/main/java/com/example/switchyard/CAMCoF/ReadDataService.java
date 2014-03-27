package com.example.switchyard.CAMCoF;

public interface ReadDataService {
	
	public JSONResponse ReceiveCommunication(JSONObject jsonObject);
	public JSONResponse SendResponse();

}
