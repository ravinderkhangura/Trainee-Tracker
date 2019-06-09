package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label incorrect;

    @FXML
    private Button log;
    
    @FXML
    private ImageView newuser;


    Connection con;
    PreparedStatement pst; 

    
    @FXML
    void dologin(ActionEvent event) {
    	
    	incorrect.setText(null);
    	
    	String passwordsaved=null;
    	
    	try {
			pst=con.prepareStatement("select password from userlogin where username=?");
			pst.setString(1,username.getText());
			
		 ResultSet rs=	pst.executeQuery();
		 boolean jasus=false;
		 while(rs.next())
		 {
			 jasus=true;
			
			 passwordsaved=rs.getString("password");
	 
		 }
		 String passwordentered =password.getText();
		 
		 
		 if(passwordentered.equals(passwordsaved))
		 {
			fun(); 
			Stage stage =(Stage) log.getScene().getWindow();
		    stage.close();
		 }
		 
		 else 
		 {
			incorrect.setText("*incorrect user-name/password*");	
			} 
		 
		 
		 if(jasus==false)
		 {
			 incorrect.setText("*incorrect user-name/password*");	
		 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	username.setText(null);
    	password.setText(null);
    	
    }
	
    	

    
    
    void fun()
    {
     	
  	try {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/Dashboard.fxml"));
		Scene scene = new Scene(root,600,425);
		Stage primaryStage=new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
	} catch(Exception e) {
		e.printStackTrace();
	}

    }
    
    void doConnect()
    {
    	try {
			
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost/trainees","root","ravinder013");
    		System.out.println("Connection established");
    		
		}
    	catch (ClassNotFoundException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void createnewuser(MouseEvent event) {

    	try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("newUser/NewUser.fxml"));
			Scene scene = new Scene(root,600,400);
			Stage primaryStage= new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage =(Stage) log.getScene().getWindow();
	    stage.close();
    	
    }


    @FXML
    void initialize() {
    	
    	doConnect();
    	
    	
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'Login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'Login.fxml'.";
        assert incorrect != null : "fx:id=\"incorrect\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
