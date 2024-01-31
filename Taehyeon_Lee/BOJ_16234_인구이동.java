package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 인구 이동 16234 G4
시작 시간 : 24-01-21 23:00
종료 시간 : 24-01-21 12:00
실행시간 : 584ms

고려사항
메모리 초과
처음에는 int -> byte로 해결할 수 있을 것으로 예상했지만,
BFS를 적용하는 과정에서 메모리 초과가 발생하였다.
이유
만약 (2,1), (1,2), (2,3)이 queue에 들어있을 때,
다음으로 갈곳이 (2,2)하나 임에도 3개의 포인트에서 모두 queue에 (2,2)를 3번 중복하여
추가하기 때문에 발생한 메모리 초과
해결방법
만약 이미 queue에 (2,2)가 존재하면 더이상 (2,2)를 추가하지 않음
 */

public class Solution16234 {

    static int N, L, R, day;
    static byte[][] data;
    static boolean[][] visited;
    static StringTokenizer st;
    static boolean isPeopleMove = true;
    static byte[] dx = {-1, 1, 0, 0};
    static byte[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        data = new byte[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                data[i][j] = Byte.parseByte(st.nextToken());
            }
        }


        while(isPeopleMove){
            isPeopleMove = false;
            visited = new boolean[N][N];

            for (byte i = 0; i < N; i++) {
                for (byte j = 0; j < N; j++) {
                    if(visited[i][j]) continue;

                    bfs(i,j);
                }
            }
            if(isPeopleMove) day++;
        }

        System.out.println(day);
    }

    private static void bfs(byte idxI, byte idxJ) {

        int sum = 0;
        ArrayList<Point> union = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(idxI, idxJ));

        while (!q.isEmpty()){
            Point cur = q.poll();
            union.add(cur);
            byte curI = cur.i, curJ = cur.j;
            visited[curI][curJ] = true;
            sum += data[curI][curJ];

            for (int i = 0; i < 4; i++) {
                byte nextI = (byte) (curI + dx[i]);
                byte nextJ = (byte) (curJ + dy[i]);

                if(nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;
                if(visited[nextI][nextJ]) continue;

                int diff = Math.abs(data[curI][curJ] - data[nextI][nextJ]);
                if(diff >= L && diff <= R){
                    if(!q.contains(new Point(nextI, nextJ)))
                        q.add(new Point(nextI, nextJ));
                }

            }
        }

        if(union.size() > 1){
            int unionVal = sum / union.size();

            for(Point p : union){
                data[p.i][p.j] = (byte) unionVal;
            }
            isPeopleMove = true;
        }
    }

    public static class Point{
        public byte i, j;

        public Point(byte i, byte j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            // 런타임 상에서 현재 이 객체와 obj객체가 동일한 객체인지 확인
            if (getClass() != obj.getClass())
                return false;
            Point p = (Point) obj;
            return this.i == p.i && this.j == p.j;
        }
    }
}