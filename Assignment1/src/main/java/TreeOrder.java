import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Source code copy from https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

public class TreeOrder {
    // Java program for different tree traversals
    /*
     * Class containing left and right child of current node and key value
     */
    static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    static class BinaryTree {
        // Root of Binary Tree
        Node root;

        BinaryTree() {
            root = null;
        }

        /*
         * Given a binary tree, print its nodes according to the "bottom-up" postorder
         * traversal.
         */
        void printPostorder(Node node) {
            if (node == null)
                return;

            // first recur on left subtree
            printPostorder(node.left);

            // then recur on right subtree
            printPostorder(node.right);

            // now deal with the node
            System.out.print(node.key + " ");
        }

        /* Given a binary tree, print its nodes in inorder */
        void printInorder(Node node) {
            if (node == null)
                return;

            /* first recur on left child */
            printInorder(node.left);

            /* then print the data of node */
            System.out.print(node.key + " ");

            /* now recur on right child */
            printInorder(node.right);
        }

        /* Given a binary tree, print its nodes in preorder */
        void printPreorder(Node node) {
            if (node == null)
                return;

            /* first print data of node */
            System.out.print(node.key + " ");

            /* then recur on left sutree */
            printPreorder(node.left);

            /* now recur on right subtree */
            printPreorder(node.right);
        }

        // Wrappers over above recursive functions
        void printPostorder() {
            printPostorder(root);
        }

        void printInorder() {
            printInorder(root);
        }

        void printPreorder() {
            printPreorder(root);
        }

    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    // Driver method
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(782521203);
        tree.root.left = new Node(971981286);
        tree.root.left.left = new Node(839950857);
        tree.root.left.right = new Node(248660666);
        tree.root.left.right.left = new Node(696374696);

        /*
         * Depth First Traversals:
            (a) Inorder (Left, Root, Right) : 4 2 5 1 3
            (b) Preorder (Root, Left, Right) : 1 2 4 5 3
            (c) Postorder (Left, Right, Root) : 4 5 2 3 1
         */
        tree.printInorder();
        tree.printPreorder();
        tree.printPostorder();
    }
}
