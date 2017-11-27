package com.touhid.tabulation.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.signserver.common.CryptoTokenOfflineException;
import org.signserver.common.IllegalRequestException;
import org.signserver.common.SignServerException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.touhid.tabulation.model.Department;
import com.touhid.tabulation.model.University;
import com.touhid.tabulation.service.DatabaseService;
import com.touhid.tabulation.service.DeleteFile;
import com.touhid.tabulation.service.SignedService;
import com.touhid.tabulation.service.XmlFileCreateService;
import com.touhid.tabulation.service.XmlNodeService;
import com.touhid.tabulation.service.XmlTrimService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignStoreController implements Initializable {

	private University university;
	private boolean isSignedByTabulatorOne;
	private boolean isSignedByTabulatorTwo;
	private boolean isSignedByChairman;

	@FXML
	public Label lbSignedTabulatorOne;
	@FXML
	public Label lbSignedTabulatorTwo;
	@FXML
	public Label lbSignedChairman;

	@FXML
	public Label lbStoredStatus;

	@FXML
	public Button btTabulatorOneSigned;
	@FXML
	public Button btTabulatorTwoSigned;

	@FXML
	public Button btChairmanSigned;

	private static final String originalFilePath = "src/com/touhid/tabulation/temp/tabulator_original.xml";
	private static final String signedFilePath = "src/com/touhid/tabulation/temp/tabulator_signed.xml";
	private static final String trimFileFilePath = "src/com/touhid/tabulation/temp/tabulator_original_trim.xml";
	private static final String tabulatorOneSigner = "XMLSigner";
	private static final String tabulatorTwoSigner = "XMLSigner2";
	private static final String chairmanSigner = "XMLSigner3";

	private Node tabolatorOneSignedNode;
	private Node tabolatorTwoSignedNode;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;

		// deleting all file and store them
		deleteFile();

		XmlFileCreateService.generateXml(university, originalFilePath);

		try {
			Files.copy(Paths.get(originalFilePath), Paths.get(signedFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			XmlTrimService.trim(originalFilePath, trimFileFilePath);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void signByTabulatorOne(ActionEvent event) {

		try {
			XmlTrimService.trim(originalFilePath, trimFileFilePath);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		isSignedByTabulatorOne = SignedService.signed(trimFileFilePath, tabulatorOneSigner);
		if (isSignedByTabulatorOne) {

			try {
				Node node = XmlNodeService.removeNode(trimFileFilePath, "Signature");
				tabolatorOneSignedNode = node;
				if (node != null) {
					XmlNodeService.addNode(signedFilePath, node, "Jahangirnagar_university");
					XmlNodeService.changeTagName(signedFilePath, "Jahangirnagar_university", "Signature",
							"Tabulator_1");
					lbSignedTabulatorOne.setText("SIGNED");
					DeleteFile.deleteFile(trimFileFilePath);

				}
			} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void signByTabulatorTwo(ActionEvent event) throws IllegalRequestException, CryptoTokenOfflineException, SignServerException {

		// test if signed by tabolatorOne

		boolean isSignedByOne=false;
		
		
		if(tabolatorOneSignedNode!=null){
			 isSignedByOne = isSignedBy(tabolatorOneSignedNode, tabulatorOneSigner,"Tabulator_1");	
		}
		
		

		if (isSignedByOne) {

			try {
				XmlTrimService.trim(originalFilePath, trimFileFilePath);
			} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			isSignedByTabulatorTwo = SignedService.signed(trimFileFilePath, tabulatorTwoSigner);
			if (isSignedByTabulatorTwo) {

				try {
					Node node = XmlNodeService.removeNode(trimFileFilePath, "Signature");
					tabolatorTwoSignedNode = node;
					if (node != null) {
						XmlNodeService.addNode(signedFilePath, node, "Jahangirnagar_university");
						XmlNodeService.changeTagName(signedFilePath, "Jahangirnagar_university", "Signature",
								"Tabulator_2");
						lbSignedTabulatorTwo.setText("SIGNED");

					}
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		else{
			
			Alert alert = new Alert(AlertType.WARNING);
 			alert.setTitle("Warning Dialog");
 			alert.setHeaderText("Warning Dialog");
 			alert.setContentText("Can not sign .Please Sign first by Tabulator 1");

 			alert.showAndWait();
		}

	}

	public void signByChairman(ActionEvent event) throws IllegalRequestException, CryptoTokenOfflineException, SignServerException {

		boolean isSignedByTwo=false;
		if(tabolatorTwoSignedNode!=null){
			isSignedByTwo = isSignedBy(tabolatorTwoSignedNode, tabulatorTwoSigner,"Tabulator_2");	
		}
		 
		//System.out.println("Is two signed:"+isSignedByTwo);
		if(isSignedByTwo){
			
			try {
				XmlTrimService.trim(originalFilePath, trimFileFilePath);
			} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			isSignedByChairman = SignedService.signed(trimFileFilePath, chairmanSigner);

			if (isSignedByChairman) {

				try {
					Node node = XmlNodeService.removeNode(trimFileFilePath, "Signature");

					if (node != null) {
						XmlNodeService.addNode(signedFilePath, node, "Jahangirnagar_university");
						XmlNodeService.changeTagName(signedFilePath, "Jahangirnagar_university", "Signature",
								"ExamCommittee_Chairman");
						lbSignedChairman.setText("SIGNED");

					}
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
 			alert.setTitle("Warning Dialog");
 			alert.setHeaderText("Warning Dialog");
 			alert.setContentText("Can not sign .Please Sign first by Tabulator 2");

 			alert.showAndWait();
		}

	}

	public void store(ActionEvent event) {

		Department department = university.getDepartment();
		final String fileName = department.getSerialNo() + "_" + department.getEducationYear() + "_"
				+ department.getClassRoll() + "_" + department.getExamRoll();
		boolean isSaved = DatabaseService.storeXmlFile(SignStoreController.signedFilePath, fileName);

		if (isSaved) {
			lbStoredStatus.setText("STORED");
			deleteFile();
		}
	}

	public void goHome(ActionEvent event) throws IOException {

		((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();

		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();

		Pane root = loader.load(getClass().getResource("/com/touhid/tabulation/design/Main.fxml").openStream());

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/touhid/tabulation/design/Main.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void deleteFile() {
		try {
			Files.deleteIfExists(Paths.get(trimFileFilePath));
			Files.deleteIfExists(Paths.get(originalFilePath));
			Files.deleteIfExists(Paths.get(signedFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isSignedBy(Node node, String signer,String tabulatorName) throws IllegalRequestException, CryptoTokenOfflineException, SignServerException {

		try {
			
			XmlNodeService.addNode(originalFilePath, node, "Jahangirnagar_university");
			
			XmlTrimService.trim(originalFilePath, trimFileFilePath);

			boolean isSigned = false;
			 isSigned = SignedService.isValid(trimFileFilePath);
			XmlNodeService.removeNode(originalFilePath, "Signature");
			
			//System.out.println("Signnnnnnn:"+isSigned);
			return isSigned;
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}
