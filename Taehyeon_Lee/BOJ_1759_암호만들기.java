package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 암호만들기 1759 G5
시작 시간 : 24-01-25 08:35
종료 시간 : 24-01-27 20:00
실행시간 : 88ms / 실패

고려사항
DFS를 통해 가능한 모든 조합의 문자열 생성
문자열의 길이가 L이 되면, 해당 문자열이
모음 1개이상과 자음 2개 이상으로 이루어져있는치 체크
true인 경우, 출력
*/

public class Solution1759 {

    static int L, C;
    static char[] data;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder(100);
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        data = new char[C];
        visited = new boolean[C];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {
            data[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(data);
        dfs(0, 0);

        if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);


    }

    private static void dfs(int start, int len) {
        // 문자열이 완성된 경우
        if(len == L){
            String word = "";
            int mo = 0;
            int ja = 0;

            for (int i = 0; i < C; i++) {
                if(visited[i]){
                    word += data[i];
                    if(data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u'){
                        mo++;
                    }else{
                        ja++;
                    }
                }
            }

            // 문자열이 조건을 만족하는 경우
            if(mo >= 1 && ja >= 2){
                sb.append(word).append("\n");
            }
        }

        for (int i = start; i < C; i++) {
            visited[i] = true; // 현재 char는 추가한 것으로 체크
            dfs(i+1, len+1);
            visited[i] = false;
        }
    }
}