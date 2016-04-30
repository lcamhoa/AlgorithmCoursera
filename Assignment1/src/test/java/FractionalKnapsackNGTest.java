/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class FractionalKnapsackNGTest {
    
    @Test
    public void testGetOptimalValue() {
        int capacity = 50;
        int[] values = {60, 100, 120};
        int[] weights = {20, 50, 30};
        double expResult = 180.0;
        double result = FractionalKnapsack.getOptimalValue(capacity, values, weights);
        assertEquals(result, expResult, 0.001);
    }

    @Test
    public void testGetOptimalValue_Only1() {
        int capacity = 10;
        int[] values = {500};
        int[] weights = {30};
        double expResult = 166.6667;
        double result = FractionalKnapsack.getOptimalValue(capacity, values, weights);
        assertEquals(result, expResult, 0.001);
    }
}
