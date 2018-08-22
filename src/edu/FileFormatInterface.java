package edu.cornell;
import java.util.List;

/**
 * Interface for genotype files of different file formats
 * Contains basic methods to handle data from various file types
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.22.18
 */
public interface FileFormatInterface {
	
	/**
	 * Loads the input file .
	 * Initializes the dnarunList with a new ArrayList containing dnarun informations.
	 */
	public void loadFile(); 
	
	/**
	 * Closes the input file if the scanner is open. 
	 * If the input file does not exist, the method will be called.
	 */
	public void closeFile();
	
	/**
	 * Reads through each line of the input file.
	 * Converts the genotype records for each line using convertRecord from Utils class.
	 * The record includes both the dna marker and genotype data.
	 * Returns the converted genotype record 
	 * @return The converted genotype record as a list
	 */
	public List<String> getRecord();
	
	/**
	 * Returns the record index indicating the current line position of the file.
	 * @return The record index 
	 */
	public int getRecordIndex();
	
	/**
	 * Checks if it is the end-of-file.
	 * @return true or false depending on whether it is the end-of-file
	 */
	public boolean isEOF();
}
