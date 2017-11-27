package com.touhid.tabulation.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


import com.touhid.tabulation.model.University;

public class XmlFileCreateService {

	public static boolean generateXml(University university, String filePath) {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(University.class);

			Marshaller jaxMarshaller = jaxbContext.createMarshaller();

			jaxMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema/ju.xsd");

			
			
			//System.out.println(filePath);
			jaxMarshaller.marshal(university, new File(filePath));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	
}
