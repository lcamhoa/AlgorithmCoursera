import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.Assert;


public class tree_ordersTest {

	@Test
	public void testAllOrders_5() {
		int[] key = {4, 2, 5, 1, 3};
		int[] left = {1, 3, -1, -1, -1};
		int[] right = {2, 4, -1, -1, -1};
		tree_orders tree = new tree_orders(key, left, right);
		
		List<Integer> inOrderActual = tree.inOrder();
		List<Integer> preOrderActual = tree.preOrder();
		List<Integer> postOrderActual = tree.postOrder();
		
		List<Integer> inOrderExpected = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> preOrderExpected = Arrays.asList(4, 2, 1, 3, 5);
		List<Integer> postOrderExpected = Arrays.asList(1, 3, 2, 5, 4);
		
		Assert.assertEquals(inOrderActual, inOrderExpected);
		Assert.assertEquals(preOrderActual, preOrderExpected);
		Assert.assertEquals(postOrderActual, postOrderExpected);
	}

}
