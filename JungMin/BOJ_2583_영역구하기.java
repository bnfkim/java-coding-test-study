package jmalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_영역구하기_2583 {

	static int N,M,K;
	static int[][] board;
	static int[] dx= {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] visited;
	static ArrayList<Integer> result;
	static int count;
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(bf.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
	
	 result = new ArrayList<>();
		board= new int[N][M];
		visited= new boolean[N][M];
		for(int i=0;i<K;i++)
		{
			st =new StringTokenizer(bf.readLine());
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			
		
			for(int x=x1;x<x2;x++)
			{
				for(int y=y1;y<y2;y++)
				{
					
					board[y][x]=1;
				}
			}
		}
		int c=0;
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				if(!visited[i][j] && board[i][j]==0)
				{
				
					bfs(i,j);
					c++;
				}
			}
		}
	
		System.out.println(c);
		Collections.sort(result);
		for(int a: result)
		{
			System.out.print(a+" ");
		}
	}
	public static void bfs(int x,int y)
	{
		int[] now = new int[] {x,y};
		Queue<int []>q = new LinkedList<>();
		q.add(now);
		visited[x][y]=true;
		
		int count=1;
		while(!q.isEmpty())
		{
			int[] current = q.poll();
			
			for(int i=0;i<4;i++)
			{
				int nx=current[0]+dx[i];
				int ny=current[1]+dy[i];
				
				if(isBoundary(nx, ny) && board[nx][ny]==0 && !visited[nx][ny])
				{
					count++;
					q.add(new int[] {nx,ny});
					visited[nx][ny]=true;
				}
			}
		}
		result.add(count);
		
		
	}
	public static boolean isBoundary(int x,int y)
	{
		return x>=0 && x<N && y>=0 && y<M;
	}

}

