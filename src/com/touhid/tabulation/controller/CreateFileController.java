package com.touhid.tabulation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.touhid.tabulation.model.Department;
import com.touhid.tabulation.model.University;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateFileController implements Initializable {

	@FXML
	TextField tfSerialNo;
	@FXML
	TextField tfEducationYear;
	@FXML
	TextField tfExamineeName;
	@FXML
	TextField tfFatherName;
	@FXML
	TextField tfClassRoll;
	@FXML
	TextField tfExamRoll;
	@FXML
	TextField tfHallName;
	@FXML
	TextField tfCourseNumber;

	

	

	
	private University university;
	private Department department;
	
	private int courseNumber;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		university=new University();
	}

	public void infoFirst(ActionEvent event) throws IOException {

		
		
		
		collectInfoOne();
		
		
		
		
		
		
		
		((Node)event.getSource()).getScene().getWindow().hide();
		
		Stage primaryStage=new Stage();
		FXMLLoader loader=new FXMLLoader();
		
		
		Pane root = loader.load(getClass().getResource("/com/touhid/tabulation/design/InfoTwo.fxml").openStream());
		InfoTwoController infoTwoController=(InfoTwoController)loader.getController();
		infoTwoController. setDepartment(department);
		infoTwoController.setUniversity(university);
		infoTwoController.setCourseNumber(courseNumber);
		
		Scene scene = new Scene(root);
		scene.getStylesheets()
		.add(getClass().getResource("/com/touhid/tabulation/design/InfoTwo.css").toExternalForm());		
		primaryStage.setScene(scene);
		primaryStage.show();
	

	}

	

	

	private void collectInfoOne(){
		
		String serialNo=tfSerialNo.getText();
		String educationYear=tfEducationYear.getText();
		String examineeName=tfExamineeName.getText();
		String fatherName=tfFatherName.getText();
		String classRoll=tfClassRoll.getText();
		String examRoll=tfExamRoll.getText();
		String hallName=tfHallName.getText();
	
		department=new Department();
		department.setSerialNo(serialNo);
		department.setEducationYear(educationYear);
		department.setExamineeName(examineeName);
		department.setFatherName(fatherName);
		department.setClassRoll(classRoll);
		department.setExamRoll(examRoll);
		department.setHallName(hallName);
		
		
		courseNumber=Integer.parseInt(tfCourseNumber.getText());

	
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
