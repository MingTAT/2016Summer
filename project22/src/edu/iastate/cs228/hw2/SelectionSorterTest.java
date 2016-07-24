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
public class SelectionSorterTest {

    private AbstractSorter selectionSorter;

    @Test
    public void checkAlgorithm() throws Exception {
        selectionSorter = new SelectionSorter("points.txt");
        assertEquals("selection sort", selectionSorter.algorithm);
    }

    @Test
    public void checkOutputFileName() throws Exception {
        selectionSorter = new SelectionSorter("points.txt");
        assertEquals("select.txt", selectionSorter.outputFileName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_null() throws Exception{
        Point[] p = null;
        selectionSorter = new SelectionSorter(p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_noLength() throws Exception{
        Point[] p = {};
        selectionSorter = new SelectionSorter(p);
    }

    @Test
    public void testConstructor_standard() throws Exception{
        Point[] p = {new Point(5, 5), new Point(0, 3)};
        selectionSorter = new SelectionSorter(p);
    }

    @Test(expected = FileNotFoundException.class)
    public void testConstructor_fileNotFound() throws Exception{
        selectionSorter = new SelectionSorter("RandomTextFileThatShouldntExist.txt");
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructor_inputMismatch() throws Exception{
        selectionSorter = new SelectionSorter("InputMismatchFile.txt");
    }

    @Test
    public void testConstructor_standardFileInput() throws Exception{
        selectionSorter = new SelectionSorter("points.txt");
    }

    @Test
    public void testSort() throws Exception {
        selectionSorter = new SelectionSorter("points.txt");
        selectionSorter.setComparator(1);
        selectionSorter.sort(1);
        Point[] actual = {new Point(-10, 0), new Point(-7, -10),
                new Point(-7, -10), new Point(-6, 3), new Point(-3, -9),
                new Point(-2, 1), new Point(-1, -6), new Point(0, -10),
                new Point(0, 0), new Point(0, 8), new Point(3, 3),
                new Point(5, -2), new Point(5, 5), new Point(7, 3),
                new Point(8, 4), new Point(10, 5), new Point(10, 5)};

        for(int i = 0; i < selectionSorter.points.length; i++) {
            assertTrue(selectionSorter.points[i].equals(actual[i]));
        }
    }

    @Test
    public void testStats() throws Exception {
        selectionSorter = new SelectionSorter("points.txt");
        selectionSorter.setComparator(1);
        selectionSorter.sort(1);
        String actual = String.format("%-17s%-9s%-10d", "selection sort", "17", selectionSorter.sortingTime);
        assertEquals(actual, selectionSorter.stats());
    }

    @Test
    public void testToString() throws Exception {
        selectionSorter = new SelectionSorter("points.txt");
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
                "5 5\n", selectionSorter.toString());
    }

    @Test
    public void testSetComparator() throws Exception {
        selectionSorter = new SelectionSorter("points.txt");
        selectionSorter.setComparator(1);
        assertEquals(false, selectionSorter.sortByAngle);
        selectionSorter.setComparator(2);
        assertEquals(true, selectionSorter.sortByAngle);
    }

    @Test
    public void testSwap() throws Exception {
        Point[] p = {new Point(5, 5), new Point(0, 3)};
        selectionSorter = new SelectionSorter(p);
        selectionSorter.swap(0, 1);
        assertTrue(selectionSorter.points[0].equals(new Point(0, 3)));
        assertTrue(selectionSorter.points[1].equals(new Point(5, 5)));
    }
}