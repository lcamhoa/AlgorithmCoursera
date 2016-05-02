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
public class BuildHeapNGTest {
    
    @Test
    public void testGenerateSwaps() {
        int[] data = {5, 4, 3, 2, 1};
        List<BuildHeap.Swap> expResult = Arrays.asList(
                new BuildHeap.Swap(1, 4),
                new BuildHeap.Swap(0, 1),
                new BuildHeap.Swap(1, 3)
                );
        List<BuildHeap.Swap> result = BuildHeap.generateSwaps(data);
        assertEquals(result, expResult);
    }
    
}
