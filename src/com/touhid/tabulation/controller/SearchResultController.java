package com.touhid.tabulation.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.touhid.tabulation.model.Course;
import com.touhid.tabulation.model.Department;
import com.touhid.tabulation.model.University;
import com.touhid.tabulation.service.SignedService;
import com.touhid.tabulation.service.XmlNodeService;
import com.touhid.tabulation.service.XmlTrimService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchResultController implements Initializable{

	
	private static final String dbTrimFile = "src/com/touhid/tabulation/temp/db_trim.xml";
	
	private University university;

	private Node tabulatorOneSignedNode;
	private Node tabulatorTwoSignedNode;
	private Node chairmanSignedNode;
	
	
	private String tabulatorOneSignedStatus="";
	private String tabulatorTwoSignedStatus="";
	private String chairmanSignedStatus="";
	
	
	
	@FXML private TableView<Course> table;
	@FXML private TableColumn<Course, String> courseNoColumn;
	@FXML private TableColumn<Course, String> courseTitleColumn;
	@FXML private TableColumn<Course, Double> totalCreditColumn;
	@FXML private TableColumn<Course, Double> totalTutorialMarkColumn;
	@FXML private TableColumn<Course, Double> obtainedTutorialMarkColumn;
	@FXML private TableColumn<Course, Double> totalFinalMarkColumn;
	@FXML private TableColumn<Course, Double> obtainedFinalMarkColumn;
	@FXML private TableColumn<Course, Double> totalObtainedMarkColumn;
	@FXML private TableColumn<Course, Double> gradeObtainedColumn;
	@FXML private TableColumn<Course, Double> gpObtainedColumn;
	
	
	public static final String dbOriginalFilePath = "src/com/touhid/tabulation/temp/db_originalFile.xml";
	public static final String dbTrim = "src/com/touhid/tabulation/temp/db_trim.xml";
	public static final String dbOriginalSignedFilePath = "src/com/touhid/tabulation/temp/db_originalSignedFile.xml";

	
	private ObservableList<Course> courseData;
	
	@FXML
	Label lbTabolatorOneSigned;
	@FXML
	Label lbTabolatorTwoSigned;
	@FXML
	Label lbChairmanSigned;
	
	
	
	@FXML
	Label lbSerialNo;
	@FXML
	Label lbEducationYear;
	@FXML
	Label lbExamineeName;
	@FXML
	Label lbFatherName;
	@FXML
	Label lbClassRoll;
	@FXML
	Label lbExamRoll;
	@FXML
	Label lbHallName;
	
	
	
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
		
		
		
		
		
		setup();
		
		
	}
	
	private void setup(){
		if(university!=null){
			try {
				Department department=university.getDepartment();
				lbSerialNo.setText(department.getSerialNo());
				lbClassRoll.setText(department.getClassRoll());
				lbEducationYear.setText(department.getEducationYear());
				lbExamineeName.setText(department.getExamineeName());
				lbExamRoll.setText(department.getExamRoll());
				lbFatherName.setText(department.getFatherName());
				lbHallName.setText(department.getHallName());
				
				
				this.courseData=FXCollections.observableArrayList(department.getSemester().getCourseDetail().getCourses());
				table.setItems(courseData);
			} catch (Exception e) {
				System.out.println("Data not full");
			}
			
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		courseNoColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("courseNo"));
		courseTitleColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("courseTitle"));
		totalCreditColumn.setCellValueFactory(new PropertyValueFactory<Course,Double>("totalCredit"));
		totalTutorialMarkColumn.setCellValueFactory(new PropertyValueFactory<Course,Double>("totalTutorialMark"));
		obtainedTutorialMarkColumn.setCellValueFactory(new PropertyValueFactory<Course,Double>("obtainedTutorialMark"));
		totalFinalMarkColumn.setCellValueFactory(new PropertyValueFactory<Course,Double>("totalFinalMark"));
		obtainedFinalMarkColumn.setCellValueFactory(new PropertyValueFactory<Course,Double>("obtainedFinalMark"));
		totalObtainedMarkColumn.setCellValueFactory(new PropertyValueFactory<Course,Double>("totalObtainedMark"));
		gradeObtainedColumn.setCellValueFactory(new PropertyValueFactory<Course,Double>("gradeObtained"));
		gpObtainedColumn.setCellValueFactory(new PropertyValueFactory<Course,Double>("gpObtained"));
		
		
		try {
			tabulatorOneSignedNode=getRemovedNode("Tabulator_1");
			tabulatorTwoSignedNode=getRemovedNode("Tabulator_2");
			chairmanSignedNode=getRemovedNode("ExamCommittee_Chairman");
			
			if(tabulatorOneSignedNode==null){
				tabulatorOneSignedStatus="NOT SIGNED";
			}
			
			if(tabulatorTwoSignedNode==null){
				tabulatorTwoSignedStatus="NOT SIGNED";
				
				
			}
			
			if(chairmanSignedNode==null){
				chairmanSignedStatus="NOT SIGNED";
				
			}
			
			if(tabulatorOneSignedNode!=null)
				isSignedByTabulatorOne();
			if(tabulatorTwoSignedNode!=null)
				isSignedByTabulatorTwo();
			if(chairmanSignedNode!=null)
				isSignedByChairman();
			
			
			
			
			lbChairmanSigned.setText(chairmanSignedStatus);
			lbTabolatorOneSigned.setText(tabulatorOneSignedStatus);
			lbTabolatorTwoSigned.setText(tabulatorTwoSignedStatus);
			
			
			
			deleteFile();
			
			
			
		} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteFile() {
		try {
			Files.deleteIfExists(Paths.get(dbOriginalFilePath));
			Files.deleteIfExists(Paths.get(dbOriginalSignedFilePath));
			Files.deleteIfExists(Paths.get(dbTrim));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Node getRemovedNode(String tagName ) throws SAXException, IOException, ParserConfigurationException, TransformerException{
		return XmlNodeService.removeNode(SearchFileController.dbOriginalFilePath, tagName);
	}
	
	
	private boolean isSignedByTabulatorOne(){
		
		
		try {
			XmlNodeService.addNode(SearchFileController.dbOriginalFilePath, tabulatorOneSignedNode, "Jahangirnagar_university");
			
			XmlNodeService.changeTagName(SearchFileController.dbOriginalFilePath, "Jahangirnagar_university", "Tabulator_1", "Signature");
			XmlTrimService.trim(SearchFileController.dbOriginalFilePath, dbTrimFile);
			
			boolean rs=SignedService.isValid(dbTrimFile);
			XmlNodeService.removeNode(SearchFileController.dbOriginalFilePath, "Signature");
			if(rs==true){
				tabulatorOneSignedStatus="VALID SIGN";
			}else{
				tabulatorOneSignedStatus="INVALID SIGN";
			}
			
			
			return rs;
		} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
			tabulatorOneSignedStatus="INVALID SIGN";
			return false;
		}
		
		
	}
	
	
	private boolean isSignedByTabulatorTwo(){
		
		
		boolean rs=false;
		try {
			XmlNodeService.addNode(SearchFileController.dbOriginalFilePath, tabulatorTwoSignedNode, "Jahangirnagar_university");
			
			XmlNodeService.changeTagName(SearchFileController.dbOriginalFilePath, "Jahangirnagar_university", "Tabulator_2", "Signature");
			XmlTrimService.trim(SearchFileController.dbOriginalFilePath, dbTrimFile);
			
			 rs=SignedService.isValid(dbTrimFile);
			XmlNodeService.removeNode(SearchFileController.dbOriginalFilePath, "Signature");
			
			if(rs==true){
				tabulatorTwoSignedStatus="VALID SIGN";
				
				
			}
			else{
				tabulatorTwoSignedStatus="INVALID SIGN";
				
			}
				
				
				
			
			return rs;
		} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
			tabulatorTwoSignedStatus="INVALID SIGN";
			
			return false;
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
	
	private boolean isSignedByChairman(){
		
		
		
		
		
		try {
			XmlNodeService.addNode(SearchFileController.dbOriginalFilePath, chairmanSignedNode, "Jahangirnagar_university");
			
			XmlNodeService.changeTagName(SearchFileController.dbOriginalFilePath, "Jahangirnagar_university", "ExamCommittee_Chairman", "Signature");
			XmlTrimService.trim(SearchFileController.dbOriginalFilePath, dbTrimFile);
			
			boolean rs=SignedService.isValid(dbTrimFile);
			XmlNodeService.removeNode(SearchFileController.dbOriginalFilePath, "Signature");
			
			if(rs==true){
				chairmanSignedStatus="VALID SIGN";
			}else{
				chairmanSignedStatus="INVALID SIGN";	
			}
			
			
			return rs;
		} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
			chairmanSignedStatus="INVALID SIGN";
			return false;
		}
		
		
	}
	
}
