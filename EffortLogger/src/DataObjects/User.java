package DataObjects;

public class User {
	// User fields
	private String username;
	private String firstName;
	private String lastName;
	private int userId;
	
	public User(String username, String firstName, String lastName, int userId) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
	}
	
	// Getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
