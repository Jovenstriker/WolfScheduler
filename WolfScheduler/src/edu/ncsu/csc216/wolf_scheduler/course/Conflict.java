/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class for conflicts
 * @author Henry Wang
 */
public interface Conflict {

	/**
	 * Method to check if there is conflict activity
	 * @param possibleConflictingActivity Other possible activity
	 * @throws ConflictException Exception for conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
