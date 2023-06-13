package DataRequests;

public class ProjectDataRequest extends DataRequest {
	
	// Project fields
	private String username;
	private String project_name;
	private int permission_level;
	
	public ProjectDataRequest(DataOperation operation, DataType dataType, String username, String project_name, int permission_level) { // Storage constructor
		super(operation, dataType);
		this.username = username;
		this.project_name = project_name;
		this.permission_level = permission_level;
	}
	
	public ProjectDataRequest(DataOperation operation, DataType dataType, String username) { // Retrieval constructor
		super(operation, dataType);
		this.username = username;
	}
	
	public Query store() {
		String preparameterizedQuery = "INSERT INTO Projects (username, project_name, permission_level) VALUES (?, ?, ?);";
		Object[] parameters = {this.username, this.project_name, this.permission_level};
		return new Query(preparameterizedQuery, parameters);
	}
	
	public Query load() {
		String preparameterizedQuery = "SELECT * FROM Projects WHERE username=?;";
		String[] parameters = {this.username};
		return new Query(preparameterizedQuery, parameters);
	}

	@Override
	public Query edit() {
		// TODO Auto-generated method stub
		return null;
	}
}
