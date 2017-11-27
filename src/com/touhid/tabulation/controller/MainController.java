package com.touhid.tabulation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	public void createFile(ActionEvent event) throws IOException{
		((Node) event.getSource()).getScene().getWindow().hide();

		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();

		Pane root = loader.load(getClass().getResource("/com/touhid/tabulation/design/CreateFile.fxml").openStream());

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/touhid/tabulation/design/CreateFile.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void search(ActionEvent event) throws IOException{
		((Node) event.getSource()).getScene().getWindow().hide();

		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();

		Pane root = loader.load(getClass().getResource("/com/touhid/tabulation/design/SearchFile.fxml").openStream());

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/touhid/tabulation/design/SearchFile.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
