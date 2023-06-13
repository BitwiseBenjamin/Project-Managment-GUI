package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import DataObjects.Defect;
import DataObjects.Project;
import DataObjects.UserSessionContainer.UserSession;
import DataRequests.DefectDataRequest;
import DataRequests.DataRequest.DataOperation;
import DataRequests.DataRequest.DataType;
import DataRequests.Driver.DataDriver;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewDefect extends BorderPane {
	
	private String[] PointBoxArray;
	
	private Stage primaryStage;
	private Button LogButton, returnButton, logDefectBtn;
	private Label TitleLbl, DescriptionLbl, helperLbl, PageLbl, PointBoxLbl;
	private ComboBox<String> PointBox;
	private StackPane stackPane;
	private Project currProject;
	private TextField TitleText, DescText;
	private Defect d;
	
	
	
	
	
	public ViewDefect(Stage primaryStage, Project P, Defect Defect) {
		this.d = Defect;
		
		currProject = P;

		this.primaryStage = primaryStage;
		this.primaryStage.show();
		
//Initialize buttons and labels and ComboBoxes
	
		LogButton = new Button("Update Defects");
		returnButton = new Button("Return");
		//logDefectBtn = new Button("Return");
		
		//effortBtn = new RadioButton("Effort");
		// defectBtn = new RadioButton("Defect");
		 
		TitleText = new TextField();
		DescText = new TextField();
		
		TitleText.setPromptText("Update Title of Defect");
		DescText.setPromptText("Update Description of Defect");
			
		
		
		
		PointBoxArray = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};		
		PointBox = new ComboBox<>(FXCollections.observableArrayList(PointBoxArray));
		PointBox.setValue("-1");
		PointBoxLbl = new Label("Point Value");
		TitleLbl = new Label("Title");
		DescriptionLbl = new Label("Description");
		helperLbl = new Label("Helper");
		PageLbl = new Label("Defect Updating");
		
		//Set initial values equal to the Defect object values
			PointBox.setValue(String.valueOf(Defect.getPoints()));
			TitleText.setText(Defect.getTitle());
			DescText.setText(Defect.getDescription());
		
		//Set the Effort Button to Default
		//tglGrp = new ToggleGroup();
		//effortBtn.setSelected(true);
		//effortBtn.setToggleGroup(tglGrp);
		//defectBtn.setToggleGroup(tglGrp);
		
		//Style Labels
		PageLbl.setFont(new Font("Arial", 20));
		
		
		
		LogButton.setOnAction(new BHandler());
		returnButton.setOnAction(new ReturnHandler());		
		
		
		this.stackPane = new StackPane();
		stackPane.getChildren().addAll(PointBox, PointBoxLbl, LogButton, returnButton, DescText, TitleText, helperLbl, DescriptionLbl, TitleLbl, PageLbl);

		//set styles
		//logDefectBtn.setPrefSize(120, 30);
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
		//stackPane.setAlignment(logDefectBtn, Pos.TOP_CENTER);
		
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
		//stackPane.setMargin(logDefectBtn, new Insets(80.0, 0, 0, 470.0));
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
			System.out.println("WDITITNG");
			DataDriver.makeRequest(new DefectDataRequest(DataOperation.EDIT, DataType.DEFECT, d.getId(), TitleText.getText(), DescText.getText(), Integer.parseInt(PointBox.getValue())));
			
			
			Button buttonClicked = (Button) event.getSource();
			
			switch(buttonClicked.getText()) {
				case "Log Info": 	
	            	
	            	//Update value in sql??

	    			}
			
		}
		
	}
	
private class ReturnHandler implements EventHandler<ActionEvent> {
		
	
	
	//setup with project page!
		@Override
		public void handle(ActionEvent event) {
			primaryStage.setScene(null);

			 System.out.println("Transferring Back'!");
	         
	         ViewEffort newRoot = new ViewEffort(primaryStage);
	         newRoot.getChildren().addAll();
	         
	         
	         primaryStage.setScene(new Scene(newRoot, 1500, 1250));
	         primaryStage.show();
		}
		
	}




}
