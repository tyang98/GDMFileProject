package edu.cornell;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A file format suitible for those with extension of .txt
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.22.18
 */

public class TXTFileFormat extends FileFormatAbstract implements FileFormatInterface {

	/**
	 * Constructs a new TXT file with filename.
	 * @param filename: The name of the input file
	 */
	public TXTFileFormat(String filename) 
	{
		delimiter = "\t";
		try {
			if (!filename.endsWith(".txt"))
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
