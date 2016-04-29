import java.util.Scanner;

public class Fib {
  static long calc_fib(int n) {
    if (n <= 1)
      return n;
    long[] fibs = new long[n+1];
    fibs[0] = 0L;
    fibs[1] = 1L;
    for (int i = 2; i <=n; i++) {
        fibs[i] = fibs[i-1] + fibs[i-2];
    }
    return fibs[n];
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
