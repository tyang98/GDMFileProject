package edu.cornell.gobii;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A file format suitible for those with extension of .hmp.txt
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.2.18
 */
public class HMPFileFormat extends FileFormatAbstract implements FileFormatInterface {
	
	
	/**
	 * Constructs a new HMP file with filename
	 * @param filename: The name of the input file
	 */
	public HMPFileFormat(String filename) 
	{
		delimiter = "\t";
		try {
			if (!filename.endsWith(".hmp.txt"))
			{
				throw new IllegalArgumentException("Wrong file extension");
			}	
			File f = new File(filename);
			sc = new Scanner(f);	
		}
		catch (FileNotFoundException e)
		{
			e.getStackTrace();
		}	
	}
	
	/* 
	 * Loads a file
	 * @see edu.cornell.gobii.FileFormatInterface#loadFile()
	 */
	public void loadFile()
	{
		try {	
			String line = null;
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				if (line.trim().startsWith("#"))
				{
					recordIndex++;
				}
				else
				{
					break;
				}
			}
			dnarunList = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
			if (!dnarunList.isEmpty())
			{
				dnarunList = (ArrayList<String>) dnarunList.subList(11, dnarunList.size());
			}
		} 
		catch (Exception e)
		{
			e.getStackTrace();
		}
	}
	
	/* 
	 * Returns the converted genotype record 
	 * @return The converted genotype record as an ArrayList
	 * @see edu.cornell.gobii.FileFormatInterface#getRecord()
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
			recordlst.add(arr[0]);
			for (int i = 11; i < arr.length; i++)
			{
				recordlst.add(arr[i]);
			}
		}
		recordlst = Utils.convertRecord(recordlst);
		return recordlst;
	}
	
}
