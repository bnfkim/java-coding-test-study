import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2116_주사위쌓기 {
	static int n, ans = 0;
	static int board[][];
	static int ntoidx[][];
	
	public static int change(int num) {
		if(num == 0) return 5;
		else if(num == 1) return 3;
		else if(num == 2) return 4;
		else if(num == 3) return 1;
		else if(num == 4) return 2;
		else return 0;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		board = new int[n][6];
		ntoidx = new int[n][7];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 6; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				ntoidx[i][board[i][j]] = j;
			}
		}
		
		for(int i = 0; i < 6; i++) {
			int res = 0, nextidx = i, maxi = 6;
			for(int j = 0; j < n-1; j++) {
				boolean five = false, six = false;
				maxi = 6;
				if(board[j][nextidx] == 5) five = true;
				else if(board[j][nextidx] == 6) six = true;
				nextidx = change(nextidx);
				
				if(board[j][nextidx] == 5) five = true;
				else if(board[j][nextidx] == 6) six = true;
				
				if(five && six) maxi = 4;
				else if(!five && six) maxi = 5;
				
				res += maxi;
				nextidx = ntoidx[j+1][board[j][nextidx]];
			}
			
			boolean five = false, six = false;
			maxi = 6;
			if(board[n-1][nextidx] == 5) five = true;
			else if(board[n-1][nextidx] == 6) six = true;
			nextidx = change(nextidx);
			
			if(board[n-1][nextidx] == 5) five = true;
			else if(board[n-1][nextidx] == 6) six = true;
			
			if(five && six) maxi = 4;
			else if(!five && six) maxi = 5;
			
			res += maxi;
			ans = Math.max(res, ans);
			
		}
		
		System.out.println(ans);
	}
}
