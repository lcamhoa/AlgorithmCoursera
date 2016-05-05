import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class HashSubstring {

    static final private int prime = 1000000007;
    
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        String pattern = in.next();
        String text = in.next();
        List<Integer> ans = getOccurrences(pattern, text);
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            for (Integer cur : ans) {
                out.print(cur);
                out.print(" ");
            }
        }
    }

    private static int hashFunc(String s, int multiplier) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash;
    }

    private static List<Integer> getOccurrences(String p, String t) {
        // Generate random multiplier between 1 and prime-1
        int multiplier = new Random().nextInt(prime-1) + 1;
        int m = p.length();
        int n = t.length();
        List<Integer> occurrences = new ArrayList<>();
        int pHash = hashFunc(p, multiplier);
        int[] h = precomputeHashes(t, m, multiplier);
        for (int i = 0; i + m <= n; ++i) {
            if (pHash != h[i])
                continue;
            if (t.substring(i, i+m).equals(p))
                occurrences.add(i);
	}
        return occurrences;
    }

    private static int[] precomputeHashes(String t, int m, int multiplier) {
        int n = t.length();
        int[] h = new int[n - m  + 1];
        String last = t.substring(n - m);
        h[n - m] = hashFunc(last, multiplier);
        int y = 1;
        for (int i = 1; i <= m; ++i) {
            y = (y * multiplier) % prime;
        }
        for (int i = n - m - 1; i >= 0; --i) {
            h[i] = (multiplier * h[i + 1] + t.charAt(i) - y * t.charAt(i + m)) % prime;
        }
        return h;
    }
    
    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

