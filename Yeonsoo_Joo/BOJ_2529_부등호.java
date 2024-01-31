package Yeonsoo_Joo;

import java.io.*;
import java.util.*;

public class BOJ_2529_부등호 {

    static final int COUNT = 10;
    static final int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static int N;
    static int[] signs;
    static boolean[] visited;
    static Long[] output;
    static List<String> ans;

    public static void main(String[] args) throws IOException {
        // 백트래킹
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        signs = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            signs[i] = (input[i].equals("<")) ? -1 : 1;
        }
        ans = new ArrayList<>();
        visited = new boolean[COUNT];
        output = new Long[N + 1]; // (N+1)개를 뽑은 숫자
        dfs(0);

        Collections.sort(ans);
        System.out.println(ans.get(ans.size() - 1));
        System.out.println(ans.get(0));

    }

    public static void dfs(int depth) {
        if (depth > 1 && signs[depth - 2] != output[depth - 2].compareTo(output[depth - 1])) {
            // 부등호가 성립하지 않는 경우
            return;
        }
        if (depth == N + 1) {
            // (N+1)개가 뽑힌 경우
            String number = "";
            for (int i = 0; i < N + 1; i++) {
                number += output[i].toString();
            }
            ans.add(number);
            return;
        }
        for (int i = 0; i < COUNT; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = (long) nums[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }

    }
}
