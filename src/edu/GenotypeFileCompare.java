package edu.cornell;
import org.apache.commons.cli.*;
import java.io.*;

/**
 * Allows the input of two genotype matrix files and reports the differences between the variants of the files.
 * Supports the datatypes: DOMINANT, CODOM, 2 Letter Nucleotides, and IUPAC.
 * Allows file extension: CSV (.csv), TXT (.txt), HMP (hmp.txt).
 * CSV: First row contains dnarun information. First column contains DNA markers for each record. 
 * Every row after the first contains genotype records.
 * TXT: First row contains dnarun information. First column contains DNA markers for each record.
 * Every row after the first contains genotype records.
 * HMP: Contains an arbitrary number of rows with comments preceded by a hash symbol. 
 * First row without comments contains dnarun information. First column without comments contains dna markers. 
 * Columns 2-11 without comments contain meta information that will not be read. 
 * 
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.22.18
 */
public class GenotypeFileCompare {
	
		/* out is the PrintWriter used to print differences between variants.*/
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
			
				//Obtain directory for the output file
				String directory = cmd.getOptionValue("o");
				out = new PrintWriter(directory + "output.txt");
				
				//Obtain the name of the two files
				String input1 = cmd.getOptionValue("i");  
				String input2 = cmd.getOptionValue("t"); 
				
				FileFormatInterface i1;
				FileFormatInterface i2;
				
				//Input file one
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
				
				//Input file two
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

