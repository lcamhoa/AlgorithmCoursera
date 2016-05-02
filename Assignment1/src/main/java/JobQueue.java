import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class JobQueue {
    
    static class JobQueueWorker implements Comparable<JobQueueWorker> {
        private int index;
        private long nextFreeTime;

        public JobQueueWorker(int index, long nextFreeTime) {
            this.index = index;
            this.nextFreeTime = nextFreeTime;
        }

        public void setNextFreeTime(long nextFreeTime) {
            this.nextFreeTime = nextFreeTime;
        }

        public int getIndex() {
            return index;
        }

        public long getNextFreeTime() {
            return nextFreeTime;
        }

        @Override
        public int compareTo(JobQueueWorker t) {
            int freeTimeCompareResult = Long.compare(nextFreeTime, t.getNextFreeTime());
            if (freeTimeCompareResult == 0) {
                return Integer.compare(index, t.getIndex());
            } else {
                return freeTimeCompareResult;
            }
        }
    }

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

    static LinkedHashMap<Integer, Long> assignJobs(int numWorkers, List<Integer> jobs) {
        final LinkedHashMap<Integer, Long> output = new LinkedHashMap<>(jobs.size());
        
        final TreeSet<JobQueueWorker> heap = new TreeSet<>();
        // Add all the workers initially
        for (int j = 0; j < numWorkers; ++j) {
            heap.add(new JobQueueWorker(j, 0));
        }
        for (Integer duration : jobs) {
            JobQueueWorker bestWorker = heap.first();
            output.put(bestWorker.getIndex(), bestWorker.getNextFreeTime());
            // Update the heap
            heap.remove(bestWorker);
            bestWorker.setNextFreeTime(bestWorker.getNextFreeTime() + duration);
            heap.add(bestWorker);
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
