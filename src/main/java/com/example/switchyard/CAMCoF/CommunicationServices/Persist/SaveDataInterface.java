package com.example.switchyard.CAMCoF.CommunicationServices.Persist;

import com.example.switchyard.CAMCoF.CommunicationServices.Objects.DataObject;

public interface SaveDataInterface {
	
	public DataObject persistData(DataObject dataObject);
	public DataObject processData(DataObject dataObject);
	public void persistProcessedData(DataObject dataObject);
	
}
