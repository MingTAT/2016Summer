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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		// TODO 
		super(pts);
		algorithm = "selection sort";
		outputFileName = "select.txt";
	

	}	
		

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public SelectionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		// TODO 
		super(inputFileName);
		algorithm = "selection sort";
		outputFileName = "select.txt";
	}
	
	
	/** 
	 * Apply insertion sort on the array points[] of the parent class AbstractSorter.  
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
		SelectionSort();
		long sortingfinishTime = System.nanoTime();
		sortingTime = sortingfinishTime -sortingbegintime ;
	}



	private void SelectionSort() {
		// TODO Auto-generated method stub
		int j,k,midIndex;
		Point min;
		//points = pts;	
		int N = points.length;
		
		for (k=0;k<N;k++){
			min = points[k];
			midIndex = k;
			for(j = k+1;j<N;j++){
				if(points[j].compareTo(min)<0){
					min = points[j];
					midIndex = j;
				}
			}
			points[midIndex] = points[k];
			points[k] = min;
		}
	}	
	


}
