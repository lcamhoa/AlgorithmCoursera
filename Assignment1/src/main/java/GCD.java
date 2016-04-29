import java.util.*;

public class GCD {
  static int gcd(int a, int b) {
      if (b == 0) {
          return a;
      }
      int a_dash = a % b;
      return gcd(b, a_dash);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcd(a, b));
  }
}
