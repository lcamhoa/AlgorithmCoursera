/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class MergingTablesNGTest {
    
    @Test
    public void testMerge_5() {
        int[] numberOfRows = {1, 1, 1, 1, 1};
        int[] merges = {3, 5, 2, 4, 1, 4, 5, 4, 5, 3};

        MergingTables mergingTables = new MergingTables();
        mergingTables.createTables(numberOfRows);
        long[] maxSizes = mergingTables.doMerges(merges);
        long[] expectedMaxSizes = {2, 2, 3, 5, 5};
        assertEquals(maxSizes, expectedMaxSizes);
    }
    
    @Test
    public void testMerge_6() {
        int[] numberOfRows = {10, 0, 5, 0, 3, 3};
        int[] merges = {6, 6, 6, 5, 5, 4, 4, 3};

        MergingTables mergingTables = new MergingTables();
        mergingTables.createTables(numberOfRows);
        long[] maxSizes = mergingTables.doMerges(merges);
        long[] expectedMaxSizes = {10, 10, 10, 11};
        assertEquals(maxSizes, expectedMaxSizes);
    }
    
    @Test
    public void testMerge_6_1() {
        int[] numberOfRows = {10, 0, 5, 0, 3, 3};
        int[] merges = {6, 6, 6, 5, 5, 4, 4, 3, 1, 5, 1, 6};

        MergingTables mergingTables = new MergingTables();
        mergingTables.createTables(numberOfRows);
        long[] maxSizes = mergingTables.doMerges(merges);
        long[] expectedMaxSizes = {10, 10, 10, 11, 21, 21};
        assertEquals(maxSizes, expectedMaxSizes);
    }
    
}
