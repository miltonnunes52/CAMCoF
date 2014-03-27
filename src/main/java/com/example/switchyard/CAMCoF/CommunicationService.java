package com.example.switchyard.CAMCoF;

public interface CommunicationService {
	
	JSONResponse ReceiveCommunication(JSONObject jsonObject);
	JSONResponse SendResponse();

}
