import java.util.*;

public class FibonacciLastDigit {

    static int getFibonacciLastDigit(int n) {
    if (n <= 1)
      return n;
        int[] fibs = new int[n+1];
        fibs[0] = 0;
        fibs[1] = 1;
        for (int i = 2; i <=n; i++) {
            fibs[i] = (fibs[i-1] + fibs[i-2]) % 10;
        }
        return fibs[n];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

