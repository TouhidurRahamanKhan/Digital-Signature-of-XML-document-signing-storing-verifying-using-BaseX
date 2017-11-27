package com.touhid.tabulation.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.lowagie.text.pdf.codec.Base64.InputStream;

public class DatabaseService {

	public static boolean storeXmlFile(String path,String fileName) {
		try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin")) {

			

			session.execute("OPEN juDB");

			
			
			
			FileInputStream input = new FileInputStream(new File(path));

			
			
			session.add(fileName, input);

		
			
			return true;

		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static boolean getXmlFile(String fileName,String fileSavedPath) throws IOException {
		
		
try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin")) {

			
			String orgFileName=fileName;
			session.execute("OPEN juDB");
			
			final String  DB_NAME="juDB";
			String query="XQUERY doc('"+DB_NAME+"/"+fileName+"')";
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     session.execute(query, baos);
		    
		    // fileName = "src/com/touhid/pki/temp" + File.separator + "temp.xml";
			
		   FileOutputStream fileOutputStream=new FileOutputStream(fileSavedPath);  
           baos.writeTo(fileOutputStream);
           
			
			
			 
		   /* String dstPath="src/com/touhid/pki/temp" + File.separator +orgFileName;
		    
		    try {
				XmlTrimService.trim(fileName, dstPath);
			} catch (ParserConfigurationException e) {
				
				return false;
			} catch (SAXException e) {
				
				return false;
			} catch (TransformerException e) {
				
				return false;
			}
		    */
		    
		    if(new File(fileName).exists()){
				// DeleteFile.deleteFile(path);
			 }
		    
		    return true;
		}
		
		
		catch (Exception e) {
			return false;
		}
		

		
	}
}
