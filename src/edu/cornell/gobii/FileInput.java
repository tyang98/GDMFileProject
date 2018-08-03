package edu.cornell.gobii;
import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;
import java.util.*;
import java.io.*;

/**
 * The Class FileInput.
 * Tests the two input file formats and compares record data.
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.2.	18
 */
public class FileInput {

		/** The PrintWriter out */
		public static PrintWriter out;
	
		public static void main(String[] args) 
		{
			
			try {
				
				Options options = new Options();
				options.addOption("i", "First File", true, "First Input File");
				options.addOption("t", "Second File", true, "Second Input File");
				options.addOption("o", "Output File", true, "Output Directory");
				CommandLineParser parser = new DefaultParser();
				CommandLine cmd = null;
				
				try {
					cmd = parser.parse(options, args);
				} catch(ParseException e) {
					new HelpFormatter().printHelp("ant", options );
				}
			
				String directory = cmd.getOptionValue("o");
				out = new PrintWriter(directory + "output.txt");
				
				String input1 = cmd.getOptionValue("i");  
				String input2 = cmd.getOptionValue("t"); 
				
				FileFormatInterface i1;
				FileFormatInterface i2;
				
				if (input1.endsWith(".hmp.txt"))
				{
					i1 = new HMPFileFormat(input1);
					i1.loadFile();
				}
				else if (input1.endsWith(".txt"))
				{
					i1 = new TXTFileFormat(input1);
					i1.loadFile();
				}
				else if (input1.endsWith(".csv"))
				{
					i1 = new CSVFileFormat(input1);
					i1.loadFile();
				}
				else
				{
					throw new IllegalArgumentException("Invalid File Extension");
				}
				
				if (input2.endsWith(".hmp.txt"))
				{
					i2 = new HMPFileFormat(input2);
					i2.loadFile();
				}
				else if (input2.endsWith(".txt"))
				{
					i2 = new TXTFileFormat(input2);
					i2.loadFile();
				}
				else if (input2.endsWith(".csv"))
				{
					i2 = new CSVFileFormat(input2);
					i2.loadFile();
				}
				else
				{
					throw new IllegalArgumentException("Invalid File Extension");
				}	
				
				out.println("Comparing Data From Input Files");
				Utils.compareRecords(out, i1, i2);
				out.println("Done!");
				
			out.close();
			}
			catch (FileNotFoundException f) {
				System.out.println(f);
			}
			System.out.println("Done");
			System.exit(0);
		}
}

