package edu.iastate.cs228.hw2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PolarAngleComparatorTest {

    PolarAngleComparator polarAngleComparator;
    Point reference;

    @Before
    public void setUp() {
        reference = new Point(0, 0);
        polarAngleComparator = new PolarAngleComparator(reference);
    }

    @Test
    public void testCompare() throws Exception {
        Point p1 = new Point(1, 0);
        Point p2 = new Point(1, 0);
        assertEquals(0, polarAngleComparator.compare(p1, p2));

        p1 = new Point(0, 0);
        assertEquals(-1, polarAngleComparator.compare(p1, p2));

        p1 = new Point(1, 1);
        p2 = new Point(2, 2);
        assertEquals(-1, polarAngleComparator.compare(p1, p2));

        p1 = new Point(0, 1);
        p2 = new Point(0, 2);
        assertEquals(-1, polarAngleComparator.compare(p1, p2));

        p1 = new Point(0, 2);
        p2 = new Point(0, 1);
        assertEquals(1, polarAngleComparator.compare(p1, p2));

        p1 = new Point(2, 2);
        p2 = new Point(1, 1);
        assertEquals(1, polarAngleComparator.compare(p1, p2));
    }

    @Test
    public void testComparePolarAngle() throws Exception {
        Point p1 = new Point(1, 0);
        Point p2 = new Point(2, 0);

        assertTrue(polarAngleComparator.comparePolarAngle(p1, p2) == 0);

        p1 = new Point(1, 0);
        p2 = new Point(2, 1);

        assertTrue(polarAngleComparator.comparePolarAngle(p1, p2) == -1);

        p1 = new Point(2, 1);
        p2 = new Point(1, 0);

        assertTrue(polarAngleComparator.comparePolarAngle(p1, p2) == 1);
    }

    @Test
    public void testCompareDistance() throws Exception {
        Point p1 = new Point(1, 0);
        Point p2 = new Point(2, 0);

        assertTrue(polarAngleComparator.compareDistance(p1, p2) == -1);

        p1 = new Point(1, 0);
        p2 = new Point(0, 1);

        assertTrue(polarAngleComparator.compareDistance(p1, p2) == 0);

        p1 = new Point(2, 0);
        p2 = new Point(1, 0);

        assertTrue(polarAngleComparator.compareDistance(p1, p2) == 1);
    }
}