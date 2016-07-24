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
public class InsertionSorterTest {

    private AbstractSorter insertionSorter;

    @Test
    public void checkAlgorithm() throws Exception {
        insertionSorter = new InsertionSorter("points.txt");
        assertEquals("insertion sort", insertionSorter.algorithm);
    }

    @Test
    public void checkOutputFileName() throws Exception {
        insertionSorter = new InsertionSorter("points.txt");
        assertEquals("insert.txt", insertionSorter.outputFileName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_null() throws Exception{
        Point[] p = null;
        insertionSorter = new InsertionSorter(p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_noLength() throws Exception{
        Point[] p = {};
        insertionSorter = new InsertionSorter(p);
    }

    @Test
    public void testConstructor_standard() throws Exception{
        Point[] p = {new Point(5, 5), new Point(0, 3)};
        insertionSorter = new InsertionSorter(p);
    }

    @Test(expected = FileNotFoundException.class)
    public void testConstructor_fileNotFound() throws Exception{
        insertionSorter = new InsertionSorter("RandomTextFileThatShouldntExist.txt");
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructor_inputMismatch() throws Exception{
        insertionSorter = new InsertionSorter("InputMismatchFile.txt");
    }

    @Test
    public void testConstructor_standardFileInput() throws Exception{
        insertionSorter = new InsertionSorter("points.txt");
    }

    @Test
    public void testSort() throws Exception {
        insertionSorter = new InsertionSorter("points.txt");
        insertionSorter.setComparator(1);
        insertionSorter.sort(1);
        Point[] actual = {new Point(-10, 0), new Point(-7, -10),
                new Point(-7, -10), new Point(-6, 3), new Point(-3, -9),
                new Point(-2, 1), new Point(-1, -6), new Point(0, -10),
                new Point(0, 0), new Point(0, 8), new Point(3, 3),
                new Point(5, -2), new Point(5, 5), new Point(7, 3),
                new Point(8, 4), new Point(10, 5), new Point(10, 5)};

        for(int i = 0; i < insertionSorter.points.length; i++) {
            assertTrue(insertionSorter.points[i].equals(actual[i]));
        }
    }

    @Test
    public void testStats() throws Exception {
        insertionSorter = new InsertionSorter("points.txt");
        insertionSorter.setComparator(1);
        insertionSorter.sort(1);
        String actual = String.format("%-17s%-9s%-10d", "insertion sort", "17", insertionSorter.sortingTime);
        assertEquals(actual, insertionSorter.stats());
    }

    @Test
    public void testToString() throws Exception {
        insertionSorter = new InsertionSorter("points.txt");
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
                "5 5\n", insertionSorter.toString());
    }

    @Test
    public void testSetComparator() throws Exception {
        insertionSorter = new InsertionSorter("points.txt");
        insertionSorter.setComparator(1);
        assertEquals(false, insertionSorter.sortByAngle);
        insertionSorter.setComparator(2);
        assertEquals(true, insertionSorter.sortByAngle);
    }

    @Test
    public void testSwap() throws Exception {
        Point[] p = {new Point(5, 5), new Point(0, 3)};
        insertionSorter = new InsertionSorter(p);
        insertionSorter.swap(0, 1);
        assertTrue(insertionSorter.points[0].equals(new Point(0, 3)));
        assertTrue(insertionSorter.points[1].equals(new Point(5, 5)));
    }
}