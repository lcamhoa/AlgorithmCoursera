import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Buffer {
    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new ArrayDeque<>(size);
    }

    public Response Process(Request request) {
        // write your code here
        // Be the processor and pop off the packages that 
        // already have been processed
        while (finish_time_.peek() != null && finish_time_.peek() <= request.arrival_time) {
            finish_time_.poll();
        }
        if (finish_time_.isEmpty()) {
            if (request.process_time != 0) {
                finish_time_.offer(request.arrival_time + request.process_time);
            }
            return new Response(false, request.arrival_time);
        } else {
            if (finish_time_.size() < size_) {
                // Queue up the packet. But, first find out the finish time 
                // based on the last element queued up
                Integer last_elem = finish_time_.peekLast();
                finish_time_.offer(last_elem + request.process_time);
                return new Response(false, last_elem);
            } else {
                // Drop the packet
                return new Response(true, -1);
            }
        }
    }

    private final int size_;
    private final Deque<Integer> finish_time_;
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses, PrintStream outputStream) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                outputStream.println(-1);
            } else {
                outputStream.println(response.start_time);
            }
        }
    }

    static void simulatePackageFlow(final InputStream inputStream, PrintStream outputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream);
        
        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses, outputStream);
    }

    public static void main(String[] args) throws IOException {
        simulatePackageFlow(System.in, System.out);
    }
}
