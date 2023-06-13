package DataRequests;

public class EffortDataRequest extends DataRequest {
	
	// Period for requesting effort
	public enum DataPeriod {
		WEEK,
		DAY
	}
	
	// Effort fields
	private String project;
	private String title;
	private String description;
	private int points;
	private String userLogging;
	private DataPeriod period;
	
	public EffortDataRequest(DataOperation operation, DataType dataType, String project, String title, String description, int points, String userLogging) { // Constructor for inserting
		super(operation, dataType);
		this.project = project;
		this.title = title;
		this.description = description;
		this.points = points;
		this.userLogging = userLogging;
	}
	
	public EffortDataRequest(DataOperation operation, DataType dataType, DataPeriod dataPeriod, String project, String userLogging) { // Constructor for if user is an engineer
		super(operation, dataType);
		this.project = project;
		this.userLogging = userLogging;
		this.period = dataPeriod;
	}
	
	public EffortDataRequest(DataOperation operation, DataType dataType, DataPeriod dataPeriod, String project) { // Constructor for if user is a manager
		super(operation, dataType);
		this.project = project;
		this.userLogging = null;
		this.period = dataPeriod;
	}
	
	public Query store() { // Effort storage query
		String preparameterizedQuery = "INSERT INTO Efforts (project, title, description, points, user_logging) VALUES (?, ?, ?, ?, ?);";
		Object[] parameters = {this.project, this.title, this.description, this.points, this.userLogging};
		return new Query(preparameterizedQuery, parameters);
	}
	
	public Query load() { // Effort retrieval query
		if (this.userLogging == null) { // If the current user is a manager
			if (this.period == DataPeriod.WEEK) { // If the user is requesting week-by-week data
				String preparameterizedQuery = "SELECT project, title, description, points, user_logging, strftime('%W',log_timestamp) FROM Efforts WHERE project=?;";
				String[] parameters = {this.project};
				return new Query(preparameterizedQuery, parameters);
			} else { // If the user is requesting day-by-day data
				String preparameterizedQuery = "SELECT project, title, description, points, user_logging, strftime('%d',log_timestamp) FROM Efforts WHERE project=? AND strftime('%W',log_timestamp)=strftime('%W',CURRENT_TIMESTAMP);";
				String[] parameters = {this.project};
				return new Query(preparameterizedQuery, parameters);
			}
		} else { // If the current user is an engineer
			if (this.period == DataPeriod.WEEK) { // If the user is requesting week-by-week data
				String preparameterizedQuery = "SELECT project, title, description, points, user_logging, strftime('%W',log_timestamp) FROM Efforts WHERE project=? AND user_logging=?;";
				String[] parameters = {this.project, this.userLogging};
				return new Query(preparameterizedQuery, parameters);
			} else { // If the user is requesting day-by-day data
				String preparameterizedQuery = "SELECT project, title, description, points, user_logging, strftime('%d',log_timestamp) FROM Efforts WHERE project=? AND user_logging=? AND strftime('%W',log_timestamp)=strftime('%W',CURRENT_TIMESTAMP);";
				String[] parameters = {this.project, this.userLogging};
				return new Query(preparameterizedQuery, parameters);
			}
		}
	}
	
	public String getUserLogging() {
		return userLogging;
	}
	
	public String getProject() {
		return project;
	}
	
	public DataPeriod getDataPeriod() {
		return period;
	}

	@Override
	public Query edit() {
		// TODO Auto-generated method stub
		return null;
	}
}
