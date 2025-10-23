package edu.ncsu.csc216.wolf_scheduler.scheduler;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
/**
 * Class for wolf scheduler
 * @author Henry Wang
 */
public class WolfScheduler {
	/**
	 * Arraylist of courses
	 */
	private ArrayList<Course> catalog;
	/**
	 * Arraylist for your schedule
	 */
	private ArrayList<Activity> schedule;
	/**
	 * Title of the schedule
	 */
	private String title = new String();
    /**
     * Constructor for wolf scheduler
     * @param fileName the file name
     * @throws IllegalArgumentException exception if can't find file
     */

	public WolfScheduler(String fileName){
		//Constructor which resets the schedule, creates new catalog, and sets a generic schedule title
		resetSchedule();
		this.catalog = new ArrayList<Course>();
		setScheduleTitle("My Schedule");
		try {
		ArrayList<Course> fileCourses = CourseRecordIO.readCourseRecords(fileName);
		for (Course i : fileCourses) {
			//Goes through arrayList of Courses in file and adds thme to catalog
			catalog.add(i);
			}
		} catch (Exception e) {
			//Sees if exceptions
				throw new IllegalArgumentException("Cannot find file.");
			}
			
		}
	
    /**
     * Returns 2d catalog with 3 labels
     * @return catalog1 returns 2d array of catalog with 3 labels
     */
	
	public String[][] getCourseCatalog() {
		//Gets the catalog in terms of an n by 3 matrix,
		//First column name, second column section, third title
		String[][] catalog1 = new String[catalog.size()][3];
		if (catalog.isEmpty()) return new String[0][0];
		//checks if empty
	
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalog1[i] = c.getShortDisplayArray();
		}
		return catalog1;

	}
    /**
     * Returns 2d catalog with 3 labels
     * @return catalog1 returns 2d array of schedule with 3 labels
     */
	
	public String[][] getScheduledActivities() {
		//Gets your scheduled courses in terms of n by 3 matrix
		//First column name, second column section, third title
		String[][] schedule1 = new String[schedule.size()][3];
		if (schedule.isEmpty()) return new String[0][0];
		//checks if empty
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			if (a instanceof Course) {
				Course course = (Course) a;
				schedule1[i] = course.getShortDisplayArray();
			}
			else {
				Event event = (Event) a;
				schedule1[i] = event.getShortDisplayArray();
			}
		}
		return schedule1;
	}
    /**
     * Returns 2d catalog with 7 labels
     * @return catalog1 returns 2d array of catalog with 6 labels
     */

	public String[][] getFullScheduledActivities() {
		//Gets your scheduled courses in terms of n by 6 matrix
		//Displays all possible parameters except start time and end time 
		String[][] schedule1 = new String[schedule.size()][6];
		if (schedule.isEmpty()) return new String[0][0];
		//Checks if empty
		for (int i = 0; i < schedule.size(); i++) {
			Activity a = schedule.get(i);
			if (a instanceof Course) {
				Course course = (Course) a;
				schedule1[i] = course.getLongDisplayArray();
			}
			else {
				Event event = (Event) a;
				schedule1[i] = event.getLongDisplayArray();
			}
		}
		return schedule1;
	}
	
    /**
     * Settor method for title
     * @param string1 sets the instance title 
     * @throws IllegalArgumentException checks if null title
     */
	public void setScheduleTitle(String string1) {
		//Setter method for title
		if (string1 == null) throw new IllegalArgumentException("Title cannot be null.");
		this.title = string1;
	}
	
    /**
     * Accessor method for title
     * @return title returns the instance title 
     * @throws IllegalArgumentException checks if null title
     */

	public String getScheduleTitle() {
		//Accessor method for title when not null
		if (title == null)throw new IllegalArgumentException("Title cannot be null.");
		return title;
	}

	/**
	 * Resets the title
	 */
	
	public void resetSchedule() {
		//Resets schedule
		schedule = new ArrayList<Activity>();
	}
	
    /**
     * Exports the title
     * @param string string for file path
     * @throws IllegalArgumentException checks if file cannot be saved
     */
	public void exportSchedule(String string) {
		try{
			//Writes the course schedule to a file
			ActivityRecordIO.writeActivityRecords(string, schedule);
		} catch (Exception c) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}

    /**
     * Gets the course from catalog
     * @param name sets the instance name 
     * @param section sets the section instance
     * @return course Returns the course from the catalog
     */ 
	
	public Course getCourseFromCatalog(String name, String section) {
		for (Course i : catalog) {
			//Iterates through catalog and checks if name and section match
			if (i.getName().equals(name) && i.getSection().equals(section)) {
				return i;
			}
		}
		return null;
	}

    /**
     * Adds course to schedule
     * @return boolean Boolean if added a course or not
     * @param name name of desired course
     * @param section section of desired course
     * @throws IllegalArgumentException checks if already enrolled
     */
	public boolean addCourseToSchedule(String name, String section) throws IllegalArgumentException {
		for (Course i : catalog) {
			//iterates through catalog and see if same name and section
				if (i.getName().equals(name) && i.getSection().equals(section)) {
					
					for (Activity a:schedule) {//Iterates through schedule to check if name already exists
						
						if (a instanceof Course) {
							Course b = (Course) a;
							if (b.getName().equals(name)) {
								throw new IllegalArgumentException("You are already enrolled in " + b.getName());
							}
						}
						try {
							a.checkConflict(i);
						} catch (ConflictException e) {
							throw new IllegalArgumentException("The course cannot be added due to a conflict.");
						}
							
					}
					schedule.add(i);
					return true;
				}
			}
		return false;
	}
	
	/**
	 * Adds event to schedule
	 * @param eventTitle Title of event
	 * @param eventMeetingDays Meeting days of event
	 * @param eventStartTime Start time of event
	 * @param eventEndTime End time of event
	 * @param eventDetails Details of event
	 * @throws ConflictException  Exception due to conflict
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) throws IllegalArgumentException {
					Event temp = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
					for (Activity a : schedule) {//Iterates through schedule to check if name already exists
						
						if (a instanceof Event) {
							Event b = (Event) a;
							if (b.getTitle().equals(eventTitle)) {
								throw new IllegalArgumentException("You have already created an event called " + b.getTitle());
							}
						}
						try {
							a.checkConflict(temp);
						} catch (ConflictException e) {
							throw new IllegalArgumentException("The event cannot be added due to a conflict.");
						}
					}
					schedule.add(temp);
	}	
	
    /**
     * Removes activity
     * @param idx Index to be removed
     * @return boolean Boolean if removed from a course or not
     * @throws IllegalArgumentException checks if already enrolled
     */

	public boolean removeActivityFromSchedule(int idx) {
		try {
		schedule.remove(idx);
		return true;
		}
		catch (IndexOutOfBoundsException e){
			return false;
		}



	}
	

	
}





