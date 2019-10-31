// Import Statements
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Admin extends User implements AdminInterface {
	
	// Admin Login Info
	static String username = "Admin";
	static String password = "001";
	
	// Constructor
	public Admin() {
		super(username, password);
	}
	
	// Course Management Methods
	public void createCourse(Scanner input) {
		System.out.print("Enter the name of the course: ");
		String name = input.nextLine();
		System.out.print("Enter the course id: ");
		String id = input.nextLine();
		System.out.print("Enter the max number of students allowed: ");
		String maxStudents = input.nextLine();
		System.out.print("Enter who will be teaching the class: ");
		String instructor = input.nextLine();
		System.out.print("Enter the section number: ");
		String sectionNumber = input.nextLine();
		System.out.print("Enter the location of the course: ");
		String location = input.nextLine();
		Course createdCourse = new Course(name, id, maxStudents, instructor,
				sectionNumber, location);
		School.courses.add(createdCourse);
		System.out.println();
		System.out.println("Course Added!");
		System.out.println();
	}
	
	public void deleteCourse(Scanner input){
		System.out.print("Enter the course id (Case-Senstitive): ");
		String id = input.nextLine();
		System.out.print("Enter the section number: ");
		String sectionNumber = input.nextLine();
		if (Course.courseExists(id, sectionNumber)){
			Course deletingCourse = Course.getCourse(id, sectionNumber);
			School.courses.remove(deletingCourse);
		}
		System.out.println();
		System.out.println("Deleted!");
		System.out.println();
	}

	public void editCourse(Scanner input){
		System.out.print("Type the course ID of the course you'd like to see: ");
		String id = input.nextLine();
		System.out.print("Type the section of the course you'd like to see: ");
		String sectionNumber = input.nextLine();
		if (Course.courseExists(id, sectionNumber)) {
			Course edittingCourse = Course.getCourse(id, sectionNumber);
			System.out.println("What would you like to edit?");
			System.out.println("1. Instructor");
			System.out.println("2. Location");
			String choice = input.nextLine();
			boolean validChoice = false;
			while (!validChoice) {
			if (choice.equals("1")) {
				validChoice = true;
				System.out.println("Who is the new instructor?");
				edittingCourse.setInstructor(input.nextLine());
			} else if (choice.equals("2")) {
				validChoice = true;
				System.out.println("Where is the class held now?");
				edittingCourse.setLocation(input.nextLine());
			} else { 
				System.out.println("Invalid Choice! Try Again.");
				System.out.println("What would you like to edit?");
				System.out.println("1. Instructor");
				System.out.println("2. Location");
				choice = input.nextLine();
				}
			}
		}
		System.out.println();
		System.out.println("Done!");
		System.out.println();
	}

	public void displayInfo(Scanner input){
		System.out.print("Type the course ID of the course you'd like to see.");
		String id = input.nextLine();
		System.out.print("Type the section of the course you'd like to see.");
		String sectionNumber = input.nextLine();
		Course displayingCourse;
		if (Course.courseExists(id, sectionNumber)) {
			displayingCourse = Course.getCourse(id, sectionNumber);
			displayingCourse.showInfo();
		}
	}
	
	public void registerStudent(Scanner input){
		System.out.println("What's the students First Name?");
		String firstName = input.nextLine();
		System.out.println("What's the students Last Name?");
		String lastName = input.nextLine();
		System.out.println("What'll the student's username be?");
		String username = input.nextLine();
		System.out.println("What'll the student's password be?");
		String password = input.nextLine();
		Student matriculant = new Student(username, password, firstName,
				lastName);
		(School.students).add(matriculant);
		System.out.println("Student Added");
	}
	
	
	// Report Methods
	public void viewAllCourses(){
		for (Course course : School.courses) {
			course.showInfo();
		}
	}
	
	public void viewAllFullCourses() {
		for (Course course : School.courses) {
			if (!(course.canRegister())) {
				course.showInfo();
			}
		}
	}
	
	public void viewAllOpenCourses() {
		for (Course course : School.courses) {
			if ((course.canRegister())) {
				course.showInfo();
			}
		}
	}
	
	public void fullCoursesToText() {
		String fileName = "fullCourses.txt";
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (Course course : School.courses) {
				if (!(course.canRegister())) {
					bufferedWriter.write(course.name + " Section: " + course.sectionNumber);
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
			fileWriter.close();
			
			System.out.println();
			System.out.println("Done!");
			System.out.println();
		}
		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}

	public void viewStudentsInCourse(Scanner input) {
		System.out.print("Type the course ID of the course you'd like to see.");
		String id = input.nextLine();
		System.out.print("Type the section of the course you'd like to see.");
		String sectionNumber = input.nextLine();
		Course searchingCourse;
		if (Course.courseExists(id, sectionNumber)) {
			searchingCourse = Course.getCourse(id, sectionNumber);
			System.out.println("Students:");
			for (Student students : searchingCourse.role) {
				System.out.println(students.firstName + " " + students.lastName);
				System.out.println("Username: " + students.username);
				System.out.println();
			}
		}
	}
	
	public void viewStudentCourses(Scanner input) {
		System.out.print("Type the first name of the student you'd like to search: ");
		String firstName = input.nextLine();
		System.out.print("Type the last name of the student you'd like to search: ");
		String lastName = input.nextLine();
		Student viewingStudent;
		if (Student.studentExists(firstName, lastName)) {
			viewingStudent = Student.getStudent(firstName, lastName);
			for (Course courses: viewingStudent.myCourses) {
				courses.showInfo();
			}
		}
	}
	
	public void sortCourses() {
		ArrayList<Course> arr = School.courses;
		int n = arr.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < (n -1) - i; j++) {
				if (((arr.get(j)).currentStudentNumber) >
				((arr.get(j+1)).currentStudentNumber)) {
					Collections.swap(arr, j, j+1);
				}
			}
		}
		System.out.println("Sorted!");
	}
}