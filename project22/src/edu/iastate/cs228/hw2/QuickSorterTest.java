package edu.iastate.cs228.hw2;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Anthony House
 *
 */
public class QuickSorterTest {

    private AbstractSorter quickSort;

    @Test
    public void checkAlgorithm() throws Exception {
        quickSort = new QuickSorter("points.txt");
        assertEquals("quicksort", quickSort.algorithm);
    }

    @Test
    public void checkOutputFileName() throws Exception {
        quickSort = new QuickSorter("points.txt");
        assertEquals("quick.txt", quickSort.outputFileName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_null() throws Exception{
        Point[] p = null;
        quickSort = new QuickSorter(p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_noLength() throws Exception{
        Point[] p = {};
        quickSort = new QuickSorter(p);
    }

    @Test
    public void testConstructor_standard() throws Exception{
        Point[] p = {new Point(5, 5), new Point(0, 3)};
        quickSort = new QuickSorter(p);
    }

    @Test(expected = FileNotFoundException.class)
    public void testConstructor_fileNotFound() throws Exception{
        quickSort = new QuickSorter("RandomTextFileThatShouldntExist.txt");
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructor_inputMismatch() throws Exception{
        quickSort = new QuickSorter("InputMismatchFile.txt");
    }

    @Test
    public void testConstructor_standardFileInput() throws Exception{
        quickSort = new QuickSorter("points.txt");
    }

    @Test
    public void testSort() throws Exception {
        quickSort = new QuickSorter("points.txt");
        quickSort.setComparator(1);
        quickSort.sort(1);
        Point[] actual = {new Point(-10, 0), new Point(-7, -10),
                new Point(-7, -10), new Point(-6, 3), new Point(-3, -9),
                new Point(-2, 1), new Point(-1, -6), new Point(0, -10),
                new Point(0, 0), new Point(0, 8), new Point(3, 3),
                new Point(5, -2), new Point(5, 5), new Point(7, 3),
                new Point(8, 4), new Point(10, 5), new Point(10, 5)};

        for(int i = 0; i < quickSort.points.length; i++) {
            assertTrue(quickSort.points[i].equals(actual[i]));
        }
    }

    @Test
    public void testStats() throws Exception {
        quickSort = new QuickSorter("points.txt");
        quickSort.setComparator(1);
        quickSort.sort(1);
        String actual = String.format("%-17s%-9s%-10d", "quicksort", "17", quickSort.sortingTime);
        assertEquals(actual, quickSort.stats());
    }

    @Test
    public void testToString() throws Exception {
        quickSort = new QuickSorter("points.txt");
        assertEquals("0 0\n" +
                "-3 -9\n" +
                "0 -10\n" +
                "8 4\n" +
                "3 3\n" +
                "-6 3\n" +
                "-2 1\n" +
                "10 5\n" +
                "-7 -10\n" +
                "5 -2\n" +
                "7 3\n" +
                "10 5\n" +
                "-7 -10\n" +
                "0 8\n" +
                "-1 -6\n" +
                "-10 0\n" +
                "5 5\n", quickSort.toString());
    }

    @Test
    public void testSetComparator() throws Exception {
        quickSort = new QuickSorter("points.txt");
        quickSort.setComparator(1);
        assertEquals(false, quickSort.sortByAngle);
        quickSort.setComparator(2);
        assertEquals(true, quickSort.sortByAngle);
    }

    @Test
    public void testSwap() throws Exception {
        Point[] p = {new Point(5, 5), new Point(0, 3)};
        quickSort = new QuickSorter(p);
        quickSort.swap(0, 1);
        assertTrue(quickSort.points[0].equals(new Point(0, 3)));
        assertTrue(quickSort.points[1].equals(new Point(5, 5)));
    }
}