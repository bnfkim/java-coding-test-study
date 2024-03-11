import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504_괄호의_값 {
    static class Bracket {
        char b;
        int num;

        public Bracket(char b) {
            this.b = b;
            this.num = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Bracket> st = new Stack<>();
        int answer = 0, curScore = 0;
        for (int i = 0, size = input.length(); i < size; i++) {
            char b = input.charAt(i);
            if (b == '(' || b == '[') {
                st.push(new Bracket(b));
            } else {
                if (st.isEmpty()) {
                    answer = 0;
                    break;
                }
                Bracket curB = st.peek();
                if (b == ')' && curB.b == '(') {
                    curScore = curB.num == 0 ? 2 : 2 * curB.num;
                    st.pop();
                    if (!st.isEmpty()) {
                        st.peek().num += curScore;
                    } else {
                        answer += curScore;
                    }
                } else if (b == ']' && curB.b == '[') {
                    curScore = curB.num == 0 ? 3 : 3 * curB.num;
                    st.pop();
                    if (!st.isEmpty()) {
                        st.peek().num += curScore;
                    } else {
                        answer += curScore;
                    }
                }
            }
        }
        if (!st.isEmpty()) {
            answer = 0;
        }
        System.out.println(answer);
    }
}
