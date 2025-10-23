/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.ArrayList;
import java.util.Objects;
/**
 * Course class for course object
 * @author Henry Wang
 */

public class Course extends Activity {
	//Class that sets up how to register for courses
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**Minimum length of the name*/
	private static final int MIN_NAME_LENGTH  = 5;
	/**Max length of the name*/
	private static final int MAX_NAME_LENGTH = 8;
	/**Minimum length of the letter*/
	private static final int MIN_LETTER_COUNT = 1;
	/**Max length of the letter*/
	private static final int MAX_LETTER_COUNT = 4;
	/**Number of the digits*/
	private static final int DIGIT_COUNT = 3;
	/**Number of digits in section*/
	private static final int SECTION_LENGTH = 3;
	/**Max number of credits available*/
	private static final int MAX_CREDITS = 5;
	/**Min number of credits available*/
	private static final int MIN_CREDITS = 1;
	/**
	 * Constructor method for course with start and end times
	 * @param name The name
	 * @param title The title
	 * @param section The section
	 * @param credits The number of credits
	 * @param instructorId The instructor ID
	 * @param meetingDays The meeting days
	 * @param startTime The start time
	 * @param endTime The end time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		//Constructor with all possible parameters
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Constructor method for course without start and end times
	 * @param name The name
	 * @param title The title
	 * @param section The section
	 * @param credits The number of credits
	 * @param instructorId The instructor ID
	 * @param meetingDays The meeting days
	 */

	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		//Constructor without set start time and set end time
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);

	}


	
	/**
	 * Returns hashcode for equals.
	 * @return hascode A hashcode to compare if equal
	 */
	@Override
	public int hashCode() {
		//Necessary for equals
		return Objects.hash(credits, getEndTime(), instructorId, getMeetingDays(), name, section, getStartTime(), getTitle());
	}


	
	/**
	 * Returns if equal or not
	 * @param obj Object to compare if equal or not.
	 * @return boolean Whether or not the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		//Sees if object are the same for all fields
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && getEndTime() == other.getEndTime() && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(getMeetingDays(), other.getMeetingDays()) && Objects.equals(name, other.name)
				&& Objects.equals(section, other.section) && getStartTime() == other.getStartTime()
				&& Objects.equals(getTitle(), other.getTitle());
	}
	
	
	
	
	/**
	 * Returns a string representation of the object
	 * @return string A string representation of the object
	 */
	@Override
	public String toString() {
		//Returns all the parameters in a list like archetype _,_,_
	    if (getMeetingDays().equals("A")) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	
    /**
     * Returns the name
     * @return  name    Returns name
     */
	public String getName() {
		//Returns the name
		return name;
	}

    /**
     * Settor method for name
     * @param name  name to be set as instance variable
     * @throw IllegalArgumentException  Checks if name follows the rules    
     */
	private void setName(String name) {//Sets the name
		//Checks if null
		if (name == null) throw new IllegalArgumentException("Invalid course name.");
		//Checks if no space
		if (name.indexOf(' ') < 0) throw new IllegalArgumentException("Invalid course name.");
		String firstName = "";
		String secondName = "";
		//Creates two strings, first course name, second-numbers
		firstName = name.substring(0, name.indexOf(' '));
		for (int i = 0; i < firstName.length(); i++) {
			if (!Character.isLetter(firstName.charAt(i))) throw new IllegalArgumentException("Invalid course name.");
		}
		secondName = name.substring(name.indexOf(' ') + 1);
		for (int i = 0; i < secondName.length(); i++) {
			if (!Character.isDigit(secondName.charAt(i))) throw new IllegalArgumentException("Invalid course name.");
		}
		
		//Condition for length not >8 nor <5
		if (name.length() > MAX_NAME_LENGTH || name.length() < MIN_NAME_LENGTH) throw new IllegalArgumentException("Invalid course name.");
		//First name between 1 and 4
		if (firstName.length() > MAX_LETTER_COUNT || firstName.length() < MIN_LETTER_COUNT) throw new IllegalArgumentException("Invalid course name.");
		//Number == 3
		if (secondName.length() !=  DIGIT_COUNT) throw new IllegalArgumentException("Invalid course name.");
		this.name = name;
	}

    /**
     * Accessor method for section
     * @return section  return section instance variable
     */
	public String getSection() {
		//Gets the section
		return section;
	}

    /**
     * Settor method for section
     * @param section  section to be set as instance variable
     * @throw IllegalArgumentException  Checks if section follows the rules    
     */
	public void setSection(String section) {
		//Checks if null
		if (section == null) throw new IllegalArgumentException("Invalid section.");
		//Checks if section is not length 3
		if (section.length() != SECTION_LENGTH) throw new IllegalArgumentException("Invalid section.");
		//Checks all are digits
		for (int i = 0; i < 3; i++) {
			char value = section.charAt(i);
			if (!Character.isDigit(value)) throw new IllegalArgumentException("Invalid section.");
		}
		//Creates a section
		this.section = section;
	}

	/**
	 * Accessor method for credits
	 * @return credits return number of credits of object
	 */
	public int getCredits() {
		//Gets number of credits
		return credits;
	}

    /**
     * Settor method for credit
     * @param credits  Credit to be set as instance variable
     * @throw IllegalArgumentException  Checks if credit follows the rules    
     */
	public void setCredits(int credits) {
		//Checks if less than 1 or greater than 5
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) throw new IllegalArgumentException("Invalid credits.");
		//Sets the number of credits
		this.credits = credits;
	}

    /**
     * Accessor method for instructor id
     * @return instructorId  returns instructorId instance variable
     */
	public String getInstructorId() {
		//Gets the instructor ID
		return instructorId;
	}

    /**
     * Setter method for instructor id
     * @param instructorId  ID to be set as instance variable
     * @throw IllegalArgumentException  Checks if ID follows the rules    
     */
	public void setInstructorId(String instructorId) {
		//Checks if null or empty
		if (instructorId == null || instructorId.length() == 0)throw new IllegalArgumentException("Invalid instructor id.");
		//Changes the instructor ID
		this.instructorId = instructorId;
	}

	/**
	 * Returns short display for course
	 * @return shortArray Array of name, section, title, and meeting string
	 */
	public String[] getShortDisplayArray() {
		String[] shortArray = {getName(), getSection(), getTitle(), getMeetingString()};
		return shortArray;
		
	}
	
	
	/**
	 * Returns long display for course
	 * @return longArray Array of name, section, title, credits, instructorId,  meeting string, and empty string
	 */
	public String[] getLongDisplayArray() {
		String[] longArray = {getName(),  getSection(), getTitle(), Integer.toString(getCredits()), getInstructorId(), getMeetingString(), ""};
		return longArray;
	}
	
	
	/**
	 * Overriden method, checks if not doulb or not null
	 * @param meetingDays1 Meeting days
	 * @param startTime Start time
	 * @param endTime End time
	 */
	
	@Override
	public void setMeetingDaysAndTime(String meetingDays1, int startTime, int endTime) {
		ArrayList<Character> used = new ArrayList<Character>();
		char[] ch = meetingDays1.toCharArray();
	    for (int i = 0; i < ch.length; i++) {
	    	if (used.contains(ch[i])) throw new IllegalArgumentException("Invalid meeting days and times.");
	        if (ch[i] != 'M' && ch[i] != 'T' && ch[i] != 'W' && ch[i] != 'H' && ch[i] != 'F' && ch[i] != 'A') {
	        	throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        used.add(ch[i]);
	    }

		super.setMeetingDaysAndTime(meetingDays1, startTime, endTime);
	}

	/**
	 * Checks if duplicate
	 * @param activity Activity to be checked
	 * @return boolean True if duplicate
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		Course temp = (Course) activity;
		return equals(temp);
	}



}