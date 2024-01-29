import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_4889_안정적인_문자열 {
    static int N, openNum, closeNum;
    static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 1;
        while(!(input = br.readLine()).contains("-")) {
            String[] data = input.split("");
            System.out.printf("%d. %d%n", N++, getFixNum(data));
            openNum = 0;
            closeNum = 0;
        }
    }

    private static int getFixNum(String[] data) {
        Stack st = new Stack<>();

        for (String  s : data) {
            if(s.equals("{")) {
                st.push(s);
            }
            if (s.equals("}")) {
                if (!st.empty() && st.peek().equals("{")) {
                    st.pop();
                } else {
                    st.push(s);
                }
            }
        }
        int size = st.size();
        for (int i=0; i<size; i++) {
            if (st.pop().equals("{")) {
                openNum++;
            } else {
                closeNum++;
            }
        }
        return openNum/2 + openNum%2 + closeNum/2 + closeNum%2;
    }
}
