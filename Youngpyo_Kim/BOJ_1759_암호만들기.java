import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {
    static int l, c;
    static char[] arr = new char[16];
    static boolean[] visited = new boolean[16];

public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine());
    l = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());

    String[] characters = in.readLine().split(" ");
    for (int i = 0; i < c; i++) {
        arr[i] = characters[i].charAt(0);
    }

    Arrays.sort(arr, 0, c);
    backtrack(0, 0);
}

static void backtrack(int idx, int cnt) {
    if (cnt == l) {
        String res = "";
        int vowel = 0, consonant = 0;

        for (int i = 0; i < c; i++) {
            if (visited[i]) {
                res += arr[i];
                if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                    vowel++;
                } else {
                    consonant++;
                }
            }
        }

        if (vowel > 0 && consonant > 1) {
            System.out.println(res);
        }

        return;
    }

    for (int i = idx; i < c; i++) {
        if (!visited[i]) {
            visited[i] = true;
            backtrack(i + 1, cnt + 1);
            visited[i] = false;
        }
    }
}
}