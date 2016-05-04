import java.io.*;
import java.util.StringTokenizer;

public class MergingTables {

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        int maximumNumberOfRows = -1;
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            tables[destination].merge(tables[source]);
            int numberOfRows = tables[destination].getParent().getNumberOfRows();
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
            System.out.printf("%d\n", maximumNumberOfRows);
        }
    }

    static class Table {
        private Table parent;
        private int rank;
        private int numberOfRows;

        public int getNumberOfRows() {
            return numberOfRows;
        }

        Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
        }

        Table getParent() {
            // find super parent and compress path
            if (parent != this) {
                parent = parent.getParent();
            }
            return parent;
        }

        public void merge(Table source) {
            Table realDestination = getParent();
            Table realSource = source.getParent();
            if (realDestination == realSource) {
                return;
            }
            // merge two components here
            // use rank heuristic
            // update maximumNumberOfRows
            if (realDestination.rank > realSource.rank) {
                realSource.parent = realDestination;
            } else {
                realDestination.parent = realSource;
                if (realSource.rank == realDestination.rank) {
                    realSource.rank += 1;
                }
            }
            realDestination.numberOfRows += realSource.numberOfRows;
            realSource.numberOfRows = 0;
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
