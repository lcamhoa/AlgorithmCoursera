import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    // for hash function
    private final int bucketCount;
    private final List<List<String>> elems;
    
    static final private int prime = 1000000007;
    static final private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int bucketCount = in.nextInt();
        int queryCount = in.nextInt();
        HashChains hashChains = new HashChains(bucketCount);
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            for (int i = 0; i < queryCount; ++i) {
                hashChains.processQuery(hashChains.readQuery(in), out);
            }
        }
    }

    private HashChains(int bucketCount) {
        this.bucketCount = bucketCount;
        this.elems = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            elems.add(null);
        }
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery(FastScanner in) throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void processQuery(Query query, PrintWriter out) {
        switch (query.type) {
            case "add": 
            {
                int hash = hashFunc(query.s);
                List<String> chain = elems.get(hash);
                if (chain == null) {
                    chain = new ArrayList<>();
                    chain.add(query.s);
                    elems.set(hash, chain);
                } else if (!chain.contains(query.s)) {
                    chain.add(0, query.s);
                }
            }
                break;
            case "del":
            {
                int hash = hashFunc(query.s);
                List<String> chain = elems.get(hash);
                if (chain != null) {
                    chain.remove(query.s);
                }
            }
                break;
            case "find":
            {
                int hash = hashFunc(query.s);
                List<String> chain = elems.get(hash);
                out.println(chain != null && chain.contains(query.s) ? "yes" : "no");
            }
                break;
            case "check":
            {
                List<String> chain = elems.get(query.ind);
                if (chain != null) {
                    for (String cur : chain) {
                        out.print(cur + " ");
                    }
                }
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
            }
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
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
