package UI;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import DataObjects.User;
import DataObjects.Project;
import DataObjects.UserSessionContainer.UserSession;
import DataRequests.DataRequest.DataOperation;
import DataRequests.DataRequest.DataType;
import DataRequests.Driver.DataDriver;
import DataRequests.ProjectDataRequest;
import DataRequests.UserDataRequest;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
 
public class LoginPage extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws SQLException {
    	DataDriver.initializeRequestManager();
    	
        primaryStage.setTitle("Login to Effortlogger");
        Button btn = new Button();
        TextField user = new TextField();
        PasswordField pass = new PasswordField();
        btn.setText("Login");
        btn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
            	
            	// SHA-256 algorithm
            	MessageDigest digest = null;
				try {
					digest = MessageDigest.getInstance("SHA-256");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Hash the password into bytes
            	byte[] encodedhash = digest.digest(
            			pass.getText().getBytes(StandardCharsets.UTF_8));
            	
            	// Get the string from the hashed password
            	StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
                for (int i = 0; i < encodedhash.length; i++) {
                    String hex = Integer.toHexString(0xff & encodedhash[i]);
                    if(hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
            	
                String hashedPass = hexString.toString().substring(0, Math.min(hexString.toString().length(), 32));
                
                System.out.println("Username: " + user.getText());
                System.out.println("Password: " + pass.getText());
                System.out.println("Hashed password: " + hashedPass);
                
                // Use hashed password to compare to DB
                User loggedInUser = (User) DataDriver.makeRequest(new UserDataRequest(DataOperation.SELECT, DataType.USER, user.getText(), hashedPass));
                
                if (loggedInUser.getFirstName() == null) {
                	return;
                }
                UserSession.setUser(loggedInUser);
                
                @SuppressWarnings("unchecked")
				ArrayList<Project> projects = (ArrayList<Project>) DataDriver.makeRequest(new ProjectDataRequest(DataOperation.SELECT, DataType.PROJECT, UserSession.getUser().getUsername()));

                System.out.println("Transferring to 'Landing Page'!");
                
                primaryStage.setScene(null);

                StackPane newRoot = new StackPane();
                LandingPage landing = new LandingPage(projects, primaryStage, "Company X", loggedInUser.getFirstName());
                
                newRoot.getChildren().add(landing);
                
                primaryStage.setScene(new Scene(newRoot, 1500, 1250));
                primaryStage.show();
            }
        });
        
        GridPane root = new GridPane();
        Label l = new Label("Enter Login Info");
        Label userl = new Label("Username:");
        Label passl = new Label("Password:");
        //root.getChildren().add(btn);
        root.add(l, 0, 1);
        root.add(userl, 1, 1);
        root.add(user, 2, 1);
        root.add(passl, 1, 2);
        root.add(pass, 2, 2);
        root.add(btn, 2, 3);
        
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33);
        ColumnConstraints column3 = new ColumnConstraints();
        column2.setPercentWidth(33);
        root.getColumnConstraints().addAll(column1, column2, column3);
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }
}