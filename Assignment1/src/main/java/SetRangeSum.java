import java.io.*;
import java.util.*;

public class SetRangeSum {

    // Splay tree implementation

    // Vertex of a splay tree
    private class Vertex {
        int key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private void update(Vertex v) {
        if (v == null) return;
        v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
        if (v.left != null) {
            v.left.parent = v;
        }
        if (v.right != null) {
            v.right.parent = v;
        }
    }

    private void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) {
            return;
        }
        Vertex grandparent = v.parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }

    private void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given vertex and returns the new root.
    private Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    private class VertexPair {
        Vertex left;
        Vertex right;
        VertexPair() {
        }
        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    private VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }
        root = splay(last);
        return new VertexPair(next, root);
    }

    // Returns (< less than node>,   <node or the next node>)
    private VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
        result.right = findAndRoot.left; // result = node or the next highest node
        if (result.right == null) { // the key is larger than all values
            result.left = findAndRoot.right;
            return result;
        }
        result.right = splay(result.right);
        result.left = result.right.left;
        result.right.left = null;
        if (result.left != null) {
            result.left.parent = null;
        }
        update(result.left);
        update(result.right);
        return result;
    }

    private Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right = splay(right);
        right.left = left;
        update(right);
        return right;
    }

    // Code that uses splay tree to solve the problem

    private Vertex root = null;

    private void insert(int x) {
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        Vertex left = leftRight.left;
        Vertex right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null);
        }
        root = merge(merge(left, new_vertex), right);
    }

    private void erase(int x) {
        // Implement erase yourself
        VertexPair leftRight = split(root, x);
        Vertex left = leftRight.left;
        Vertex right = leftRight.right;
        if (right != null && right.key == x) {
            // We have the node as the root of the tree pointed by right.
            // Just remove this node and join the left and its right child
            right = right.right;
            if (right != null) {
                right.parent = null;
            }
        }
        root = merge(left, right);
    }

    private boolean find(int x) {
        // Implement find yourself
        final VertexPair findAndRoot = find(root, x);
        root = findAndRoot.right;
        final Vertex left = findAndRoot.left;

        return left != null && left.key == x;
    }

    private long sum(int from, int to) {
        VertexPair leftMiddle = split(root, from);
        Vertex left = leftMiddle.left;
        Vertex middle = leftMiddle.right;
        VertexPair middleRight = split(middle, to + 1);
        middle = middleRight.left;
        Vertex right = middleRight.right;
        // Complete the implementation of sum
        long ans = middle != null ? middle.sum : 0;
        root = merge(merge(left, middle), right);
        return ans;
    }

    long _sum(int x, int y) {
//        System.out.println("+ " + x + " " + y);
//        BTreePrinter.printNode(root);
        long res = sum((x + last_sum_result) % MODULO, (y + last_sum_result) % MODULO);
        last_sum_result = (int)(res % MODULO);
        return res;
    }

    boolean _find(int x) {
//        System.out.println("? " + x);
//        BTreePrinter.printNode(root);
        return find((x + last_sum_result) % MODULO);
    }
    
    void _erase(int x) {
//        System.out.println("- " + x);
//        BTreePrinter.printNode(root);
        erase((x + last_sum_result) % MODULO);
    }
    
    void _insert(int x) {
//        System.out.println("+ " + x);
//        BTreePrinter.printNode(root);
        insert((x + last_sum_result) % MODULO);
    }
    
    static final int MODULO = 1000000001;
    int last_sum_result = 0;

    void solve(FastScanner in, PrintStream out) throws IOException {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            char type = in.nextChar();
            switch (type) {
                case '+' : {
                    int x = in.nextInt();
                    _insert(x);
                } break;
                case '-' : {
                    int x = in.nextInt();
                    _erase(x);
                } break;
                case '?' : {
                    int x = in.nextInt();
                    out.println(_find(x) ? "Found" : "Not found");
                } break;
                case 's' : {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    long res = _sum(l, r);
                    out.println(String.valueOf(res));
                }
            }
        }
    }

    static class BTreePrinter {

        public static <T extends Comparable<?>> void printNode(Vertex root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <T extends Comparable<?>> void printNodeInternal(List<Vertex> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Vertex> newNodes = new ArrayList<Vertex>();
            for (Vertex node : nodes) {
                if (node != null) {
                    System.out.printf("%d (sum %d) (parent %s)", 
                            node.key, 
                            node.sum, 
                            node.parent == null ? "null" : String.valueOf(node.parent.key));
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static <T extends Comparable<?>> int maxLevel(Vertex node) {
            if (node == null)
                return 0;

            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }

    }
    public static void main(String[] args) throws IOException {
        SetRangeSum setRangeSum = new SetRangeSum();
        FastScanner in = new FastScanner(System.in);
        setRangeSum.solve(in, System.out);
    }

    static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st = new StringTokenizer("");
        private boolean eof = false;

        FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        public String next() {
            if (!eof) {
                while (!st.hasMoreTokens()) {
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (Exception e) {
                        eof = true;
                        return null;
                    }
                }
                return st.nextToken();
            } else {
                return null;
            }
        }
        
        public char nextChar() {
            return next().charAt(0);
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
