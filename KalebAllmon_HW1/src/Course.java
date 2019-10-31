//Import Statements
import java.util.*;
import java.io.*;

public class Course implements Serializable {
	// Instance Variables
	String name;
	String id;
	int maxStudents;
	String sectionNumber;
	String instructor;
	String location;
	ArrayList<Student> role = new ArrayList<>();
	int currentStudentNumber = role.size();
	
	// Default Constructor
	public Course() {}
	
	// Constructor
	public Course(String name, String id, String maxStudents,
			String instructor, String sectionNumber, String location) {
		this.name = name;
		this.id = id;
		this.maxStudents = Integer.parseInt(maxStudents);
		this.instructor = instructor;
		this.sectionNumber = sectionNumber;
		this.location = location;
	}
	

	// Getter and Setter for Course Name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Getter and Setter for Course Section Number
	public String getSectionNumber() {
		return sectionNumber;
	}
	
	public void setSectionNumber(String sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	
	// Getter and Setter for Course Location
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	// Getter and Setter for Course ID Number
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	// Getter and Setter for Course Student Limit
	public int getMaxStudents() {
		return maxStudents;
	}
	
	public void setMaxStudents(String maxStudents) {
		this.maxStudents = Integer.parseInt(maxStudents);
	}
	
	// Getter and Setter for Course ID Number
	public int getCurrentStudentNumber() {
		return currentStudentNumber;
	}
	
	public void setCurrentStudentNumber() {
		this.currentStudentNumber = role.size();
	}
	// Getter and Setter for Course Role
	public ArrayList<Student> getRole() {
		return role;
	}
	
	public String getInstructor() {
		return instructor;
	}
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public static boolean courseExists(String id, String sectionNumber){
		for (Course course : School.courses) {
			if (((course.getId()).equals(id))
					&& ((course.getSectionNumber()).equals(sectionNumber))) {
				return true;
			}
		}
		System.out.println("Course not found!");
		return false;
	}
	
	public static Course getCourse(String id, String sectionNumber) {
		for (Course course : School.courses) {
			if (((course.getId()).equals(id)) && 
					((course.getSectionNumber()).equals(sectionNumber))) {
				return course;
			}
		} return null;
	}
	
	public boolean canRegister() {
		if ((this.getCurrentStudentNumber()) != this.getMaxStudents()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void showInfo() {
		System.out.println("Course Name: " + this.getName());
		System.out.println("-----------------");
		System.out.println("Course ID: " + this.getId());
		System.out.println("Maximum Number of Students: " +
		this.getMaxStudents());
		System.out.println("Number of Students Entrolled: " +
		this.getCurrentStudentNumber());
		System.out.println("Students:");
		for (Student students : role) {
			System.out.println(students.firstName + " " + students.lastName);
		}
		System.out.println("Course Instructor: " + 
		this.getInstructor());
		System.out.println("Course Section Number: " + 
		this.getSectionNumber());
		System.out.println("Course Location: " + 
		this.getLocation());
	} 
}

