package edu.cornell;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * A file format suitible for those with extension of .csv
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.22.18
 */
public class CSVFileFormat extends FileFormatAbstract implements FileFormatInterface{
	
	/**
	 * Constructs a new CSV file given filename.
	 * @param filename: The name of the input file
	 */
	public CSVFileFormat(String filename) 
	{
		delimiter = ",";
		try {
			if (!filename.endsWith(".csv"))
			{
				throw new IllegalArgumentException("Wrong file exstension");
			}
			File f = new File(filename);
			sc = new Scanner(f);
		}
		catch (FileNotFoundException e)
		{
			e.getStackTrace();
			closeFile();
		}
	}
	
	/* 
	 * Loads the input file.
	 * @see edu.cornell.FileFormatInterface#loadFile()
	 */
	public void loadFile()
	{
		try {
			String line = null;
			if(sc.hasNextLine())
			{
				line = sc.nextLine();
			}
			dnarunList = new LinkedList<String>(Arrays.asList(line.split(delimiter)));
		} 
		catch (Exception e) 
		{
			e.getStackTrace();
		}
	}	
}
