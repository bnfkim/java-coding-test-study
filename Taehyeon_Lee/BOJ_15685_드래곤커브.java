package Baekjoon;

import java.io.*;
import java.util.*;

/*
시작 시간 : 24-02-02 23:30
종료 시간 : 24-02-03 02:00
실행 시간 : 88ms
메 모 리 : 12004KB

고려사항
끝점을 기준으로 회전하는 라인의 좌표및 방향 계산과, 기존의 라인을
적절하게 잇는 것이 관건이였습니다
모든 계산은 현재 시점에서 끝점을 기준으로 계산합니다

0~m세대를 진행하는 경우, (0<k<m 일때)
1. 끝 점에서 기존의 line을 한칸씩 확인함
2. 끝 점에서 "기존의 라인으로 가는 1칸의 방향"의 90도 회전한 방향으로 1칸 이동
3. 2에서 간 반대 방향을 리스트에 저장
4. 기존의 line을 모두 방문할 때까지 2~3번 반복
5. 3번에서 저장한 리스트 오른쪽 끝에 기존의 리스트 concat
6. 기존 리스트를 5번에서 생성한 리스트로 교체
7. 세대가 끝날 떄까지 1~6번을 반복

- 0~100의 2차원 배열(isVisited)에서 사각형 1칸의 네 모서리가 모두 true인 칸 세기

 */

public class BOJ_15685_드래곤커브_G3 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int[][] input;
    static int[][] dirTable = {{1,0}, {0,-1}, {-1,0}, {0,1}};
    static boolean[][] isVisited  = new boolean[101][101];
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N][4];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 line 그리기
        for(int i = 0; i < N; i++){
            drawLine(input[i]);
        }

        // 유효한 정사각형 개수 계산
        int ans = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if(isVisited[x][y] && isVisited[x][y+1] && isVisited[x+1][y] && isVisited[x+1][y+1]) ans++;
            }
        }
        System.out.println(ans);
    }

    private static void drawLine(int[] data) {
        int gen = data[3];
        Point start = new Point(data[0], data[1]);
        checkVisit(start);
        // 다음 방향으로 이동함
        start.x += dirTable[data[2]][0]; start.y += dirTable[data[2]][1];
        checkVisit(start);

        Deque<Integer> dirList = new ArrayDeque<>();
        dirList.add(getReverse(data[2]));
        while(gen-- > 0){

            Deque<Integer> temp = new ArrayDeque<>();
            for(int dir : dirList){
                // 회전 고려
                int nextDir = getRotate(dir);
                // 저장할 배열 생성
                temp.addFirst(getReverse(nextDir));
                start.x += dirTable[nextDir][0]; start.y += dirTable[nextDir][1];
                // 도착지점마다 visit 체크
                checkVisit(start);
            }

            // 기존배열 = 저장배열 + 기존배열
            temp.addAll(dirList);
            dirList = temp;
        }
    }

    static int getReverse(int dir){
        return (dir+2) % 4;
    }

    static int getRotate(int dir){
        return (dir+3) % 4;
    }

    static void checkVisit(Point p){
        isVisited[p.y][p.x] = true;
    }
}