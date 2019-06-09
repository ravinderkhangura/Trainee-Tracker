package batchInfo;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import batchInfo.infoBean;




public class batchInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combotech;

    @FXML
    private ComboBox<String> combodate;

    @FXML
    private ListView<String> timelist;

    @FXML
    private TableView<infoBean> batchtable;

    @FXML
    private TableColumn<infoBean, Integer> colid;

    @FXML
    private TableColumn<infoBean,String> colname;

    @FXML
    private TableColumn<infoBean, Integer> colphone;

    @FXML
    private TableColumn<infoBean, String> colcollege;

    @FXML
    private TableColumn<infoBean, String> colcourse;

    @FXML
    private TableColumn<infoBean, String> colsem;
    
    @FXML
    private ImageView back;

    Connection con;
    PreparedStatement pst; 
    
    String timeselected;
    
    @FXML
    void fetchinfo(ActionEvent event) {
    	
    	ObservableList<infoBean> all=getinfo();
    	
    	batchtable.setItems(all);
    	
    }
    
    //technology//
    ArrayList<String> arrayrtech;   
    void filltechs()
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
 			combotech.getItems().clear();
 			combotech.getItems().addAll(arrayrtech);
 			
 		} 
    	catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	
    }
  
    
    //date//
    ArrayList<String> arraydate;
    @FXML
    void seltech(ActionEvent event) {
    		   
    		   arraydate = new ArrayList<String>();
    		   
    		   try {
    			pst=con.prepareStatement("select distinct bdate from batchmaster where combotech in (?) ");
    			pst.setString(1, combotech.getValue());
    			
    			ResultSet rs=pst.executeQuery();
    			
    			while(rs.next())
    			{
    				java.sql.Date date=rs.getDate("bdate");
    				 LocalDate local = date.toLocalDate();
    				String d = String.valueOf(local);
    				 arraydate.add(d);
    				
    			}
    			combodate.getItems().clear();
    		    combodate.getItems().addAll(arraydate);
    			
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
          }
    
   
    //Time// 
    
    ArrayList<String> arraytime;
    @FXML
    void seldate(ActionEvent event) {
    	
    	
    	 arraytime = new ArrayList<String>();
  	   
  	   try {
  		   
  			pst=con.prepareStatement("select time from batchmaster where combotech =? and bdate= ? ");
  			pst.setString(1, combotech.getValue());
  			pst.setString(2, combodate.getValue());
  			
  			
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
    
    }
    
    //getting info//
    
    ObservableList<infoBean> getinfo()
    {
    	ObservableList<infoBean> info=FXCollections.observableArrayList();
    	
    	try {
			pst = con.prepareStatement("select sid, rsname, rsphone, rcollege, rcourse, rsem from registerationform where rtechnology in (?) and rdate in (?) and rtime in (?)");
			pst.setString(1,combotech.getValue());
			pst.setString(2, combodate.getValue());
			pst.setString(3, timeselected);
			
			ResultSet rs=  pst.executeQuery();
			
    	   
			while(rs.next())
			{
				int sid =rs.getInt("sid");
				String rsname = rs.getString("rsname");
				String rsphone= rs.getString("rsphone");
				String rcollege= rs.getString("rcollege");
				String rcourse= rs.getString("rcourse");
				String rsem= rs.getString("rsem");
				infoBean bean= new infoBean(sid,rsname,rsphone,rcollege,rcourse,rsem);
				info.add(bean);
				
			}
			
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block	
    		e.printStackTrace();
    	}
    	
    	return info;
    	
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
    	colid.setCellValueFactory(new PropertyValueFactory<>("sid"));
    	colname.setCellValueFactory(new PropertyValueFactory<>("rsname"));
    	colphone.setCellValueFactory(new PropertyValueFactory<>("rsphone"));
    	colcollege.setCellValueFactory(new PropertyValueFactory<>("rcollege"));
    	colcourse.setCellValueFactory(new PropertyValueFactory<>("rcourse"));
    	colsem.setCellValueFactory(new PropertyValueFactory<>("rsem"));
    	
    	doConnect();
    	filltechs();
    	
    	
        assert combotech != null : "fx:id=\"combotech\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert combodate != null : "fx:id=\"combodate\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert timelist != null : "fx:id=\"timelist\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert batchtable != null : "fx:id=\"batchtable\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert colid != null : "fx:id=\"colid\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert colname != null : "fx:id=\"colname\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert colphone != null : "fx:id=\"colphone\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert colcollege != null : "fx:id=\"colcollege\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert colcourse != null : "fx:id=\"colcourse\" was not injected: check your FXML file 'batchInfo.fxml'.";
        assert colsem != null : "fx:id=\"colsem\" was not injected: check your FXML file 'batchInfo.fxml'.";

    }
}
