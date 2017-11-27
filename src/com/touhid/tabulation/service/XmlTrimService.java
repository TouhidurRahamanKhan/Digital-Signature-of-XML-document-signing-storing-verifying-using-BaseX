package com.touhid.tabulation.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlTrimService {

	
	public static void trim(String srcPath,String dstPath) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, TransformerException{
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		dbfac.setIgnoringElementContentWhitespace(true);
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		
		
		Document doc = docBuilder.parse(new FileInputStream(srcPath));
		
		
		Node company = doc.getFirstChild();
		trimWhitespace(company);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		FileOutputStream fileOutputStream=new FileOutputStream(dstPath);
		StreamResult result = new StreamResult(fileOutputStream);
		transformer.transform(source, result);
		
		
	}
	
	public static void trimWhitespace(Node node)
	{
		//System.out.println("OK:"+node.getNodeName());
	    NodeList children = node.getChildNodes();
	    for(int i = 0; i < children.getLength(); ++i) {
	        Node child = children.item(i);
	        if(child.getNodeType() == Node.TEXT_NODE) {
	            child.setTextContent(child.getTextContent().trim());
	        }
	        trimWhitespace(child);
	    }
	}
}
