import java.util.*;

public class LCM {
  static int gcd(int a, int b) {
      if (b == 0) {
          return a;
      }
      int a_dash = a % b;
      return gcd(b, a_dash);
  }

  static long lcm(int a, int b) {
    //write your code here
    int gcd = gcd(a, b);
    return (a/gcd) * (long)b;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm(a, b));
  }
}
