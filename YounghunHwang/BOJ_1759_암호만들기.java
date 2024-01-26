import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static char[] letters;
    static StringBuilder sb = new StringBuilder();
    static Set<Character> mo = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        letters = new char[C];
        for (int i = 0; i < C; i++) {
            letters[i] = (char) br.read();
            br.read();
        }

        Arrays.sort(letters);

        DFS(0);
    }

    private static void DFS(int depth) {
        if (sb.length() == L) {
            if (isAnswer()) {
                System.out.println(sb);
            }
            return;
        }

        if (depth >= C) {
            return;
        }

        sb.append(letters[depth]);
        DFS(depth + 1);

        sb.deleteCharAt(sb.length() - 1);
        DFS(depth + 1);
    }

    private static boolean isAnswer() {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (mo.contains(sb.charAt(i))) {
                count1++;
            } else {
                count2++;
            }
        }

        return count1 >= 1 && count2 >= 2;
    }
}