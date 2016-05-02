import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {

    public static void main(String[] args) throws IOException {
        int[] data = readData();
        List<Swap> swaps = generateSwaps(data);
        writeResponse(swaps);
    }

    private static int[] readData() throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
        return data;
    }

    private static void writeResponse(List<Swap> swaps) {
        System.out.println(swaps.size());
        for (Swap swap : swaps) {
          System.out.println(swap.index1 + " " + swap.index2);
        }
    }

    static List<Swap> generateSwaps(int[] data) {
      List<Swap> swaps = new ArrayList<>();
      for (int i = data.length/2 -1; i >= 0; i--) {
        List<Swap> siftSwaps = siftDown(data, i);
        swaps.addAll(siftSwaps);
      }
      return swaps;
    }

    private static List<Swap> siftDown(int[] data, int i) {
        int minIndex = i;
        final List<Swap> swaps = new ArrayList<>();
        final int leftChildIndex = 2*i + 1;
        if (leftChildIndex < data.length && data[leftChildIndex] < data[minIndex]) {
            minIndex = leftChildIndex;
        }
        final int rightChildIndex = 2*i + 2;
        if (rightChildIndex < data.length && data[rightChildIndex] < data[minIndex]) {
            minIndex = rightChildIndex;
        }
        if (i != minIndex) {
            int temp = data[minIndex];
            data[minIndex] = data[i];
            data[i] = temp;
            swaps.add(new Swap(i, minIndex));
            List<Swap> siftSwaps = siftDown(data, minIndex);
            swaps.addAll(siftSwaps);
        }
        return swaps;
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
        @Override
        public String toString() {
            return "(" + index1 + ", " + index2 + ")";
	}
        @Override
        public boolean equals(Object o) {
            if (o instanceof Swap) {
                Swap s = (Swap)o;
                if (s.index1 == index1 && s.index2 == index2) {
                    return true;
                }
            }
            return false;
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
