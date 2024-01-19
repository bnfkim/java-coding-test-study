package java_codingtest_study;

import java.io.*;
import java.util.*;

class Point{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class BOJ_2468_안전영역 {
	
	static int[][] arr;
	static int maxh;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static int bfs(int height,int n) {
		boolean[][] check = new boolean[n][n];
		ArrayDeque<Point> dq = new ArrayDeque<>();
		int tmp=0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				
				if((arr[i][j]>height)&&(check[i][j]==false)) {
					tmp+=1;
					dq.offer(new Point(i,j));
					check[i][j]=true;
					while(!dq.isEmpty()) {
						Point p = dq.poll();
						int x = p.x;
						int y = p.y;
						
						for(int k=0;k<4;k++) {
							int xx = x + dx[k];
							int yy = y + dy[k];
							if((0<=xx)&&(xx<n)&&(0<=yy)&&(yy<n)&&(arr[xx][yy]>height)&&(check[xx][yy]==false)) {
								check[xx][yy]=true;
								dq.offer(new Point(xx,yy));
							}
							
						}
					}
				}
				
			}
		}
		return tmp;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for(int i=0;i<n;i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(s[j]);
				if(arr[i][j]>maxh) {
					maxh=arr[i][j];
				}
					
			}
		}
		
		int tmp=0;
		int maxCnt=0;
		for(int h=1;h<maxh;h++) {
			tmp = bfs(h,n);
			if(tmp>maxCnt) {
				maxCnt=tmp;
			}
		}
		
		System.out.println(maxCnt);
		
	}

}
