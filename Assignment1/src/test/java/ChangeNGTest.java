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
public class ChangeNGTest {
    
    @Test
    public void testGetChange_2() {
        int n = 2;
        int expResult = 2;
        int result = Change.getChange(n);
        assertEquals(result, expResult);
    }

    @Test
    public void testGetChange_8() {
        int n = 8;
        int expResult = 4;
        int result = Change.getChange(n);
        assertEquals(result, expResult);
    }

    @Test
    public void testGetChange_28() {
        int n = 28;
        int expResult = 6;
        int result = Change.getChange(n);
        assertEquals(result, expResult);
    }

}
