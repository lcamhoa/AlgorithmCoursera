/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class DifferentSummandsNGTest {
    
    @Test
    public void testOptimalSummands_5() {
        int n = 5;
        List<Integer> expResult = Arrays.asList(1, 4);
        List<Integer> result = DifferentSummands.optimalSummands(n);
        assertEquals(result, expResult);
    }

    @Test
    public void testOptimalSummands_8() {
        int n = 8;
        List<Integer> expResult = Arrays.asList(1, 2, 5);
        List<Integer> result = DifferentSummands.optimalSummands(n);
        assertEquals(result, expResult);
    }

}
