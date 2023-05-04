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
import sql.sqliteDemo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class ChangePassController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	public PasswordModel passwordModel = new PasswordModel();
	
	@FXML
	private TextField txtOldPassword; // text field where user enters new password
	@FXML
	private TextField txtNewPassword; // text field where user enters new password
	@FXML
	private TextField txtConfirmPassword; // text field where user confirms password
	
	@FXML private Label status;
	
	@FXML
	public void SaveNewPassword(ActionEvent event) throws IOException, SQLException {
		// Base Case: Checks if any fields are empty
		if(txtNewPassword.getText().isEmpty() || txtOldPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty()) {
			status.setText("One or more of the fields is not filled");
			return;
		}
		// checks if the new pasword is the default or not
		if (txtNewPassword.getText().equals("p")) {
			status.setText("You cannot use the default password again");
		}
		// checks if old password maatches teh one in the database AND new passwords amtch one another
		else if (txtOldPassword.getText().equals(PasswordModel.GetOldPassword()) && txtNewPassword.getText().equals(txtConfirmPassword.getText()))
		{
			//change pass to inputed text
			PasswordModel.ChangePassword(txtNewPassword.getText());
			status.setText("Password successfully changed!");
			
			//go back to home screen after save
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
			root = loader.load();
			SceneController sceneController = loader.getController();
			sceneController.switchToLoginScene(event);
		}
		// what to do if old password does not match the one in the database
		else if(!txtOldPassword.getText().equals(PasswordModel.GetOldPassword())) {
			status.setText("Incorrect Old Password");
		}
		else
		{
			status.setText("Passwords do not match");
		}	
	}
	
	
}
