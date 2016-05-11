/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class SetRangeSumNGTest {
    
    @Test
    public void testSolve_1() throws Exception {
        String inputStr = "10\n" +
"? 1\n" +
"+ 1\n" +
"? 1\n" +
"+ 2\n" +
"+ 3\n" +
"- 1\n" +
"+ 6\n" +
"+ 7\n" +
"? 2\n" +
"? 1\n";
        InputStream inStream = new ByteArrayInputStream(inputStr.getBytes(StandardCharsets.UTF_8));

        SetRangeSum.FastScanner in = new SetRangeSum.FastScanner(inStream);
        OutputStream out = new ByteArrayOutputStream();
        SetRangeSum instance = new SetRangeSum();
        instance.solve(in, new PrintStream(out));
        String expectedString = "Not found\n" +
"Found\n" +
"Found\n" +
"Not found\n";
        Assert.assertEquals(expectedString, out.toString());
    }
    
    @Test
    public void testSolve_1_sum() throws Exception {
        String inputStr = "9\n+ 2\n+ 3\n+ 4\n- 3\n- 2\n- 4\n? 3\n+ 3\n? 3\n";
        InputStream inStream = new ByteArrayInputStream(inputStr.getBytes(StandardCharsets.UTF_8));

        SetRangeSum.FastScanner in = new SetRangeSum.FastScanner(inStream);
        OutputStream out = new ByteArrayOutputStream();
        SetRangeSum instance = new SetRangeSum();
        instance.solve(in, new PrintStream(out));
        String expectedString = "Not found\nFound\n";
        Assert.assertEquals(expectedString, out.toString());
    }
    
    @Test
    public void testSolve_15() throws Exception {
        String inputStr = "15\n" +
"? 1\n" +
"+ 1\n" +
"? 1\n" +
"+ 2\n" +
"s 1 2\n" +
"+ 1000000000\n" +
"? 1000000000\n" +
"- 1000000000\n" +
"? 1000000000\n" +
"s 999999999 1000000000\n" +
"- 2\n" +
"? 2\n" +
"- 0\n" +
"+ 9\n" +
"s 0 9\n";
        InputStream inStream = new ByteArrayInputStream(inputStr.getBytes(StandardCharsets.UTF_8));

        SetRangeSum.FastScanner in = new SetRangeSum.FastScanner(inStream);
        OutputStream out = new ByteArrayOutputStream();
        SetRangeSum instance = new SetRangeSum();
        instance.solve(in, new PrintStream(out));
        String expectedString = "Not found\n" +
"Found\n" +
"3\n" +
"Found\n" +
"Not found\n" +
"1\n" +
"Not found\n" +
"10\n";
        Assert.assertEquals(expectedString, out.toString());
    }
    
    @Test
    public void testSolve_15_1() throws Exception {
        String inputStr = "15\n" +
"? 1\n" +
"+ 1\n" +
"? 1\n" +
"+ 2\n" +
"s 1 2\n" +
"+ 1000000000\n" +
"? 1000000000\n" +
"- 1000000000\n" +
"? 1000000000\n" +
"s 999999999 1000000000\n" +
"- 2\n" +
"? 2\n" +
"- 0\n" +
"+ 9\n" +
"s 0 9\n";
        InputStream inStream = new ByteArrayInputStream(inputStr.getBytes(StandardCharsets.UTF_8));

        SetRangeSum.FastScanner in = new SetRangeSum.FastScanner(inStream);
        OutputStream out = new ByteArrayOutputStream();
        SetRangeSum instance = new SetRangeSum();
        instance.solve(in, new PrintStream(out));
        String expectedString = "Not found\n" +
"Found\n" +
"3\n" +
"Found\n" +
"Not found\n" +
"1\n" +
"Not found\n" +
"10\n";
        Assert.assertEquals(expectedString, out.toString());
    }
    
    @Test
    public void testSolve_5() throws Exception {
        String inputStr = "5\n" +
"? 0\n" +
"+ 0\n" +
"? 0\n" +
"- 0\n" +
"? 0\n";
        InputStream inStream = new ByteArrayInputStream(inputStr.getBytes(StandardCharsets.UTF_8));

        SetRangeSum.FastScanner in = new SetRangeSum.FastScanner(inStream);
        OutputStream out = new ByteArrayOutputStream();
        SetRangeSum instance = new SetRangeSum();
        instance.solve(in, new PrintStream(out));
        String expectedString = "Not found\n" +
"Found\n" +
"Not found\n";
        Assert.assertEquals(expectedString, out.toString());
    }
    
    
    @Test
    public void testSolve_5_1() throws Exception {
        String inputStr = "5\n" +
"+ 491572259\n" +
"? 491572259\n" +
"? 899375874\n" +
"s 310971296 877523306\n" +
"+ 352411209\n";
        InputStream inStream = new ByteArrayInputStream(inputStr.getBytes(StandardCharsets.UTF_8));

        SetRangeSum.FastScanner in = new SetRangeSum.FastScanner(inStream);
        OutputStream out = new ByteArrayOutputStream();
        SetRangeSum instance = new SetRangeSum();
        instance.solve(in, new PrintStream(out));
        String expectedString = "Found\n" +
"Not found\n" +
"491572259\n";
        Assert.assertEquals(expectedString, out.toString());
    }
    
}
