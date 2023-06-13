package DataRequests;

public abstract class DataRequest {
	public enum DataType { // Type of data in request
		USER,
		EFFORT,
		DEFECT,
		PROJECT
	}
	
	public enum DataOperation { // Operation of data in request
		STORE,
		SELECT,
		EDIT
	}

	protected DataOperation operation;
	protected DataType dataType;
	
	public DataRequest(DataOperation operation, DataType dataType) { // Abstract data request
		this.operation = operation;
		this.setDataType(dataType);
	}
	
	public Query getQuery() { // Maps getQuery to associated abstract function to be implemented in children
		if (this.operation == DataOperation.STORE) {
			return this.store();
		}
		if (this.operation == DataOperation.SELECT) {
			return this.load();
		}
		if (this.operation == DataOperation.EDIT) {
			return this.edit();
		}
		return null;
	}
	
	public abstract Query store(); // Store query
	public abstract Query load(); // Load query
	public abstract Query edit(); // Edit query
	
	public DataType getDataType() {
		return this.dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
}
