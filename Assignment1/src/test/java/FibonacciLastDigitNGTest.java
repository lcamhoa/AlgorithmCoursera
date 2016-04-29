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
public class FibonacciLastDigitNGTest {
    
    @Test
    public void testGetFibonacciLastDigit_3() {
        int n = 3;
        int expResult = 2;
        int result = FibonacciLastDigit.getFibonacciLastDigit(n);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testGetFibonacciLastDigit() {
        int n = 327305;
        int expResult = 5;
        int result = FibonacciLastDigit.getFibonacciLastDigit(n);
        assertEquals(result, expResult);
    }
}
