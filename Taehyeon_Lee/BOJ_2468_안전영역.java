package Baekjoon;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 안전영역 2468 S1
// 24-01-19 19:30 시작
// 24-01-19 20:20 끝
// 288ms

// 고려사항
// 가장 낮은 높이에서 가장 높은 지대의 범위 내에서 비가 내리는 경우 탐색
// BFS이용, 잠기지 않은 영역 체크
public class Solution2468 {
    static int n, rain, minH, maxH, maxSafeZone = 1, sumSafeZone;
    static int[][] mat;
    static boolean[][] visited;
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Init
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        mat = new int[n][n];
        visited = new boolean[n][n];
        minH = 100; maxH = 0;

        // 데이터 입력
        // 최소 및 최대값 기록
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int h = Integer.parseInt(st.nextToken());
                mat[i][j] = h;
                minH = Math.min(minH, h);
                maxH = Math.max(maxH, h);
            }
        }

        // 비의 양이 최소에서 최대로 증가하며, safezone의 최대값 탐색
        for(rain = minH; rain <= maxH; rain++){
            visited = new boolean[n][n];
            sumSafeZone = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(visited[i][j]) continue;

                    if(mat[i][j] <= rain) {
                        visited[i][j] = true;
                        continue;
                    }

                    sumSafeZone++;
                    bfs(i,j);
                }
            }

            maxSafeZone = Math.max(maxSafeZone, sumSafeZone);
        }
        System.out.println(maxSafeZone);

    }

    private static void bfs(int idxI, int idxJ) {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(idxI, idxJ));
        visited[idxI][idxJ] = true;

        while (!q.isEmpty()){
            Point p = q.poll();
            int curI = p.x, curJ = p.y;

            for(int d = 0; d < 4; d++){
                int nextI = curI + delta[d][0], nextJ = curJ + delta[d][1];

                // 맵 밖인 경우 skip
                if(nextI < 0 || nextJ < 0 || nextI >= n || nextJ >= n) continue;

                // 이미 방문한 지역인 경우 skip
                if(visited[nextI][nextJ]) continue;

                // 잠기지 않은 지역인 경우 다음 탐색에 추가
                if(mat[nextI][nextJ] > rain) {
                    q.add(new Point(nextI, nextJ));
                }

                visited[nextI][nextJ] = true;
            }

        }
    }

}