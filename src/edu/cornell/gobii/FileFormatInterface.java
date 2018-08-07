package edu.cornell;
import java.util.ArrayList;

/**
<<<<<<< HEAD
 * Interface for genotype files of different file formats
 * Contains basic methods used for specific file formats
=======
 * Generic interface for genotype files of different file formats
>>>>>>> 9022ebe43319a48b381e4b344e47d0f84a22aabc
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.2.18
 */
public interface FileFormatInterface {
	
	/**
	 * Loads the input file 
	 * Initializes dnarunList with a new ArrayList containing dnarun informations
	 */
	public void loadFile(); 
	
	/**
	 * Closes the input file if the scanner is open. 
	 * If the input file does not exist, the method will be called.
	 */
	public void closeFile();
	
	/**
	 * Reads through each line of the input file
	 * Converts the genotype records for each line using convertRecord from Utils class
	 * Returns the converted genotype record 
	 * @return The converted genotype record as an ArrayList
	 */
	public ArrayList<String> getRecord();
	
	/**
	 * Returns the record index which is the position of the scanner in a file
	 * @return The record index 
	 */
	public int getRecordIndex();
	
	/**
	 * Checks if it is the end-of-file
	 * @return true or false depending on whether it is the end-of-file
	 */
	public boolean isEOF();
}
