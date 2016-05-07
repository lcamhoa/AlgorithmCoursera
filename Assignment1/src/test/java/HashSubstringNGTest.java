import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;


public class HashSubstringNGTest {

	@Test
	public void testGetOccurrences_3() {
        String p = "ab";
        String t = "baba";
        List<Integer> expResult = Arrays.asList(1);
        List<Integer> result = HashSubstring.getOccurrences(p, t);
        Assert.assertEquals(result, expResult);
	}

	@Test
	public void testGetOccurrences_7() {
        String p = "aba";
        String t = "abacaba";
        List<Integer> expResult = Arrays.asList(0, 4);
        List<Integer> result = HashSubstring.getOccurrences(p, t);
        Assert.assertEquals(result, expResult);
	}

	@Test
	public void testGetOccurrences_12() {
        String p = "Test";
        String t = "testTesttesT";
        List<Integer> expResult = Arrays.asList(4);
        List<Integer> result = HashSubstring.getOccurrences(p, t);
        Assert.assertEquals(result, expResult);
	}
}
