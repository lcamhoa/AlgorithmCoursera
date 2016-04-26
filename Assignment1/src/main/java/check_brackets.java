import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        String result = check_for_unbalanced_brackets(text);
        // Printing answer, write your code here
        System.out.println(result); 
    }

    static String check_for_unbalanced_brackets(String text) {
        final Deque<Bracket> opening_brackets_stack = new ArrayDeque<>();
        for (int position = 0; position < text.length(); ++position) {
            final char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                final Bracket br = new Bracket(next, position);
                opening_brackets_stack.push(br);
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                if (opening_brackets_stack.isEmpty()) {
                    return String.valueOf(position+1);
                }
                final Bracket br = opening_brackets_stack.pop();
                if (!br.Match(next)) {
                    return String.valueOf(position+1);
                }
            }
        }
        Bracket br = null;
        while (!opening_brackets_stack.isEmpty()) {
            br = opening_brackets_stack.pop();
        }
        return br != null ? String.valueOf(br.position+1) : "Success";
    }
}
