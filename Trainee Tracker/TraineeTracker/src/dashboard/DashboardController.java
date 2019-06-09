package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bm;

    @FXML
    private ImageView reg;

    @FXML
    private ImageView fc;

    @FXML
    private ImageView fi;

    @FXML
    private ImageView bi;

    @FXML
    private ImageView logout;

    @FXML
    private Label bmtxt;

    @FXML
    private Label regtxt;

    @FXML
    private Label fctxt;

    @FXML
    private Label bitxt;

    @FXML
    private Label fitxt;

    @FXML
    void batchmaster(MouseEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("batchMaster/BatchMaster.fxml"));
			Scene scene = new Scene(root,600,510);
			Stage primaryStage =new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	 Stage stage =(Stage) logout.getScene().getWindow();
    	    stage.close();

    }
    
    @FXML
    void reg(MouseEvent event) {

    	try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registerationForm/RegisterationForm.fxml"));
			Scene scene = new Scene(root,608,600);
			Stage primaryStage =new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	 Stage stage =(Stage) logout.getScene().getWindow();
    	    stage.close();
    }
    
    @FXML
    void logout(MouseEvent event) {

    	try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login/Login.fxml"));
			Scene scene = new Scene(root,584,370);
			Stage primaryStage =new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    Stage stage =(Stage) logout.getScene().getWindow();
    stage.close();
    
    }
    

    @FXML
    void fi(MouseEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("feeInfo/FeeInfo.fxml"));
			Scene scene = new Scene(root,603,434);
			Stage primaryStage= new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	 Stage stage =(Stage) logout.getScene().getWindow();
    	    stage.close();

    }
    
    @FXML
    void fc(MouseEvent event) {

    	try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("feeCollector/feeCollector.fxml"));
			Scene scene = new Scene(root,600,400);
			Stage primaryStage =new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	 Stage stage =(Stage) logout.getScene().getWindow();
    	    stage.close();
    }
    

    @FXML
    void bi(MouseEvent event) {
    	
    	try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("batchInfo/batchInfo.fxml"));
			Scene scene = new Scene(root,689,570);
			Stage primaryStage =new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	 Stage stage =(Stage) logout.getScene().getWindow();
    	    stage.close();

    }




    @FXML
    void biunzoom(MouseEvent event) {
    	bi.setFitHeight(140);
    	bi.setFitWidth(129);
    	

    }

    @FXML
    void bizoom(MouseEvent event) {
    	
    	bi.setFitHeight(150);
    	bi.setFitWidth(139);
    	

    }

    @FXML
    void bmunzoom(MouseEvent event) {
    	bm.setFitHeight(129);
    	bm.setFitWidth(131);
    	

    }

    @FXML
    void bmzoom(MouseEvent event) {
    

    	bm.setFitHeight(139);
    	bm.setFitWidth(141);
    	
    }

    @FXML
    void fcunzoom(MouseEvent event) {
    	fc.setFitHeight(146);
    	fc.setFitWidth(159);
    	
    }

    @FXML
    void fczoom(MouseEvent event) {
    	fc.setFitHeight(156);
    	fc.setFitWidth(159);
    	
    }

    @FXML
    void fiunzoom(MouseEvent event) {
    	fi.setFitHeight(129);
    	fi.setFitWidth(140);
    	
    }

    @FXML
    void fizoom(MouseEvent event) {
    	fi.setFitHeight(139);
    	fi.setFitWidth(150);
    	
    }

    @FXML
    void logoutunzoom(MouseEvent event) {
    	logout.setFitHeight(129);
    	logout.setFitWidth(131);
    	
    }

    @FXML
    void logoutzoom(MouseEvent event) {
    	logout.setFitHeight(140);
    	logout.setFitWidth(143);
    	
    }

    @FXML
    void regunzoom(MouseEvent event) {
    	reg.setFitHeight(153);
    	reg.setFitWidth(164);
    	
    }

    @FXML
    void regzoom(MouseEvent event) {
    	reg.setFitHeight(163);
    	reg.setFitWidth(174);
    	
    }

    @FXML
    void initialize() {
        assert bm != null : "fx:id=\"bm\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert reg != null : "fx:id=\"reg\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert fc != null : "fx:id=\"fc\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert fi != null : "fx:id=\"fi\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert bi != null : "fx:id=\"bi\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert bmtxt != null : "fx:id=\"bmtxt\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert regtxt != null : "fx:id=\"regtxt\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert fctxt != null : "fx:id=\"fctxt\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert bitxt != null : "fx:id=\"bitxt\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert fitxt != null : "fx:id=\"fitxt\" was not injected: check your FXML file 'Dashboard.fxml'.";

    }
}
