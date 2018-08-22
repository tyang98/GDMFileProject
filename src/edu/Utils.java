package edu.cornell;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.io.PrintWriter;

/**
 * Contains utility methods for the comparison of genotype records between files
 * 
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.22.18
 */
public  final class Utils {
	
	/* IUPAC is a HashMap that contains the 2 Letter Nucleotide characters (values) for IUPAC characters (keys) */
	public static final HashMap<String, String> IUPAC = new HashMap<String, String>();
	/* NUMBER is a HashSet that contains the numbers 0, 1, and 2 for CODOM and DOM data types */
	public static final HashSet<String> NUMBER = new HashSet<String>();
	
	static {
		IUPAC.put("A", "AA");
		IUPAC.put("C", "CC");
		IUPAC.put("G", "GG");
		IUPAC.put("T", "TT");
		IUPAC.put("U", "TT");
		IUPAC.put("R", "AG");
		IUPAC.put("Y", "TC");
		IUPAC.put("S", "GC");
		IUPAC.put("W", "AT");
		IUPAC.put("K", "TG");
		IUPAC.put("M", "AC");
		IUPAC.put("B", "CG");
		IUPAC.put("D", "AG");
		IUPAC.put("H", "AC");
		IUPAC.put("V", "AC");
		IUPAC.put("N", "NN");
		IUPAC.put(".", "--");
		IUPAC.put("-", "--");
		IUPAC.put("0", "+-");
		
		NUMBER.add("0");
		NUMBER.add("1");
		NUMBER.add("2");
		
	}
	
	/**
	 * Compares the genotype records of f1 and f2 line by line.
	 * @param out: The PrintWriter used to print to output.txt
	 * @param f1: The first FileFormatInterface 
	 * @param f2: The second FileFormatInterface 
	 */
	public static void compareRecords(PrintWriter out, FileFormatInterface f1, FileFormatInterface f2)
	{
		while (!f1.isEOF() && !f2.isEOF())
		{
			List<String> lst1 = f1.getRecord();
			List<String> lst2 = f2.getRecord();
			if (lst1.size() != lst2.size())
			{
				out.println("Different Record Lengths at: " + f1.getRecordIndex());
			}
			for (int i = 0; i < lst1.size(); i++)
			{
				if (!lst1.get(i).equals(lst2.get(i)))
				{	
					out.println("Different Variants for marker " + lst1.get(0) + " at Record " + f1.getRecordIndex() + ", at position: " + i) ;
				}
			}
		}
	}

	/**
	 * Converts a list of variants based on their initial format to a 2 letter nucleotide format.
	 * @param recordlst: The original list of variants
	 * @return The converted list of variants
	 */
	public static List<String> convertRecord(List<String> recordlist)
	{
		boolean isIUPAC = false;
		
		for (int i = 1; i < recordlist.size(); i++)
		{
			String variant = recordlist.get(i);
			if (variant.length() == 0)
			{
				throw new IllegalArgumentException("Invalid Variant Type");
			}
			else if (variant.length() == 1)
			{
				if (!NUMBER.contains(variant))
				{
					variant = IUPAC.get(variant);
					isIUPAC = true;
				}
				if (variant.equals(null))
				{
					throw new IllegalArgumentException("Invalid Variant Type");
				}
				else
				{
					recordlist.set(i, variant);
				}
			}
			else
			{
				variant = variant.substring(0, 1) + variant.substring(variant.length() - 1, variant.length());
				if (variant.equals(null))
				{
					throw new IllegalArgumentException("Invalid Variant Type");
				}
				else
				{
					recordlist.set(i, variant);
				}
			}
		}
		if (isIUPAC)
		{
			for (int i = 0; i < recordlist.size(); i++)
			{
				if (recordlist.get(i).equals("0"))
				{
					recordlist.set(i, IUPAC.get("0"));
				}
			}
		}
		return recordlist;
	}
}
