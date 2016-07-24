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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		// TODO  
		super(pts);
		algorithm = "mergesort";
		outputFileName = "merge.txt";
	}
	
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public MergeSorter(String inputFileName) throws InputMismatchException, FileNotFoundException
	{
		// TODO 
		super(inputFileName);
		algorithm = "mergesort";
		outputFileName = "merge.txt";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
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
		mergeSortRec(points);
		long sortingfinishTime = System.nanoTime();
		sortingTime = sortingfinishTime -sortingbegintime ;
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		Point[] tmpPint = new Point[pts.length];
		mergeSort(pts,tmpPint, 0, pts.length-1);
	}

	
	// Other private methods in case you need ...
//	private static void sort(Point[] a, Point[] tmp, int left, int right, int rightEnd){
//		int leftEnd = right - 1;
//		int k = left;
//		int num = rightEnd - left + 1;
//		
//        while(left <= leftEnd && right <= rightEnd)
//            if(a[left].compareTo(a[right]) <= 0)
//                tmp[k++] = a[left++];
//            else
//                tmp[k++] = a[right++];
//
//        while(left <= leftEnd)    // Copy rest of first half
//            tmp[k++] = a[left++];
//
//        while(right <= rightEnd)  // Copy rest of right half
//            tmp[k++] = a[right++];
//
//        // Copy tmp back
//        for(int i = 0; i < num; i++, rightEnd--)
//            a[rightEnd] = tmp[rightEnd];
//    
//	}
	
	 private static void merge( Point [ ] a, Point [ ] tmpArray,
             int leftPos, int rightPos, int rightEnd )
	 	{
		 	    int leftEnd = rightPos - 1;
		 		int tmpPos = leftPos;
		 		int numElements = rightEnd - leftPos + 1;

		 		// Main loop
		 		while( leftPos <= leftEnd && rightPos <= rightEnd )
		 			if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
		 				tmpArray[ tmpPos++ ] = a[ leftPos++ ];
		 			else
		 				tmpArray[ tmpPos++ ] = a[ rightPos++ ];

		 		while( leftPos <= leftEnd )    // Copy rest of first half
		 			tmpArray[ tmpPos++ ] = a[ leftPos++ ];

		 		while( rightPos <= rightEnd )  // Copy rest of right half
		 			tmpArray[ tmpPos++ ] = a[ rightPos++ ];

		 		// Copy tmpArray back
		 		for( int i = 0; i < numElements; i++, rightEnd-- )
		 			a[ rightEnd ] = tmpArray[ rightEnd ];
}
	
	 private static void mergeSort( Point [ ] a, Point[ ] tmpArray,
             int left, int right )
  {
      if( left < right )
      {
          int center = ( left + right ) / 2;
          mergeSort( a, tmpArray, left, center );
          mergeSort( a, tmpArray, center + 1, right );
          merge( a, tmpArray, left, center + 1, right );
      }
  }
//	private static void mergeSort(Point[] a, Point[] tmp, int left, int right){
//		if( left < right )
//		{
//			int center = (left + right) / 2;
//			mergeSort(a, tmp, left, center);
//			mergeSort(a, tmp, center + 1, right);
//			sort(a, tmp, left, center + 1, right);
//		}
//	}

}
