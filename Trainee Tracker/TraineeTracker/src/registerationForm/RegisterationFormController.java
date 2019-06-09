package registerationForm;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class RegisterationFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField rsname;

    @FXML
    private TextField rfname;

    @FXML
    private TextField rsphone;

    @FXML
    private TextField rpphone;

    @FXML
    private ComboBox<String> rcollege;

    @FXML
    private ComboBox<String> rcourse;

    @FXML
    private ComboBox<String> rbranch;

    @FXML
    private ComboBox<String> rsem;

    @FXML
    private ComboBox<String> rtechnology;

    @FXML
    private ComboBox<String> rdate;

    @FXML
    private ListView<String> timelist;
    
    @FXML
    private TextField rtime;

    @FXML
    private TextField rbalance;

    @FXML
    private TextField rtotalfee;

    @FXML
    private TextField rfeerecieve;

    @FXML
    private Button rregister;
    

    @FXML
    private ImageView back;

    
    Connection con;
    PreparedStatement pst; 
    
    String timeselected;

    @FXML
    void doregister(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("insert into registerationform values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, rsname.getText());
			pst.setString(2, rfname.getText());
			pst.setString(3, rsphone.getText());
			pst.setString(4, rpphone.getText());
			pst.setString(5, rcollege.getEditor().getText());
			pst.setString(6, rcourse.getEditor().getText());
			pst.setString(7, rbranch.getEditor().getText());
			pst.setString(8, rsem.getEditor().getText());
			pst.setString(9, rtechnology.getValue());
			pst.setString(10, rdate.getValue());
			pst.setString(11, rtime.getText());
			pst.setInt(12, Integer.parseInt(rtotalfee.getText()));
			pst.setInt(13, Integer.parseInt(rfeerecieve.getText()));			
			pst.setInt(14, Integer.parseInt(rbalance.getText()));
		
			pst.executeUpdate();
			showMsg("Data Saved");
		    fillclg();
		    fillcourse();
		    fillbranch();
		    

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
    
    //clg//
 ArrayList<String> arrayclg;
    
    void fillclg()
    {
    	arrayclg=new ArrayList<String>();
    	
    	try {
			pst=con.prepareStatement("select distinct rcollege from registerationform");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				String clg=rs.getString("rcollege");
				arrayclg.add(clg);
			}
			rcollege.getItems().clear();
			rcollege.getItems().addAll(arrayclg);
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
   
    //course//
ArrayList<String> arraycourse;
    
    void fillcourse()
    {
    	arraycourse=new ArrayList<String>();
    	
    	try {
			pst=con.prepareStatement("select distinct rcourse from registerationform");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				String course=rs.getString("rcourse");
				arraycourse.add(course);
			}
			rcourse.getItems().clear();
			rcourse.getItems().addAll(arraycourse);
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    //branch//
ArrayList<String> arraybranch;
    
    void fillbranch()
    {
    	arraybranch=new ArrayList<String>();
    	
    	try {
			pst=con.prepareStatement("select distinct rbranch from registerationform");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				String branch=rs.getString("rbranch");
				arraybranch.add(branch);
			}
			rbranch.getItems().clear();
			rbranch.getItems().addAll(arraybranch);
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
  //sem//
   void fillsem()
   {
	  ArrayList<String> sem =new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8"));
	  rsem.getItems().addAll(sem);
   }

  //technology//
   ArrayList<String> arrayrtech;   
   void fillrtechs()
   {
   	arrayrtech=new ArrayList<String>();
   	
   	try {
			pst=con.prepareStatement("select distinct combotech from batchmaster");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				String tech=rs.getString("combotech");
				arrayrtech.add(tech);
			}
			rtechnology.getItems().clear();
			rtechnology.getItems().addAll(arrayrtech);
			
		} 
   	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
   }
   
   //dates//
   ArrayList<String> arraydate;
   @FXML
   void seltech(ActionEvent event) {
	   
	   arraydate = new ArrayList<String>();
	   
	   try {
		pst=con.prepareStatement("select distinct bdate from batchmaster where combotech in (?) ");
		pst.setString(1, rtechnology.getValue());
		
		ResultSet rs=pst.executeQuery();
		
		while(rs.next())
		{
			java.sql.Date date=rs.getDate("bdate");
			 LocalDate local = date.toLocalDate();
			String d = String.valueOf(local);
			 arraydate.add(d);
			
		}
		rdate.getItems().clear();
		rdate.getItems().addAll(arraydate);
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



   }
   
   //time//
   ArrayList<String> arraytime;
   @FXML
   void seldate(ActionEvent event) {
	   
	   arraytime = new ArrayList<String>();
	   
	   try {
		   
			pst=con.prepareStatement("select time from batchmaster where combotech =? and bdate= ? ");
			pst.setString(1, rtechnology.getValue());
			pst.setString(2, rdate.getValue());
			
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				String timing=rs.getString("time");
				arraytime.add(timing);
			}
			timelist.getItems().clear();
			timelist.getItems().addAll(arraytime);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


   }
//selecting time//
   
   @FXML
   void seltime(MouseEvent arg0) {
	   
	 timeselected =	timelist.getSelectionModel().getSelectedItem();
	 rtime.setText(timeselected);
	 try {
		   
			pst=con.prepareStatement("select fee from batchmaster where combotech =? and bdate= ? and time=?");
			pst.setString(1, rtechnology.getValue());
			pst.setString(2, rdate.getValue());
			pst.setString(3,timeselected);
			
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				int tfee=rs.getInt("fee");
				rtotalfee.setText(String.valueOf(tfee));
				
			}
			
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

   }
   
  // entering Balance//
  
 

   @FXML
   void enterbalance(MouseEvent event) {

	   int  totalfee = Integer.parseInt(rtotalfee.getText());
		int recievedfee = Integer.parseInt(rfeerecieve.getText());
		int balance= totalfee-recievedfee;
		rbalance.setText(String.valueOf(balance));

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
    	fillrtechs();
        fillclg();
		fillcourse();
		fillbranch();
		fillsem();
	    
	    
		    

    	
    	
    	
    	
    	
        assert rsname != null : "fx:id=\"rsname\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rfname != null : "fx:id=\"rfname\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rsphone != null : "fx:id=\"rsphone\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rpphone != null : "fx:id=\"rpphone\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rcollege != null : "fx:id=\"rcollege\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rcourse != null : "fx:id=\"rcourse\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rbranch != null : "fx:id=\"rbranch\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rsem != null : "fx:id=\"rsem\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rtechnology != null : "fx:id=\"rtechnology\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rdate != null : "fx:id=\"rdate\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert timelist != null : "fx:id=\"rtime\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rbalance != null : "fx:id=\"rbalance\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rtotalfee != null : "fx:id=\"rtotalfee\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rfeerecieve != null : "fx:id=\"rfeerecieve\" was not injected: check your FXML file 'RegisterationForm.fxml'.";
        assert rregister != null : "fx:id=\"rregister\" was not injected: check your FXML file 'RegisterationForm.fxml'.";

    }
}
