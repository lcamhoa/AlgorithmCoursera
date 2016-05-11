
import java.io.*;
import java.util.*;

class RopeProblem {

    private static class FastScanner {

        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
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
    /** Class RopeNode **/
    static class RopeNode
    {
        RopeNode left, right;
        String data;
        int weight;    
        /** Constructor **/
        public RopeNode(String data)
        {
            this.data = data;
            left = null;
            right = null;
            weight = data.length();
        }
        /** Constructor **/
        public RopeNode()
        {
            data = null;
            left = null;
            right = null;
            weight = 0;
        }
        public static RopeNode concat(RopeNode root, RopeNode nptr)
        {
            RopeNode newRoot = new RopeNode();
            newRoot.left = root;
            newRoot.right = nptr;
            if (root != null)
                newRoot.weight += root.weight ;
            if (nptr != null)
                newRoot.weight += nptr.weight;
            return newRoot;
        }
        // substring with inclusive start and exclusive end 
        public static RopeNode substring(RopeNode root, int start, int end)
        {
            if (root.left == null) {
                // leaf node
                return new RopeNode(root.data.substring(start, end));
            } else if (end <= root.left.weight) {
                // get the data from the left subtree
                return substring(root.left, start, end);
            } else if (start >= root.left.weight) {
                // get the data from the right subtree
                return substring(root.right, start - root.left.weight, end - root.left.weight);
            } else {
                // concatenate data from the two subtrees
                RopeNode newRoot = new RopeNode();
                newRoot.left = substring(root.left, start, root.left.weight);
                newRoot.right = substring(root.right, 0, end - root.left.weight);
                newRoot.weight = end - start;
                return newRoot;
            }
        }

        static String convertToString(RopeNode root)
        {
            if (root.left == null) {
                return root.data;
            } else {
                return convertToString(root.left) + convertToString(root.right);
            }
        }    

        static RopeNode process(RopeNode root, int i, int j, int k) {
            // Replace this code with a faster implementation
            //String t = s.substring(0, i) + s.substring(j + 1);
            //s = t.substring(0, k) + s.substring(i, j + 1) + t.substring(k);
            RopeNode s1 = substring(root, 0, i);
            RopeNode s2 = substring(root, j+1, root.weight);
            RopeNode s3 = substring(root, i, j+1);
            RopeNode truncated = new RopeNode();
            truncated.left = s1;
            truncated.right = s2;
            truncated.weight = s1.weight + s2.weight;
            RopeNode t1 = substring(truncated, 0, k);
            RopeNode t2 = substring(truncated, k, truncated.weight);
            RopeNode intermediate = concat(t1, s3);
            RopeNode result = concat(intermediate, t2);
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        new RopeProblem().run();
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        RopeNode root = new RopeNode(in.next());
        for (int q = in.nextInt(); q > 0; q--) {
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();
            root = RopeNode.process(root, i, j, k);
        }
        out.println(RopeNode.convertToString(root));
        out.close();
    }
}
