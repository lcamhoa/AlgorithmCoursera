import java.util.*;

public class CoveringSegments {

    static int[] optimalPoints(Segment[] segments) {
        int[] points = new int[2 * segments.length];
        int count = 0;
        Arrays.sort(segments, (Segment s1, Segment s2) -> Integer.compare(s1.start, s2.start));
        Segment currentIntersection = null;
        if (segments.length > 0) {
            currentIntersection = new Segment(segments[0].start, segments[0].end);
        }
        for (int i = 1; i < segments.length; i++) {
            if (currentIntersection.start <= segments[i].end && segments[i].start <= currentIntersection.end) {
                // Update the continuing intersection
                currentIntersection.start = Math.max(currentIntersection.start, segments[i].start);
                currentIntersection.end = Math.min(currentIntersection.end, segments[i].end);
            } else {
                // we need to select a point from the older intersection and start a new intersection
                points[count++] = currentIntersection.start;
                currentIntersection = new Segment(segments[i].start, segments[i].end);
            }
        }
        if ((count == 0 && currentIntersection != null) || points[count-1] != currentIntersection.start) {
            // process the last intersection
            points[count++] = currentIntersection.start;
        }
        return Arrays.copyOf(points, count);
    }

    static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
