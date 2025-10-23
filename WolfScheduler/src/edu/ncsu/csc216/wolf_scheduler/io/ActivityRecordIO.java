package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Writes activites to file
 * @author Henry Wang
 */
public class ActivityRecordIO {

	/**
	 * Writes file records
	 * @param fileName  String of file name
	 * @param courses The courses to be written
	 * @throws IOException if file cannot be written
	 */ 
	public static void writeActivityRecords(String fileName, ArrayList<Activity> courses) throws IOException {
		//Method to write a new file from a filename
		PrintStream fileWriter = new PrintStream(new File(fileName));
	
		for (Activity a : courses) {
		    fileWriter.println(a.toString());
		}
	
		fileWriter.close();
	}

}
