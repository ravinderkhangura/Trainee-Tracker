package batchMaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class BatchMasterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combotech;
    
    @FXML
    private Spinner<String> ampm;

    @FXML
    private TextField timehh;

    @FXML
    private TextField timemm;
    
    @FXML
    private TextField id;

    @FXML
    private DatePicker bdate;

    @FXML
    private TextField fee;
    
    @FXML
    private ImageView back;

    Connection con;
    PreparedStatement pst; 
    String valt;

    @FXML
    void doselect(ActionEvent event) {

    	try {
			pst=con.prepareStatement("select * from batchmaster where id=?");
			pst.setInt(1, Integer.parseInt(id.getText()));
			
		 ResultSet rs=	pst.executeQuery();
		 boolean jasus=false;
		 while(rs.next())
		 {
			 jasus=true;
			 //Technology//
			 String tech1=rs.getString("combotech");
			 combotech.setValue(tech1);
			 //time//
			 String  time1=rs.getString("time");
			 String []t = time1.split(":");
		     String hours = t[0];
		     String minutes = t[1];
		     String ap = t[2];
		     timehh.setText(hours);
		     timemm.setText(minutes);
		     ampm.setAccessibleText(ap);
		     
		     
 			 
			 //date//
			 java.sql.Date date= rs.getDate("bdate");
			 LocalDate local = date.toLocalDate();
			 bdate.setValue(local);
			 
			 
			 //fee//
			 int fee1=rs.getInt("fee");
			 fee.setText(String.valueOf(fee1));
			 
			 
			 
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
    void dodelete(ActionEvent event) {


    	try {
			pst=con.prepareStatement("delete from batchmaster where id=?");
			pst.setInt(1, Integer.parseInt(id.getText()));
			int count=pst.executeUpdate();
			if(count==0)
				showMsg("Invalid Batch ID");
			else
				showMsg(count+" Records Deleted");
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
    	empty();
    	
    }

    void empty()
    {
    	 combotech.setValue(null);
         id.setText(null);
         timehh.setText(null);
         timemm.setText(null);
        
       bdate.getEditor().clear();
       
         fee.setText(null);
         
    }
    @FXML
    void donew(ActionEvent event) {
    
       empty();
     
     
    }

    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into batchmaster values(null,?,?,?,?)");
			
			//technology//
			pst.setString(1, combotech.getEditor().getText());
			
			//time//
			time();
			pst.setString(2, valt);
			
			//date//
			LocalDate local = bdate.getValue();
			java.sql.Date date= java.sql.Date.valueOf(local);
			pst.setDate(3, date);
			
			//fee//
			pst.setInt(4, Integer.parseInt(fee.getText()));
			
		    
			pst.executeUpdate();
			showMsg("Data Saved");
			filltechs();
		} 
    	
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showMsg("ERROR "+e.getMessage());
		}
    	
    	
    	
    }

    @FXML
    void doupdate(ActionEvent event) {
    	
    	try {
			pst=con.prepareStatement("update batchmaster set combotech=?, time=?, bdate=?, fee=? where id=?");
			pst.setInt(5, Integer.parseInt(id.getText()));
			//technology//
			pst.setString(1, combotech.getEditor().getText());
			
			//time//
			time();
			pst.setString(2, valt);
			
			//date//
			LocalDate local = bdate.getValue();
			java.sql.Date date= java.sql.Date.valueOf(local);
			pst.setDate(3, date);
			
			//fee//
			pst.setInt(4, Integer.parseInt(fee.getText()));
			
			
			int count=pst.executeUpdate();
			if(count==0)
			showMsg("Invalid Id");
			else
				showMsg("Record Updated");
				
		} 
    	
    	catch (SQLException e) {
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
    
   
    
    ObservableList<String> ap;
    void time()
    {
      String hour,minutes,AmPm;
      hour= timehh.getText();
      minutes= timemm.getText();
      AmPm = ampm.getValue();
      
      valt= hour+":"+minutes+":"+AmPm;
      
    }
    

    ArrayList<String> arraytech;
    
    void filltechs()
    {
    	arraytech=new ArrayList<String>();
    	
    	try {
			pst=con.prepareStatement("select distinct combotech from batchmaster");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				String tech=rs.getString("combotech");
				arraytech.add(tech);
			}
			combotech.getItems().clear();
			combotech.getItems().addAll(arraytech);
			
		} 
    	catch (SQLException e) {
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
    	
    	ap= FXCollections.observableArrayList("AM","PM");
    	SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(ap) ; 	
    	valueFactory.setValue("AM");
    	ampm.setValueFactory(valueFactory);
   	   
    	doConnect();
    	 filltechs();
    	
        assert combotech != null : "fx:id=\"combotech\" was not injected: check your FXML file 'BatchMaster.fxml'.";
        assert timehh != null : "fx:id=\"timehh\" was not injected: check your FXML file 'BatchMaster.fxml'.";
        assert timemm != null : "fx:id=\"timemm\" was not injected: check your FXML file 'BatchMaster.fxml'.";
        assert bdate != null : "fx:id=\"bdate\" was not injected: check your FXML file 'BatchMaster.fxml'.";
        assert fee != null : "fx:id=\"fee\" was not injected: check your FXML file 'BatchMaster.fxml'.";

    }
}
