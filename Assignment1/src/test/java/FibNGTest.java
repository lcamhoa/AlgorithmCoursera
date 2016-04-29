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
public class FibNGTest {
    
    @Test
    public void testCalc_fib_10() {
        int n = 10;
        long expResult = 55L;
        long result = Fib.calc_fib(n);
        assertEquals(result, expResult);
    }

    @Test
    public void testCalc_fib_100() {
        int n = 64;
        long expResult = 10610209857723L;
        long result = Fib.calc_fib(n);
        assertEquals(result, expResult);
    }

}
