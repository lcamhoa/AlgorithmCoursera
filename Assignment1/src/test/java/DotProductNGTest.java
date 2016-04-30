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
public class DotProductNGTest {
    
    @Test
    public void testMinDotProduct_1() {
        int[] a = {23};
        int[] b = {39};
        long expResult = 897L;
        long result = DotProduct.minDotProduct(a, b);
        assertEquals(result, expResult);
    }

    @Test
    public void testMinDotProduct_3() {
        int[] a = {1, 3, -5};
        int[] b = {-2, 4, 1};
        long expResult = -25L;
        long result = DotProduct.minDotProduct(a, b);
        assertEquals(result, expResult);
    }
}
