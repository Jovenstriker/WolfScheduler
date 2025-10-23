package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.ArrayList;
import java.util.Objects;
/**
 * Class for activity, superclass for course and event
 * @author Henry Wang
 */
public abstract class Activity implements Conflict {

	/**
	 * Overridden possible conflict method. Throws exception if at least one day with overlapping time
	 * @param possibleConflictingActivity Other activity to have conflict
	 * @throws ConflictException Exception when conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		//Creates arraylist of meeting days
		ArrayList<Character> arrayDays = new ArrayList<Character>();
		for (char i:this.meetingDays.toCharArray()) {
			arrayDays.add(i);
		}
		//Creates arraylist of opposing meeting days
		
		ArrayList<Character> otherArrayDays = new ArrayList<Character>();
		
		for (char i:possibleConflictingActivity.getMeetingDays().toCharArray()) {
			otherArrayDays.add(i);
		}
		if (arrayDays.contains('A') || otherArrayDays.contains('A')) {
			return;
		}
		
		
		int otherStart = possibleConflictingActivity.getStartTime();
		int otherEnd = possibleConflictingActivity.getEndTime();
		//Iterates through days to compare times
		
		for (char day:arrayDays) {
			if (otherArrayDays.contains(day)) {
				if (this.startTime >= otherStart && this.startTime <= otherEnd)
					throw new ConflictException();
				if (this.endTime >= otherStart && this.endTime <= otherEnd)
					throw new ConflictException();
				if (this.startTime >= otherStart && this.endTime <= otherEnd)
					throw new ConflictException();
				if (this.startTime <= otherStart && this.endTime >= otherEnd)
					throw new ConflictException();
			}
		}
	}

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** Highest hour you can go to (24) */
	private static final int UPPER_HOUR = 24;
	/** Highest minute you can go to (60) */
	private static final int UPPER_MINUTE = 60;

	/**
	 * Constructor for activity class
	 * @param title Title
	 * @param meetingDays The Meeting day
	 * @param startTime The start time
	 * @param endTime The end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	
	/**
	 * Returns title of method
	 * @return title  returns instance variable title
	 */
	public String getTitle() {
		//Returns the title
		return title;
	}

	/**
	 * Settor method for title
	 * @param title1  title to be set as instance variable
	 * @throw IllegalArgumentException  Checks if title is null or empty  
	 */
	public void setTitle(String title1) {
		//Makes a title
		//Checks if null or length = 0
		if (title1 == null || title1.length() == 0) throw new IllegalArgumentException("Invalid title.");
		this.title = title1;
	}

	/**
	 * Settor method for meeting days and time
	 * @param meetingDays1  meetingDays to be set as instance variable
	 * @param startTime  name to be set as starting time instance variable
	 * @param endTime  time to be set as ending time instance variable
	 * @throw IllegalArgumentException  Checks if times and meeting days follows the rules    
	 */
	public void setMeetingDaysAndTime(String meetingDays1, int startTime, int endTime) {
		//If null
		if (meetingDays1 == null) throw new IllegalArgumentException("Invalid meeting days and times.");
		//if empty
		if (meetingDays1.length() == 0)throw new IllegalArgumentException("Invalid meeting days and times.");
		//not a valid meeting day
		if (meetingDays1.length() != 1 && meetingDays1.contains("A")) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		//checks if non-zero start time for arranged
		if (meetingDays1.length() == 1 && meetingDays1.contains("A") && !(startTime == 0 && endTime == 0)) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		// Creates minutes and hour
		int startMinute = startTime % 100;
		int startHour = startTime / 100;
		int endMinute = endTime % 100;
		int endHour = endTime / 100;
		if (startMinute < 0 || startMinute >= UPPER_MINUTE) throw new IllegalArgumentException("Invalid meeting days and times.");
		if (startHour < 0 || startHour >= UPPER_HOUR) throw new IllegalArgumentException("Invalid meeting days and times.");
		if (endMinute < 0 || endMinute >= UPPER_MINUTE) throw new IllegalArgumentException("Invalid meeting days and times.");
		if (endHour < 0 || endHour >= UPPER_HOUR) throw new IllegalArgumentException("Invalid meeting days and times.");
		if (startTime > endTime) throw new IllegalArgumentException("Invalid meeting days and times.");
		this.meetingDays = meetingDays1;
		this.startTime = startTime;
		this.endTime = endTime;
		//Method to set all of meetinDays, and start and end times
	}

	/**
	 * Accessor method for meeting days
	 * @return meetingDays  returns meeting day instance variable
	 */
	public String getMeetingDays() {
		//Gets the meeting days
		return meetingDays;
	}

	/**
	 * Converts from military to 12 hour time
	 * @param time military time to be inputted
	 * @return time  returns miliarty time converted to 12 hour system with AM,PM
	 */
	public String getTimeString(int time) {
		//converts from military to AM or PM
		int hour = time / 100;
		int minute = time % 100;
		String label = "AM";
		
		if (hour >= 13) {
			hour = hour - 12;
			label = "PM";
		}
		else if (hour >= 12) {
			label = "PM";
		}
		//Returns time in format Hour:Minute Label
		if (minute < 10) {
			return "" + hour + ":0" + minute + label; 
		}
		
		return "" + hour + ":" + minute + label;
	}
	
	
	
	
	/**
	 * Returns hashcode for equals.
	 * @return hascode A hashcode to compare if equal
	 */
	@Override
	public int hashCode() {
		//Necessary for equals
		return Objects.hash(title, meetingDays, startTime, endTime);
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
		Activity other = (Activity) obj;
		return  getEndTime() == other.getEndTime() &&  Objects.equals(getMeetingDays(), other.getMeetingDays()) && getStartTime() == other.getStartTime()
				&& Objects.equals(getTitle(), other.getTitle());
	}
	

	/**
	 * Accessor method for meeting string
	 * @return meetingString  returns meeting day and time converted to 12 hour system with AM,PM
	 */
	public String getMeetingString() {
		//Gets the meeting days
		if ("A".equals(meetingDays)) return "Arranged";
		return meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
	}

	/**
	 * Accessor method for start time
	 * @return startTime  returns start time converted
	 */
	public int getStartTime() {
		//Gets the start time
		return startTime;
	}

	/**
	 * Accessor method for end time
	 * @return endTime  returns end time
	 */
	public int getEndTime() {
		//Gets the end time
		return endTime;
	}
	
	/**
	 * Abstract method to check if duplicate
	 * @param activity Activity of other
	 * @return True if duplicate
	 */
	
	public abstract boolean isDuplicate(Activity activity);


	/**
	 * Abstract method for list of short display
	 * @return String[] returns short display
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Abstract method for list of longer display
	 * @return String[] returns long display
	 */
	public abstract String[] getLongDisplayArray();


}