package jmalgo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_주사위쌓기_2116 {

	static int N;
	static int[] first;
	static int[][] dice;
	static int answer;
	static int result;
	public static void main(String[] args) throws IOException {

		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		
		
		
	
		StringTokenizer st;
		
		N= Integer.parseInt(bf.readLine());
		
		
		
		
		first = new int[6];
		dice = new int[N-1][6];
		
		st=new StringTokenizer(bf.readLine());
		for(int i=0;i<6;i++)
		{
			first[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N-1;i++)
		{
			st=new StringTokenizer(bf.readLine());
			for(int j=0;j<6;j++)
			{
				dice[i][j]=Integer.parseInt(st.nextToken());
			}
		}
	
		for(int i=0;i<4;i++)
		
		{
		
			//System.out.println(i);
			rotate1(first);	
			//System.out.println(Arrays.toString(first));
			check();
			result=Math.max(result, answer);
			//System.out.println();
		}
		for(int i=0;i<4;i++)
		{
			rotate2(first);
			//System.out.println(Arrays.toString(first));
			check();
			result=Math.max(result, answer);
			//System.out.println();
		}

		
		System.out.println(result);
	}

	public static void swap(int x,int y)
	{
		int temp= first[x];
		first[x]=first[y];
		first[y]=temp;
	}
	public static void check()
	{
		int upNum=first[0];
		 answer=0;
		
		int maxNum=0;
		for(int z=1;z<5;z++)
		{
			maxNum=Math.max(maxNum, first[z]);
		}
		
		answer+=maxNum;
		
		
		
		for(int i=0;i<dice.length;i++)
		{
			boolean flag= false;
			for(int r=0;r<4;r++)
			{
				rotate1(dice[i]);
				if(dice[i][5]==upNum)
				{
					flag=true;
					break;
					
				}
				
				
			}
			if(!flag)
			{
			for(int r=0;r<4;r++)
			{
				rotate2(dice[i]);
				if(dice[i][5]==upNum)
				{
					break;
				}
				
			}
			}
			//System.out.println(Arrays.toString(dice[i]));
			upNum=dice[i][0];
			
			maxNum=0;
			for(int z=1;z<5;z++)
			{
				maxNum=Math.max(maxNum, dice[i][z]);
			}
			
			answer+=maxNum;
			
		}
		
	}
	public static void rotate1(int[] board)
	{
		int[] temp = new int[6];
		
		temp[0]=board[1];
		temp[1]=board[5];
		temp[2]=board[2];
		temp[3]=board[0];
		temp[4]=board[4];
		temp[5]=board[3];
		
		for(int i=0;i<6;i++)
		{
			board[i]=temp[i];
		}

	}
	public static void rotate2(int[] board)
	{
		int[] temp = new int[6];
		
		temp[0]=board[4];//
		temp[1]=board[1];
		temp[2]=board[0];//
		temp[3]=board[3];
		temp[4]=board[5]; //
		temp[5]=board[2];//
		
		for(int i=0;i<6;i++)
		{
			board[i]=temp[i];
		}
	}
}
 
