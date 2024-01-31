package java_codingtest_study;

import java.io.*;
import java.util.*;

public class BOJ_2615_오목 {
	static String[][] arr = new String[19][19];
	static boolean[][] rcheck = new boolean[19][19];
	static boolean[][] drcheck = new boolean[19][19];
	static boolean[][] dcheck = new boolean[19][19];
	static boolean[][] dlcheck = new boolean[19][19];
	
	static void rcheck(int x, int y) {
		int cnt=1;
		rcheck[x][y]=true;
		while((y+cnt<19)&&(arr[x][y+cnt].equals(arr[x][y]))) {
			rcheck[x][y+cnt]=true;
			cnt+=1;
		}
		
		if(cnt==5) {
			System.out.println(arr[x][y]);
			System.out.println((x+1)+" "+(y+1));
			System.exit(0);
		}
	}
	
	static void drcheck(int x, int y) {
		int cnt=1;
		drcheck[x][y]=true;
		while((x+cnt<19)&&(y+cnt<19)&&(arr[x+cnt][y+cnt].equals(arr[x][y]))) {
			drcheck[x+cnt][y+cnt]=true;
			cnt+=1;
		}
		
		if(cnt==5) {
			System.out.println(arr[x][y]);
			System.out.println((x+1)+" "+(y+1));
			System.exit(0);
		}
	}
	
	static void dcheck(int x, int y) {
		int cnt=1;
		dcheck[x][y]=true;
		while((x+cnt<19)&&(arr[x+cnt][y].equals(arr[x][y]))) {
			dcheck[x+cnt][y]=true;
			cnt+=1;
		}
		
		if(cnt==5) {
			System.out.println(arr[x][y]);
			System.out.println((x+1)+" "+(y+1));
			System.exit(0);
		}
	}
	
	static void dlcheck(int x, int y) {
		int cnt=1;
		dlcheck[x][y]=true;
		while((x+cnt<19)&&(y-cnt>=0)&&(arr[x+cnt][y-cnt].equals(arr[x][y]))) {
			dlcheck[x+cnt][y-cnt]=true;
			cnt+=1;
		}
		
		if(cnt==5) {
			System.out.println(arr[x][y]);
			System.out.println((x+cnt)+" "+(y-cnt));
			System.exit(0);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0;i<19;i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0;j<19;j++) {
				arr[i][j]=s[j];
			}
		}
		
		for(int i=0;i<19;i++) {
			for(int j=0;j<19;j++) {
				if(!(arr[i][j].equals("0"))) {
					if(!rcheck[i][j]) {
						rcheck(i,j);
					}
					if(!drcheck[i][j]) {
						drcheck(i,j);
					}
					if(!dcheck[i][j]) {
						dcheck(i,j);
					}
					if(!dlcheck[i][j]) {
						dlcheck(i,j);
					}
					
				}
					
			}
		}
		
	}

}
