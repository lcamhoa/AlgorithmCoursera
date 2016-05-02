import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class JobQueue {

    public static void main(String[] args) throws IOException {
        // Get input
        final FastScanner in = new FastScanner();
        int numWorkers = in.nextInt();
        int m = in.nextInt();
        List<Integer> jobs = new ArrayList<>(m);
        for (int i = 0; i < m; ++i) {
            jobs.add(in.nextInt());
        }
        
        LinkedHashMap<Integer, Long> output = assignJobs(numWorkers, jobs);
        
        // Print
        for (Map.Entry<Integer, Long> entry : output.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static void siftDown(ArrayList<Integer> data, int i) {
        int minIndex = i;
        final int leftChildIndex = 2*i + 1;
        if (leftChildIndex < data.size() && data.get(leftChildIndex) < data.get(minIndex)) {
            minIndex = leftChildIndex;
        }
        final int rightChildIndex = 2*i + 2;
        if (rightChildIndex < data.size() && data.get(rightChildIndex) < data.get(minIndex)) {
            minIndex = rightChildIndex;
        }
        if (i != minIndex) {
            Collections.swap(data, i, minIndex);
            siftDown(data, minIndex);
        }
    }

    private static void siftUp(ArrayList<Integer> data, int i) {
        while (i > 0 && data.get((i-1)/2) < data.get(i)) {
            Collections.swap(data, i, (i-1)/2);
            i = (i-1)/2;
        }
    }
    
//    private static int extractMin(ArrayList<Integer> data) {
//        int max = data.get(0);
//        Collections.swap(data, 0, data.size()-1);
//        data.remove(data.size()-1);
//        siftDown(data, 0);
//        return max;
//    }

    private static void changePriority(ArrayList<Integer> data, int i, int newPriority) {
        int oldPriority = data.get(i);
        data.set(i, newPriority);
        if (oldPriority < newPriority) {
            siftUp(data, i);
        } else {
            siftDown(data, i);
        }
    }
    
    static LinkedHashMap<Integer, Long> assignJobs(int numWorkers, List<Integer> jobs) {
        final LinkedHashMap<Integer, Long> output = new LinkedHashMap<>(jobs.size());
        final long[] nextFreeTime = new long[numWorkers];
        for (Integer duration : jobs) {
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            output.put(bestWorker, nextFreeTime[bestWorker]);
            nextFreeTime[bestWorker] += duration;
        }
        return output;
    }

    private static class FastScanner {
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
