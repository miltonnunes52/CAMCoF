package com.example.switchyard.CAMCoF.CommunicationServices;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.solder.servlet.http.ContextPath;

import com.example.switchyard.CAMCoF.CommunicationServices.Objects.*;



@Path("/send")
public interface EndPointServiceResource {


	
	@POST
	@ContextPath
	@Path("{id}/{sensorid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	DataResponse receiveData(@PathParam("id") String id,@PathParam("sensorid") String sensorid, String data);
	
	
	@POST
	@Path("/connect")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	SensorServiceResponse connectService(SensorService sensorService);
	
	@POST
	@Path("/teste")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	StatusResponse camcofPongProvisorio(StatusRequest statusRequest);

	@POST
	@Path("/teste1")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	StatusResponse camcofPongProvisorio1(StatusRequest statusRequest);


}
