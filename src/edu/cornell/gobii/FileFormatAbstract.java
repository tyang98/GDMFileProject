package edu.cornell;
import java.util.*;

/**
 * Provides generic methods for genotype files of different file formats.
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.2.18
 */
public abstract class FileFormatAbstract implements FileFormatInterface {

	/* dnarunList is a list of dnaruns */
	protected List<String> dnarunList = new LinkedList<String>(); 
	
	/* filename is the name of the input files */
	private String filename = null; 
	
	/* sc is the Scanner used to read input files */
	protected Scanner sc = null; 
	
	/* recordIndex is a pointer to the position of the scanner in a file */
	protected static int recordIndex = 0;	
	
	/*delimiter is a character that the input file uses to separate genotype information */
	protected String delimiter = null; 
	
	/* marker is the name of each DNA marker */
	protected String marker = null; 
	

	/**
	 * Returns the dnarunlist.
	 * @return The dnarunlist
	 */
	public List<String> getDnarunlist() {
		return dnarunList;
	}

	/**
	 * Returns the filename.
	 * @return The filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 * @param filename: The new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Returns the scanner.
	 * @return The scanner
	 */
	public Scanner getScanner() {
		return sc;
	}

	/**
	 * Sets the scanner.
	 * @param sc: The new scanner
	 */
	public void setScanner(Scanner sc) {
		this.sc = sc;
	}

	/* 
	 * Returns the record index.
	 * @return The record index
	 * @see edu.cornell.FileFormatInterface#getRecordIndex()
	 */
	public int getRecordIndex() {
		return recordIndex;
	}

	/**
	 * Returns the delimiter.
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * Sets the delimiter.
	 * @param delimiter: The new delimiter
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	/**
	 * Returns list of DNA runs.
	 * @return The list of DNA runs 
	 */
	public List<String> getDNAruns()
	{
		dnarunList.remove(0);
		return dnarunList;
	}
	
	/**
	 * Returns the DNA marker.
	 * @return The DNA marker
	 */
	public String getMarker()
	{
		String line;
		line = sc.nextLine();
		String[] arr = line.trim().split(delimiter);
		String marker = arr[0];
		return marker;		
	}
	
	/* 
	 * Returns the converted genotype record.
	 * @return The converted genotype record as a list
	 * @see edu.cornell.FileFormatInterface#getRecord()
	 */
	public List<String> getRecord()
	{
		List<String> recordlst = new LinkedList<String>();
		String line;
		if (sc.hasNextLine())
		{
			line = sc.nextLine();
			recordIndex++;
			String[] arr = line.trim().split(delimiter);
			for (int i = 0; i < arr.length; i++)
			{
				recordlst.add(arr[i]);
			}
		}
		recordlst = Utils.convertRecord(recordlst);
		return recordlst;
	}
	/* 
	 * Closes the input file if scanner is open.
	 * @see edu.cornell.FileFormatInterface#closeFile()
	 */
	public void closeFile()
	{
		try
		{
			if (sc != null)
			{
				sc.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/* 
	 * Checks if it is the end-of-file.
	 * @return true or false depending on whether it is the end-of-file
	 * @see edu.cornell.gobii.FileFormatInterface#isEOF()
	 */
	public boolean isEOF()
	{
		return !sc.hasNext();
	}

}
