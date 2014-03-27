package com.example.switchyard.CAMCoF;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/send")
public interface CommunicationServiceResource {

	@POST
	@Path("/data")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	JSONResponse ReceiveCommunication(JSONObject jsonObject);


}
