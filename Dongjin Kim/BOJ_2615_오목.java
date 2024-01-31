package bj;

import java.io.*;
import java.util.*;

public class bj_2615 {
	
	static final int N = 19;
				     //우상 우 우하 하 좌하 좌 좌상 상  시계방향으로 회전 (왼쪽 기준으로 가장 먼저 증가를 시작할 수 있는 방향)
	static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};

    static int map[][] = new int[19][19];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int i, j;
        
        // 바둑알 정보 입력
        for (i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } 

        // 가장 왼쪽 인덱스를 출력해야 하기 때문에 j 먼저 반복 돌림
        for(j = 0; j < N; j++) {
            for (i = 0; i < N; i++) {
            	if(map[i][j]!=0) {
            		if(solution(i, j, map[i][j])) {
            			System.out.println(map[i][j]);
            			System.out.println((i + 1) + " " + (j + 1));
            			return;
            		}
            	}
            }
        }
        System.out.println(0);
    }

    public static boolean solution(int x, int y, int now) {
    	
    	
    	for(int i = 0; i < 4; i++) {
    		int total = 1, sum1 = 0, sum2 = 0;
    		// 다음 돌의 정보 저장
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		
    		// 이전 방향의 돌의 정보 저장
    		int px = x + dx[(i + 4) % 8];
    		int py = y + dy[(i + 4) % 8];
    		
    		
    		// (1) 이전 방향의 일직선 돌 개수 저장
    		while(check(px, py) && map[px][py] == now) {
    			sum1++;
    			px += dx[(i + 4) % 8];
    			py += dy[(i + 4) % 8];
    		}
    		
    		// (2) 다음 방향의 일직선 돌 개수 저장
    		while(check(nx, ny) && map[nx][ny] == now) {
    			sum2++;
    			nx += dx[i];
    			ny += dy[i];
    		}
    		total = total + sum1 + sum2;
    		if(total == 5) {
    			return true;
    		}
    	}
    	return false;

    }

    
    public static boolean check(int x, int y) {
    	return 0 <= x && x < N && 0 <= y && y < N;
    }

}
