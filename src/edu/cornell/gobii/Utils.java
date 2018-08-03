package edu.cornell.gobii;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.PrintWriter;

/**
 * The Class Utils
 * Contains utility methods for File Comparator
 * @author Tony Yang
 * @version 0.1
 * @created 6.28.18
 * @updated 8.2.	18
 */
public final class Utils {
	
	public static final HashMap<String, String> IUPAC = new HashMap<String, String>();
	
	
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
	}
	
	/**
	 * Compare records.
	 * Compares the Genotype records of f1 and f2
	 * @param out: PrintWriter used to print to output.txt
	 * @param f1: The first FileFormatInterface 
	 * @param f2: The second FileFormatInterface 
	 */
	public static void compareRecords(PrintWriter out, FileFormatInterface f1, FileFormatInterface f2)
	{
		while (!f1.isEOF() && !f2.isEOF())
		{
			ArrayList<String> lst1 = f1.getRecord();
			ArrayList<String> lst2 = f2.getRecord();
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
	 * Convert record.
	 * Converts a list of variants based on their format
	 * @param recordlst: The original list of variants
	 * @return The converted list of variants
	 */
	public static ArrayList<String> convertRecord(ArrayList<String> recordlst)
	{
		boolean isIUPAC = false;
		
		for (int i = 1; i < recordlst.size(); i++)
		{
			String variant = recordlst.get(i);
			if (variant.length() == 0)
			{
				throw new IllegalArgumentException("Invalid Variant Type");
			}
			else if (variant.length() == 1)
			{
				if (!variant.equals("0") && !variant.equals("1") && !variant.equals("2"))
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
					recordlst.set(i, variant);
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
					recordlst.set(i, variant);
				}
			}
		}
		if (isIUPAC)
		{
			for (int i = 0; i < recordlst.size(); i++)
			{
				if (recordlst.get(i).equals("0"))
				{
					recordlst.set(i, IUPAC.get("0"));
				}
			}
		}

		return recordlst;
	}
}
