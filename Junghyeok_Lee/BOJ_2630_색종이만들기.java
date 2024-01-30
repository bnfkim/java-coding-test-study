package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_색종이만들기 {
	
	static int[][] arr;
	static int res;
	static int blue;
	static int white;
	
	static void rec(int x,int y,int num) {
		int tmp=arr[x][y];
		int b=0;
		loop1:
		for(int i=x;i<x+num;i++) {
			for(int j=y;j<y+num;j++) {
				if(arr[i][j]!=tmp) {
					b=1;
					break loop1;
				}
			}
		}
		if(b==1) {
			rec(x,y,num/2);
			rec(x,y+num/2,num/2);
			rec(x+num/2,y,num/2);
			rec(x+num/2,y+num/2,num/2);
		}else {
			if(tmp==0) white++;
			else blue++;
		}
			
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		rec(0,0,n);
		System.out.println(white);
		System.out.println(blue);
	}

}
