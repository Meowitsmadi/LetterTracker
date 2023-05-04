package controller;

// Importing necessary libraries
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;

import application.LoginModel;
import application.Student;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.*;
import sql.AccessFunctions;
import sql.sqliteDemo;

// Defining the SceneController class which deals with switching to various scenes and implements the Initializable interface
// for creating and populating Combo Boxes
public class SceneController implements Initializable {
	
	
	// Declaring necessary variables for creating create pages and login
	static Stage stage;
	static Scene scene;
	static Parent root;
	public LoginModel loginModel = new LoginModel();
	static Student student = new Student();

	
	static int ID = 0;
	static boolean isEdit = false;
	@FXML
	static TextArea lorText = new TextArea();
	@FXML
	Button back = new Button();
	
	
	// Method that switches to the login scene
	@FXML
	public void switchToLoginScene(ActionEvent event) throws IOException, SQLException{
		root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		changeScene(event);
	}
	
	// Method switches the scene to the Create New LOR page when the create new recommendation button is clicked
	public void switchToHomeScene1(ActionEvent event) throws IOException{	
		root = FXMLLoader.load(getClass().getResource("/view/LThomepage.fxml"));
		changeScene(event);
	}
	
	// Method that switches to the reset password scene
	public void GoResetPassword(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/view/firstLogin.fxml"));
		changeScene(event);
	}
	
	// Method switches the scene to home page view when the reset password button is clicked
	public void switchToResetPWScene(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/LThomepage.fxml"));
		changeScene(event);
	}
	
	// Method switches the scene to the Create New LOR page when the create new recommendation button is clicked
	public void switchToNewLORScene(ActionEvent event) throws IOException, SQLException{
		isEdit = false;
		root = FXMLLoader.load(getClass().getResource("/view/LTCreateNewRec.fxml"));
		changeScene(event);
	}
	
	// Method switches the scene back to the Create New LOR page from View page when the create new recommendation button is clicked
	public void switchBackToNewLORScene(ActionEvent event) throws IOException, SQLException{
		root = FXMLLoader.load(getClass().getResource("/view/LTCreateNewRec.fxml"));
		changeScene(event);
	}
	
	void changeScene(ActionEvent event) {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void save(ActionEvent event) throws IOException, SQLException{
		// get data from Student class
		sqliteDemo.InsertRecData(student);
		this.switchToHomeScene1(event);
	}

	public class StudentGrade {
		private String course;
		private TextField grade;
		
		public StudentGrade(String course, TextField grade) {
			this.course = course;
			this.grade = grade;
		}
		
		public TextField getGrade() {
			return grade;
		}
		
		public String getCourse() {
			return course;
		}
		
	}
	
	public void EditLOR(ActionEvent event) throws SQLException, IOException {
		AccessFunctions.EditRecommendation(student,ID);
		this.switchToHomeScene1(event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
