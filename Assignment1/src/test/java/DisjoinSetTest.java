import org.junit.Test;

public class DisjoinSetTest {

    @Test
    public void test1() {
        DisjoinSet set = new DisjoinSet(13);
        for (int i = 0; i <= 12; i++) {
            set.makeSet(i);
        }
        set.union(2, 10);
        set.union(7, 5);
        set.union(6, 1);
        set.union(3, 4);
        set.union(5, 11);
        set.union(7, 8);
        set.union(7, 3);
        set.union(12, 2);
        set.union(9, 6);
        System.out.println(set.find(6));
        System.out.println(set.find(3));
        System.out.println(set.find(11));
        System.out.println(set.find(9));
    }

    @Test
    public void test2() {
        DisjoinSet set = new DisjoinSet(61);
        for (int i = 0; i <= 60; i++) {
            set.makeSet(i);
        }
        for (int i = 1; i <= 30; i++) {
            set.union(i, 2 * i);
        }
        for (int i = 1; i <= 20; i++) {
            set.union(i, 2 * i);
        }
        for (int i = 1; i <= 12; i++) {
            set.union(i, 5 * i);
        }

        System.out.println(set.find(6));
        System.out.println(set.find(3));
        System.out.println(set.find(11));
        System.out.println(set.find(9));
    }
}
