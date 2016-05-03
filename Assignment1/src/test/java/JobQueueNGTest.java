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
    public void testAssignJobs_5() {
        LinkedHashMap<Integer, Long> expResult = new LinkedHashMap<>(5);
        expResult.put(0, 0L);
        expResult.put(1, 0L);
        expResult.put(0, 1L);
        expResult.put(1, 2L);
        expResult.put(0, 4L);
        LinkedHashMap<Integer, Long> result = JobQueue.assignJobs(2, Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(result, expResult);
    }

    @Test
    public void testAssignJobs_20() {
        LinkedHashMap<Integer, Long> expResult = new LinkedHashMap<>(20);
        expResult.put(0, 0L);
        expResult.put(1, 0L);
        expResult.put(2, 0L);
        expResult.put(3, 0L);
        expResult.put(0, 1L);
        expResult.put(1, 1L);
        expResult.put(2, 1L);
        expResult.put(3, 1L);
        expResult.put(0, 2L);
        expResult.put(1, 2L);
        expResult.put(2, 2L);
        expResult.put(3, 2L);
        expResult.put(0, 3L);
        expResult.put(1, 3L);
        expResult.put(2, 3L);
        expResult.put(3, 3L);
        expResult.put(0, 4L);
        expResult.put(1, 4L);
        expResult.put(2, 4L);
        expResult.put(3, 4L);
        LinkedHashMap<Integer, Long> result = JobQueue.assignJobs(4, Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        assertEquals(result, expResult);
    }

}
