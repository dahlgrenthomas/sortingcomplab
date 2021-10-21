import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.*;
import java.util.Collections;

public class Tester {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		if (args.length < 2) {
			System.out.println("Please run with two command line arguments: input and output file names");
			System.exit(0);
		}

		String inputFileName = args[0];
		String outFileName = args[1];
		
		// read as strings
		Integer [] data = readInts(inputFileName);
		
		Integer [] toSort = data.clone();
		Integer [] ourSort = data.clone();
		
		long start = System.currentTimeMillis();
		sort(toSort);
		long end = System.currentTimeMillis();
		
		System.out.println("It took TIM sort " + (end - start) + " milis");
		//printArray(sorted, 100);
		
		
		Thread.sleep(10); //to let other things finish before timing; adds stability of runs

		long start2 = System.currentTimeMillis();
		
		buckets(ourSort);
		
		long end2 = System.currentTimeMillis();
		
		System.out.println("It took our sort " + (end2 - start2) + " milis");
		
		System.out.println(Arrays.equals(toSort, ourSort));

		writeOutResult(toSort, "expectedoutput.txt");
		writeOutResult(ourSort, outFileName);
	}
	
	private static void sort(Integer[] toSort) {
		Arrays.sort(toSort, new BinaryComparator());
	}
	// YOUR SORTING METHOD GOES HERE. 
	// You may call other methods and use other classes. 
	// Note: you may change the return type of the method. 
	// You would need to provide your own function that prints your sorted array to 
	// a file in the exact same format that my program outputs
	private static void buckets(Integer[] toSort){
		int n = toSort.length;
		if (n <= 0)
            return;
 
        @SuppressWarnings("unchecked")
        Vector<Integer>[] buckets = new Vector[25];
 
        for (int i = 0; i < 25; i++) {
            buckets[i] = new Vector<Integer>();
        }
 
        for (int i = 0; i < n; i++) {
            int idx = Helper.numBinaryOnes(toSort[i]);
            buckets[idx].add(toSort[i]);
        }
 
        for (int i = 0; i < 25; i++) {
            buckets2(buckets[i]);
        }
 
        int index = 0;
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                toSort[index++] = buckets[i].get(j);
            }
        }

	}

	private static void buckets2(Vector<Integer> toSort){
		int n = toSort.size();
		if (n <= 0)
            return;

        @SuppressWarnings("unchecked")
        Vector<Integer>[] buckets = new Vector[13];
 
        for (int i = 0; i < 13; i++) {
            buckets[i] = new Vector<Integer>();
        }
 
        for (int i = 0; i < n; i++) {
            int idx = Helper.lengthLongestRepeatedSubstring(Integer.toBinaryString(toSort.get(i)));
            buckets[idx].add(toSort.get(i));
        }
 
        for (int i = 0; i < 13; i++) {
			//Collections.sort(buckets[i], new BinaryComparator2());
			radixsort(buckets[i]);
			//quickWithInsertion(buckets[i], 0, buckets[i].size() - 1);
        }
 
        int index = 0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                toSort.set(index++, buckets[i].get(j));
            }
        }
	}

	static int getMax(Vector<Integer> arr, int n)
    {
        int mx = arr.get(0);
        for (int i = 1; i < n; i++)
            if (arr.get(i) > mx)
                mx = arr.get(i);
        return mx;
    }
 
    static void countSort(Vector<Integer> arr, int n, int exp)
    {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
 
        for (i = 0; i < n; i++)
            count[(arr.get(i) / exp) % 10]++;
 
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr.get(i) / exp) % 10] - 1] = arr.get(i);
            count[(arr.get(i) / exp) % 10]--;
        }
 
        for (i = 0; i < n; i++)
            arr.set(i, output[i]);
    }

    private static void radixsort(Vector<Integer> toSort)
    {
		int n = toSort.size();
		if(n!=0){
			int m = getMax(toSort, n);
			for (int exp = 1; m / exp > 0; exp *= 10)
				countSort(toSort, n, exp);
		}
        
    }
	
	private static String[] readData(String inFile) throws FileNotFoundException {
		ArrayList<String> input = new ArrayList<>();
		Scanner in = new Scanner(new File(inFile));
		
		while(in.hasNext()) {
			input.add(in.next());
		}
				
		in.close();
		
		// the string array is passed just so that the correct type can be created
		return input.toArray(new String[0]);
	}
	
	private static Integer[] readInts(String inFile) throws FileNotFoundException {
		ArrayList<Integer> input = new ArrayList<>();
		Scanner in = new Scanner(new File(inFile));
		
		// we don't know how many input numbers, so we add them to an array list
		while(in.hasNext()) {
			input.add(in.nextInt());
		}
				
		in.close();
		
		// move input to an array of Integers
		return (Integer[]) input.toArray(new Integer[0]);
	}
	
	private static void writeOutResult(Integer[] sorted, String outputFilename) throws FileNotFoundException {

		PrintWriter out = new PrintWriter(outputFilename);
		for (Integer s : sorted) {
			out.println(s);
			//out.println(s + " " + Integer.toBinaryString(s));
		}
		out.close();

	}
	
	private static class BinaryComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer n1, Integer n2) {
			int digits1 = Helper.numBinaryOnes(n1);
			int digits2 = Helper.numBinaryOnes(n2);
			
			int lengthSubstring1 = Helper.lengthLongestRepeatedSubstring(Integer.toBinaryString(n1));
			int lengthSubstring2 = Helper.lengthLongestRepeatedSubstring(Integer.toBinaryString(n2));
			
			if (digits1 != digits2) return (digits1 - digits2);
			// executed only of the number of 1s is the same
			if (lengthSubstring1 != lengthSubstring2) return (lengthSubstring1 - lengthSubstring2);
			
			// executed only if both of the other ones were the same:
			return (n1 - n2);
		}
		
	}
	private static class BinaryComparator2 implements Comparator<Integer> {

		@Override
		public int compare(Integer n1, Integer n2) {
			// int digits1 = Helper.numBinaryOnes(n1);
			// int digits2 = Helper.numBinaryOnes(n2);
			
			// int lengthSubstring1 = Helper.lengthLongestRepeatedSubstring(Integer.toBinaryString(n1));
			// int lengthSubstring2 = Helper.lengthLongestRepeatedSubstring(Integer.toBinaryString(n2));
			
			// if (digits1 != digits2) return (digits1 - digits2);
			// executed only of the number of 1s is the same
			// if (lengthSubstring1 != lengthSubstring2) return (lengthSubstring1 - lengthSubstring2);
			
			// executed only if both of the other ones were the same:
			return (n1 - n2);
		}
		
	}
	

}

