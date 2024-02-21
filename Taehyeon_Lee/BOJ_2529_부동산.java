package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
백준 부등호 2529 S1
시작 시간 : 24-01-24 19:15
종료 시간 : 24-01-24 21:40
실행시간 : 152ms

고려사항
백트래킹, DFS이용
왼쪽부터 DFS 탐색으로 부등호에 따른 값비교
*/

public class Solution2529 {


    static int N;
    static char[] data;
    static boolean[] check = new boolean[10];
    static StringTokenizer st;
    static List<String> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        data = new char[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            data[i] = st.nextToken().charAt(0);
        }

        dfs("", 0);
        Collections.sort(ans);

        System.out.println(ans.get(ans.size() - 1));
        System.out.println(ans.get(0));
        br.close();
}

    private static void dfs(String num, int idx) {

        if(idx == N+1){
            ans.add(num);
            return;
        }

        for(int i =0; i <= 9; i++){
            if(check[i]) continue;

            if(idx == 0 || checkCorrect(Character.getNumericValue(num.charAt(num.length()-1)), i, data[idx - 1])){
                check[i] = true;
                dfs(num+i, idx+1);
                check[i] = false;
            }
        }
    }

    private static boolean checkCorrect(int a, int b, char datum) {

        if(datum == '>'){
            return a > b;
        }else{
            return a < b;
        }
    }
}