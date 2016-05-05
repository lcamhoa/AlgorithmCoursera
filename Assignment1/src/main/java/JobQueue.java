import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class JobQueue {
    
    static class JobQueueWorker implements Comparable<JobQueueWorker> {
        private final int index;
        private long nextFreeTime;

        public JobQueueWorker(int index, long nextFreeTime) {
            this.index = index;
            this.nextFreeTime = nextFreeTime;
        }

        @Override
        public int compareTo(JobQueueWorker t) {
            int compareResult = Long.compare(nextFreeTime, t.nextFreeTime);
            if (compareResult == 0) {
                compareResult = Integer.compare(index, t.index);
            }
            return compareResult;
        }
        
    }
    
    static class IntLongPair {

        public IntLongPair(int first, long second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 41 * hash + this.first;
            hash = 41 * hash + (int) (this.second ^ (this.second >>> 32));
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final IntLongPair other = (IntLongPair) obj;
            if (this.first != other.first) {
                return false;
            }
            if (this.second != other.second) {
                return false;
            }
            return true;
        }
        int first;
        long second;
        
    }

    private static void siftDown(ArrayList<JobQueueWorker> data, int i) {
        int minIndex = i;
        final int leftChildIndex = 2*i + 1;
        if (leftChildIndex < data.size() && data.get(leftChildIndex).compareTo(data.get(minIndex)) < 0) {
            minIndex = leftChildIndex;
        }
        final int rightChildIndex = 2*i + 2;
        if (rightChildIndex < data.size() && data.get(rightChildIndex).compareTo(data.get(minIndex)) < 0) {
            minIndex = rightChildIndex;
        }
        if (i != minIndex) {
            Collections.swap(data, i, minIndex);
            siftDown(data, minIndex);
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
        
        ArrayList<IntLongPair> output = assignJobs(numWorkers, jobs);
        
        // Print
        for (IntLongPair entry : output) {
            System.out.println(entry.first + " " + entry.second);
        }
    }

    static ArrayList<IntLongPair> assignJobs(int numWorkers, List<Integer> jobs) {
        final ArrayList<IntLongPair> output = new ArrayList<>(jobs.size());
        final ArrayList<JobQueueWorker> workersHeap = new ArrayList<>(numWorkers);
        for (int j = 0; j < numWorkers; ++j) {
            workersHeap.add(new JobQueueWorker(j, 0));
        }
        for (Integer duration : jobs) {
            JobQueueWorker bestWorker = workersHeap.get(0);
            output.add(new IntLongPair(bestWorker.index, bestWorker.nextFreeTime));
            bestWorker.nextFreeTime += duration;
            siftDown(workersHeap, 0);
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
