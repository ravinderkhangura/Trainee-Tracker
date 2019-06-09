package newUser;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class NewUserController  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField newusername;

    @FXML
    private PasswordField newpassword1;

    @FXML
    private PasswordField newpassword2;

    @FXML
    private Button create;

    @FXML
    private PasswordField oldpassword;
    
    @FXML
    private Label mismatch;
    @FXML
    private ImageView back;
    
    Connection con;
    PreparedStatement pst; 
  

    @FXML
    void docreate(ActionEvent event) {
    	String ok=null;
    	
    	String newpass1= newpassword1.getText();
    	String newpass2= newpassword2.getText();
    	if(newpass1.equals(newpass2))
    	{   mismatch.setText(null);
    		ok=newpass1;

        	try {
    			pst=con.prepareStatement("update userlogin set username=?, password=? where password=?");
    		
        	pst.setString(3,oldpassword.getText());
        	pst.setString(1, newusername.getText());
        	pst.setString(2, ok);

    		int count=pst.executeUpdate();
    		
    		if(count==0)
    			{showMsg("Invalid Password");
    			oldpassword.setText(null);
    			}
    			else
    			{
    				showMsg("New User Created");
    				newusername.setText(null);
    				newpassword1.setText(null);
    				newpassword2.setText(null);
    			   oldpassword.setText(null);
    			}
    			} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    		
    	}
    	else
    	{ newpassword1.setText(null);
    	newpassword2.setText(null);
    	mismatch.setText("*password do not match*");
    	oldpassword.setText(null);
    	}
    	
    }
  
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	
    	
    	alert.setContentText(msg);
    	
    	alert.show();
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
    void goback(MouseEvent event) {

    	try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login/Login.fxml"));
			Scene scene = new Scene(root,584,370);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    	Stage stage =(Stage) back.getScene().getWindow();
	    stage.close();

    }
    

    @FXML
    void initialize() {
    	
    	doConnect();
    	
        assert newusername != null : "fx:id=\"username\" was not injected: check your FXML file 'NewUser.fxml'.";
        assert newpassword1 != null : "fx:id=\"password1\" was not injected: check your FXML file 'NewUser.fxml'.";
        assert newpassword2 != null : "fx:id=\"password2\" was not injected: check your FXML file 'NewUser.fxml'.";
        assert create != null : "fx:id=\"create\" was not injected: check your FXML file 'NewUser.fxml'.";
        assert oldpassword != null : "fx:id=\"oldpassword\" was not injected: check your FXML file 'NewUser.fxml'.";

    }
}
