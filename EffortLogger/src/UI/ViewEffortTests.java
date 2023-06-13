package UI;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import DataObjects.Project;
import DataObjects.User;
import DataObjects.UserSessionContainer.UserSession;
import DataRequests.DataRequest.DataOperation;
import DataRequests.DataRequest.DataType;
import DataRequests.EffortDataRequest;
import DataRequests.EffortDataRequest.DataPeriod;

class ViewEffortTests {

	@Test
	void testDailySelectAsManager() {
		// Generate random user
		String randomUsername = generateRandomString(10);
		String randomFirstName = generateRandomString(10);
		String randomLastName = generateRandomString(10);
	    Random random = new Random();
		int randomUserId = random.nextInt(0, 100);
		
		// Set random user
		UserSession.setUser(new User(randomUsername, randomFirstName, randomLastName, randomUserId));
		
		// Generate random project
		String randomProjectName = generateRandomString(10);
		int randomProjectId = random.nextInt(0, 100);
		int permissionLevel = 1; // Indicates user is a manager
		
		// Set random project
		UserSession.setProject(new Project(randomProjectId, randomProjectName, permissionLevel));

		// Get test data request
		EffortDataRequest r = getEffortsQuery(DataPeriod.DAY);
		
		// Test that it is retrieving efforts
		assertEquals(r.getDataType(), DataType.EFFORT);
		// Test that it is querying for no specific user (manager)
		assertEquals(r.getUserLogging(), null);
		// Test that it is querying for correct project
		assertEquals(r.getProject(), randomProjectName);
	}

	@Test
	void testWeeklySelectAsManager() {
		// Generate random user
		String randomUsername = generateRandomString(10);
		String randomFirstName = generateRandomString(10);
		String randomLastName = generateRandomString(10);
	    Random random = new Random();
		int randomUserId = random.nextInt(0, 100);
		
		// Set random user
		UserSession.setUser(new User(randomUsername, randomFirstName, randomLastName, randomUserId));
		
		// Generate random project
		String randomProjectName = generateRandomString(10);
		int randomProjectId = random.nextInt(0, 100);
		int permissionLevel = 1; // Indicates user is a manager
		
		// Set random project
		UserSession.setProject(new Project(randomProjectId, randomProjectName, permissionLevel));

		// Get test data request
		EffortDataRequest r = getEffortsQuery(DataPeriod.WEEK);
		
		// Test that it is retrieving efforts
		assertEquals(r.getDataType(), DataType.EFFORT);
		// Test that it is querying for no specific user (manager)
		assertEquals(r.getUserLogging(), null);
		// Test that it is querying for correct project
		assertEquals(r.getProject(), randomProjectName);
	}

	@Test
	void testDailySelectAsEnginner() {
		// Generate random user
		String randomUsername = generateRandomString(10);
		String randomFirstName = generateRandomString(10);
		String randomLastName = generateRandomString(10);
	    Random random = new Random();
		int randomUserId = random.nextInt(0, 100);
		
		// Set random user
		UserSession.setUser(new User(randomUsername, randomFirstName, randomLastName, randomUserId));
		
		// Generate random project
		String randomProjectName = generateRandomString(10);
		int randomProjectId = random.nextInt(0, 100);
		int permissionLevel = 0; // Indicates user is an engineer
		
		// Set random project
		UserSession.setProject(new Project(randomProjectId, randomProjectName, permissionLevel));

		// Get test data request
		EffortDataRequest r = getEffortsQuery(DataPeriod.DAY);
		
		// Test that it is retrieving efforts
		assertEquals(r.getDataType(), DataType.EFFORT);
		// Test that it is querying for the correct user
		assertEquals(r.getUserLogging(), randomUsername);
		// Test that it is querying for correct project
		assertEquals(r.getProject(), randomProjectName);
	}

	@Test
	void testWeeklySelectAsEngineer() {
		// Generate random user
		String randomUsername = generateRandomString(10);
		String randomFirstName = generateRandomString(10);
		String randomLastName = generateRandomString(10);
	    Random random = new Random();
		int randomUserId = random.nextInt(0, 100);
		
		// Set random user
		UserSession.setUser(new User(randomUsername, randomFirstName, randomLastName, randomUserId));
		
		// Generate random project
		String randomProjectName = generateRandomString(10);
		int randomProjectId = random.nextInt(0, 100);
		int permissionLevel = 0; // Indicates user is an engineer
		
		// Set random project
		UserSession.setProject(new Project(randomProjectId, randomProjectName, permissionLevel));

		// Get test data request
		EffortDataRequest r = getEffortsQuery(DataPeriod.WEEK);
		
		// Test that it is retrieving efforts
		assertEquals(r.getDataType(), DataType.EFFORT);
		// Test that it is querying for the correct user
		assertEquals(r.getUserLogging(), randomUsername);
		// Test that it is querying for correct project
		assertEquals(r.getProject(), randomProjectName);
	}
	
	// Random alphabetic string of length n
	private String generateRandomString(int n) {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    Random random = new Random();

	    return random.ints(leftLimit, rightLimit + 1).limit(n).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		
	}

	// Method to test from ViewEffort class (cannot test using JUnit directly as it is a JavaFX object)
	// Get efforts grouped by the period specified (DAY or WEEK)
		public EffortDataRequest getEffortsQuery(DataPeriod period) {
	        if (UserSession.getProject().getPermissionLevel() == 0) { // If user is not a manager
	        	// Specify username to match efforts on
	        	return new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, period, UserSession.getProject().getName(), UserSession.getUser().getUsername()); 
	        } else {
	        	// Get all efforts regardless of username
				return new EffortDataRequest(DataOperation.SELECT, DataType.EFFORT, period, UserSession.getProject().getName());
	        }
		}
	
}
