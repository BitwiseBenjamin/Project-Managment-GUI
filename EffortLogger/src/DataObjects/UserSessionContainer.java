package DataObjects;

public class UserSessionContainer {
	// User session has a user and project associated (project can be null)
	private static User user;
	private static Project project;
	
	public static class UserSession { // User session object

		public static User getUser() {
			return user;
		}

		public static void setUser(User newUser) {
			user = newUser;
		}

		public static Project getProject() {
			return project;
		}

		public static void setProject(Project newProject) {
			project = newProject;
		}
	}
}
