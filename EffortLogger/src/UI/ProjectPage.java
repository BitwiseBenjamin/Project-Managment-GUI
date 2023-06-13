package UI;

import java.util.ArrayList;

import DataObjects.Project;
import DataObjects.UserSessionContainer.UserSession;
import DataRequests.ProjectDataRequest;
import DataRequests.DataRequest.DataOperation;
import DataRequests.DataRequest.DataType;
import DataRequests.Driver.DataDriver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProjectPage extends BorderPane {

	// attributes
	private Button returnButton, logEffortButton, logDefectButton, viewEffortButton, viewDefectsButton;
	private Label projectNameLabel;
	private Stage primaryStage;
	
	//containers
	private GridPane actionGrid;
	private HBox headingBox;
	
	// constructors
	public ProjectPage(Project p, Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		projectNameLabel = new Label(p.getName());
		projectNameLabel.setStyle("-fx-padding: 10 20 0 20;-fx-font-size: 50;-fx-alignment: center-left");
		
		returnButton = new Button("Return to All Projects"); 
		returnButton.setPrefSize(150, 50);
		returnButton.setOnAction(new ReturnToLandingHandler());
		
		logEffortButton = new Button("LOG EFFORT");
		logEffortButton.setOnAction(new LogEffortHandler());
		logDefectButton = new Button("LOG DEFECTS");
		logDefectButton.setOnAction(new LogDefectHandler());
		viewEffortButton = new Button("VIEW EFFORT");
		viewEffortButton.setOnAction(new ViewEffortHandler());
		viewDefectsButton = new Button("VIEW DEFECTS");
		viewDefectsButton.setOnAction(new ViewDefectHandler());

		
		headingBox = new HBox();
		headingBox.getChildren().addAll(projectNameLabel, returnButton);
		HBox.setHgrow(projectNameLabel, Priority.ALWAYS);
		HBox.setHgrow(returnButton, Priority.ALWAYS);
		headingBox.setStyle("-fx-padding: 20 20 10 20;-fx-background-color: red");
		
		actionGrid = new GridPane();
		actionGrid.setHgap(10);
		actionGrid.setVgap(10);
		actionGrid.setStyle("-fx-padding: 20 20 10 20");
		actionGrid.add(logEffortButton, 0,0);
		actionGrid.add(logDefectButton, 1,0);
		actionGrid.add(viewEffortButton, 0,1);
		actionGrid.add(viewDefectsButton, 1,1);
		actionGrid.setMaxWidth(Double.MAX_VALUE);
		actionGrid.setMaxHeight(Double.MAX_VALUE);
		actionGrid.setStyle("-fx-background-color: blue;");


		//this.getChildren().addAll(headingBox, actionGrid);
		this.setTop(headingBox);
		this.setCenter(actionGrid);
		
	}
	
	// methods
	
	// get the values of the parameters from the DB
	private void returnToLanding() {
		
		System.out.println("Transferring to 'Landing Page'!");
        
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
	
	private class ReturnToLandingHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			System.out.println("Returning to landing page!");
			returnToLanding();
		}
		
	}
	
	private class LogEffortHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			System.out.println("Going to log effort page!");
	        
			primaryStage.setScene(null);
	      
			 LogEffortPage hi = new LogEffortPage(primaryStage, UserSession.getProject());
			 
			 StackPane newRoot = new StackPane();
			 
			 Scene scene = new Scene(hi.returnPane(newRoot), 600, 400);
		    	
				primaryStage.setScene(scene);
		        primaryStage.show();
		}
		
	}
	
	private class LogDefectHandler implements EventHandler<ActionEvent> {
		
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
	
	private class ViewEffortHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			System.out.println("Going to view effort page!");
	        
	        primaryStage.setScene(null);

	        StackPane newRoot = new StackPane();
	        ViewEffort viewing = new ViewEffort(primaryStage);
	        
	        newRoot.getChildren().add(viewing);
	        
	        primaryStage.setScene(new Scene(newRoot, 1500, 1250));
	        primaryStage.show();
		}
		
	}
	
	private class ViewDefectHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			System.out.println("Going to view defect page!");
	        
	        primaryStage.setScene(null);

	        StackPane newRoot = new StackPane(); 
	        ViewEffort viewing = new ViewEffort(primaryStage);
	        
	        newRoot.getChildren().add(viewing);
	        
	        primaryStage.setScene(new Scene(newRoot, 1500, 1250));
	        primaryStage.show();
		}
		
	}
}