package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
시작 시간 : 24-02-09 01:40
종료 시간 : 24-02-09 02:40
실행시간 : 76ms
메 모 리 : 11632KB

고려사항
뿌요가 터지는 로직과, 터진 빈자리를 채워 떨어지는 로직을 분리하여, 코드를 구성하였습니다
터지는 뿌요는 BFS를 통하여 인접한 뿌요가 몇 개인지 체크하였습니다.
*/

public class BOJ_11559_PuyoPuyo_G4{

    static class Cell{
        int i, j;

        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int chain;
    static char[][] fields = new char[12][6];
    static boolean[][] isVisited = new boolean[12][6];
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = { 0, 0,-1, 1};

    private static void input() throws IOException {
        System.setIn(Files.newInputStream(Paths.get("input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 12; i++){
            char[] temp = br.readLine().toCharArray();
            System.arraycopy(temp, 0, fields[i], 0, 6);
        }
    }

    private static boolean pop() {

        isVisited = new boolean[12][6];

        boolean bombOccur = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if(fields[i][j] == '.') continue;
                if(isVisited[i][j]) continue;

                if(bfs(i, j)) bombOccur = true;
            }
        }

        return bombOccur;

    }

    private static boolean bfs(int idxI, int idxJ) {
        char target = fields[idxI][idxJ];
        Queue<Cell> q = new LinkedList<>();
        ArrayList<Cell> bombList = new ArrayList<>();
        q.add(new Cell(idxI, idxJ));

        while(!q.isEmpty()){
            Cell cur = q.poll();
            bombList.add(cur);
            isVisited[cur.i][cur.j] = true;

            int nextI, nextJ;
            for(int i = 0; i < 4; i++){
                nextI = cur.i + di[i]; nextJ = cur.j + dj[i];
                if(nextI < 0 || nextJ < 0 || nextI >= 12 || nextJ >= 6) continue;
                if(isVisited[nextI][nextJ]) continue;
                if(fields[nextI][nextJ] != target) continue;

                q.add(new Cell(nextI, nextJ));
            }
        }

        if(bombList.size() >= 4){
            for(Cell c : bombList){
                fields[c.i][c.j] = 'x';
            }
            return true;
        }else{
            return false;
        }
    }

    private static void fallDown() {

        for (int col = 0; col < 6; col++) {
            int cur = 11;
            int up = 11;
            char[] vLine = "............".toCharArray();
            while(up >= 0 &&  fields[up][col] != '.'){
                if(fields[up][col] == 'x'){
                    up--;
                }else{
                    vLine[cur] = fields[up][col];
                    cur--; up--;
                }
            }
            for(int i = 0; i < 12; i++) fields[i][col] = vLine[i];
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        while(pop()){
            chain++;
            fallDown();
        }

        System.out.print(chain);
    }
}
