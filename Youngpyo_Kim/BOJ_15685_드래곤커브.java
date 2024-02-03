package subset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SubSetTest3 {
	static int n, ans = 0;
	static int delta[][] = {{0,1},{-1,0},{0,-1},{1,0}};
	static boolean board[][] = new boolean[101][101];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < n; t++) {
			int r,c,d,g;
			StringTokenizer st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> dir = new ArrayList<>();
			dir.add(d);
			
			for(int i = 0; i < g; i++) {
				ArrayList<Integer> tmp = dir;
				for(int j = tmp.size()-1; j >= 0; j--) 
					dir.add((tmp.get(j)+1)%4);
			}
			
			
			board[r][c] = true;
			for(int i = 0; i < dir.size(); i++) {
				r += delta[dir.get(i)][0];
				c += delta[dir.get(i)][1];
				board[r][c] = true;
			}
		}
		
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(board[i][j] && board[i][j+1] && board[i+1][j] && board[i+1][j+1])
	                ans++;
			}
		}
		
		System.out.println(ans);
	}
}