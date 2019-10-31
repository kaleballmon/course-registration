import java.util.*;

public interface AdminInterface {
	
	// Course Management
	public abstract void createCourse(Scanner scanner);
	
	public abstract void deleteCourse(Scanner scanner);
	
	public abstract void editCourse(Scanner scanner);
	
	public abstract void displayInfo(Scanner scanner);
	
	public abstract void registerStudent(Scanner scanner);
	
	// Reports
	public abstract void viewAllCourses();

}
