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
public class PlacingParenthesesNGTest {
    
    @Test
    public void testGetMaximValue_3() {
        PlacingParentheses instance = new PlacingParentheses("1+2");
        long expResult = 3L;
        long result = instance.getMaximValue();
        assertEquals(result, expResult);
    }
    
    @Test
    public void testGetMaximValue_n() {
        PlacingParentheses instance = new PlacingParentheses("5-8+7*4-8+9");
        long expResult = 200L;
        long result = instance.getMaximValue();
        assertEquals(result, expResult);
    }
}
