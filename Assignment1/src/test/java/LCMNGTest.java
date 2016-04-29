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
public class LCMNGTest {
    
    @Test
    public void testLcm_small() {
        int a = 6;
        int b = 8;
        long expResult = 24L;
        long result = LCM.lcm(a, b);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testLcm_large() {
        int a = 28851538;
        int b = 1183019;
        long expResult = 1933053046L;
        long result = LCM.lcm(a, b);
        assertEquals(result, expResult);
    }
    
}
