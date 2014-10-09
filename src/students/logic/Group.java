package students.logic;


public class Group {
	
	private int id;
	private String name;
	private String curator;
	private String speciality;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCurator() {
		return curator;
	}
	
	public void setCurator(String curator) {
		this.curator = curator;
	}
	
	public String getSpeciality() {
		return speciality;
	}
	
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	
	public String toString() {
		return getName();
	}

}
