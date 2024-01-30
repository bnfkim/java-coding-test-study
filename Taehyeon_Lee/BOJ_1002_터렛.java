package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 터렛 1002 S3
시작 시간 : 24-01-25 21:30
종료 시간 : 24-01-25 21:53
실행시간 : 76ms

고려사항
2개의 원이 겹쳐질 수 있는 6가지의 경우의 수를 계산하였습니다.
*/

public class Solution1002 {

    static int T;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int x1, y1, r1, x2, y2, r2;
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());

            double dis = Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));

            if(r1+r2 < dis) sb.append(0);
            else if(x1==x2 && y1==y2 && r1==r2) sb.append(-1);
            else if(r1+r2 == dis) sb.append(1);
            else if(r1+r2>dis && Math.abs(r1-r2) < dis) sb.append(2);
            else if(Math.abs(r1-r2) == dis) sb.append(1);
            else sb.append(0);

            sb.append("\n");
        }

        System.out.println(sb);
    }
}