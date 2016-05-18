import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }
        //write your code here
        int middle = (left+right)/2;
        int majorityLeft = getMajorityElement(a, left, middle);
        int majorityRight = getMajorityElement(a, middle, right);
        if (majorityLeft == majorityRight) {
            return majorityLeft;
        } else {
            // Count which of the majority elements of the halves is a majority
            // element
            int majorityThreshhold = (right - left)/2;
            if (majorityLeft > -1) {
                int count = countOccurences(left, right, a, majorityLeft);
                if (count > majorityThreshhold) {
                    return majorityLeft;
                }
            }
            if (majorityRight > -1) {
                int count = countOccurences(left, right, a, majorityRight);
                if (count > majorityThreshhold) {
                    return majorityRight;
                }
            }
        }
        return -1;
    }

    static int countOccurences(int left, int right, int[] a, int elem) {
        int count = 0;
        for (int i = left; i < right; i++) {
            if (a[i] == elem) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
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
}

