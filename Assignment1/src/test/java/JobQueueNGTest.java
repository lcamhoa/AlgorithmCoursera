/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class JobQueueNGTest {

    @Test
    public void testAssignJobs_5() {
        ArrayList<JobQueue.IntLongPair> expResult = new ArrayList<>(5);
        expResult.add(new JobQueue.IntLongPair(0, 0L));
        expResult.add(new JobQueue.IntLongPair(1, 0L));
        expResult.add(new JobQueue.IntLongPair(0, 1L));
        expResult.add(new JobQueue.IntLongPair(1, 2L));
        expResult.add(new JobQueue.IntLongPair(0, 4L));
        ArrayList<JobQueue.IntLongPair> result = JobQueue.assignJobs(2, Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(result, expResult);
    }

    @Test
    public void testAssignJobs_20() {
        ArrayList<JobQueue.IntLongPair> expResult = new ArrayList<>(20);
        expResult.add(new JobQueue.IntLongPair(0, 0L));
        expResult.add(new JobQueue.IntLongPair(1, 0L));
        expResult.add(new JobQueue.IntLongPair(2, 0L));
        expResult.add(new JobQueue.IntLongPair(3, 0L));
        expResult.add(new JobQueue.IntLongPair(0, 1L));
        expResult.add(new JobQueue.IntLongPair(1, 1L));
        expResult.add(new JobQueue.IntLongPair(2, 1L));
        expResult.add(new JobQueue.IntLongPair(3, 1L));
        expResult.add(new JobQueue.IntLongPair(0, 2L));
        expResult.add(new JobQueue.IntLongPair(1, 2L));
        expResult.add(new JobQueue.IntLongPair(2, 2L));
        expResult.add(new JobQueue.IntLongPair(3, 2L));
        expResult.add(new JobQueue.IntLongPair(0, 3L));
        expResult.add(new JobQueue.IntLongPair(1, 3L));
        expResult.add(new JobQueue.IntLongPair(2, 3L));
        expResult.add(new JobQueue.IntLongPair(3, 3L));
        expResult.add(new JobQueue.IntLongPair(0, 4L));
        expResult.add(new JobQueue.IntLongPair(1, 4L));
        expResult.add(new JobQueue.IntLongPair(2, 4L));
        expResult.add(new JobQueue.IntLongPair(3, 4L));
        ArrayList<JobQueue.IntLongPair> result = JobQueue.assignJobs(4, Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        assertEquals(result, expResult);
    }

}
