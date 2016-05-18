import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] weights) {
        int[][] value = new int[W+1][weights.length+1];
        for (int w = 0; w <= W; w++) {
            value[w][0] = 0;
        }
        for (int wi = 0; wi <= weights.length; wi++) {
            value[0][wi] = 0;
        }
        //write you code here
        for (int w = 1; w <= W; w++) {
            for (int wi = 1; wi <= weights.length; wi++) {
                value[w][wi] = value[w][wi-1]; // don't take this weight
                if (weights[wi-1] <= w) {
                    int v = value[w - weights[wi-1]][wi-1] + weights[wi-1];
                    if (v > value[w][wi]) {
                        value[w][wi] = v;
                    }
                }
            }
        }
        return value[W][weights.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

