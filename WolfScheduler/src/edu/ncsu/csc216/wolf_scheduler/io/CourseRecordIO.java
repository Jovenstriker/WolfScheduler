package edu.ncsu.csc216.wolf_scheduler.io;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Class for course records
 * @author Henry Wang
 */
public class CourseRecordIO extends ActivityRecordIO {

    /**
     * Reads course from line
     * @param line  Line to be read
     * @return      Returns course object
     * @throw IllegalArgumentException  Checked Exception. Checks if more tokens than necessary for specific parameters      
     */
    private static Course readCourse(String line) {
        // Construct Scanner to process the line parameter
        Scanner scanner = new Scanner(line);
        // Set the delimiter to ","
        scanner.useDelimiter(",");

        try {
            // Read in tokens for name, title, section, credits, instructorId, and meetingDays
            String name = scanner.next();
            String title = scanner.next();
            String section = scanner.next();
            int credits = scanner.nextInt();
            String instructorId = scanner.next();
            String meetingDays = scanner.next();

            // If meetingDays is "A"
            if ("A".equals(meetingDays)) {
                // If there are more tokens, throw IllegalArgumentException
                if (scanner.hasNext()) {
                    throw new IllegalArgumentException("Additional tokens found after meetingDays 'A'.");
                } else {
                    // Return a newly constructed Course object
                    return new Course(name, title, section, credits, instructorId, meetingDays);
                }
            } else {
                // Read in tokens for startTime and endTime
                int startTime = scanner.nextInt();
                int endTime = scanner.nextInt();

                // If there are more tokens, throw IllegalArgumentException
                if (scanner.hasNext()) {
                    throw new IllegalArgumentException("Additional tokens found after meetingDays 'A'.");
                } else {
                    // Return a newly constructed Course object
                    return new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
                }
            }
        } catch (Exception e) {
            // Catch any exceptions and throw a new IllegalArgumentException
            throw new IllegalArgumentException("Malformed input line or invalid data.", e);
        } finally {
            // Close the scanner on all possible paths out of the method
            scanner.close();
        }
    	
    }
    	
    	
    	
    	
 
    /**
     * Read course records
     * @param fileName  file path name to be read
     * @return courses     Returns arraylist of courses
     */
    public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
    	Scanner fileReader = new Scanner(new File(fileName));  //Create a file scanner to read the file
    	
        ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
        while (fileReader.hasNextLine()) { //While we have more lines in the file
            try { //Attempt to do the following
                //Read the line, process it in readCourse, and get the object
                //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
                Course course = readCourse(fileReader.nextLine());

                //Create a flag to see if the newly created Course is a duplicate of something already in the list  
                boolean duplicate = false;
                //Look at all the courses in our list
                for (int i = 0; i < courses.size(); i++) {
                    //Get the course at index i
                    Course current = courses.get(i);
                    //Check if the name and section are the same
                    if (course.getName().equals(current.getName()) &&
                            course.getSection().equals(current.getSection())) {
                        //It's a duplicate!
                        duplicate = true;
                        break; //We can break out of the loop, no need to continue searching
                    }
                }
                //If the course is NOT a duplicate
                if (!duplicate) {
                    courses.add(course); //Add to the ArrayList!
                } //Otherwise ignore
            } catch (IllegalArgumentException e) {
                //The line is invalid b/c we couldn't create a course, skip it!
            }
        }
        //Close the Scanner b/c we're responsible with our file handles
        fileReader.close();
        //Return the ArrayList with all the courses we read!
        return courses;
    }


	
	
}

