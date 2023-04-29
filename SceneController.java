package application;

// Importing necessary libraries
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.CheckListView;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.*;
import sql.sqliteDemo;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

// Defining the SceneController class which deals with switching to various scenes and implements the Initializable interface
// for creating and populating Combo Boxes
public class SceneController implements Initializable {
	
	
	// Declaring necessary variables for creating create pages and login
	private Stage stage;
	private Scene scene;
	private Parent root;
	public LoginModel loginModel = new LoginModel();
	
	/* Initializes Combo Boxes for the gender, semester, programs, personal characteristics, academic characteristics, and courses
	   in the Create New Recommendation page */
	
	@FXML private ComboBox<String> genders = new ComboBox<String>();
	@FXML private ComboBox<String> semesters = new ComboBox<String>();
	@FXML private ComboBox<String> programs = new ComboBox<String>();
	@FXML private CheckListView<String> personalChar = new CheckListView<String>();
	@FXML private CheckListView<String> academicChar = new CheckListView<String>();
	@FXML private CheckListView<String> courses = new CheckListView<String>();
	
	@FXML private TableView<StudentGrade> fullCoursesView = new TableView<StudentGrade>();
	@FXML private Button updateButton;
	
	public static TableView<StudentGrade> table_info_app;
	public static ObservableList<StudentGrade> data_table;
	
	@FXML private TableColumn<StudentGrade, String> col_course = new TableColumn<StudentGrade, String>();
	@FXML private TableColumn<StudentGrade, TextField> col_grade = new TableColumn<StudentGrade, TextField>();
	
	// TableView setup for displaying the search results
	@FXML private TableView<Student> ResultsTable = new TableView<Student>();
	@FXML private TableColumn<Student, String> studentFNColumn = new TableColumn<Student, String>();
	@FXML private TableColumn<Student, String> studentLNColumn = new TableColumn<Student, String>();
	@FXML private TableColumn<Student, Button> studentViewColumn = new TableColumn<Student, Button>();
	@FXML private TableColumn<Student, Button> studentEditColumn = new TableColumn<Student, Button>();
	@FXML private TableColumn<Student, Button> studentDeleteColumn = new TableColumn<Student, Button>();
	@FXML private Text ResultsLabel;
	@FXML public TextField searchBar = new TextField();
	public String searchedName = null;
	private StringProperty searchText = new SimpleStringProperty("");
	
	private ObservableList<Student> studentData;
	private ObservableList<Student> studentList;
	
	// Text field containing password inputed by user
	@FXML private TextField txtPassword; 
	
	// Label displaying login status of user
	@FXML private Label isConnected;
	
	// Method that handles user login and authentication
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
	
	// Method that switches to the login scene
	@FXML
	public void switchToLoginScene(ActionEvent event) throws IOException, SQLException{
		root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		changeScene(event);
	}
	
	// Method that switches to the home scene
	@FXML
	public void switchToHomeScene(ActionEvent event) throws IOException, SQLException{
		Login(event); // check if PW is correct
		if (isConnected.getText().equals("Login success")) {
			root = FXMLLoader.load(getClass().getResource("/view/LThomepage.fxml"));
			changeScene(event);
		}
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
	public void switchToNewLORScene(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("/view/LTCreateNewRec.fxml"));
		changeScene(event);
	}
	
	public void switchToSaveScene(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("/view/NewLOR.fxml"));
		changeScene(event);
	}
	private void changeScene(ActionEvent event) {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void save(ActionEvent event) throws IOException{
		//sqliteDemo.saveLOR();
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
	
	public void updateCourses(ActionEvent event) throws IOException{
		data_table=FXCollections.observableArrayList();
	    
	    for (String course : courses.getCheckModel().getCheckedItems()) {
            data_table.add(new StudentGrade(course, new TextField()));
        }
	    
	    System.out.println(data_table);
	    fullCoursesView.setItems(data_table);
	    //fullCoursesView.getColumns().addAll(col_course, col_grade);
	    System.out.println(data_table);
	}
	
	public class Student {
		private IntegerProperty id = new SimpleIntegerProperty();
		private StringProperty firstName;
		private StringProperty lastName = new SimpleStringProperty();
		
		public Student(int id, String firstName, String lastName) {
        	this.id = new SimpleIntegerProperty(id);
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
        }
        public Student() {}
		
		public IntegerProperty idProperty() { 
	         if (id == null) id = new SimpleIntegerProperty(this, "id");
	         return id; 
	     }
		public int getId() { return idProperty().get(); }
		public void setId(int value) { idProperty().set(value); }
		
		public StringProperty firstNameProperty() { 
	         if (firstName == null) firstName = new SimpleStringProperty(this, "firstName");
	         return firstName; 
	     }
		public String getFirstName() { return firstNameProperty().get(); }
		public void setFirstName(String value) { firstNameProperty().set(value); }
		
		public StringProperty lastNameProperty() { 
	         if (lastName == null) lastName = new SimpleStringProperty(this, "lastName");
	         return lastName; 
	     }
		public String getLasttName() { return lastNameProperty().get(); }
		public void setLastName(String value) { lastNameProperty().set(value); }
		        
        
	}
	
	public ObservableList<Student> getData(String searchedName) throws SQLException, IOException {
		studentData = FXCollections.observableArrayList();
		Connection conn = DriverManager.getConnection("jdbc:sqlite:letterTrackerInfo.db");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		// selects from recommendation students
		String query = "SELECT * FROM recommendations WHERE LastName = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, searchedName); // selects when searchedName = LastName in DB
			rs = pst.executeQuery();
			while (rs.next()) { //there exists an entry
				Student student = new Student(rs.getInt("ID"), rs.getString("FirstName"), rs.getString("LastName"));
				studentData.add(student); // adding student into list
			}
		}		
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
			rs.close();
		}
		return studentData;
	}

	// Method initializes the combo boxes in the Create New Recommendation page by populating them with their respective data from the database
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sqliteDemo.setOptions(genders, semesters, programs, courses, personalChar, academicChar);
		
		table_info_app = fullCoursesView;
		col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        	col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
		
		searchText.bind(searchBar.textProperty());
	    		searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
	    		    searchedName = newValue;
	    		});
	    		
	    	studentFNColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
	  	studentLNColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
	}
}
