package edu.iastate.cs228.hw2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Anthony House
 *
 */
public class PointTest {
    Point p;
    Point q;
    Point s;
    Point z;
    Point r;
    Point v;

    @Before
    public void setup() {
        p = new Point(5, 5);
        q = new Point(7, 6);
        s = new Point(2, 4);
        z = new Point(5, 5);
        r = new Point(5, 7);
        v = new Point(5, 4);
    }

    @Test
    public void testGetX() throws Exception {
        assertTrue(p.getX() == 5);
        assertFalse(p.getX() == 7);
    }

    @Test
    public void testGetY() throws Exception {
        assertTrue(p.getY() == 5);
        assertFalse(p.getY() == 7);
    }

    @Test
    public void testEquals() throws Exception {
        assertFalse(p.equals(q));
        assertTrue(p.equals(z));
    }

    @Test
    public void testCompareTo() throws Exception {
        assertEquals(1, p.compareTo(s));
        assertEquals(0, p.compareTo(z));
        assertEquals(-1, p.compareTo(q));
        assertEquals(-1, p.compareTo(r));
        assertEquals(1, p.compareTo(v));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("(5, 5)", p.toString());
        assertEquals("(7, 6)", q.toString());
        assertEquals("(2, 4)", s.toString());
        assertEquals("(5, 5)", z.toString());
        assertEquals("(5, 7)", r.toString());
        assertEquals("(5, 4)", v.toString());
    }
}