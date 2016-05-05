import java.io.*;
import java.util.StringTokenizer;

public class MergingTables {

    public static void main(String[] args) {
        Table.maximumNumberOfRows = -1;
        InputReader reader = new InputReader();
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows);
        }
        PrintWriter writer = new PrintWriter(System.out);
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            tables[destination].merge(tables[source]);
            writer.print(String.valueOf(Table.maximumNumberOfRows) + "\n");
        }
        writer.flush();
    }

    public static class Table {
        static int maximumNumberOfRows = -1;

        private Table parent;
        private int rank;
        private int numberOfRows;

        public Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
            if (numberOfRows > maximumNumberOfRows) {
                maximumNumberOfRows = numberOfRows;
            }
        }

        public Table getParent() {
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
            if (realDestination.rank < realSource.rank) {
                realDestination.parent = realSource;
            } else {
                realSource.parent = realDestination;
                if (realSource.rank == realDestination.rank) {
                    realSource.rank += 1;
                }
            }
            realDestination.numberOfRows += realSource.numberOfRows;
            realSource.numberOfRows = 0;
            if (realDestination.numberOfRows > maximumNumberOfRows) {
                maximumNumberOfRows = realDestination.numberOfRows;
            }
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
