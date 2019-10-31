//Import Statements
import java.util.*;
import java.io.*;

public class Student extends User implements StudentInterface, Serializable {
	
	// Instance Variables
	String firstName;
	String lastName;
	ArrayList<Course> myCourses = new ArrayList<>();
	
	// Constructor
	public Student(String username, String password, String firstName,
			String lastName) {
		super(username, password);
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	//Course Management Methods
	public void viewAllCourses() {
		for (Course course : School.courses ) {
			System.out.println("Course Name:" + course.getName());
			System.out.println("----------------------");
			System.out.println("Course ID:" + course.getId());
			System.out.println("Course Section Number:" + course.getSectionNumber());
			System.out.println("Course Instructor:" + course.getInstructor());
			System.out.println("Course Location:" + course.getLocation());
			System.out.println();
		}
	}
	
	public void viewAllAvailableCourses() {
		for (Course course : School.courses ) {
			if (course.canRegister()) {
				System.out.println("Course Name:" + course.getName());
				System.out.println("----------------------");
				System.out.println("Course ID:" + course.getId());
				System.out.println("Course Section Number:" + course.getSectionNumber());
				System.out.println("Course Instructor:" + course.getInstructor());
				System.out.println("Course Locaation:" + course.getLocation());
				System.out.println();
			}
		}
	};
	
	public void registerInCourse(Scanner input) {
		System.out.print("Enter the course id (Case-Senstitive): ");
		String id = input.nextLine();
		System.out.print("Enter the section number: ");
		String sectionNumber = input.nextLine();
		System.out.println();
		if (Course.courseExists(id, sectionNumber)) {
			Course enrollingCourse = Course.getCourse(id, sectionNumber);
			if (enrollingCourse.canRegister()) {
				(enrollingCourse.role).add(this);
				(this.myCourses).add(enrollingCourse);
				enrollingCourse.setCurrentStudentNumber();
				System.out.println();
				System.out.println("You are now enrolled in " + enrollingCourse.name);
				System.out.println();
			} else {
				System.out.println("Class if full!");
			}
		} else {
			System.out.println("This class doesn't exist.");
		}
	}
	
	public void withdrawFromCourse(Scanner input) {
		System.out.print("Enter the course id (Case-Senstitive): ");
		String id = input.nextLine();
		System.out.print("Enter the section number: ");
		String sectionNumber = input.nextLine();
		System.out.println();
		if (Course.courseExists(id, sectionNumber)) {
			Course withdrawingCourse = Course.getCourse(id, sectionNumber);
			boolean didRemove = (withdrawingCourse.role).remove(this);
			if (didRemove) {
				System.out.println();
				System.out.println("You've withdrawn from the course.");
				System.out.println();
				(this.myCourses).remove(withdrawingCourse);
				withdrawingCourse.setCurrentStudentNumber();
			} else {
				System.out.println("You were not in this course.");
				}
			} else {
			System.out.println("This class doesn't exist.");
		}
	}
	
	public void viewMyCourses() {
		for (Course course: this.myCourses) {
			course.showInfo();
			System.out.println("-------------------");
		}
	}
	
	public static boolean studentExists(String firstName, String lastName) {
		for (Student student : School.students) {
			if (((student.firstName).equals(firstName))
					&& ((student.lastName).equals(lastName))) {
				return true;
			}
		}
		System.out.println("Student not found!");
		return false;
	}
	
	public static Student getStudent(String firstName, String lastName) {
		for (Student student : School.students) {
			if (((student.firstName).equals(firstName)) && 
			((student.lastName).equals(lastName))) {
				return student;
			}
		} return null;
	}
}
