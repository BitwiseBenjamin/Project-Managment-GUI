package UI;

import java.util.ArrayList;

import DataObjects.Project;
import DataObjects.UserSessionContainer.UserSession;
import javafx.application.Platform;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LandingPage extends BorderPane {
	
	// attributes 
	private Stage primaryStage;
	private ArrayList<Project> projects;
	private Label companyLabel, welcomeLabel, projectLabel;
	private Button projectLaunchButton, logoutButton;
	
	// containers for attributes
	private VBox headLabelsBox;		// holds the company name and welcome labels vertically
	private HBox headingBox;		// holds the labels and the logout button horizontally
	
	private GridPane projectsGrid;
	private VBox projectDetailsBox;
	
	// constructor
	public LandingPage(ArrayList<Project> projects, Stage primaryStage, String companyName, String userFirstName) {
		this.primaryStage = primaryStage;
		this.projects = projects;

		
		logoutButton = new Button("Log Out");
		logoutButton.setOnAction(new LogOutHandler());
		logoutButton.setPrefSize(100, 50);
		
		// create the labels for the welcoming part of the top part of the page
		companyLabel = new Label(companyName);
		companyLabel.setStyle("-fx-padding: 10 20 0 20;-fx-font-size: 50;-fx-alignment: center-left");
		
		welcomeLabel = new Label("Welcome to your projects, " + userFirstName);
		welcomeLabel.setStyle("-fx-padding: 0 20 10 20;-fx-font-size: 25;-fx-alignment: center-left");
		
		// Initialing the containers for the welcome heading labels
		headLabelsBox = new VBox();
		headLabelsBox.getChildren().addAll(companyLabel, welcomeLabel);
		
		// create the container for the header which includes the welcome labels and the logout button
		headingBox = new HBox();
		headingBox.getChildren().addAll(headLabelsBox, logoutButton);
		HBox.setHgrow(headLabelsBox, Priority.ALWAYS);
		HBox.setHgrow(logoutButton, Priority.ALWAYS);
		headingBox.setStyle("-fx-padding: 20 20 10 20;");

		
		projectsGrid = new GridPane();
		projectsGrid.setMaxWidth(Double.MAX_VALUE);
		projectsGrid.setMaxHeight(Double.MAX_VALUE);
		
		this.fillProjectGrid();
		
		this.setTop(headingBox);
		this.setCenter(projectsGrid);
		
	}
	
	// methods
	private void fillProjectGrid() {
		
		try {
			int i = 20;
			int j = 20;
			for(Project project: projects) {
				System.out.println(project.getName());
				projectLaunchButton = new Button("Launch");
				projectLaunchButton.setOnAction(new LaunchHandler(project));
				projectDetailsBox = new VBox();
				projectLabel = new Label();
				
				fillProjectDetailsBox(project);
				
				projectsGrid.add(projectDetailsBox, i, j);
				i += 20;
				if (i >= 80) {
					i = 20;
					j += 20;
				}
				
			}
		} catch(NullPointerException ne) {
						
			for(int i = 0; i < 5; i++) {
				projectLaunchButton = new Button("Launch");
				projectLaunchButton.setOnAction(new LaunchHandler(null));
				projectDetailsBox = new VBox();
				projectLabel = new Label();
				
				System.out.println("Not supposed to happen...");
				//fillProjectDetailsBox(null);
				// TODO make the gridpane add the vboxes in columns and rows
				projectsGrid.add(projectDetailsBox, i, 0);
				projectsGrid.setHgap(10);
				projectsGrid.setVgap(10);
				projectsGrid.setStyle("-fx-padding: 20 20 10 20");

				
			}
		}
		
	}
	
	// paramater should be Project project
	private void fillProjectDetailsBox(Project p) {
		
	
		// String name = project.getName();
		String name = p.getName();
		projectLabel.setText(name);
		
		projectLaunchButton.setStyle("-fx-font-size: 20px;");
		projectLaunchButton.setPrefSize(250, 50);
		
		projectDetailsBox.getChildren().addAll(projectLabel, projectLaunchButton);
		projectDetailsBox.setStyle("-fx-background-color: gray; -fx-padding: 20 20 10 20; -fx-alignment: center; -fx-spacing: 10");
		projectDetailsBox.setPrefSize(250, 250);
		VBox.setVgrow(projectLabel, Priority.ALWAYS);

		
	}
	
	// pass in parameteres necessary to load the page
	// get the values of the parameters from the DB
	private void loadProjectPage(Project project) {
		
		primaryStage.setScene(null);
		
		UserSession.setProject(project);

        StackPane newRoot = new StackPane();
        ProjectPage projectPage = new ProjectPage(project, primaryStage);
        
        newRoot.getChildren().add(projectPage);
        
        primaryStage.setScene(new Scene(newRoot, 1000, 1000));
        primaryStage.show();
		
	}
	
	private class LogOutHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			System.out.println("LOGGED OUT");
			Platform.exit();
		}
		
	}
	
	private class LaunchHandler implements EventHandler<ActionEvent> {

		// consider adding some methods to getset methods and variables to recieve paramater input so we know what to send the DB
		Project project;
		
		private LaunchHandler(Project p) {
			this.project = p;
		}
		
		@Override
		public void handle(ActionEvent event) {
			System.out.println(project.getName() + " launched");
			loadProjectPage(project);
		}
		
	}
	

	// createClickableProject which uses the list of projects and adds a button to each of them to switch to that project

}