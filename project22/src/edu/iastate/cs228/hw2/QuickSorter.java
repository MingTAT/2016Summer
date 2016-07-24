package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Ming
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
		
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		// TODO 
		super(pts);
		algorithm = "quicksort";
		outputFileName = "quick.txt";
	}
		

	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public QuickSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		// TODO 
		super(inputFileName);
		algorithm = "quicksort";
		outputFileName = "quick.txt";
	}


	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		// TODO 
		long sortingbegintime = System.nanoTime();
		setComparator(order);
		quickSortRec(0, points.length-1);
		long sortingfinishTime = System.nanoTime();
		sortingTime = sortingfinishTime -sortingbegintime ;
	}


	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		// TODO
		
		if(first >= last) return;
		int m = partition(first, last);
		quickSortRec(first, m-1);
		quickSortRec(m+1, last);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		// TODO 
//		int i = first +1;
//		int j = last;
//		while(true){
//			while(pointComparator.compare(points[++i], points[last]) == -1)
//				if(i == last) break;
//			while(pointComparator.compare(points[j], points[--j])==-1)
//				if(j == first) break;
//			
//			if (i >= j) break;
//				super.swap(i, j);
//			
//		}
//		
//			super.swap(i, last);
//			return i;
		
		Point pivot = points[last];
		int i = first -1;
		for (int j=first;j<last;j++){
			if (pointComparator.compare(points[j], pivot)<=0){
				i++;
				super.swap(i, j);
			}
		}
		
		super.swap(i+1, last);
		return i+1; 
		
		
		
	}	
		

	// Other private methods in case you need ...

}
