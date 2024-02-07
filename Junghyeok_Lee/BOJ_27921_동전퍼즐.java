package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_27921_동전퍼즐 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h1 = Integer.parseInt(st.nextToken());
		int w1 = Integer.parseInt(st.nextToken());
		char[][] arr1 = new char[h1][w1];
		int ocount=0;
		for(int i=0;i<h1;i++) {
			String s = br.readLine();
			for(int j=0;j<w1;j++) {
				arr1[i][j] = s.charAt(j);
				if(arr1[i][j]=='O') {
					ocount+=1;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int h2 = Integer.parseInt(st.nextToken());
		int w2 = Integer.parseInt(st.nextToken());
		char[][] arr2 = new char[h2+h1*2][w2+w1*2];
		for(int i=h1;i<h1+h2;i++) {
			String s = br.readLine();
			for(int j=w1;j<w1+w2;j++) {
				arr2[i][j] = s.charAt(j-w1);
			}
		}
		
		int maxVal=0;
		for(int i=0;i<h1+h2;i++) {
			for(int j=0;j<w1+w2;j++) {
				int cnt=0;
				for(int k=0;k<h1;k++) {
					for(int l=0;l<w1;l++) {
						if(arr1[k][l]=='O'&&arr2[i+k][j+l]=='O') {
							cnt+=1;
						}
					}
				}
				if(maxVal<cnt) {
					maxVal=cnt;
				}
			}
		}
		System.out.println(ocount-maxVal);
	}

}
