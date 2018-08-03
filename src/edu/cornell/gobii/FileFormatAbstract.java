package edu.cornell.gobii;
import java.util.*;

/**
 * The Class FileFormatAbstract.
 * Provides generic methods for genotype files of different file formats
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.2.	18
 */
public abstract class FileFormatAbstract implements FileFormatInterface {

	
	/** The dnarun list. */
	protected ArrayList<String> dnarunList = new ArrayList<String>();
	
	/** The filename */
	private String filename = null;
	
	/** The Scanner sc */
	protected Scanner sc = null;
	
	/** The record index. */
	protected static int recordIndex = 0;
	
	/** The delimiter. */
	protected String delimiter = null;
	
	/** The marker. */
	protected String marker = null;
	

	/**
	 * Returns the dnarunlist
	 * @return The dnarunlist
	 */
	public ArrayList<String> getDnarunlist() {
		return dnarunList;
	}

	/**
	 * Sets the dnarunlist.
	 * @param dnarunlist: The new dnarunlist
	 */
	public void setDnarunlist(ArrayList<String> dnarunlist) {
		this.dnarunList = dnarunList;
	}

	/**
	 * Returns the filename
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
	 * Returns the scanner
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
	 * Returns the index of a record
	 * @return The record index
	 * @see edu.cornell.gobii.FileFormatInterface#getRecordIndex()
	 */
	public int getRecordIndex() {
		return recordIndex;
	}

	/**
	 * Sets the record index.
	 * @param recordIndex: The new record index
	 */
	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
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
	 * Returns List of DNA runs
	 * @return The list of DNA runs 
	 */
	public ArrayList<String> getDNAruns()
	{
		dnarunList.remove(0);
		return dnarunList;
	}
	
	/**
	 * Returns the DNA marker
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
	 * Closes file
	 * @see edu.cornell.gobii.FileFormatInterface#closeFile()
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
	 * Checks if it is the end-of-file
	 * @return true or false depending on whether it is the end-of-file
	 * @see edu.cornell.gobii.FileFormatInterface#isEOF()
	 */
	public boolean isEOF()
	{
		return !sc.hasNext();
	}

}
