
import java.util.Scanner;

public class PlacingParentheses {

    private final long[][] mins;
    private final long[][] maxs;
    private final char[] ops;
    private final int D;
    
    public PlacingParentheses(String exp) {
        int n = exp.length();
        D = n/2+1;
        mins = new long[D][D];
        maxs = new long[D][D];
        ops = new char[D-1];
        char[] chars = exp.toCharArray();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                mins[i/2][i/2] = chars[i] - '0';
                maxs[i/2][i/2] = chars[i] - '0';
                //System.out.printf("min(%d,%d) = %d, max(%d,%d) = %d\n", i/2, i/2, mins[i/2][i/2], i/2, i/2, maxs[i/2][i/2]);
            } else {
                ops[i/2] = chars[i];
            }
        }
    }
    
    private void calculateMinAndMax(int i, int j) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int k = i; k < j; k++) {
            long a = eval(maxs[i][k], maxs[k+1][j], ops[k]);
            long b = eval(maxs[i][k], mins[k+1][j], ops[k]);
            long c = eval(mins[i][k], maxs[k+1][j], ops[k]);
            long d = eval(mins[i][k], mins[k+1][j], ops[k]);
            min = Math.min(Math.min(Math.min(a, b), Math.min(c, d)), min);
            max = Math.max(Math.max(Math.max(a, b), Math.max(c, d)), max);
        }
        mins[i][j] = min;
        maxs[i][j] = max;
    }

    public long getMaximValue() {
        for (int s = 1; s < D; s++) {
            for (int i = 0; i < D-s; i++) {
                int j = i+s;
                calculateMinAndMax(i, j);
                //System.out.printf("min(%d,%d) = %d, max(%d,%d) = %d\n", i, j, mins[i][j], i, j, maxs[i][j]);
            }
        }
        return maxs[0][D-1];
    }

    private static long eval(long a, long b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                assert false;
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        PlacingParentheses pp = new PlacingParentheses(exp);
        System.out.println(pp.getMaximValue());
    }
}
