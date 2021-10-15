import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.*;

public class Group0 {

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
		Integer [] toSortQuick = data.clone();
		
		long start = System.currentTimeMillis();
		sort(toSort);
		long end = System.currentTimeMillis();
		
		System.out.println("It took TIM sort " + (end - start) + " milis");
		//printArray(sorted, 100);
		
		
		Thread.sleep(10); //to let other things finish before timing; adds stability of runs

		long start2 = System.currentTimeMillis();
		
		quicksort(toSortQuick, 0, toSort.length-1);
		
		long end2 = System.currentTimeMillis();
		
		System.out.println("It took our sort " + (end2 - start2) + " milis");
		
		writeOutResult(toSort, "expectedoutput.txt");
		writeOutResult(toSortQuick, outFileName);
	}
	
	private static void sort(Integer[] toSort) {
		Arrays.sort(toSort, new BinaryComparator());
	}
	// YOUR SORTING METHOD GOES HERE. 
	// You may call other methods and use other classes. 
	// Note: you may change the return type of the method. 
	// You would need to provide your own function that prints your sorted array to 
	// a file in the exact same format that my program outputs
	private static void quicksort(Integer[] toSort, int start, int end){

			if (start < end) {
				int q = partitionRandom(toSort, start, end);
				quicksort(toSort, start, q - 1);
				quicksort(toSort, q + 1, end);
	
			}
	}
	private static int partitionRandom(Integer[] array, int start, int end) {
        Random rand = new Random();
        int i = rand.nextInt(end-start)+start;
        Integer saved = array[i];
        array[i] = array[end];
        array[end] = saved;
        return partition(array,start,end);
    }
    
    private static int partition(Integer[] array, int start, int end) {
		BinaryComparator binary = new BinaryComparator();
        Integer x = array[end];
        int i = (start - 1);
        for (int j = start; j < end; j++) {
            if (binary.compare(array[j], x) < 0) {
                i++;
                Integer saved = array[i];
                array[i] = array[j];
                array[j] = saved;
            }

        }
        Integer saved = array[i + 1];
        array[i + 1] = array[end];
        array[end] = saved;
        return (i + 1);
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
	

}

