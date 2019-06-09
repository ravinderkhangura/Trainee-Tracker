package feeInfo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import feeInfo.feeBean;


public class FeeInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combotech;

    @FXML
    private TableView<feeBean> feetable;

    @FXML
    private TableColumn<feeBean, Integer> colsid;

    @FXML
    private TableColumn<feeBean, String> colname;

    @FXML
    private TableColumn<feeBean, String> coldate;

    @FXML
    private TableColumn<feeBean, String> coltime;

    @FXML
    private TableColumn<feeBean, Integer> colbalance;
    
    @FXML
    private ImageView back;

    Connection con;
    PreparedStatement pst; 

    @FXML
    void seltech(ActionEvent event) {
    	
ObservableList<feeBean> all=getinfo();
    	

    	feetable.setItems(all);

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
    
   //fee  info//
    
    ObservableList<feeBean> getinfo()
    {
    	ObservableList<feeBean> info=FXCollections.observableArrayList();
    	
    	try {
			pst = con.prepareStatement("select sid, rsname, rdate, rtime, rbalance from registerationform where rtechnology in (?)");
			pst.setString(1,combotech.getValue());
		
			ResultSet rs=  pst.executeQuery();
    	   
			while(rs.next())
			{
				int sid =rs.getInt("sid");
				String rsname = rs.getString("rsname");
				String rdate= rs.getString("rdate");
				String rtime= rs.getString("rtime");
				int rbalance =rs.getInt("rbalance");
				feeBean bean= new feeBean(sid,rsname,rdate,rtime,rbalance);
				info.add(bean);
				
			}
			
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block	
    		e.printStackTrace();
    	}
    	
    	return info;
    	
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
    	
    	colsid.setCellValueFactory(new PropertyValueFactory<>("sid"));
    	colname.setCellValueFactory(new PropertyValueFactory<>("rsname"));
    	coldate.setCellValueFactory(new PropertyValueFactory<>("rdate"));
    	coltime.setCellValueFactory(new PropertyValueFactory<>("rtime"));
    	colbalance.setCellValueFactory(new PropertyValueFactory<>("rbalance"));
    
    	
    	
    	doConnect();
    	filltechs();
    	
    	
    	
    	
        assert combotech != null : "fx:id=\"combotech\" was not injected: check your FXML file 'FeeInfo.fxml'.";
        assert feetable != null : "fx:id=\"feetable\" was not injected: check your FXML file 'FeeInfo.fxml'.";
        assert colsid != null : "fx:id=\"colsid\" was not injected: check your FXML file 'FeeInfo.fxml'.";
        assert colname != null : "fx:id=\"colname\" was not injected: check your FXML file 'FeeInfo.fxml'.";
        assert coldate != null : "fx:id=\"coldate\" was not injected: check your FXML file 'FeeInfo.fxml'.";
        assert coltime != null : "fx:id=\"coltime\" was not injected: check your FXML file 'FeeInfo.fxml'.";
        assert colbalance != null : "fx:id=\"colbalance\" was not injected: check your FXML file 'FeeInfo.fxml'.";

    }
}
