package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable. Students have a first name, last
 * name, id number, number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Gianna Mastrandrea
 */
public class Student implements Comparable<Student>, Identifiable {

	private String first;
	private String last;
	private int id;
	private int creditHours;
	private double gpa;
	private String unityID;

	/**
	 * Creates a new Student object with the required parameters
	 * 
	 * @param first       first name of student
	 * @param last        last name of student
	 * @param id          id of student
	 * @param creditHours credit hours of student
	 * @param gpa         gpa of student
	 * @param unityID     unity id of student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityID = unityID;
	}

	/**
	 * Gets first name of student
	 * 
	 * @return first name of student
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets first name of student
	 * 
	 * @param first first name to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Gets last name of student
	 * 
	 * @return last name of student
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets last name of student
	 * 
	 * @param last last name to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Gets id of student
	 * 
	 * @return id of student
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id of student
	 * 
	 * @param id id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets credit hours of student
	 * 
	 * @return credit hours of student
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets credit hours of student
	 * 
	 * @param creditHours credit hours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Gets gpa of student
	 * 
	 * @return gpa of student
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets gpa of student
	 * 
	 * @param gpa gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Gets unity id of student
	 * 
	 * @return unity id of student
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Sets unity id of student
	 * 
	 * @param unityID unity id to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}

	@Override
	public int compareTo(Student o) {
		if (this.equals(o)) {
			return 0;
		}
		if (this.last.compareTo(o.last) < 0) {
			return -1;
		} else if (this.last.compareTo(o.last) > 0) {
			return 1;
		} else if (this.first.compareTo(o.first) < 0) {
			return -1;
		} else if (this.first.compareTo(o.first) > 0) {
			return 1;
		} else if (this.id < o.id) {
			return -1;
		}
		return 1;
	}

}
