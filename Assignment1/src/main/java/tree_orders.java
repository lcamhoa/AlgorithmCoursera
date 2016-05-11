
import java.util.*;
import java.io.*;
import java.util.function.Supplier;

public class tree_orders {

    int n;
    int[] key, left, right;

    public tree_orders(int[] key, int[] left, int[] right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>(key.length);
        int node = 0;
        while (node != -1 || !stack.isEmpty()) {
            while (node != -1) {
                stack.push(node);
                node = left[node];
            }
            node = stack.pop();
            result.add(key[node]);
            node = right[node];
        }
        return result;
    }

    List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>(key.length);
        stack.push(0);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            result.add(key[node]);
            if (right[node] != -1) {
                stack.push(right[node]);
            }
            if (left[node] != -1) {
                stack.push(left[node]);
            }
        }
        return result;
    }

    List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> stack1 = new ArrayDeque<>(key.length);
        Deque<Integer> stack2 = new ArrayDeque<>(key.length);
        stack1.push(0);
        while (!stack1.isEmpty()) {
            int node = stack1.pop();
            stack2.push(node);
            if (left[node] != -1) {
                stack1.push(left[node]);
            }
            if (right[node] != -1) {
                stack1.push(right[node]);
            }
        }
        while (!stack2.isEmpty()) {
            result.add(key[stack2.pop()]);
        }
        return result;
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                FastScanner in = new FastScanner();
                int n1 = Integer.parseInt(in.get());
                int[] key1 = new int[n1];
                int[] left1 = new int[n1];
                int[] right1 = new int[n1];
                for (int i = 0; i < n1; i++) {
                    key1[i] = Integer.parseInt(in.get());
                    left1[i] = Integer.parseInt(in.get());
                    right1[i] = Integer.parseInt(in.get());
                }
                tree_orders tree = new tree_orders(key1, left1, right1);
                print(tree.inOrder());
                print(tree.preOrder());
                print(tree.postOrder());
            } catch (Exception e) {
            }
        }, "1", 1 << 26).start();
    }

    public static void print(List<Integer> s) {
        s.forEach(i -> System.out.print(String.valueOf(i) + " "));
        System.out.println();
    }

    private static class FastScanner implements Supplier<String> {

        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;
        boolean eof = false;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        @Override
        public String get() {
            String token = null;
            if (!eof) {
                try {
                    if (!tok.hasMoreElements()) {
                        tok = new StringTokenizer(in.readLine());
                    }
                    token = tok.nextToken();
                } catch (IOException ex) {
                    eof = true;
                    token = null;
                }
            }
            return token;
        }
    }

}
