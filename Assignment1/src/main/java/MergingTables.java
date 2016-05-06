import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MergingTables {

    private long maximumNumberOfRows = -1;
    private Table[] tables;

    void createTables(int[] numberOfRows) {
        int n = numberOfRows.length;
        tables = new Table[n];
        for (int i = 0; i < n; i++) {
            tables[i] = new Table(numberOfRows[i]);
            if (numberOfRows[i] > maximumNumberOfRows) {
                maximumNumberOfRows = numberOfRows[i];
            }
        }
    }

    long[] doMerges(int[] merges) {
        int m = merges.length;
        long[] maxSizes = new long[m/2];
        for (int i = 0; i < m; i += 2) {
            long destinationSize = Table.merge(tables[merges[i] - 1], tables[merges[i + 1] - 1]);
            if (destinationSize > maximumNumberOfRows)
                maximumNumberOfRows = destinationSize;
            maxSizes[i/2] = maximumNumberOfRows;
        }
        return maxSizes;
    }


    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int n = reader.nextInt();
        int m = reader.nextInt();
        int[] numberOfRows = new int[n];
        for (int i = 0; i < n; i++) {
            numberOfRows[i] = reader.nextInt();
        }
        int[] merges = new int[m*2];
        PrintWriter writer = new PrintWriter(System.out);
        for (int i = 0; i < m; i++) {
            merges[i*2] = reader.nextInt();
            merges[i*2+1] = reader.nextInt();
        }
        MergingTables mergingTables = new MergingTables();
        mergingTables.createTables(numberOfRows);
        long[] maxSizes = mergingTables.doMerges(merges);
        for (long maxSize : maxSizes) {
            writer.print(String.valueOf(maxSize) + "\n");
        }
        writer.flush();
    }

    private static class Table {
        private Table parent;
        private int rank;
        private long numberOfRows;

        private Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
        }

        public Table getParent() {
            // find super parent and compress path
            if (parent != this) {
                parent = parent.getParent();
            }
            return parent;
        }

        static long merge(Table destination, Table source) {
            Table realDestination = destination.getParent();
            Table realSource = source.getParent();
            if (realDestination == realSource) {
                return 0;
            }
            // merge two components here
            // use rank heuristic
            // update maximumNumberOfRows
            if (realDestination.rank < realSource.rank) {
                realDestination.parent = realSource;
            } else {
                realSource.parent = realDestination;
                if (realSource.rank == realDestination.rank) {
                    realSource.rank += 1;
                }
            }
            realDestination.numberOfRows += realSource.numberOfRows;
            realSource.numberOfRows = -1;
            return realDestination.numberOfRows;
        }

    }

    private static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader() {
            reader = new BufferedReader(new InputStreamReader(System.in), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

}
