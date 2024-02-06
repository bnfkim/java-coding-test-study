package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {

	static int n,R;
	static int[][] arr;
	static boolean[] isSelected;
	static int res = 9000;
	
	static void combi(int depth, int start) {
		if(depth==R) {
			int a = 0;
			int b = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(isSelected[i] && isSelected[j]) {
						a+=arr[i][j];
					}else if(!isSelected[i] && !isSelected[j]){
						b+=arr[i][j];
					}
				}
			}
			int tmp = Math.abs(a-b);
			if(res>tmp) {
				res=tmp;
			}
			
			return;
		}
		
		for(int i = start;i<n;i++) {
			isSelected[i]=true;
			combi(depth+1,i+1);
			isSelected[i]=false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		R = n/2;
		arr = new int[n][n];
		isSelected = new boolean[n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		isSelected[0]=true;
		combi(1,1);
		System.out.println(res);
		
	}

}
