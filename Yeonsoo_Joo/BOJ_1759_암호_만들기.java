package Yeonsoo_Joo;

import java.io.*;
import java.util.*;

public class BOJ_1759_암호_만들기 {
    static final String cons = "aeiou";
    static int L; // 서로 다른 L개 선택
    static int C; // 문자 개수
    static char[] arr;
    static boolean[] visited;
    static char[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        visited = new boolean[C];
        ans = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        dfs(0, 0);
    }

    public static void dfs(int index, int count) {
        if (count == L) {
            if(check(ans)) {
                System.out.println(String.valueOf(ans));
            }
            return;
        }

        for (int i = index; i < C; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            ans[count] = arr[i];
            dfs(i+1, count+1);
            visited[i] = false;
        }

    }

    public static boolean check(char[] s) {
        int vowelCount = 0;
        int consCount = 0;
        for (char c : s) {
            if (cons.indexOf(c) == -1) {
                vowelCount++;
            } else {
                consCount++;
            }
        }
        if (vowelCount < 2 || consCount < 1) {
            return false;
        }
        return true;
    }
}
