package DataObjects;

public class Effort {
	// Effort fields
	private String project;
	private String title;
	private String description;
	private int points;
	private String userLogging;
	private int timeSlot;
	
	public Effort(String project, String title, String description, int points, String userLogging, int timeSlot) {
		this.setProject(project);
		this.setTitle(title);
		this.setDescription(description);
		this.setPoints(points);
		this.setUserLogging(userLogging);
		this.setTimeSlot(timeSlot);
	}

	// Getters and setters
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getUserLogging() {
		return userLogging;
	}

	public void setUserLogging(String userLogging) {
		this.userLogging = userLogging;
	}

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int w) {
		this.timeSlot = w;
	}
}
