import java.util.*;

public class DifferentSummands {
    static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<>();
        int lowerBound = 1;
        while (n != 0) {
            if (n > 2 * lowerBound) {
                summands.add(lowerBound);
                n -= lowerBound;
                lowerBound++;
            } else {
                summands.add(n);
                n = 0;
            }
        }
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

