package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14719_빗물 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[w];
		for(int i=0;i<w;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int res=0;
		int tmpres = 0;
		boolean flag;
		for(int i=h;i>0;i--) {
			flag=false;
			for(int j=0;j<w;j++) {
				if(arr[j]>=i) {
					if(!flag) {
						flag=true;
					}
					else {
						res+=tmpres;
					}
					tmpres=0;
				}else {
					if(flag) {
						tmpres+=1;
					}
				}
			}
		}
		System.out.println(res);
	}

}
