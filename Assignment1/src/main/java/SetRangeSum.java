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
        root = findAndRoot.right;
        result.right = findAndRoot.left; // result.newRoot = node or the next highest node
        if (result.right == null) { // the key is larger than all values
            result.left = root;
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
        final VertexPair findAndRoot = find(root, x);
        final Vertex next = findAndRoot.left;
        final Vertex newRoot = findAndRoot.right;
        if (next == null || newRoot == null || next != newRoot) {
            return; // nothing to erase
        }
        // We have the node as the root of the tree pointed by left.
        // Just remove this node and join its left and right children
        Vertex rightV = newRoot.right;
        Vertex leftV = newRoot.left;
        if (rightV != null)
            rightV.parent = null;
        if (leftV != null)
            leftV.parent = null;
        root = merge(leftV, rightV);        
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


    static final int MODULO = 1000000001;

    void solve(FastScanner in, PrintStream out) throws IOException {
        int n = in.nextInt();
        int last_sum_result = 0;
        for (int i = 0; i < n; i++) {
            char type = in.nextChar();
            switch (type) {
                case '+' : {
                    int x = in.nextInt();
                    insert((x + last_sum_result) % MODULO);
                } break;
                case '-' : {
                    int x = in.nextInt();
                    erase((x + last_sum_result) % MODULO);
                } break;
                case '?' : {
                    int x = in.nextInt();
                    out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
                } break;
                case 's' : {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
                    out.println(String.valueOf(res));
                    last_sum_result = (int)(res % MODULO);
                }
            }
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
