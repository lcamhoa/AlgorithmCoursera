import java.util.Scanner;

public class FractionalKnapsack {
    static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        final int n = values.length;
        for (int i = 0; i < n && capacity != 0; i++) {
            int bestOne = findBest(values, weights);
            if (bestOne == -1) { // no solution found
                return -1;
            }
            int weightOfItem = Math.min(weights[bestOne], capacity);
            value += weightOfItem * ((double)values[bestOne]/weights[bestOne]);
            capacity -= weightOfItem;
            weights[bestOne] -= weightOfItem;
        }

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    private static int findBest(int[] values, int[] weights) {
        int bestOne = -1;
        double bestValueForWeight = -1;
        final int n = values.length;
        for (int i = 0; i < n; i++) {
            if (weights[i] > 0 && ((double)values[i] / weights[i]) > bestValueForWeight) {
                bestOne = i;
                bestValueForWeight = ((double)values[i] / weights[i]);
            }
        }
        return bestOne;
    }
} 
