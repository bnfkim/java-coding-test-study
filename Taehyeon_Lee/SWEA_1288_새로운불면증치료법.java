package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
SWEA 새로운불면증치료법 1288 D2
시작 시간 : 24-01-28 21:30
종료 시간 : 24-01-28 21:45

고려사항
boolean[] visited 배열을 활용하여 0~9의 모든 숫자를 확인하였는지 체크하였습니다
1N, 2N등의 숫자는 String.valueOf(val).toCharArray()를 활용하여
String으로 변경한 후 char마다 확인 여부를 체크하였습니다.
*/

public class Solution1288 {

    static int T;
    static int[] data;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        data = new int[T];
        for (int i = 0; i < T; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < T; i++) {
            visited = new boolean[10];
            int iter = 0;
            int delta = data[i];
            while(!allVisited()){
                iter++;
                int val = delta * iter;
                for(char c : String.valueOf(val).toCharArray()){
                    visited[c-'0'] = true;
                }
            }
            System.out.println("#" + (i+1) + " " + iter * data[i]);
        }

    }

    private static boolean allVisited() {
        for(boolean b : visited){
            if(!b) return false;
        }
        return true;
    }
}