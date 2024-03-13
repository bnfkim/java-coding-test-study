import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504_괄호의_값 {
    static class Bracket {
        char value;
        int num;

        public Bracket(char value) {
            this.value = value;
            this.num = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Bracket> st = new Stack<>();
        int answer = 0, curScore = 0;
        for (int i = 0, size = input.length(); i < size; i++) {
            char cur = input.charAt(i);
            if (cur == '(' || cur == '[') {
                st.push(new Bracket(cur));
            } else {
                if (st.isEmpty()) {
                    answer = 0;
                    break;
                }
                Bracket curB = st.peek();
                if (cur == ')' && curB.value == '(') {
                    curScore = curB.num == 0 ? 2 : 2 * curB.num;
                    st.pop();
                    if (!st.isEmpty()) {
                        st.peek().num += curScore;
                    } else {
                        answer += curScore;
                    }
                } else if (cur == ']' && curB.value == '[') {
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
