/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class SortingNGTest {
    
    @Test
    public void testRandomizedQuickSort_3() {
        int[] a = {3, 2, 1};
        int l = 0;
        int r = 2;
        Sorting.randomizedQuickSort(a, l, r);
        int[] expected = {1, 2, 3};
        assertTrue(Arrays.equals(a, expected));
    }
    
    @Test
    public void testRandomizedQuickSort_10() {
        int[] a = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int l = 0;
        int r = 9;
        Sorting.randomizedQuickSort(a, l, r);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertTrue(Arrays.equals(a, expected));
    }
    
}
