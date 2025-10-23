/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.ArrayList;

/**
 * Class fr event
 * @author Henry Wang
 */
public class Event extends Activity {
	/**
	 * Additional item to Event
	 * String of event details
	 */
	private String eventDetails;
	
	/**
	 * Constructor for Event
	 * @param title Title for event
	 * @param meetingDays Meeting days for event
	 * @param startTime Start time of event
	 * @param endTime End time of event
	 * @param eventDetails Event details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Accessor method for event detail
	 * @return eventDetails The event details of event
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Settor method for event details
	 * @param eventDetails Event details
	 * @throw IAException Throws if null
	 */
	public void setEventDetails(String eventDetails) {
		//Checks if event details is null
		if (eventDetails == null) throw new IllegalArgumentException("Invalid event details.");
		this.eventDetails = eventDetails;
	}
	
	
	/**
	 * Returns short display for event
	 * @return shortArray Array of , , title, and meeting string
	 */
	public String[] getShortDisplayArray() {
		String[] shortArray = {"", "", getTitle(), getMeetingString()};
		return shortArray;
		
	}
	
	
	/**
	 * Returns long display for course
	 * @return longArray Array of , , title, , ,  meeting string, and empty string
	 */ 
	public String[] getLongDisplayArray() {
		String[] longArray = {"", "", getTitle(), "", "", getMeetingString(), eventDetails};
		return longArray;
		
	}
	 
	 /**
	  * toString method
	  * @return String representation
	  */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + eventDetails;
	}

	/**
	 * Overriden set MeetingDaysAnd Time, checks also if U and S
	 * @param meetingDays1 Meeting days of event
	 * @param startTime Start time of event
	 * @param endTime End time of event
	 * @throws IllegalArgumentException Exception if double or not day of the week
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays1, int startTime, int endTime) {
		if (meetingDays1 == null) throw new IllegalArgumentException("Invalid meeting days and times.");
		ArrayList<Character> used = new ArrayList<Character>();
		char[] ch = meetingDays1.toCharArray();
	    for (int i = 0; i < ch.length; i++) {
	    	if (used.contains(ch[i])) throw new IllegalArgumentException("Invalid meeting days and times.");
	        if (ch[i] != 'M' && ch[i] != 'T' && ch[i] != 'W' && ch[i] != 'H' && ch[i] != 'F' && ch[i] != 'U' && ch[i] != 'S') {
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
		Event temp = (Event) activity;
		return equals(temp);
	}
	
	
	
	
	
}
