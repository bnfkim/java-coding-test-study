package basic;

import java.io.*;

public class BOJ_20125_쿠키의신체측정 {
	public static void main(String[] args) throws IOException  {
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bw.readLine());
		
		char[][] c_array = new char[n][n];
		
		for (int i = 0; i < n; i++ ) {
			c_array[i] = bw.readLine().toCharArray();
		}
		
		
		int hx = 0, hy = 0, s=0;
		int ans1=0, ans2=0, ans3=0, ans4=0, ans5=0;
		boolean flag = false;
		for (int i = 0; i < n; i++ ) {
			for (int j = 0; j < n; j++ ) {
				if (c_array[i][j] == '*') {
					hx = i+2;
					hy = j+1;
					flag = true;
					continue;
				}
			}
			if (flag) {
				break;
			}
		}
		
		for (int j = 0; j < n; j++ ) {
			if (c_array[hx-1][j] == '*') {
				if (j < hy-1) {
					ans1++;
				} else if (j > hy-1) {
					ans2++;
				}
				
			}
		}
		
		for (int i = hx; i < n; i++ ) {
			if (c_array[i][hy-1] == '*') {
				ans3++;
			} else {
				s = i;
				break;
			}
		}
		
		
		for (int i = s; i < n; i++ ) {
			flag = false;
			if (c_array[i][hy-2] == '*') {
				ans4++;
				flag = true;
			} 
			if (c_array[i][hy] == '*') {
				ans5++;
				flag = true;
			}
			if (!flag) break;
		}
		
		System.out.println(hx + " " + hy);
		System.out.print(ans1 + " " + ans2 + " " + ans3 + " " + ans4 + " " + ans5);
	}
}
