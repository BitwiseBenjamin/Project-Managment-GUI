package DataRequests;

public class UserDataRequest extends DataRequest {
	
	// User fields
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public UserDataRequest(DataOperation operation, DataType dataType, String firstName, String lastName, String username, String password) { // Storage constructor
		super(operation, dataType);
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserDataRequest(DataOperation operation, DataType dataType, String username, String password) { // Retrieval constructor
		super(operation, dataType);
		this.username = username;
		this.password = password;
	}
	
	public Query store() {
		String preparameterizedQuery = "INSERT INTO Users (username, password, first_name, last_name) VALUES (?, ?, ?, ?);";
		String[] parameters = {this.username, this.password, this.firstName, this.lastName};
		return new Query(preparameterizedQuery, parameters);
	}
	
	public Query load() {
		String preparameterizedQuery = "SELECT * FROM Users WHERE username=? AND password=?;";
		String[] parameters = {this.username, this.password};
		return new Query(preparameterizedQuery, parameters);
	}

	@Override
	public Query edit() {
		// TODO Auto-generated method stub
		return null;
	}
}
