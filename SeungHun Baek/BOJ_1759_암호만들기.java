import java.util.*;
import java.io.*;

public class BOJ_1759_암호만들기 {

    static int L, C;
    static String[] arr;
    static String[] vowels = {"a","e","i","o","u"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = br.readLine().split(" ");
        Arrays.sort(arr);

        dfs(0, 0, 0, new StringBuilder());
    }

    private static void dfs(int next, int depth, int vowel, StringBuilder sb) {
        if (depth == L) {
            if (vowel < 1 || vowel > L - 2) return;
            System.out.println(sb.toString());
            return;
        }

        for (int i = next; i < C; i++) {
            String alpha = arr[i];
            sb.append(alpha);
            dfs(i+1, depth+1, vowel+checkVowels(alpha),sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private static int checkVowels(String v) {
        for (String s : vowels) {
            if (v.equals(s)) return 1;
        }
        return 0;
    }
}
