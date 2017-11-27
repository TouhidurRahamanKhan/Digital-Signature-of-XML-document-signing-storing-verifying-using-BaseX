package com.touhid.tabulation.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.touhid.tabulation.model.University;
import com.touhid.tabulation.service.DatabaseService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchFileController implements Initializable {

	@FXML
	public TextField tfSerialNo;
	@FXML
	public TextField tfEducationYear;
	@FXML
	public TextField tfExamRoll;
	@FXML
	public TextField tfClassRoll;

	public static final String dbOriginalFilePath = "src/com/touhid/tabulation/temp/db_originalFile.xml";
	public static final String dbTrim = "src/com/touhid/tabulation/temp/db_trim.xml";
	public static final String dbOriginalSignedFilePath = "src/com/touhid/tabulation/temp/db_originalSignedFile.xml";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void search(ActionEvent event) throws IOException, JAXBException {

		
        try {
			deleteFile();
			
			String serialNo = tfSerialNo.getText();
			String educationYear = tfEducationYear.getText();
			String examRoll = tfExamRoll.getText();
			String classRoll = tfClassRoll.getText();

			final String fileName = serialNo + "_" + educationYear + "_" + classRoll + "_" + examRoll;

			DatabaseService.getXmlFile(fileName, dbOriginalFilePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(University.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			University university = (University) jaxbUnmarshaller.unmarshal(new File(dbOriginalFilePath));

			Files.copy(Paths.get(dbOriginalFilePath), Paths.get(dbOriginalSignedFilePath));

			
			
			
			((Node) event.getSource()).getScene().getWindow().hide();

			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();

			Pane root = loader.load(getClass().getResource("/com/touhid/tabulation/design/SearchResult.fxml").openStream());

			SearchResultController searchResultController = (SearchResultController) loader.getController();
			searchResultController.setUniversity(university);

			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/touhid/tabulation/design/SearchResult.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
 			alert.setTitle("Search Result");
 			alert.setHeaderText("Give Correct Information");
 			alert.setContentText("Cat not find required file.");

 			alert.showAndWait();
		}
	}

	private void deleteFile() {
		try {
			Files.deleteIfExists(Paths.get(dbOriginalFilePath));
			Files.deleteIfExists(Paths.get(dbOriginalSignedFilePath));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goHome(ActionEvent event) throws IOException {

		((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();

		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();

		Pane root = loader
				.load(getClass().getResource("/com/touhid/tabulation/design/Main.fxml").openStream());

		

		Scene scene = new Scene(root);
		scene.getStylesheets()
				.add(getClass().getResource("/com/touhid/tabulation/design/Main.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
