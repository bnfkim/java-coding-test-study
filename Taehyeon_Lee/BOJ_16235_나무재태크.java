package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
백준 나무제태크 16235 G3
시작 : 24-01-20 12:40
끝 : 24-01-20 14:35
실행시간 : 904ms


고려사항
1 x 1 칸에 나무 여러개 가능
처음에는 모든 칸 양분 5
봄
 - 나무가 자신의 나이만큼 양분을 먹고, 나이 1 증가
 - 나무는 자신이 존재하는 1x1칸 내의 양분만 흡수 가능
 - 나무 여러개 -> 나이가 어린 나무부터 양분 흡수
 - 양분 흡수 실패 -> 나무 죽음
여름
 - 죽은 나무 양분으로 변환 -> 나이 / 2 만큼 양분 변환
가을
 - 나이가 5의 배수인 나무 주변 8방향에 나이 1인 나무 생성
겨울
 - 특정 수만큼 각 구역에 양분 추가

K년 후 살아있는 나무의 수는?
-----------------------------------------------
사이클 별로 계산
한 칸에 있어야할 정보
존재하는 나무의 나이별 숫자, 죽은 나무들의 나이 숫자, 추가될 양분, 현재 양분

 */

public class Solution16235 {
    static class Sector{
        ArrayList<Integer> survive;
        ArrayList<Integer> dead;
        int adder, energy;
        public Sector(int adder) {
            survive = new ArrayList<>();
            dead = new ArrayList<>();
            this.adder = adder;
            energy = 5;
        }
    }

    static StringTokenizer st;
    static int N, M, K;
    static Sector[][] map;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Sector[N][N];

        // init map adder
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = new Sector(Integer.parseInt(st.nextToken()));
            }
        }

        // init initial tree pos
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            map[r][c].survive.add(age);
        }

        for (int year = 0; year < K; year++) {
            spring();
            summernFallnWinter();
        }

        // 최종 살아남은 나무 개수 계산
        int surviveTreeNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                surviveTreeNum += map[i][j].survive.size();
            }
        }

        System.out.println(surviveTreeNum);
    }

    private static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grow(map[i][j]);
            }
        }
    }

    private static void grow(Sector s) {
        ArrayList<Integer> newSur = new ArrayList<>();

        // 어린애부터 양분 먹기
        for(Integer age : s.survive){
            if(age <= s.energy){
                newSur.add(age+1);
                s.energy -= age;
            }else{
                s.dead.add(age);
            }
        }
        s.survive = newSur;
    }

    private static void summernFallnWinter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // summer
                for (Integer age : map[i][j].dead) {
                    map[i][j].energy += age / 2;
                }
                map[i][j].dead.clear();
                // fall
                breeding(i,j);
                // winter
                map[i][j].energy += map[i][j].adder;
            }
        }
    }

    private static void breeding(int idxI, int idxJ) {

        for (Integer age : map[idxI][idxJ].survive) {

            // 나무 나이가 5의 배수인 경우
            if (age % 5 == 0) {

                // 주변 8방향에 나이 1인 나무 심기
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int nextI = idxI + i, nextJ = idxJ + j;
                        if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;
                        if (nextI == idxI && nextJ == idxJ) continue;
                        map[idxI+i][idxJ+j].survive.add(0,1);
                    }

                }
            }
        }
    }

}