package edu.cornell.gobii;
import java.util.ArrayList;

/**
 * The Interface FileFormatInterface.
 * Generic interface for genotype files of different file formats
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.2.	18
 */
public interface FileFormatInterface {
	
	/**
	 * Loads a file
	 */
	public void loadFile(); 
	
	/**
	 * Closes file
	 */
	public void closeFile();
	
	/**
	 * Returns the genotype record
	 * @return The genotype record as an ArrayList
	 */
	public ArrayList<String> getRecord();
	
	/**
	 * Returns the index of a record
	 * @return The record index
	 */
	public int getRecordIndex();
	
	/**
	 * Checks if it is the end-of-file
	 * @return true or false depending on whether it is the end-of-file
	 */
	public boolean isEOF();
}
