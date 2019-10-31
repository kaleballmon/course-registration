import java.util.*;
import java.io.*;

public class CourseRegistrationSystem {
	
	public static void main(String[] args) {
		
		// Running Variables
		Scanner input = new Scanner(System.in);
		String username;
		String password;
		boolean userNotFound = true;
		boolean runSystem = true;
		boolean isAdmin = false;
		Student currentStudent = null;
		Admin currentAdmin = null;
		boolean userChosen = false;
		
		// Check if this is first time using (
		System.out.println("Is this your first time using the Course Registration System? (Y/N) *case sensitive*");
		String firstTime = input.nextLine();
		if (firstTime.equals("N")) {
			CourseRegistrationSystem.deserializeData();
		} else {
			School.readCourseCSV();
		}
		
		// Actual Runner
		while (runSystem) {
			System.out.println("Would you like to quit the system? (Y/N) *case sensitive*");
			String stop = input.nextLine();
			if (stop.equals("Y")) {
				runSystem = false;
				userNotFound = false;
			}
			// Login 
				while (userNotFound) {
					System.out.println("Please enter your username");
					username = input.nextLine();
					System.out.println("Please enter your password");
					password = input.nextLine();
					if (username.equals(Admin.username) && password.equals(Admin.password)) {
						isAdmin = true;
						userNotFound = false;
						currentAdmin = new Admin();
						System.out.println();
						System.out.println("Logged In!");
						userChosen = true;
					} 
					else{
						for (Student student : School.students) {
							if (username.equals(student.username) && password.equals(student.password)) {
								System.out.println();
								System.out.println("Logged In!");
								currentStudent = student;
								userNotFound = false;
								userChosen = true;
								isAdmin = false;
							}
						}
					}
					if (userNotFound) {
						System.out.println();
						System.out.println("Invalid Login. Try Again.");
						System.out.println();
					}
				}
			while (userChosen) {
				// Admin Menu
			if ((isAdmin)){
				System.out.println();
				CourseRegistrationSystem.printAdminMenu();
				String option = input.nextLine();
				System.out.println();
				if (option.equals("1")){currentAdmin.createCourse(input);} 
				else if (option.equals("2")){currentAdmin.deleteCourse(input);}
				else if (option.equals("3")){currentAdmin.editCourse(input);}
				else if (option.equals("4")){currentAdmin.displayInfo(input);}
				else if (option.equals("5")){currentAdmin.registerStudent(input);}
				else if (option.equals("6")){currentAdmin.viewAllCourses();}
				else if (option.equals("7")){currentAdmin.viewAllOpenCourses();}
				else if (option.equals("8")){currentAdmin.fullCoursesToText();}
				else if (option.equals("9")){currentAdmin.viewStudentsInCourse(input);}
				else if (option.equals("10")){currentAdmin.viewStudentCourses(input);}
				else if (option.equals("11")){currentAdmin.sortCourses();}
				else if (option.equals("12")){
					userChosen = false; 
					userNotFound = true;
					CourseRegistrationSystem.serializeData();
					}			
			} else {
				// Student Menu
				System.out.println();
				CourseRegistrationSystem.printStudentMenu();
				String option = input.nextLine();
				System.out.println();
				if (option.equals("1")){currentStudent.viewAllCourses();} 
				else if (option.equals("2")){currentStudent.viewAllAvailableCourses();}
				else if (option.equals("3")){currentStudent.registerInCourse(input);}
				else if (option.equals("4")){currentStudent.withdrawFromCourse(input);}
				else if (option.equals("5")){currentStudent.viewMyCourses();}
				else if (option.equals("6")){
					userChosen = false; 
					userNotFound = true;
					CourseRegistrationSystem.serializeData();
					}
				}
			}
		}
		input.close();
	}
	// Extra methods
	public static void printAdminMenu() {
		System.out.println("Menu");
		System.out.println("1. Create a new Course");
		System.out.println("2. Delete a course");
		System.out.println("3. Edit a course");
		System.out.println("4. Display Information about a course");
		System.out.println("5. Register Student");
		System.out.println("6. View all courses");
		System.out.println("7. View all available courses");
		System.out.println("8. Write full courses to a file");
		System.out.println("9. View students in a course");
		System.out.println("10.View courses a Student is in");
		System.out.println("11. Sort courses");
		System.out.println("12. Exit");
	}

	public static void printStudentMenu() {
		System.out.println("Menu");
		System.out.println("1. View all courses");
		System.out.println("2. View all available courses");
		System.out.println("3. Register in a course");
		System.out.println("4. Withdraw from a course");
		System.out.println("5.View your courses");
		System.out.println("6. Exit");
		}
	
	public static void serializeData() {
		try{
	         FileOutputStream fos1= new FileOutputStream("courses.ser");
	         FileOutputStream fos2= new FileOutputStream("students.ser");
	         ObjectOutputStream oos1= new ObjectOutputStream(fos1);
	         ObjectOutputStream oos2= new ObjectOutputStream(fos2);
	         oos1.writeObject(School.courses);
	         oos2.writeObject(School.students);
	         oos1.close();
	         oos2.close();
	         fos1.close();
	         fos2.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	
	public static void deserializeData() {
		try {
            FileInputStream fis1 = new FileInputStream("courses.ser");
            FileInputStream fis2 = new FileInputStream("students.ser");
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            ArrayList<Student> students = (ArrayList<Student>) ois2.readObject();
            ArrayList<Course> courses = (ArrayList<Course>) ois1.readObject();
            ois1.close();
            ois2.close();
            fis1.close();
            fis2.close();
            School.students = students;
            School.courses = courses;
         }catch(IOException ioe){
             ioe.printStackTrace();
             return;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return;
          }
		
		
	}
}
	

