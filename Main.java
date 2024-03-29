//Github project
//Andrew Emad

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

class arithmeticCoding
{
	char symbol;
	double lowRange,highRange;
}

public class Main 
{
	private static Scanner scan;

	public static double getRandom(double low,double high) 
	{
		return ThreadLocalRandom.current().nextDouble(low, high);
	}
	
	public static String decToBinary(double num) 
  { 
      if (num >= 1 || num <= 0) 
          return "ERROR"; 

      StringBuilder binary = new StringBuilder(); 
      binary.append("0");
      binary.append("."); 

      while (num > 0) 
      { 
    	  
          if (binary.length() >= 32) 
              return "ERROR"; 

          double r = num * 2; 
          if (r >= 1) 
          { 
              binary.append(1); 
              num = r - 1; 
          } 
          else
          { 
              binary.append(0); 
              num = r; 
          } 
      } 
      return binary.toString(); 
  } 
	
	public static double binaryToDecimal(String binary, int len) 
	{ 
	    int point = binary.indexOf('.'); 
	  
	    if (point == -1) 
	        point = len; 
	  
	    double intDecimal = 0, fracDecimal = 0, twos = 1; 
	  
	    for (int i = point-1; i>=0; --i) 
	    { 
	        intDecimal += (binary.charAt(i) - '0') * twos; 
	        twos *= 2; 
	    } 
	  
	    twos = 2; 
	    for (int i = point+1; i < len; ++i) 
	    { 
	        fracDecimal += (binary.charAt(i) - '0') / twos; 
	        twos *= 2.0; 
	    } 
	  
	    return intDecimal + fracDecimal; 
	} 
	
	public static char[] getUniqueChar(String word) 
	{
		Set<Character> tree_Set = new TreeSet<Character>(); 
		for(int i=0;i<word.length();i++)
  		tree_Set.add(word.charAt(i));
		char[] uniqueChar=new char[tree_Set.size()];
		int k=0;
		for (char value : tree_Set) 
      {
			uniqueChar[k++]=value;
      }
		return uniqueChar;
	}
	
	public static int[] getFrequencies(String word , char[] uniqueChar) 
	{
		int[] freq = new int[uniqueChar.length];
		int k = 0;
		for (int i = 0; i < word.length(); i++) 
		{
			for (int j = 0; j < uniqueChar.length; j++)
			{
				if (uniqueChar[j]==word.charAt(i))
				{
					k=j;
					break;
				}
			}
			freq[k]++;
		}
		return freq;
	}
	
	public static double[] getLowHigh(Character symbol,arithmeticCoding[] lh) 
	{
		double[] symbolRange = new double[2];
		for (int i = 0; i < lh.length; i++) 
		{
			if(lh[i].symbol==symbol)
			{
				symbolRange[0] = lh[i].lowRange;
				symbolRange[1] = lh[i].highRange;
				break;
			}
		}
		return symbolRange;
	}
	
	public static arithmeticCoding[] calculateRanges(char[] uniqueChar,int[] freq,String word )
	{
		arithmeticCoding[] lh = new arithmeticCoding[uniqueChar.length];
		for (int i = 0; i < uniqueChar.length; i++) 
		{
			lh[i] = new arithmeticCoding();
			lh[i].symbol = uniqueChar[i];
			if(i==0)
				lh[i].lowRange=0;
			else
				lh[i].lowRange=lh[i-1].highRange;
			lh[i].highRange = lh[i].lowRange + ((double)freq[i]/(double)word.length());
		}
		return lh;
	}
	
	public static Character getSymobl(double code,arithmeticCoding[] lh)
	{
		char symbol = new Character('s');
		for (int i = 0; i < lh.length; i++) 
		{
			if(lh[i].lowRange<=code && lh[i].highRange>code)
			{
				symbol = lh[i].symbol;
				break;
			}
		}
		return symbol;
	}
	
	public static String compression(String word,arithmeticCoding[] lh)
	{
		String bin = "ERROR";
		double rand = 0.1,range=1,lower=0,upper=1;
		for (int i = 0; i < word.length(); i++)
		{
			upper = lower + range* getLowHigh(word.charAt(i), lh)[1];
			lower = lower + range*getLowHigh(word.charAt(i), lh)[0];
			range = upper-lower;
		}
		while(bin.equals("ERROR"))
		{
			rand = getRandom(lower, upper);
			bin = decToBinary(rand);
		}
		return bin; 
	}
	
	public static String decompression(String bin,int length,arithmeticCoding[] lh) 
	{
		double range=1,lower=0,upper=1;
		double value = binaryToDecimal(bin, bin.length());
		double code = binaryToDecimal(bin, bin.length());
		String word = new String("");
		for (int i = 0; i < length; i++) 
		{
			word += getSymobl(code, lh);
			upper = lower + range* getLowHigh(word.charAt(i), lh)[1];
			lower = lower + range*getLowHigh(word.charAt(i), lh)[0];
			range = upper-lower;
			code = (value - lower)/range;
		}
		return word;
	}
}
