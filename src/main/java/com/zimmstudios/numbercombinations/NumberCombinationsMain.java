package com.zimmstudios.numbercombinations;

import java.util.Arrays;
import java.util.List;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * The purpose of this class is to help with Sudoku-variant puzzles
 * that are crossed with kakuros, often referred to as "Killer Sudoku".
 * http://en.wikipedia.org/wiki/Killer_sudoku
 * 
 * You often have to find a certain sum that is composed of a certain number
 * of digits, and excludes certain digits.
 * 
 * For example, find all combinations of FOUR digits that add up to TWENTY
 * and exclude the digits TWO and SEVEN.  The result would be:
 * [8, 5, 4, 3]
 * [8, 6, 5, 1]
 * [9, 6, 4, 1]
 * 
 * To use this class, fill out the constants indicated below.  The constants
 * define the sum, the number of digits, and the digits to exclude. Then
 * run this class in your IDE or from the command-line, and the results will
 * be printed to standard output.
 */
public class NumberCombinationsMain
{
    // ************************************************************************
    // **** These are the parameters you should alter depending on the sum ****
    // ************************************************************************
    // The desired sum
    private static final int DESIRED_SUM = 20;
    // The number of digits you need in the sum
    private static final int NUM_DIGITS = 4;
    // A list of digits that cannot be included in the sum.
    // Set this to null or Collections.EMPTY_LIST if all digits are allowed.
    private static final List<Integer> DO_NOT_INCLUDE = Arrays.asList(2, 7);
    // ************************************************************************
    // ************************************************************************
    
    // Do not alter this (9 is always going to be the max digit for Sudokus)
    private static final int MAX_DIGIT = 9;
    
    
    /**
     * See class JavaDoc.
     * @param args are not used for this program.
     */
    public static void main( String[] args )
    {
        // Create an instance of the partition generator to generate all
        // possible partitions of the sum
        Generator<Integer> partitionGenerator =
                Factory.createPartitionGenerator(DESIRED_SUM);

        // Loop through all the partitions
        for (ICombinatoricsVector<Integer> partition : partitionGenerator)
        {
            // Eliminate partitions that don't fit the criteria
            if (!partition.hasDuplicates()                          // eliminate lists that contain duplicates
                    && !containsGreaterThan(partition, MAX_DIGIT)   // eliminate lists with digits greater than the max
                    && partition.getSize() == NUM_DIGITS            // ensure the list contains the required number of digits
                    && !containsAny(partition, DO_NOT_INCLUDE))     // ensure the list does not contain any restricted digits
            {
                // Print the partitions that meet the criteria
                System.out.println(partition);
            }
        }
    }
    
    /**
     * Returns true if the given vector contains a value that is larger
     * than the threshold.
     * @param vector is the vector to check.
     * @param threshold is the maximum number allowed.
     * @return true if the vector contains any digits greater than the
     * threshold; return false otherwise.
     */
    public static boolean containsGreaterThan(
            ICombinatoricsVector<Integer> vector,
            int threshold)
    {
        validateVector(vector);
        
        for (Integer i : vector)
        {
            if (i > threshold)
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Returns true if the given vector contains any of the digits in the
     * specified list of integers.
     * @param vector is the vector to check.
     * @param list is the list of digits to look for in the vector.
     * @return true if any of the digits in list are contained in vector;
     * return false otherwise.
     */
    public static boolean containsAny(
            ICombinatoricsVector<Integer> vector,
            List<Integer> list)
    {
        validateVector(vector);
        
        // If the list is empty, obviously the vector won't contain any of it
        if (list == null || list.isEmpty())
        {
            return false;
        }
        
        for (Integer i : list)
        {
            if (vector.contains(i))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Verify that the given vector is not null or empty.
     * @param vector is the vector to check.
     * @throws IllegalArgumentException if the vector is null or empty.
     */
    private static void validateVector(ICombinatoricsVector<Integer> vector)
    {
        // Check the vector
        if (vector == null)
        {
            throw new IllegalArgumentException("The vector cannot be null.");
        }
        if (vector.getSize() == 0)
        {
            throw new IllegalArgumentException("The vector cannot be empty.");
        }
    }
}
