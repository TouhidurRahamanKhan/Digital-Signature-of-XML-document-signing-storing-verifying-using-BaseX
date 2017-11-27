package com.touhid.tabulation.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlNodeService {

	
	private static String getHtmlBodyAsString(final Document doc) throws TransformerException {
	    StringWriter writer = new StringWriter();
	    StreamResult result = new StreamResult(writer);

	    Node body = (Node) doc.getElementsByTagName("signature");
	    doc.renameNode(body, "", "div");

	    javax.xml.transform.dom.DOMSource domSource = new javax.xml.transform.dom.DOMSource(body);
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.transform(domSource, result);

	    final String content = writer.toString();
	    System.out.println(content);
	    return content;
	}
	
	
	public static  void changeTagName(String filePath, String tag, String fromTag, String toTag) throws ParserConfigurationException, SAXException, IOException, TransformerException {
	  
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
		        .newDocumentBuilder();
		 Document doc=builder.parse(new InputSource(filePath));
		
		
		NodeList nodes = doc.getElementsByTagName(fromTag);
	    
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	 
	        if (nodes.item(i) instanceof Element) {
	            Element elem = (Element)nodes.item(i);
	           
	            doc.renameNode(elem, elem.getNamespaceURI(), toTag);
	        }
	    }
	    
	    
	    File file=new File(filePath);
		if(file.exists()){
			//System.out.println("File  found:"+file.getAbsolutePath());
			doc.getDocumentElement().normalize();
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(file.toURI().getPath());
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, result);
	        //System.out.println("XML file updated successfully");

		}
	}
	
	
	
	public static  Node removeNode(String filePath, String tagName) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		 
		
		Document doc = DocumentBuilderFactory.newInstance()
		        .newDocumentBuilder().parse(new InputSource(filePath));
		
		Element element = (Element) doc.getElementsByTagName(tagName).item(0);

		if(element==null){
			return null;
		}
		
		// remove the specific node
		Node node=element.getParentNode().removeChild(element);
		doc.normalize();
		
		
		
		File file=new File(filePath);
		if(file.exists()){
			//System.out.println("File  found:"+file.getAbsolutePath());
			doc.getDocumentElement().normalize();
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(file.toURI().getPath());
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, result);
	       // System.out.println("XML file updated successfully");

		}
        
        
		
		return node;
	}
	
	

	public static  void addNode(String filePath, Node node,String tag) throws SAXException, IOException, ParserConfigurationException, TransformerException {
	   
		
		Document doc = DocumentBuilderFactory.newInstance()
		        .newDocumentBuilder().parse(new InputSource(filePath));
		
		
		Node  element = (Element) doc.getElementsByTagName(tag).item(0);
		Node node2=doc.importNode(node, true);
		element.appendChild(node2);
		
		
		File file=new File(filePath);
		if(file.exists()){
			//System.out.println("File  found:"+file.getAbsolutePath());
			doc.getDocumentElement().normalize();
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(file.toURI().getPath());
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, result);
	       // System.out.println("XML file updated successfully");

		}
		
		
		
	}
	
	
	
	

}
