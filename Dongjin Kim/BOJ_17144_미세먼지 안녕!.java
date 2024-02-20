package boj;

/*
 * BOJ_17144_미세먼지 안녕!.java
 * [BOJ]17144/골드4/352ms/1h/김동진
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {
	
	static int R, C, T, a_loc;
	static int[][] map;
	static int[][] copy_map;
	static int[][] dir = {
			{-1, 0},
			{0, 1},
			{1, 0},
			{0, -1}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) a_loc = i;	// 아래 좌표 저장
			}
		}
		
		
		for(int t = 0; t < T; t++) {
			copy_map = new int[R][C];
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					diffusion(i, j);
				}
			}
			copy();
			airclean();
		}
		
		System.out.println(print());
	}
	
	public static void diffusion(int cx, int cy) {
		
		if(map[cx][cy] == 0) return;
		if(map[cx][cy] == -1) {
			copy_map[cx][cy] = -1;
			return;
		}
		
		int temp = map[cx][cy];
		int dif = temp / 5;
		
		for(int i = 0; i < 4; i++) {
			int nx = cx + dir[i][0];
			int ny = cy + dir[i][1];
			
			if(inMap(nx, ny) && map[nx][ny] != -1) {
				copy_map[nx][ny] += dif;
				temp -= dif;
			}
		}
		copy_map[cx][cy] += temp;
		
	}
	
	public static void airclean() {
		
		// 위의 공기 순환
		
		// 좌
		for(int i = a_loc - 2; i > 0; i--) {
			map[i][0] = map[i-1][0];
		}
		
		// 상
		for(int i = 1; i < C; i++) {
			map[0][i-1] = map[0][i];
		}
		
		// 우
		for(int i = 1; i < a_loc; i++) {
			map[i-1][C-1] = map[i][C-1];
		}
			
		// 하
		for(int i = C - 2; i > 0; i--) {
			map[a_loc-1][i + 1] = map[a_loc-1][i];
			map[a_loc-1][i] = 0;
		}
		
		
		// 아래 공기 순환
		
		// 좌
		for(int i = a_loc + 2; i < R; i++) {
			map[i - 1][0] = map[i][0];
		}
		
		// 하
		for(int i = 1; i < C; i++) {
			map[R-1][i-1] = map[R-1][i];
		}
		
		// 우
		for(int i = R - 1; i > a_loc; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		
		// 상
		for(int i = C - 2; i > 0; i--) {
			map[a_loc][i + 1] = map[a_loc][i];
			map[a_loc][i] = 0;
		}
		
		
	}
	
	public static boolean inMap(int x, int y) {
		return (0 <= x && x < R && 0 <= y && y < C);
	}
	
	public static void copy() {
		for(int i = 0; i < R; i++) {
			map[i] = copy_map[i].clone();
		}
		return;
	}
	
	public static int print() {
		int total = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] > 0) total += map[i][j];
			}
		}
		
		return total;
	}
}
