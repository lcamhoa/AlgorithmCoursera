import java.util.*;

public class DotProduct {
    static long minDotProduct(int[] a, int[] b) {
        long result = 0;
        Arrays.sort(a);
        Arrays.sort(b);
        // Reverse b so that b now is reverse sorted
        for (int i = 0; i < b.length/2; i++) {
            int temp = b[b.length-1-i];
            b[b.length-1-i] = b[i];
            b[i] = temp;
        }
        for (int i = 0; i < a.length; i++) {
            result += (long)a[i] * b[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(minDotProduct(a, b));
    }
}

