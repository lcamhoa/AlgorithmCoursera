import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        int[] optimalCount = new int[n+1]; // skip the 0th element
        int[] prevElement = new int[n+1];
        optimalCount[1] = 0;
        for (int i = 2; i <= n; i++) {
            optimalCount[i] = Integer.MAX_VALUE;
            if (optimalCount[i-1] + 1 < optimalCount[i]) {
                optimalCount[i] = optimalCount[i-1] + 1;
                prevElement[i] = i-1;
            }
            if(i % 2 == 0 && optimalCount[i/2] + 1 < optimalCount[i]) {
                optimalCount[i] = optimalCount[i/2] + 1;
                prevElement[i] = i/2;
            }
            if(i % 3 == 0 && optimalCount[i/3] + 1 < optimalCount[i]) {
                optimalCount[i] = optimalCount[i/3] + 1;
                prevElement[i] = i/3;
            }
        }
        List<Integer> sequence = new LinkedList<>();
        int i = n;
        sequence.add(0, n);
        while (i != 1) {
            sequence.add(0, prevElement[i]);
            i = prevElement[i];
        }
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

