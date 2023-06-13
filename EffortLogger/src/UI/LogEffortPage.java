package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DataObjects.Effort;
import DataObjects.Project;
import DataObjects.UserSessionContainer.UserSession;
import DataRequests.ProjectDataRequest;
import DataRequests.DataRequest.DataOperation;
import DataRequests.DataRequest.DataType;
import DataRequests.Driver.DataDriver;
import DataRequests.EffortDataRequest;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LogEffortPage extends BorderPane {
	
	private String[] PointBoxArray;
	
	private Stage primaryStage;
	private Button LogButton, returnButton, logDefectBtn;
	private Label TitleLbl, DescriptionLbl, helperLbl, PageLbl, PointBoxLbl;
	private ComboBox<String> PointBox;
	private StackPane stackPane;
	private Project currProject;
	private TextField TitleText, DescText;
	
	
	
	
	
	public LogEffortPage(Stage primaryStage, Project P) {
		
		currProject = P;
		
		this.primaryStage = primaryStage;
		
//Initialize buttons and labels and ComboBoxes
	
		LogButton = new Button("Log Info");
		returnButton = new Button("Return");
		logDefectBtn = new Button("Log Defects");
		
		//effortBtn = new RadioButton("Effort");
		// defectBtn = new RadioButton("Defect");
		 
		TitleText = new TextField();
		DescText = new TextField();
		
		TitleText.setPromptText("Enter Title of Effort");
		DescText.setPromptText("Enter Description of Effort");
			
		
		
		
		PointBoxArray = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};		
		PointBox = new ComboBox<>(FXCollections.observableArrayList(PointBoxArray));
		PointBox.setValue("-1");
		PointBoxLbl = new Label("Point Value");
		TitleLbl = new Label("Title");
		DescriptionLbl = new Label("Description");
		helperLbl = new Label("Helper");
		PageLbl = new Label("Effort Logging");
		
		//Set the Effort Button to Default
		//tglGrp = new ToggleGroup();
		//effortBtn.setSelected(true);
		//effortBtn.setToggleGroup(tglGrp);
		//defectBtn.setToggleGroup(tglGrp);
		
		//Style Labels
		PageLbl.setFont(new Font("Arial", 20));
		
		
		logDefectBtn.setOnAction(new defectBtnHandler());
		LogButton.setOnAction(new BHandler());
		returnButton.setOnAction(new ReturnHandler());		
		
		
		this.stackPane = new StackPane();
		stackPane.getChildren().addAll(PointBox, PointBoxLbl, logDefectBtn, LogButton, returnButton, DescText, TitleText, helperLbl, DescriptionLbl, TitleLbl, PageLbl);

		//set styles
		logDefectBtn.setPrefSize(120, 30);
		LogButton.setPrefSize(120, 30);
		returnButton.setPrefSize(120, 30);
		
		DescText.setMaxSize(160, 30);
		TitleText.setMaxSize(160, 30);
		helperLbl.setPrefSize(120, 30);
		DescriptionLbl.setPrefSize(120, 30);
		TitleLbl.setPrefSize(120, 30);
		PageLbl.setPrefSize(250, 30);
		
		PointBox.setPrefSize(120, 30);
		PointBoxLbl.setPrefSize(120, 30);
		
		//do not change these!!!
		stackPane.setAlignment(LogButton, Pos.TOP_LEFT);
		
		stackPane.setAlignment(returnButton, Pos.TOP_LEFT);
		stackPane.setAlignment(logDefectBtn, Pos.TOP_CENTER);
		
		stackPane.setAlignment(PointBoxLbl, Pos.TOP_LEFT);
		stackPane.setAlignment(PointBox, Pos.TOP_LEFT);

		stackPane.setAlignment(DescText, Pos.TOP_LEFT);
		stackPane.setAlignment(TitleText, Pos.TOP_LEFT);

		stackPane.setAlignment(helperLbl, Pos.TOP_LEFT);
		stackPane.setAlignment(DescriptionLbl, Pos.TOP_LEFT);
		stackPane.setAlignment(TitleLbl, Pos.TOP_LEFT);
		stackPane.setAlignment(PageLbl, Pos.TOP_LEFT);

		stackPane.setMargin(DescText, new Insets(160.0, 0, 0, 210.0));
		stackPane.setMargin(TitleText, new Insets(90.0, 0, 0, 210.0));
		stackPane.setMargin(DescriptionLbl, new Insets(135.0, 0, 0, 210.0));
		stackPane.setMargin(TitleLbl, new Insets(65.0, 0, 0, 210.0));
		stackPane.setMargin(helperLbl, new Insets(110.0, 0, 0, 40.0));
		stackPane.setMargin(PageLbl, new Insets(15.0, 0, 0, 30.0));
		stackPane.setMargin(LogButton, new Insets(325.0, 0, 0, 210.0));
		stackPane.setMargin(logDefectBtn, new Insets(80.0, 0, 0, 470.0));
		stackPane.setMargin(returnButton, new Insets(10.0, 0, 0, 470.0));
		
		stackPane.setMargin(PointBoxLbl, new Insets(250.0, 0, 0, 210.0));
		stackPane.setMargin(PointBox, new Insets(280.0, 0, 0, 210.0));
	
    }
		
	public StackPane returnPane(StackPane newRoot) {
		
		newRoot = this.stackPane;
		return newRoot;
	}
	
	
	//methods and event handlers
	private class BHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			Button buttonClicked = (Button) event.getSource();
			
			switch(buttonClicked.getText()) {
				case "Log Info": 	
	            	DataDriver.makeRequest(new EffortDataRequest(DataOperation.STORE, DataType.EFFORT, UserSession.getProject().getName(), TitleText.getText(), DescText.getText(), Integer.parseInt(PointBox.getValue()), UserSession.getUser().getUsername()));
				
			}
			
		}
		
	}
	
private void returnToLanding() {
		
		System.out.println("Transferring to 'Landing Page'!awdasdasd");
     
     primaryStage.setScene(null);

     StackPane newRoot = new StackPane(); 
     @SuppressWarnings("unchecked")
		ArrayList<Project> projects = (ArrayList<Project>) DataDriver.makeRequest(new ProjectDataRequest(DataOperation.SELECT, DataType.PROJECT, UserSession.getUser().getUsername()));
     LandingPage landing = new LandingPage(projects, primaryStage, "Company X", UserSession.getUser().getFirstName());
     
     UserSession.setProject(null);
     
     newRoot.getChildren().add(landing);
     
     primaryStage.setScene(new Scene(newRoot, 1500, 1250));
     primaryStage.show();
		
	}
	
	private class ReturnHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			System.out.println("Returning to landing page!");
			returnToLanding();
		}
		
	}



private class defectBtnHandler implements EventHandler<ActionEvent> {
		
	
	
	//setup with project page!
		@Override
		public void handle(ActionEvent event) {
			primaryStage.setScene(null);

			 System.out.println("Transferring to Defect Page'!");
	      
			 
	         LogDefectPage hi = new LogDefectPage(primaryStage, UserSession.getProject());
	         StackPane newRoot = new StackPane();
			 
			 Scene scene = new Scene(hi.returnPane(newRoot), 600, 400);
		    	
	 		primaryStage.setScene(scene);
	        primaryStage.show();
		
	}
		

}
}