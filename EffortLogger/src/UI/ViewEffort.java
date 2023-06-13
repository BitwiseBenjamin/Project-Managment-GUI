package UI;


/*
 *Author: Matthew Van De Velde 
 *Group: M32
 */


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import DataObjects.Defect;
import DataObjects.Effort;
import DataObjects.Project;
import DataObjects.UserSessionContainer.UserSession;
import DataRequests.DataRequest.DataOperation;
import DataRequests.DataRequest.DataType;
import DataRequests.DefectDataRequest;
import DataRequests.Driver.DataDriver;
import DataRequests.EffortDataRequest;
import DataRequests.ProjectDataRequest;
import DataRequests.EffortDataRequest.DataPeriod;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class ViewEffort extends BorderPane {
    
    @SuppressWarnings("unchecked")
    private Stage primaryStage;
	@SuppressWarnings("unchecked")
	public ViewEffort(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    	
    	//Set title of Page
        primaryStage.setTitle("Effort Logger");
        
       
        
        // Create the X and Y axis
        final NumberAxis xAxis_Line = new NumberAxis();
        final NumberAxis yAxis_Line = new NumberAxis();
        
        // Create the line chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis_Line, yAxis_Line);
        
        // Set the chart titles
        lineChart.setTitle("Effort Logged for Current Sprint");
        // label x-Axis
        xAxis_Line.setLabel("Week Number");
        
        // label y-Axis
        yAxis_Line.setLabel("Effort Level Logged");
        
        // Retrieve data from backend database
        XYChart.Series series = new XYChart.Series();
        
        XYChart.Series seriesTEST= new XYChart.Series();
        seriesTEST.getData().add(new XYChart.Data(0, 0));
        seriesTEST.getData().add(new XYChart.Data(1, 7));
        seriesTEST.getData().add(new XYChart.Data(2, 4));
        seriesTEST.getData().add(new XYChart.Data(3, 5));
        seriesTEST.getData().add(new XYChart.Data(4, 7));
        seriesTEST.getData().add(new XYChart.Data(5, 4)); 
        
        
        List<Effort> efforts = null;
        
        if (UserSession.getProject().getPermissionLevel() == 0) { // If user is not a manager
        	System.out.println(new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, DataPeriod.WEEK, UserSession.getProject().getName(), UserSession.getUser().getUsername()).getQuery().getPreparameterizedQuery());
			efforts = (List<Effort>) DataDriver.makeRequest(new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, DataPeriod.WEEK, UserSession.getProject().getName(), UserSession.getUser().getUsername()));
        } else {
			efforts = (List<Effort>) DataDriver.makeRequest(new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, DataPeriod.WEEK, UserSession.getProject().getName())); //Else get data from effort Logged 
        }
        
        Hashtable<Integer, Integer> cumSum = new Hashtable<Integer, Integer>();
        if (efforts != null) {
	        for (Effort effort : efforts) {
	        	if (cumSum.get(effort.getTimeSlot()) == null) { // Record not in there
	        		cumSum.put(effort.getTimeSlot(), effort.getPoints());
	        	} else {
	        		int existing = cumSum.get(effort.getTimeSlot()); // Get the time 
	        		cumSum.remove(effort.getTimeSlot());
	        		cumSum.put(effort.getTimeSlot(), effort.getPoints() + existing); //Get the points of effort
	        	}
	        }
	        
	        for (int timeSlot : cumSum.keySet()) {
	        	series.getData().add(new XYChart.Data(timeSlot, cumSum.get(timeSlot))); // Add time and effort to graph 
	        }
        }
        
        
        
       
        // Add the data to the chart
        lineChart.getData().add(series); //Create the chart from data.

       
        
        
        
        
        // Create Stacked Area Chart x-Axis
        CategoryAxis Stacked_xAxis = new CategoryAxis();
        
        // Name the x-Axis
        Stacked_xAxis.setLabel("Time Period");

        // Create Stacked Area Chart y-axis
        NumberAxis Stacked_yAxis = new NumberAxis();
        
        // Name the y-Axis
        Stacked_yAxis.setLabel("Effort Level Logged");
        
        

        // Create a stacked area chart
        StackedAreaChart<String, Number> stackedAreaChart = new StackedAreaChart<>(Stacked_xAxis, Stacked_yAxis);
        stackedAreaChart.setTitle("Current Sprint vs LifeTime Average");
        
        
        // Retrieve data for Effort logged this sprint
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Effort Logged This Sprint");
        
        
        List<Effort> efforts2 = null;
        if (UserSession.getProject().getPermissionLevel() == 0) { // If user is not a manager
        	efforts2 = (List<Effort>) DataDriver.makeRequest(new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, DataPeriod.DAY, UserSession.getProject().getName(), UserSession.getUser().getUsername()));
        } else {
        	efforts2 = (List<Effort>) DataDriver.makeRequest(new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, DataPeriod.DAY, UserSession.getProject().getName())); // Make call to Effort data request for data to put into graph
        }
        
        if (efforts2 != null) {
	        Hashtable<Integer, Integer> cumSum2 = new Hashtable<Integer, Integer>();
	        for (Effort effort : efforts2) {
	        	if (cumSum2.get(effort.getTimeSlot()) == null) { // Record not in there
	        		cumSum2.put(effort.getTimeSlot(), effort.getPoints());
	        	} else {
	        		int existing = cumSum2.get(effort.getTimeSlot());
	        		cumSum2.remove(effort.getTimeSlot());
	        		cumSum2.put(effort.getTimeSlot(), effort.getPoints() + existing);
	        	}
	        }
	        
	        for (int timeSlot : cumSum2.keySet()) {
	        	series1.getData().add(new XYChart.Data(timeSlot, cumSum2.get(timeSlot)));
	        }
        }
        
        

        // Retrieve data for all time average
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("All Time Average");
        
        List<Effort> efforts3 = null;
        if (UserSession.getProject().getPermissionLevel() == 0) { // If user is not a manager
        	efforts3 = (List<Effort>) DataDriver.makeRequest(new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, DataPeriod.DAY, UserSession.getProject().getName(), UserSession.getUser().getUsername()));
        } else {
        	efforts3 = (List<Effort>) DataDriver.makeRequest(new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, DataPeriod.DAY, UserSession.getProject().getName())); //Make request to data base 
        }
        
        if (efforts3 != null) {
	        Hashtable<Integer, Integer> cumSum3 = new Hashtable<Integer, Integer>(); 
	        for (Effort effort : efforts3) {
	        	if (cumSum3.get(effort.getTimeSlot()) == null) { // Record not in there
	        		cumSum3.put(effort.getTimeSlot(), effort.getPoints());
	        	} else {
	        		int existing = cumSum3.get(effort.getTimeSlot()); // get the time for the graph 
	        		cumSum3.remove(effort.getTimeSlot());
	        		cumSum3.put(effort.getTimeSlot(), effort.getPoints() + existing); // get the points for the graph
	        	}
	        }
	        
	        for (int timeSlot : cumSum3.keySet()) {
	        	series2.getData().add(new XYChart.Data(timeSlot, cumSum3.get(timeSlot))); // put the time and points in the graph
	        }
        }
        
        
        
        
        // Get the data after connections and create the Chart
        stackedAreaChart.getData().addAll(series1, series2);
        
        
        
        // Create a border pane 
      
        this.setLeft(lineChart);

        // Create a scene with the border pane 
        this.setCenter(stackedAreaChart);
        
        // Create back button for Return to Landing button
        Button backButton = new Button("Return to Landing"); 
        backButton.setOnAction(new ReturnToLandingHandler()); // back button 
        
   
      
		this.setTop(backButton);
		
      
		

		
		// Create Boxes for Text box
		VBox textBoxes = new VBox();
		textBoxes.setSpacing(100); // Set spacing

        List<Defect> defects = null;
		// Get list of defects!
        if (UserSession.getProject().getPermissionLevel() == 0) { // If user is not a manager
        	defects = (List<Defect>) DataDriver.makeRequest(new DefectDataRequest(DataOperation.SELECT, DataType.DEFECT, UserSession.getProject().getName(), UserSession.getUser().getUsername()));
        } else {
        	defects = (List<Defect>) DataDriver.makeRequest(new DefectDataRequest(DataOperation.SELECT, DataType.DEFECT, UserSession.getProject().getName())); // Make call to Defect data request for data
        }
        
        if (defects != null) {
        
			int numFields = defects.size();
			// Create an array to hold the TextFields and edit buttons
			TextField[] textFields = new TextField[numFields ];
			Button[] viewButtons = new Button[numFields];
	
			int i = 0;
			// Create the HBox and TextFields
			for (Defect d : defects) {
			    HBox textBox_HBox = new HBox();
			    viewButtons[i] = new Button("View Defect");
			    viewButtons[i].setOnAction(new ViewDefectHandler(d)); // View defect handler
			    textFields[i] = new TextField();
			    textFields[i].setText(d.getTitle()); // Add text to the TextField
			    textBox_HBox.getChildren().addAll(textFields[i], viewButtons[i]);
			    textBoxes.getChildren().add(textBox_HBox);
			    i++;
			}
        }

		textBoxes.setPadding(new Insets(150)); // set spacing

		// Create a ScrollPane and set the content to the VBox
		ScrollPane scrollPane = new ScrollPane(textBoxes);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefViewportWidth(500); // set preferred width of viewport

		// Create the Pane for text boxes
		//BorderPane textBox_Root = new BorderPane(); 
		scrollPane.setStyle("-fx-border-color: black; -fx-border-width: 1px;"); // Create the black line for border space and thickness 
		//root.setRight(textBoxes); // Set to right side of screen
		this.setRight(scrollPane);
		primaryStage.show();    // Show both charts and the text boxes 
        
   
    
	}
	
	
	 //get the values of the parameters from the DB
  	private void returnToLanding() {
  		
  		System.out.println("Transferring to 'Landing Page'!");
       
       primaryStage.setScene(null);

       StackPane newRoot = new StackPane(); 
       @SuppressWarnings("unchecked")
  		ArrayList<Project> projects = (ArrayList<Project>) DataDriver.makeRequest(new ProjectDataRequest(DataOperation.SELECT, DataType.PROJECT, UserSession.getUser().getUsername()));
       LandingPage landing = new LandingPage(projects, primaryStage, "Company X", UserSession.getUser().getFirstName()); //Get name for the page 
       
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
  	
  	 //get the values of the parameters from the DB
  	private void viewDefect(Defect defect) {
       
       primaryStage.setScene(null);
       @SuppressWarnings("unchecked")
       ViewDefect d = new ViewDefect(primaryStage, UserSession.getProject(), defect);
		 
		 StackPane newRoot = new StackPane();
		 
		 Scene scene = new Scene(d.returnPane(newRoot), 600, 400);
	    	
			primaryStage.setScene(scene);
	        primaryStage.show();
  		
  	}
  	
  	private class ViewDefectHandler implements EventHandler<ActionEvent> {
  		Defect d;
  		
  		public ViewDefectHandler(Defect d) {
  			this.d = d;
  		}
  		
  		@Override
  		public void handle(ActionEvent event) {
  			System.out.println("Viewing defect!");
  			viewDefect(this.d);
  		}
  		
  	}
 


	}