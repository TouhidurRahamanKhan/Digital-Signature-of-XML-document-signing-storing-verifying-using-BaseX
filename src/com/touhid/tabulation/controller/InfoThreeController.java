package com.touhid.tabulation.controller;

import java.io.IOException;

import com.touhid.tabulation.model.CourseDetail;
import com.touhid.tabulation.model.Department;
import com.touhid.tabulation.model.Semester;
import com.touhid.tabulation.model.University;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InfoThreeController {
	@FXML
	private TextField tfGPA;
	@FXML
	private TextField tfPreviousGPA;
	@FXML
	private TextField tfCGPA;

	private University university;
	private Department department;
	private CourseDetail courseDetail;
	private Semester semester;

	public void finalInfo(ActionEvent event) throws IOException {

		

		String gpa = tfGPA.getText();
		String previousGPA = tfPreviousGPA.getText();
		String cGPA = tfCGPA.getText();
		semester = new Semester(courseDetail, gpa, previousGPA, cGPA);
		department.setSemester(semester);
		university.setDepartment(department);

		((Node) event.getSource()).getScene().getWindow().hide();

		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();

		Pane root = loader.load(getClass().getResource("/com/touhid/tabulation/design/SignStore.fxml").openStream());

		SignStoreController signStoreControllerController = (SignStoreController) loader.getController();
		signStoreControllerController.setUniversity(university);
		

		Scene scene = new Scene(root);
		scene.getStylesheets()
				.add(getClass().getResource("/com/touhid/tabulation/design/SignStore.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public CourseDetail getCourseDetail() {
		return courseDetail;
	}

	public void setCourseDetail(CourseDetail courseDetail) {
		this.courseDetail = courseDetail;
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
