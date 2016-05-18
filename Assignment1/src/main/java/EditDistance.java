import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    //write your code here
    int[][] d = new int[s.length()+1][t.length()+1];
    for (int i = 0; i <= s.length(); i++) {
        for (int j = 0; j <= t.length(); j++) {
            if (j == 0) {
                d[i][j] = i;
            } else if (i == 0) {
                d[i][j] = j;
            } else if (s.charAt(i-1) == t.charAt(j-1)) {
                d[i][j] = d[i-1][j-1];
            } else {
                int distance = Integer.min(d[i-1][j-1] + 1, Integer.min(d[i-1][j]+1, d[i][j-1]+1));
                d[i][j] = distance;
            }
        }
    }
    return d[s.length()][t.length()];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
