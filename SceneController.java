package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import sql.sqliteDemo;

public class SceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	public LoginModel loginModel = new LoginModel();
	
	@FXML
	public ComboBox<String> genders = new ComboBox<String>();
	@FXML
	public ComboBox<String> semesters= new ComboBox<String>();
	@FXML
	public ComboBox<String> programs = new ComboBox<String>();
	
	
	@FXML 
	private TextField txtPassword; 
	
	@FXML
	private Label isConnected;
	
	public void Login(ActionEvent event) throws IOException {
		try {
			// if password in DB does not equal p meaning not first time, but user enters p again, decline
			if ((txtPassword.getText().equals("p")) && (!loginModel.checkLogin(1, "p")))
			{
				isConnected.setText("You cannot use the default PW again");
			}
			else if (txtPassword.getText().equals("p")) {
				GoResetPassword(event);
			}
			// check if inputed password = DB password	
			else if (loginModel.checkLogin(1, txtPassword.getText())) 
			{
				isConnected.setText("Login success");	
				
			}
			else if (txtPassword.getText().isEmpty()) 
			{
				isConnected.setText("Please enter a password");
			}
			else
			{
				isConnected.setText("Login fail");
			}	
		}
		catch (SQLException e) {
			isConnected.setText("Login fail");
			e.printStackTrace();
		}
	}
	
	@FXML
	public void switchToLoginScene(ActionEvent event) throws IOException, SQLException{
		root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void switchToHomeScene(ActionEvent event) throws IOException, SQLException{
		Login(event); // check if PW is correct
		if (isConnected.getText().equals("Login success")) {
			root = FXMLLoader.load(getClass().getResource("/view/LThomepage.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	public void GoResetPassword(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/view/firstLogin.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToResetPWScene(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/LThomepage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToNewLORScene(ActionEvent event) throws IOException{
		ArrayList<String> d = sqliteDemo.getAllData("semester");
		ObservableList<String> g = FXCollections.observableArrayList("Boy", "Girl");
		genders.setItems(g);
		
		root = FXMLLoader.load(getClass().getResource("/view/LTCreateNewRec.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		//genders.setItems(g);
		//semesters.setItems(FXCollections.observableArrayList(sqliteDemo.getAllData("semester")));
		//programs.setItems(FXCollections.observableArrayList(sqliteDemo.getAllData("programs")));
	}
	

}
