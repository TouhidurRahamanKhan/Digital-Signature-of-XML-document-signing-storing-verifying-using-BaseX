package com.touhid.tabulation.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.X509Certificate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.signserver.client.api.ISigningAndValidation;
import org.signserver.client.api.SigningAndValidationWS;
import org.signserver.common.CryptoTokenOfflineException;
import org.signserver.common.GenericSignResponse;
import org.signserver.common.GenericValidationResponse;
import org.signserver.common.IllegalRequestException;
import org.signserver.common.SignServerException;
import org.xml.sax.SAXException;

import com.touhid.tabulation.controller.SearchFileController;

public class SignedService {

	public static boolean signed(String path,String signer) {

		int i = 0;

		StringBuffer sb = new StringBuffer();

		try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {

			while ((i = inputStream.read()) != -1) {
				// System.out.print((char) i);
				char c = (char) i;
				sb.append(c);
			}

			// System.out.println(sb.toString());

			System.setProperty("javax.net.ssl.trustStore", "truststore.jks");
			// System.setProperty("javax.net.ssl.trustStorePassword",
			// "changeit");

			ISigningAndValidation signserver = new SigningAndValidationWS("localhost", 8442, true);

			GenericSignResponse signResp = signserver.sign(signer, sb.toString().getBytes());
			byte[] signed = signResp.getProcessedData();
			// System.out.println("Signed: " + new String(signed));

			try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(path)))) {
				outputStream.write(signed);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} catch (IOException e1) {

			//e1.printStackTrace();
			return false;
		} catch (IllegalRequestException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			return false;
		} catch (CryptoTokenOfflineException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			return false;
		} catch (SignServerException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			return false;
		}
		
	}

	public static boolean isValid(String path)
			{

		int i = 0;
		System.out.println("Val:" + path);
		StringBuffer sb = new StringBuffer();

		try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {

			while ((i = inputStream.read()) != -1) {
				// System.out.print((char) i);
				char c = (char) i;
				sb.append(c);
			}

			// System.out.println(sb.toString());

			System.setProperty("javax.net.ssl.trustStore", "truststore.jks");
			// System.setProperty("javax.net.ssl.trustStorePassword",
			// "changeit");

			ISigningAndValidation signserver = new SigningAndValidationWS("localhost", 8442, true);

			byte[] signed = sb.toString().getBytes();

			// Validating
			GenericValidationResponse validateResp = signserver.validate("DemoXMLValidator", signed);
			System.out.println("Valid: " + validateResp.isValid());

			if (validateResp.getSignerCertificate() != null) {
				if (validateResp.getSignerCertificate() instanceof X509Certificate) {
					X509Certificate signerCert = (X509Certificate) validateResp.getSignerCertificate();
					System.out.println("Signed by: " + signerCert.getSubjectDN().getName());
					return true;
				}
			}
		} catch (IOException | IllegalRequestException | CryptoTokenOfflineException | SignServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	
	
	
	
	

}