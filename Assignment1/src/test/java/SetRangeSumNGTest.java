/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author swadnerkar
 */
public class SetRangeSumNGTest {

    static final int MODULO = 1000000001;

    @Test
    public void testSolve_1() throws Exception {
        SetRangeSum s = new SetRangeSum();
        s._insert(7);
        s._insert(2);
        s._erase(4);
        s._erase(7);
        Assert.assertEquals(0, s._sum(3, 6));
        Assert.assertEquals(2, s._sum(2, 8));
    }

    @Test
    public void testSolve_1_sum() throws Exception {
        SetRangeSum s = new SetRangeSum();
        s._insert(2);
        s._insert(3);
        s._insert(4);
        s._erase(3);
        s._erase(2);
        s._erase(4);
        Assert.assertFalse(s._find(3));
        s._insert(3);
        Assert.assertTrue(s._find(3));
    }

    @Test
    public void testSolve_15() throws Exception {
        SetRangeSum s = new SetRangeSum();
        Assert.assertFalse(s._find(1));
        s._insert(1);
        Assert.assertTrue(s._find(1));
        s._insert(2);
        Assert.assertEquals(3, s._sum(1, 2));
        s._insert(1000000000);
        Assert.assertTrue(s._find(1000000000));
        s._erase(1000000000);
        Assert.assertFalse(s._find(1000000000));
        Assert.assertEquals(1, s._sum(999999999, 1000000000));
        s._erase(2);
        Assert.assertFalse(s._find(2));
        s._erase(0);
        s._insert(9);
        Assert.assertEquals(10, s._sum(0, 9));
    }

    @Test
    public void testSolve_5_1() throws Exception {
        SetRangeSum s = new SetRangeSum();
        s._insert(491572259);
        Assert.assertTrue(s._find(491572259));
        s._insert(899375874);
        Assert.assertEquals(491572259, s._sum(310971296, 877523306));
        s._insert(352411209);
        Assert.assertEquals(0, s._sum(310971296, 877523306));
    }

    @Test
    public void stress_test() throws Exception {
        SetRangeSum s = new SetRangeSum();
        TreeSet<Integer> set = new TreeSet<>();
        int lastSum = 0;
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (true) {
            int op = r.nextInt(4);
            switch (op) {
                case 0: { // sum 
                    int _x = r.nextInt(10);
                    int _y = r.nextInt(10);
                    int x, y;
                    if (_x <= _y) {
                        x = _x;
                        y = _y;
                    } else {
                        x = _y;
                        y = _x;
                    }
                    sb.append(String.format("s %d %d\n", x, y));
                    long res = s._sum(x, y);
                    long expected = set.subSet((x + lastSum) % MODULO, (y + 1 + lastSum) % MODULO).stream().reduce(0, Integer::sum);
                    Assert.assertEquals(res, expected,
                            String.format("ops: %s, TreeSet: %s", sb, Objects.toString(set)));
                    lastSum = (int) (res % MODULO);
                }
                break;
                case 1: { // delete
                    boolean existing = r.nextBoolean();
                    int x = existing && !set.isEmpty() ? set.last() : r.nextInt(10);
                    sb.append(String.format("- %d\n", x));
                    s._erase(x);
                    set.remove((lastSum + x)% MODULO);
                }
                break;
                case 2: { // find
                    boolean existing = r.nextBoolean();
                    int x = existing && !set.isEmpty() ? set.last() : r.nextInt(10);
                    sb.append(String.format("? %d\n", x));
                    boolean status = s._find(x);
                    Assert.assertEquals(status, set.contains((x + lastSum) % MODULO),
                            String.format("ops: %s, TreeSet: %s", sb, Objects.toString(set)));
                }
                break;
                case 3: { // insert
                    boolean existing = r.nextBoolean();
                    int x = existing && !set.isEmpty() ? set.last() : r.nextInt(10);
                    sb.append(String.format("+ %d\n", x));               
                    s._insert(x);
                    set.add((x + lastSum) % MODULO);
                }
                break;
            }
        }
    }
}
