package edu.cornell;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
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
	 * Constructs a new HMP file with filename.
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
	 * Loads the input file.
	 * Checks for the first line of the file that starts without a hash .
	 * @see edu.cornell.FileFormatInterface#loadFile()
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
			dnarunList = new LinkedList<String>(Arrays.asList(line.split(delimiter)));
			if (!dnarunList.isEmpty())
			{
				dnarunList = (LinkedList<String>) dnarunList.subList(11, dnarunList.size());
			}
		} 
		catch (Exception e)
		{
			e.getStackTrace();
		}
	}
	
	/* 
	 * Returns the converted genotype record for HMP file format.
	 * Does not include meta information from columns 2 to 11.  
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
