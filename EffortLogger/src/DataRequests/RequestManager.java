package DataRequests;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataObjects.User;
import DataRequests.DataRequest.DataOperation;
import DataObjects.Defect;
import DataObjects.Effort;
import DataObjects.Project;

public class RequestManager {
	
	Connection connection;
	public RequestManager(String connString) throws SQLException { // Make connection to db
		this.connection = DriverManager.getConnection(connString);
	}
	
	public void closeConnection() throws SQLException { // Close connection to db
		this.connection.close();
	}
	
	public Object makeRequest(DataRequest re) throws SQLException { // Make request given abstract data request
		Query q = re.getQuery();
		Object[] parameters = q.getParameters();
		PreparedStatement statement = this.connection.prepareStatement(q.getPreparameterizedQuery()); // Prepare parameterized query
		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i] instanceof String) {
				statement.setString(i+1, (String) parameters[i]);
				continue;
			}
			if (Integer.class.isInstance(parameters[i])) {
				statement.setInt(i+1, (int) parameters[i]);
				continue;
			}
			throw new SQLException();
		}
		
		
		if (re.operation == DataOperation.SELECT) {
		
		ResultSet rs = statement.executeQuery(); // Get result of query
		switch (re.operation) {
		case SELECT: // If the query was a retrieval query
			switch (re.dataType) {
			case DEFECT:
				System.out.println("Getting defects");
				List<Defect> defects = new ArrayList<Defect>();
		        while (rs.next()) { // Build list of efforts
		        	Defect d = new Defect(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7),  rs.getInt(8));
		        	defects.add(d);
		        }
		        return defects;
			case EFFORT:
				System.out.println("Getting efforts");
				List<Effort> efforts = new ArrayList<Effort>();
		        while (rs.next()) { // Build list of efforts
		        	Effort e = new Effort(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
		        	efforts.add(e);
		        }
		        return efforts;
			case PROJECT:
				System.out.println("Getting projects");
				List<Project> projects = new ArrayList<Project>();
		        while (rs.next()) { // Build list of projects
		        	Project p = new Project(rs.getInt(1), rs.getString(3), rs.getInt(4));
		        	projects.add(p);
		        }
		        return projects;
			case USER:
				rs.next();
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				return new User(username, firstName, lastName, id);
			default:
				break;
			
			}
			break;
		case EDIT:
			switch (re.dataType) {
			case DEFECT:
				System.out.println("Editing defect");
				break;
			case EFFORT:
				System.out.println("Storing effort");
			case PROJECT:
				break;
			case USER:
				break;
			default:
				break;
		
			}
			break;
		default:
			break;
		
		}
		}
		else {
			System.out.println(re.operation);
			statement.executeUpdate();
			switch (re.dataType) {
			case DEFECT:
				System.out.println("Storing defect");
				break;
			case EFFORT:
				System.out.println("Storing effort");
			case PROJECT:
				break;
			case USER:
				break;
			default:
				break;
		
			}
		}
		return null;
	}
}
