package Baekjoon;

import java.io.*;
import java.util.*;

/*
시작 시간 : 24-02-03 03:00
종료 시간 : 24-02-03 03:35
실행 시간 : 4260ms
메 모 리 : 16120KB

고려사항
DFS를 이용하여 해결하였습니다
1. 좌상단에서 탐색 시작
2. 주변 4방향과 현재까지 지나오면 만난 문자를 비교
3. 4방향 모두 갈 곳이 없다면 현재까지 지나온 칸 수와 현재까지의 최소값과 비교
4. 4방향중 진행할 수 있는 칸이 있는 경우, 해당 칸으로 dfs 실행
5. 최종 최소값을 정답으로 도출
*/

public class BOJ_1987_알파벳_G4 {

    static int R, C, ans = Integer.MIN_VALUE;
    static char[][] input;
    static List<Character> contained = new ArrayList<>();
    static int[][] delta = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        input = new char[R][C];

        for (int r = 0; r < R; r++) {
            input[r] = br.readLine().toCharArray();
        }

        contained.add(input[0][0]);
        dfs(0,0, 0);
        System.out.println(ans+1);
    }

    private static void dfs(int idxI, int idxJ, int cnt) {

        boolean noWay = true;
        for(int i = 0; i < 4; i++){
            int nextI = idxI + delta[i][0];
            int nextJ = idxJ + delta[i][1];
            if(nextI < 0 || nextJ < 0 || nextI >= R || nextJ >= C) continue;
            if(contained.contains(input[nextI][nextJ])) continue;

            noWay = false;
            contained.add(input[nextI][nextJ]);
            dfs(nextI, nextJ, cnt+1);
            contained.remove(contained.size()-1);
        }

        if(noWay){
            ans = Math.max(ans, cnt);
        }
    }
}