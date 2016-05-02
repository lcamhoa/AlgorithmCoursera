/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.LinkedHashMap;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class JobQueueNGTest {
    
    @Test
    public void testAssignJobs() {
        LinkedHashMap<Integer, Long> expResult = new LinkedHashMap<>(5);
        expResult.put(0, 0L);
        expResult.put(1, 0L);
        expResult.put(0, 1L);
        expResult.put(1, 2L);
        expResult.put(0, 4L);
        LinkedHashMap<Integer, Long> result = JobQueue.assignJobs(2, Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(result, expResult);
    }
    
}
