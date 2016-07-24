package edu.iastate.cs228.hw2;

/**
 *  
 * @author Ming
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Perform the four sorting algorithms over each sequence of integers, comparing 
	 * points by x-coordinate or by polar angle with respect to the lowest point.  
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 **/
	public static void main(String[] args) throws InputMismatchException, FileNotFoundException 
	{		
		// TODO 
		// 
		// Conducts multiple sorting rounds. In each round, performs the following: 
		// 
		//    a) If asked to sort random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		//    b) Reassigns to elements in the array sorters[] (declared below) the references to the 
		//       four newly created objects of SelectionSort, InsertionSort, MergeSort and QuickSort. 
		//    c) Based on the input point order, carries out the four sorting algorithms in a for 
		//       loop that iterates over the array sorters[], to sort the randomly generated points
		//       or points from an input file.  
		//    d) Meanwhile, prints out the table of runtime statistics.
		// 
		// A sample scenario is given in Section 2 of the project description. 
		// 	
		int order = 0;
		AbstractSorter[] sorters = new AbstractSorter[4]; 
		int key = 0;
		int trial =1;
		Random rd = new Random();

		while(true){
			
			System.out.println("Comparison of Four Sorting Algorithms");
			System.out.println();
			System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
			System.out.println("order: 1 (by x-coordinate) 2 (by polar angle)");
			System.out.println();
			Scanner in = new Scanner(System.in);

			
			System.out.print("Trial "+ trial +":");
			key = in.nextInt();
			if(key==1){
				trial++;

				System.out.print("Enter number of random points:");
				int num = in.nextInt();
				System.out.print("Order used in sorting: ");
				order=in.nextInt();

				Point[] pts =generateRandomPoints(num,rd);
				sorters[1] = new InsertionSorter(pts);
				sorters[2] = new MergeSorter(pts);
				sorters[3] = new QuickSorter(pts);
				sorters[0] = new SelectionSorter(pts);
				
				System.out.println("Alogrithm \tsize \ttime(ns)");
				System.out.println("---------------------------");
				for (int j=0;j<4;j++){
					sorters[j].sort(order);
					sorters[j].writePointsToFile();
					System.out.println(sorters[j].stats());
				}
				
			 
			}
			else if(key == 2){
				trial++;

				System.out.println("Points from a file");
				System.out.print("Enter File name: ");
				String filename = in.next();
				System.out.println("File name : "+filename);
				System.out.print("Order used in sorting: ");
				order = in.nextInt();

				sorters[1] = new InsertionSorter(filename);
				sorters[2] = new MergeSorter(filename);
				sorters[3] = new QuickSorter(filename);
				sorters[0] = new SelectionSorter(filename);
				
				System.out.println("Alogrithm \tsize \ttime(ns)");
				System.out.println("---------------------------");
				for (int j=0;j<4;j++){
					sorters[j].sort(order);
					sorters[j].writePointsToFile();
					System.out.println(sorters[j].stats());
			}
			}
			else if(key ==3){

				break;
			}
			
			
		}

		

		// Within a sorting round, every sorter object write its output to the file 
		// "select.txt", "insert.txt", "merge.txt", or "quick.txt" if it is an object of 
		// SelectionSort, InsertionSort, MergeSort, or QuickSort, respectively. 
		
	}
	
	
	/**
	 * This method generates a given number of random points to initialize randomPoints[].
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		
		Point[] points = new Point[numPts];
		for(int i = 0; i < numPts; i++)
		{
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		return points; 
	}
	
}
