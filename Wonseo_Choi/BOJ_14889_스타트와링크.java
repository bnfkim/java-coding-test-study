import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	
	public static int combination(int n, int r) {
		if(n == r || r == 0) 
			return 1; 
		else 
			return combination(n - 1, r - 1) + combination(n - 1, r); 
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] ar = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++ ) {
				ar[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] temp1 = new int[n/2];
		int[] temp2 = new int[n/2];
		int cnt1 = 0, cnt2 = 0, ans1 = 0, ans2 = 0, cnt3=0;
		int tmp=0, ans=Integer.MAX_VALUE;
		int iterval = combination(n, n/2)/2;
 		for (int i = 0; i < 1<<n; i++ ) {
			if (Integer.bitCount(i) == n/2) {
				for (int j = 0; j < n; j++) {
					if (((1<<j) & i) > 0) {
						temp1[cnt1++] = j;
					} else {
						temp2[cnt2++] = j;
					}
				}
				cnt1 = 0;
				cnt2 = 0;
				for (int k = 0; k < n/2; k++ ) {
					for (int l = k+1; l < n/2; l++ ) {
						ans1 += ar[temp1[k]][temp1[l]];
						ans1 += ar[temp1[l]][temp1[k]];
					}
				}
				for (int k = 0; k < n/2; k++ ) {
					for (int l = k; l < n/2; l++ ) {
						ans2 += ar[temp2[k]][temp2[l]];
						ans2 += ar[temp2[l]][temp2[k]];
					}
				}
				tmp = Math.abs(ans1-ans2);
				if (ans > tmp) {
					ans = tmp;
				}
				ans1 = 0;
				ans2 = 0;
				cnt3++;
				if (cnt3 > iterval) {
					System.out.println(ans);
					System.exit(0);
				}
			}
		}
 		
	}
}
