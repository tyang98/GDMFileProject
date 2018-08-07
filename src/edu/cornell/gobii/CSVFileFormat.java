package edu.cornell;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * A file format suitible for those with extension of .csv
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.2.18
 */
public class CSVFileFormat extends FileFormatAbstract implements FileFormatInterface{
	
	/**
	 * Constructs a new CSV file given filename
	 * @param filename: The name of the input file
	 */
	public CSVFileFormat(String filename) 
	{
		delimiter = ",";
		try {
			if (!filename.endsWith(".csv"))
			{
				throw new IllegalArgumentException("Wrong file extension");
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
	 * Loads the input file
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
			dnarunList = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
		} 
		catch (Exception e) 
		{
			e.getStackTrace();
		}
	}
	
	/* 
	 * Returns the converted genotype record 
	 * @return The converted genotype record as an ArrayList
	 * @see edu.cornell.FileFormatInterface#getRecord()
	 */
	public ArrayList<String> getRecord()
	{
		ArrayList<String> recordlst = new ArrayList<String>();
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
	
}
