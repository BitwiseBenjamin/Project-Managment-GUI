package DataObjects;

public class Project {
	// Project fields
	private int id;
	private String name;
	private int permissionLevel;
	
	public Project(int id, String name, int permissionLevel) {
		this.setId(id);
		this.setName(name);
		this.setPermissionLevel(permissionLevel);
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(int permissionLevel) {
		this.permissionLevel = permissionLevel;
	}
	
}
