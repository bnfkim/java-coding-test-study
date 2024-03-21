package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_24954_물약구매 {
	static int N;
	static int[] arr;
	static int[][] info;
	static int[] numbers;
	static int res = 10000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		info = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			for(int j=0;j<p;j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				info[i][a-1]=d;
			}
		}
		
		numbers = new int[N];
		for(int i=0;i<N;i++) {
			numbers[i]=i;
		}
		
		do {
			cal();
		} while (np());
		
		System.out.println(res);
	}
	
	static void cal() {
		int[] tmp = new int[N];
		for(int i=0;i<N;i++) {
			tmp[i]=arr[i];
		}
		
		int t=0;
		
		for(int i=0;i<N;i++) {
			t+=tmp[numbers[i]];
			tmp[numbers[i]]=0;
			if(t>res) return;
			for(int j=0;j<N;j++) {
				if(info[numbers[i]][j]!=0 && tmp[j]!=0) {
					tmp[j]=Math.max(1,tmp[j]-info[numbers[i]][j]);
				}
			}
		}
		res=t;
	}
	
	static boolean np() {
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) i--;
		
		if(i==0) return false;
		
		int j = N-1;
		while(numbers[i-1]>=numbers[j]) j--;
		
		swap(i-1,j);
		
		int k = N-1;
		while(i<k) swap(i++,k--);
		
		return true;
	}
	
	static void swap(int i, int j) {
		int tmp = numbers[i];
		numbers[i]=numbers[j];
		numbers[j]=tmp;
	}

}

