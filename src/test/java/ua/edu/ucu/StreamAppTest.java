package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class StreamAppTest {
    
    private IntStream intStream;
    private IntStream emptyStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
        int[] emptyArr = {};
        emptyStream = AsIntStream.of(emptyArr);
    }
    
    @Test
    public void testStreamOperations() {
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testStreamToArray() {
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);        
    }

    @Test
    public void testStreamForEach() {
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);        
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAverageOnEmpty() {
        emptyStream.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxOnEmpty() {
        emptyStream.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinOnEmpty() {
        emptyStream.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountOnEmpty() {
        emptyStream.count();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumOnEmpty() {
        emptyStream.sum();
    }

    @Test
    public void testToArrayOnEmpty() {
        int[] expected = new int[]{};
        int[] actual = emptyStream.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testToArrayOnNormal() {
        int[] expected = new int[]{-1, 0, 1, 2, 3};
        int[] actual = intStream.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSumOnNormal() {
        int expected = 5;
        int actual = intStream.sum();
        assertEquals(expected, actual);
    }

    @Test
    public void testCountOnNormal() {
        int expected = 5;
        int actual = (int) intStream.count();
        assertEquals(expected, actual);
    }

    @Test
    public void testAverageOnNormal() {
        double expected = 1.0;
        double actual = intStream.average();
        assertEquals(expected, actual, 0.000000001);
    }

    @Test
    public void testMaxOnNormal() {
        int expected = 3;
        int actual = intStream.max();
        assertEquals(expected, actual);
    }

    @Test
    public void testMinOnNormal() {
        int expected = -1;
        int actual = intStream.min();
        assertEquals(expected, actual);
    }
}
