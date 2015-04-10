package com.zimmstudios.numbercombinations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * JUnit test case for the NumberCombinationsMain class.
 */
public class NumberCombinationsMainTest 
{
    /**
     * Verify that an IllegalArgumentException is thrown when the
     * containsGreaterThan method is called with a null vector.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testContainsGreaterThanNullVector()
    {
        NumberCombinationsMain.containsGreaterThan(null, 2);
    }
    
    /**
     * Verify that an IllegalArgumentException is thrown when the
     * containsGreaterThan method is called with an empty vector.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testContainsGreaterThanEmptyVector()
    {
        NumberCombinationsMain.containsGreaterThan(
                Factory.createVector(new Integer[] {}),
                7);
    }
    
    /**
     * Test the containsGreaterThan method with a list that has at least
     * one number over the threshold.
     */
    @Test
    public void testContainsGreaterThanPositiveResult()
    {
        ICombinatoricsVector<Integer> vector =
                Factory.createVector(new Integer[] { 1, 5, 14 });
        
        boolean containsGreater =
            NumberCombinationsMain.containsGreaterThan(vector, 9);
        
        assertTrue(containsGreater);
    }
    
    /**
     * Test the containsGreaterThan method with a list that does not have
     * any numbers over the threshold.
     */
    @Test
    public void testContainsGreaterThanNegativeResult()
    {
        ICombinatoricsVector<Integer> vector =
                Factory.createVector(new Integer[] { 3, 42, 17 });
        
        boolean containsGreater =
            NumberCombinationsMain.containsGreaterThan(vector, 50);
        
        assertFalse(containsGreater);
    }
    
    /**
     * Verify that an IllegalArgumentException is thrown when the
     * containsAny method is called with a null vector.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testContainsAnyNullVector()
    {
        NumberCombinationsMain.containsAny(null, null);
    }
    
    /**
     * Verify that an IllegalArgumentException is thrown when the
     * containsAny method is called with an empty vector.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testContainsAnyEmptyVector()
    {
        NumberCombinationsMain.containsAny(
                Factory.createVector(new Integer[] {}),
                null);
    }
    
    
    /**
     * Test the containsAny method with a null list of numbers to look for.
     */
    @Test
    public void testContainsAnyNullList()
    {
        ICombinatoricsVector<Integer> vector =
                Factory.createVector(new Integer[] { 5, 23, 8 });
        
        List<Integer> lookFor = null;
        
        boolean containsAny =
                NumberCombinationsMain.containsAny(vector, lookFor);
        
        assertFalse(containsAny);
    }
    
    /**
     * Test the containsAny method with an empty list of numbers to look for.
     */
    @Test
    public void testContainsAnyEmptyList()
    {
        ICombinatoricsVector<Integer> vector =
                Factory.createVector(new Integer[] { 30, 2, -14 });
        
        List<Integer> lookFor = Collections.emptyList();
        
        boolean containsAny =
                NumberCombinationsMain.containsAny(vector, lookFor);
        
        assertFalse(containsAny);
    }
    
    /**
     * Test the containsAny method with a vector that contains at least
     * one of the numbers in the list of numbers to look for.
     */
    @Test
    public void testContainsAnyPositiveResult()
    {
        ICombinatoricsVector<Integer> vector =
                Factory.createVector(new Integer[] { 11, 17, 4 });
        
        List<Integer> lookFor = Arrays.asList(2, 4, 6);
        
        boolean containsAny =
                NumberCombinationsMain.containsAny(vector, lookFor);
        
        assertTrue(containsAny);
    }
    
    /**
     * Test the containsAny method with a vector that does not contain
     * any of the numbers in the list of numbers to look for.
     */
    @Test
    public void testContainsAnyNegativeResult()
    {
        ICombinatoricsVector<Integer> vector =
                Factory.createVector(new Integer[] { 123, 46, 33 });
        
        List<Integer> lookFor = Arrays.asList(2, 4, 6);
        
        boolean containsAny =
                NumberCombinationsMain.containsAny(vector, lookFor);
        
        assertFalse(containsAny);
    }
}
