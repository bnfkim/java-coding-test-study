package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
백준 아기상어 16236 G3
시작 시간 : 24-01-22 21:00
종료 시간 : 24-01-22 22:20
실행시간 : 368ms

고려사항
BFS를 이용해서 현재 상어의 위치에서 먹을 수 있는 물고기 위치 탐색
탐색한 물고기들을 기준에 맞게 sort
가장 우선순위에 해당하는 물고기 위치로 이동 및 물고기 크기 증가, 새로운 상어 위치 업데이트
먹을 수 있는 물고기가 없는 경우 while문 중지
이동한 거리를 엄마 상어를 부르지 않는 시간과 동일시하여 출력
*/

public class Solution16236 {

    static class Pos{
        int i, j, route;

        public Pos(int i, int j, int route) {
            this.i = i;
            this.j = j;
            this.route = route;
        }

        @Override
        public boolean equals(Object obj) {
            if(getClass() == obj.getClass()){
                Pos p = (Pos) obj;
                return this.i == p.i && this.j == p.j;
            }
            return false;
        }


    }

    static int N, sharkSize = 2, sharkHealth, result, sharkI, sharkJ;
    static int[][] data;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

    static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        data = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
                if(data[i][j] == 9){
                    sharkI = i;
                    sharkJ = j;
                }
            }
        }

        while(findRoute(sharkI,sharkJ)){
            visited = new boolean[N][N];
        }

        System.out.println(result);
    }

    public static boolean findRoute(int idxI, int idxJ){
        data[idxI][idxJ] = 0;

        ArrayList<Pos> eatableList = new ArrayList<>();
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(idxI, idxJ, 0));

        while(!q.isEmpty()){
            Pos curPos = q.poll();
            int curI = curPos.i, curJ = curPos.j, curRoute = curPos.route;

            // 먹을 수 있는 먹이에 도착
            if(data[curI][curJ] > 0 && data[curI][curJ] < sharkSize)
                eatableList.add(curPos);

            // 방문 체크
            visited[curI][curJ] = true;

            for (int i = 0; i < 4; i++) {
                int nextI = curI + di[i], nextJ = curJ + dj[i];
                if(nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue; // 맵 밖
                if(visited[nextI][nextJ]) continue; // 이미 방문한 곳

                // 먹지 못하는 상어 만남, 방문 체크
                if(data[nextI][nextJ] > sharkSize) {
                    visited[nextI][nextJ] = true;
                    continue;
                }

                Pos nextPos = new Pos(nextI, nextJ, curRoute+1);
                if(!q.contains(nextPos)){ // 이미 큐에 들어있으면 스킵
                    q.add(nextPos);
                }
            }
        }

        // 더이상 먹을 물고기 없음
        if(eatableList.isEmpty()) return false;

        eatableList.sort(((o1, o2) -> {
            if (o1.route == o2.route) {
                if (o1.i == o2.i) {
                    return o1.j - o2.j;
                }
                return o1.i - o2.i;
            }
            return o1.route - o2.route;
        }));

        Pos eatPos = eatableList.get(0);
        data[eatPos.i][eatPos.j] = 0;

        sharkHealth++;
        if(sharkHealth == sharkSize){
            sharkSize++;
            sharkHealth = 0;
        }
        result += eatPos.route;
        sharkI = eatPos.i;
        sharkJ = eatPos.j;
        return true;
    }
}