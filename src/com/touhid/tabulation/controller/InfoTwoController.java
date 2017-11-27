package com.touhid.tabulation.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.touhid.tabulation.model.Course;
import com.touhid.tabulation.model.CourseDetail;
import com.touhid.tabulation.model.Department;
import com.touhid.tabulation.model.University;

import javafx.event.ActionEvent;

public class InfoTwoController implements Initializable {
	@FXML
	private TextField tfCourseNo;
	@FXML
	private TextField tfCourseTitle;
	@FXML
	private TextField tfTotalCredit;
	@FXML
	private TextField tfTutorialMarkTotal;
	@FXML
	private TextField tfTutorialMarkObtained;
	@FXML
	private TextField tfFinalMarkTotal;
	@FXML
	private TextField tfFinalMarkObtained;
	@FXML
	private TextField tfTotalMarkObtained;
	@FXML
	private TextField tfGradeObtained;
	@FXML
	private TextField tfGPObtained;

	private University university;
	private Department department;
	private CourseDetail courseDetail;
	private ArrayList<Course> courses;
	private int courseNumber;
	private int i;

	@FXML
	private Label totalCourse;
	@FXML
	private Label addedCourse;
	
	
	
	public void add(ActionEvent event) throws IOException {

		

		i++;
        addedCourse.setText(String.valueOf(i));
		if (i == courseNumber) {

			getInfo();
			
			courseDetail.setCourses(courses);

			((Node) event.getSource()).getScene().getWindow().hide();

			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();

			Pane root = loader
					.load(getClass().getResource("/com/touhid/tabulation/design/InfoThree.fxml").openStream());

			InfoThreeController infoThreeController = (InfoThreeController) loader.getController();
			infoThreeController.setDepartment(department);
			infoThreeController.setUniversity(university);
			infoThreeController.setCourseDetail(courseDetail);

			Scene scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/com/touhid/tabulation/design/InfoThree.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} else {

			getInfo();
			clear();

		}

	}

	private void getInfo() {
		String courseNo = tfCourseNo.getText();
		String courseTitle = tfCourseTitle.getText();
		double totalCredit = Double.parseDouble(tfTotalCredit.getText());
		double totalTutorialMark = Double.parseDouble(tfTutorialMarkTotal.getText());
		double obtainedTutorialMark = Double.parseDouble(tfTutorialMarkObtained.getText());
		double totalFinalMark = Double.parseDouble(tfFinalMarkTotal.getText());
		double obtainedFinalMark = Double.parseDouble(tfFinalMarkObtained.getText());
		double totalObtainedMark = Double.parseDouble(tfTotalMarkObtained.getText());
		double gradeObtained = Double.parseDouble(tfGradeObtained.getText());
		double gpObtained = Double.parseDouble(tfGPObtained.getText());

		Course course = new Course(courseNo, courseTitle, totalCredit, totalTutorialMark, obtainedTutorialMark,
				totalFinalMark, obtainedFinalMark, totalObtainedMark, gradeObtained, gpObtained);
		courses.add(course);
		
	}

	private void clear() {

		tfCourseNo.clear();
		tfCourseTitle.clear();
		tfTotalCredit.clear();
		tfTutorialMarkTotal.clear();
		tfTutorialMarkObtained.clear();
		tfFinalMarkTotal.clear();
		tfFinalMarkObtained.clear();
		tfTotalMarkObtained.clear();
		tfGradeObtained.clear();
		tfGPObtained.clear();
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

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
		totalCourse.setText(String.valueOf(courseNumber));
		addedCourse.setText(String.valueOf(0));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		courseDetail = new CourseDetail();
		courses = new ArrayList<>();
		
		
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
