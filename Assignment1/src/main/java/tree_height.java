
import java.util.*;
import java.io.*;

public class tree_height {

    class FastScanner {

        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner(InputStream inputStream) {
            in = new BufferedReader(new InputStreamReader(inputStream));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements()) {
                tok = new StringTokenizer(in.readLine());
            }
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {

        int n;
        int parent[];

        void read(InputStream inputStream) throws IOException {
            FastScanner in = new FastScanner(inputStream);
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {
            // Replace this code with a faster implementation
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;
                for (int i = vertex; i != -1; i = parent[i]) {
                    height++;
                }
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                int height = new tree_height().computeHeight(System.in);
                System.out.println(height);
            } catch (IOException e) {
            }
        }, "1", 1 << 26).start();
    }

    public int computeHeight(InputStream inputStream) throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read(inputStream);
        return tree.computeHeight();
    }
}
