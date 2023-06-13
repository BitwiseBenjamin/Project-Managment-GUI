package DataObjects;

public class Defect {
	// Defect fields
	private int id;
	private String project;
	private String title;
	private String description;
	private int points;
	private String userLogging;
	private int log_week;
	private int fix_week;
	
	public Defect(int id, String project, String title, String description, int points, String userLogging, int log_week, int fix_week) {
		this.id = id;
		this.setProject(project);
		this.setTitle(title);
		this.setDescription(description);
		this.setPoints(points);
		this.setUserLogging(userLogging);
		this.setLogWeek(log_week);
		this.setFixWeek(fix_week);
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

	public int getLogWeek() {
		return log_week;
	}

	public void setLogWeek(int log_week) {
		this.log_week = log_week;
	}

	public int getFixWeek() {
		return fix_week;
	}

	public void setFixWeek(int fix_week) {
		this.fix_week = fix_week;
	}

	public int getId() {
		return this.id;
	}
	
}
