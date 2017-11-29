package heap;

import org.junit.Test;

import java.util.Comparator;
import static org.junit.Assert.*;


public class HeapArrayTest {

    @Test
    public void testGetCapacity() {
        HeapArray<Integer> test = new HeapArray<Integer>(5, new Comparator<Integer>() {public int compare(Integer to, Integer t1) { return (to - t1); }});
        assertEquals(5, test.getCapacity());
    }

    @Test
    public void testGetComparator() {
        HeapArray<Integer> test = new HeapArray<Integer>(5, new Comparator<Integer>() {public int compare(Integer to, Integer t1) { return (to - t1); }});
        assertEquals(test.getComparator(), new Comparator() {public int compare(Integer to, Integer t1) { return (to - t1); }});
    }


    @Test
    public void testIsEmpty() {
        HeapArray<Integer> test = new HeapArray<Integer>(5, new Comparator<Integer>() {public int compare(Integer to, Integer t1) { return (to - t1); }});
        assertTrue(test.isEmpty());
        test.insertElement(5);
        assertFalse(test.isEmpty());
    }

    @Test
    public void testIsFull() {
        HeapArray<Integer> test = new HeapArray<Integer>(1, new Comparator<Integer>() {public int compare(Integer to, Integer t1) { return (to - t1); }});
        assertFalse(test.isEmpty());
        test.insertElement(6);
        assertTrue(test.isFull());
    }

    @Test
    public void testSize() {
        HeapArray<Integer> test = new HeapArray<Integer>(5, new Comparator<Integer>() {public int compare(Integer to, Integer t1) { return (to - t1); }});
        for(int i = 0; i < 3 ; i++){
            test.insertElement(i);
        }
        assertEquals(test.size(), 3);
    }

    @Test
    public void testInsertElement() {
            HeapArray<Integer> test = new HeapArray<Integer>(5, new Comparator<Integer>() {public int compare(Integer to, Integer t1) { return (to - t1); }});
            assertTrue(test.insertElement(1));
        }
    }

    @Test
    public void element() {
        HeapArray<Integer> test = new HeapArray<Integer>(5, new Comparator<Integer>() {public int compare(Integer to, Integer t1) { return (to - t1); }});
        test.insertElement(2);
        assertEquals((int)test.element(), 2);
    }

}