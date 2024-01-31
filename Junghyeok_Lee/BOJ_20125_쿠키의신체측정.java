package java_codingtest_study;

import java.io.*;
import java.util.Arrays;

public class BOJ_20125_쿠키의신체측정 {
	
	static char[][] arr; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		arr = new char[n][n];
		
		for(int i=0;i<n;i++) {
			String s = br.readLine();
			for(int j=0;j<n;j++) {
				arr[i][j]=s.charAt(j);
			}
		}
		
		int x=0,y=0;
		Loop1 :
		for(int i=0;i<n;i++) {
			Loop2 :
			for(int j=0;j<n;j++) {
				if(arr[i][j]=='*') {
					x=i;
					y=j;
					System.out.println((x+2)+" "+(y+1));
					break Loop1;
				}
			}
		}
		
		x+=1;
		int tmp=1;
		while(y-tmp>=0) {
			if(arr[x][y-tmp]=='_') {
				break;
			}
			tmp++;
		}
		System.out.print(tmp-1);
		
		tmp=1;
		while(y+tmp<n) {
			if(arr[x][y+tmp]=='_') {
				break;
			}
			tmp++;
		}
		System.out.print(" "+(tmp-1));
		
		tmp=1;
		while(true) {
			if(arr[x+tmp][y]=='_') {
				break;
			}
			tmp+=1;
		}
		System.out.print(" "+(tmp-1));
		
		x+=tmp-1;
		tmp=1;
		while(x+tmp<n) {
			if(arr[x+tmp][y-1]=='_') {
				break;
			}
			tmp+=1;
		}
		System.out.print(" "+(tmp-1));
		
		tmp=1;
		while(x+tmp<n) {
			if(arr[x+tmp][y+1]=='_') {
				break;
			}
			tmp+=1;
		}
		System.out.print(" "+(tmp-1));
		
	}

}
