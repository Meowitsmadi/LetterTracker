package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;

public class ChangePassController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	public PasswordModel passwordModel = new PasswordModel();
	
	@FXML
	private TextField txtNewPassword; // text field where user enters new password
	@FXML
	private TextField txtConfirmPassword; // text field where user confirms password
	
	@FXML private Label status;
	
	@FXML
	public void SaveNewPassword(ActionEvent event) throws IOException, SQLException {
		if (txtNewPassword.getText().equals(txtConfirmPassword.getText()))
		{
			//change pass to inputed text
			passwordModel.ChangePassword(txtNewPassword.getText());
			status.setText("Password successfully changed!");
			
			//go back to home screen after save
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
			root = loader.load();
			SceneController sceneController = loader.getController();
			sceneController.switchToLoginScene(event);
		}
		else
		{
			status.setText("Passwords do not match");
		}	
	}
	
}
