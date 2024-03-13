/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    //@Disabled("TODO revise test logic")
    @DisplayName("Test construction")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        //setB.add(11);
        assertThrows(IllegalArgumentException.class, () -> setB.add(11), "add: element added.");
        assertFalse(setB.contains(11), "add: added element not found in set.");
        assertNotEquals(7, setB.size(), "add: elements count not as expected.");

        setA = new BoundedSetOfNaturals(3);
        setA.add(99);
        assertThrows(IllegalArgumentException.class, () -> setA.add(99), "add duplicate: duplicate element added.");
        assertThrows(IllegalArgumentException.class, () -> setA.add(0), "non natural element add: non natural element added.");

    }

    //@Disabled("TODO revise to test the construction from invalid arrays")
    @DisplayName("Test construction from array")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(elems), "fromArray: negative element added.");
    }

    @DisplayName("Test Intersection")
    @Test
    public void testIntersection() {
        int[] elems = new int[]{10, 20, 30};

        assertFalse(setA.intersect(BoundedSetOfNaturals.fromArray(elems)), "intersects: sets do intersect.");
        assertTrue(setB.intersect(BoundedSetOfNaturals.fromArray(elems)), "intersects: sets do not intersect.");
        assertTrue(setB.intersect(setC), "intersects: sets do not intersect.");
        assertFalse(setA.intersect(setB), "intersects: sets do intersect.");
    }

    @DisplayName("Test hashCode")
    @Test
    public void testHashCode() {
        assertEquals(setA.hashCode(), setA.hashCode(), "hashCode: hash codes are not equal.");
        assertNotEquals(setA.hashCode(), setB.hashCode(), "hashCode: hash codes are equal.");
    }

    @DisplayName("Test equals")
    @Test
    public void testEquals() {
        assertTrue(setA.equals(setA), "equals: sets are not equal.");
        assertFalse(setA.equals(setB), "equals: sets are equal.");
        assertFalse(setB.equals(null), "equals: sets are equal.");
        assertFalse(setB.equals(new Object()), "equals: sets are equal.");
    }
}