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
public class GCDNGTest {
    
    @Test
    public void testGcd_18() {
        int a = 18;
        int b = 35;
        int expResult = 1;
        int result = GCD.gcd(a, b);
        assertEquals(result, expResult);
    }
    

    @Test
    public void testGcd_large() {
        int a = 3918848;
        int b = 1653264;
        int expResult = 61232;
        int result = GCD.gcd(a, b);
        assertEquals(result, expResult);
    }

    @Test
    public void testGcd_another_large() {
        int a = 28851538;
        int b = 1183019;
        int expResult = 17657;
        int result = GCD.gcd(a, b);
        assertEquals(result, expResult);
    }
}
