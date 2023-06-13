package DataRequests;

public class DefectDataRequest extends DataRequest {
	
	// Defect fields
	private int id;
	private String project;
	private String title;
	private String description;
	private int severity;
	private String userLogging;
	
	public DefectDataRequest(DataOperation operation, DataType dataType, String project, String title, String description, int severity, String userLogging) { // Constructor for inserting
		super(operation, dataType);
		this.project = project;
		this.title = title;
		this.description = description;
		this.severity = severity;
		this.userLogging = userLogging;
	}
	
	public DefectDataRequest(DataOperation operation, DataType dataType, String project, String userLogging) { // Constructor for viewing as an engineer
		super(operation, dataType);
		this.project = project;
		this.userLogging = userLogging;
	}
	
	public DefectDataRequest(DataOperation operation, DataType dataType, String project) { // Constructor for viewing as a manager
		super(operation, dataType);
		this.project = project;
		this.userLogging = null;
	}
	
	// Constructor for editing a defect
	
	public DefectDataRequest(DataOperation operation, DataType dataType, int id, String title, String description, int severity) { // Constructor for inserting
		super(operation, dataType);
		this.id = id;
		this.title = title;
		this.description = description;
		this.severity = severity;
	}
	
	public Query store() { // Query for storage
		String preparameterizedQuery = "INSERT INTO Defects (project, title, description, severity, user_logging) VALUES (?, ?, ?, ?, ?);";
		Object[] parameters = {this.project, this.title, this.description, this.severity, this.userLogging};
		return new Query(preparameterizedQuery, parameters);
	}
	
	public Query load() { // Query for retrieval
		if (this.userLogging == null) { // If the user is a manager (no user specified)
			String preparameterizedQuery = "SELECT * FROM Defects WHERE project=?;";
			String[] parameters = {this.project};
			return new Query(preparameterizedQuery, parameters);
		} else { // If the user is an engineer
			String preparameterizedQuery = "SELECT * FROM Defects WHERE project=? AND user_logging=?;";
			String[] parameters = {this.project, this.userLogging};
			return new Query(preparameterizedQuery, parameters);
		}
	}
	
	public Query edit() { // Query for retrieval
		String preparameterizedQuery = "UPDATE Defects SET title=?, description=?, severity=? WHERE defect_id=?;";
		Object[] parameters = {this.title, this.description, this.severity, this.id};
		return new Query(preparameterizedQuery, parameters);
	}
}
