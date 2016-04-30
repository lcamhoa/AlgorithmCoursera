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
public class CoveringSegmentsNGTest {
    
    @Test
    public void testOptimalPoints_3() {
        CoveringSegments.Segment[] segments = new CoveringSegments.Segment[3];
        segments[0] = new CoveringSegments.Segment(1, 3);
        segments[1] = new CoveringSegments.Segment(2, 5);
        segments[2] = new CoveringSegments.Segment(3, 6);
        int[] expResult = {3};
        int[] result = CoveringSegments.optimalPoints(segments);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testOptimalPoints_4() {
        CoveringSegments.Segment[] segments = new CoveringSegments.Segment[4];
        segments[0] = new CoveringSegments.Segment(4, 7);
        segments[1] = new CoveringSegments.Segment(1, 3);
        segments[2] = new CoveringSegments.Segment(2, 5);
        segments[3] = new CoveringSegments.Segment(5, 6);
        int[] expResult = {2, 5};
        int[] result = CoveringSegments.optimalPoints(segments);
        assertEquals(result, expResult);
    }
    
}
