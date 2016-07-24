package edu.iastate.cs228.hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
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
	public InsertionSorter(Point[] pts) 
	{
		// TODO 
		super(pts);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 * @throws IOException 
	 */
	public InsertionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException
	{
		// TODO 
		super(inputFileName);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}
	
	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 */
	@Override 
	public void sort(int order)
	{
		// TODO 
		long sortingbegintime = System.nanoTime();
		setComparator(order);
		InsertionSort();
		long sortingfinishTime = System.nanoTime();
		sortingTime = sortingfinishTime -sortingbegintime ;
	}


	private void InsertionSort() {
		// TODO Auto-generated method stub
		//points = pts;
		int k, j;
		Point tmp;
		//points=pts;
		int N = points.length;
		
		for(k =1; k<N ;k++){
			tmp = points[k];
			j = k-1;
			while((j>=0) && (points[j].compareTo(tmp)>0)){
				points[j+1] = points[j];
				j--;
			}
			points[j+1]=tmp;
		}
		
	}		
	

}
