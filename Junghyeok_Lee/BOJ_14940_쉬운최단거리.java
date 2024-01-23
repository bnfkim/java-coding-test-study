package java_codingtest_study;

import java.io.*;
import java.util.*;

class Po{
	int x;
	int y;
	Po(int x, int y){
		this.x=x;
		this.y=y;
	}
}

public class BOJ_14940_쉬운최단거리 {
	static int n,m;
	static int[][] arr;
	static int[][] res;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		res = new int[n][m];
		arr = new int[n][m];
		
		int x=0,y=0;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(arr[i][j]==2) {
					x=i;
					y=j;
				}
				if(arr[i][j]!=0) {
					res[i][j]=-1;
				}
			}
		}
		
		Deque<Po> dq = new LinkedList<>();
		dq.add(new Po(x,y));
		res[x][y]=0;
		while(!dq.isEmpty()) {
			Po p = dq.poll();
			int a = p.x;
			int b = p.y;
			for(int i=0;i<4;i++) {
				int xx = a + dx[i];
				int yy = b + dy[i];
				if((xx>=0)&&(xx<n)&&(yy>=0)&&(yy<m)&&(arr[xx][yy]==1)&&(res[xx][yy]==-1)) {
					res[xx][yy]=res[a][b]+1;
					dq.add(new Po(xx,yy));
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
