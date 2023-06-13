package DataRequests;
import java.nio.file.FileSystems;
import java.sql.SQLException;

public class Driver { // Driver for SQL queries
	public static RequestManager manager;
	public static class DataDriver {
		public static void initializeRequestManager() throws SQLException { // Initialize to use effortlogger.db
				 manager = new RequestManager("jdbc:sqlite:" + FileSystems.getDefault().getPath("EffortLogger.db").normalize().toAbsolutePath().toString());
		}
		
		public static Object makeRequest(DataRequest request) { // Request method that uses RequestManager
			try {
				return manager.makeRequest(request);
			} catch (SQLException e) {
				return null;
			}
		}
	}
}
