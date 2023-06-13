package DataRequests;

public class Query { // Generic query object
	private String preparameterizedQuery;
	private Object[] parameters;
	
	public Query(String p1, Object[] p2) {
		this.preparameterizedQuery = p1;
		this.parameters = p2;
	}
	
	public String getPreparameterizedQuery() {
		return this.preparameterizedQuery;
	}
	
	public Object[] getParameters() {
		return this.parameters;
	}
}
