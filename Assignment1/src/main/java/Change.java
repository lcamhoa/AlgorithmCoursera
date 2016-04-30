import java.util.Scanner;

public class Change {
    private static int[] coins = {10, 5, 1};
    
    static int getChange(int n) {
        int count = 0;
        for (int i = 0; n > 0 && i < 3; i++) {
            count += n / coins[i];
            n = n % coins[i];
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getChange(n));

    }
}

