package edu.iastate.cs228.hw2;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class SorterTests {
    private Comparator<Point> comp = new Comparator<Point>() {
        @Override
        public int compare(Point p, Point other) {
            return p.compareTo(other);
        }
    };
    private int length = 1000;
    private AbstractSorter sorter;

    @Test
    public void insertionSortTest() {
        Point[] pts = CompareSorters.generateRandomPoints(length, new Random());
        Point[] expected = pts.clone();

        sorter = new InsertionSorter(pts);
        sorter.sort(1);

        Arrays.sort(expected, comp);

        assertArrayEquals("insertion sort", expected, sorter.points);
    }

    @Test
    public void mergeSortTest() {
        Point[] pts = CompareSorters.generateRandomPoints(length, new Random());
        Point[] expected = pts.clone();

        sorter = new MergeSorter(pts);
        sorter.sort(1);

        Arrays.sort(expected, comp);

        assertArrayEquals("merge sort", expected, sorter.points);
    }

    @Test
    public void quickSortTest() {
        Point[] pts = CompareSorters.generateRandomPoints(length, new Random());
        Point[] expected = pts.clone();

        sorter = new QuickSorter(pts);
        sorter.sort(1);

        Arrays.sort(expected, comp);

        assertArrayEquals("quick sort", expected, sorter.points);
    }

    @Test
    public void selectionSortTest() {
        Point[] pts = CompareSorters.generateRandomPoints(length, new Random());
        Point[] expected = pts.clone();

        sorter = new SelectionSorter(pts);
        sorter.sort(1);

        Arrays.sort(expected, comp);

        assertArrayEquals("selection sort", expected, sorter.points);
    }
}
