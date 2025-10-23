/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class for conflict exception
 * @author Henry Wang
 */
public class ConflictException extends Exception {

	/**
	 * Default constructor
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

	/**
	 * Constructor that throws a message
	 * @param message Message to be thrown
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;

}
