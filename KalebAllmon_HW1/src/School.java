import java.io.*;
import java.util.*;
public class School implements Serializable {
	
	static ArrayList<Course> courses = new ArrayList<>();
	static ArrayList<Student> students = new ArrayList<>();
	
	public School() {}
	
	// Read Courses from CSV
	public static void readCourseCSV() {
		String fileName = "MyUniversityCourses.csv";
		String line = null;
		String[] courseElements;
		
		try {
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		bufferedReader.readLine();
		while (((line = bufferedReader.readLine()) != null)) {
			courseElements = line.split(",");
			Course newCourse = new Course(courseElements[0],
					courseElements[1],courseElements[2],
					courseElements[5], courseElements[6],
					courseElements[7]); 
			courses.add(newCourse);
			}
		bufferedReader.close();
		}
		
		catch (FileNotFoundException fnf) {
			System.out.println("Cannot find that file!");
		}
		catch (IOException exk) {
			System.out.println( "I suddenly can't read!");
		}
	}
	// Done Reading Course From CSV
	
}