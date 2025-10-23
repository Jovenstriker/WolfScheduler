/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test for conflict exception
 * @author Henry Wang
 */
class ConflictExceptionTest {

//	/**
//	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}.
//	 */
//	@Test
//	void testConflictException() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for parameterized and no parameter constructor {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	    ConflictException ce2 = new ConflictException();
	    assertEquals("Schedule conflict.", ce2.getMessage());
	}
}
