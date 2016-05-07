import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    static final private int prime = 1000000007;
    static final private int multiplier = 256;
    
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

    static List<Integer> getOccurrences(String p, String t) {
        final int m = p.length();
        final int n = t.length();
        int pHash = 0;
        int rollingHash = 0;
        for (int i = 0; i < m; ++i) {
        	pHash = (pHash * multiplier + p.charAt(i)) % prime;
        	rollingHash = (rollingHash * multiplier + t.charAt(i)) % prime;
        }
        // Calculate d = (multiplier^(m-1) % prime)
        int poweredOffset = 1;
        for (int i = 1; i < m; i++) {
        	poweredOffset = (poweredOffset * multiplier) % prime;
        }
        	
        final List<Integer> occurrences = new ArrayList<>();
        for (int i = 0; i <= n-m; ++i) {
            if (pHash == rollingHash) {
            	if (t.substring(i, i+m).equals(p)) {
            		occurrences.add(i);
            	}
            }
            // Calculate the hash of the next substring
            if (i < n-m) {
            	rollingHash = (rollingHash + prime - poweredOffset * t.charAt(i) % prime) % prime;
            	rollingHash = (multiplier * rollingHash + t.charAt(i+m)) % prime;
            }
        }
        return occurrences;
    }
    
    private static class FastScanner {
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
    }
}

