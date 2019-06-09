package feeCollector;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class feeCollectorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fsname;

    @FXML
    private TextField fid;

    @FXML
    private TextField ftotalfee;

    @FXML
    private TextField fbalance;

    @FXML
    private TextField frecievednow;

    @FXML
    private Button fetchfee;

    @FXML
    private ImageView back;

    
    Connection con;
    PreparedStatement pst; 

    @FXML
    void dofetch(ActionEvent event) {
    	
    	try {
			
    		pst=con.prepareStatement("select  rsname,rtotalfee,rbalance from registerationform where sid=?");
			pst.setInt(1, Integer.parseInt(fid.getText()));
			
			frecievednow.setText(null);
			
			 ResultSet rs=	pst.executeQuery();
			 boolean jasus=false;
			 while(rs.next())
			 {
				 jasus=true;
				 String name=rs.getString("rsname");
				 fsname.setText(name);
				 
				 int tfee=rs.getInt("rtotalfee");
				 ftotalfee.setText(String.valueOf(tfee));
				 
				 
				 int bfee=rs.getInt("rbalance");
				 fbalance.setText(String.valueOf(bfee));
				 		 
			 }
			 
			 if(jasus==false)
			 {
				 showMsg("Invalid Id");
			 }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	

    }

    @FXML
    void doupdatefee(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("update registerationform set rbalance= rbalance-?,rfeerecieve = rfeerecieve+?  where sid=?");
			
			pst.setInt(3, Integer.parseInt(fid.getText()));
			pst.setInt(1, Integer.parseInt(frecievednow.getText()));
			pst.setInt(2, Integer.parseInt(frecievednow.getText()));
			
			
			
			int count=pst.executeUpdate();
			if(count==0)
			showMsg("Invalid Id");
			else
				showMsg("Record Updated");
			
			int oldbalance=Integer.parseInt(fbalance.getText());
			int recievedfee=Integer.parseInt(frecievednow.getText());
			int newbalance =oldbalance-recievedfee;
			fbalance.setText(String.valueOf(newbalance));
			
			frecievednow.setText(null);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showMsg("ERROR "+e.getMessage());
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
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/Dashboard.fxml"));
			Scene scene = new Scene(root,600,425);
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
    	
        assert fsname != null : "fx:id=\"fsname\" was not injected: check your FXML file 'feeCollector.fxml'.";
        assert fid != null : "fx:id=\"fid\" was not injected: check your FXML file 'feeCollector.fxml'.";
        assert ftotalfee != null : "fx:id=\"ftotalfee\" was not injected: check your FXML file 'feeCollector.fxml'.";
        assert fbalance != null : "fx:id=\"fbalance\" was not injected: check your FXML file 'feeCollector.fxml'.";
        assert frecievednow != null : "fx:id=\"frecievednow\" was not injected: check your FXML file 'feeCollector.fxml'.";
        assert fetchfee != null : "fx:id=\"fetchfee\" was not injected: check your FXML file 'feeCollector.fxml'.";

    }
}
