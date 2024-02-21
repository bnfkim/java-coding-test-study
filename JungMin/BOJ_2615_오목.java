package jungmin;
import java.util.*;

public class ooo {
	
	static int N;
	static int current;
	static int[][] arr;
	static boolean flag;
	static int answer_x;
	static int answer_y;
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
		arr=new int[19][19];
		
		for(int i=0;i<19;i++)
		{
			for(int j=0;j<19;j++)
			{
				arr[i][j]=sc.nextInt();
			}
		}
		//System.out.println(Arrays.deepToString(arr));
	
		
		for(int i=0;i<19;i++) 
		{
			for(int j=0;j<19;j++)
			{
				if (arr[j][i]!=0)
				{
					
					
					current=arr[j][i];
					
					
					check(j,i);
					if(flag==true) {
						
						answer_x=j;
						answer_y=i;
						break;
					}
				}
				
			}
			if(flag==true) break;

		}
		if(flag)
		{
			System.out.println(current);
			System.out.println((answer_x+1)+" "+(1+answer_y));
		}
		else
		{
			System.out.println(0);
		}

	}
	public static boolean isBoundary(int x,int y)
	{
		if(x>=0 && x<19 && y>=0 && y<19) return true;
		return false;
	}
	public static void check(int x,int y)
	{
		int cnt=0;
		boolean flag1;
		boolean flag2;
		
		// up
		cnt=1;
		flag1=false;
		flag2=false;
		for(int i=1;i<19;i++)
		{
			
			if(isBoundary(x-i,y) && arr[x-i][y]==current && flag1==false )
			{
				cnt++;
			}
			else {
				flag1=true;
			}
			
			
			if(isBoundary(x+i,y) && arr[x+i][y]==current && flag2==false)
			{
				cnt++;
			}
			else {
				flag2=true;
			}
			
			if(flag1==true && flag2==true)
			{
				
				break;
				
			}
		}
		if(cnt==5) {flag=true; return;}
		
		
		// left + right
		
		cnt=1;
		flag1=false;
		flag2=false;
		for(int i=1;i<19;i++)
		{
			
			if(isBoundary(x,y+i) &&arr[x][y+i]==current && flag1==false)
			{
				cnt++;
			}
			else {
				flag1=true;
			}
			
			
			if(isBoundary(x,y-i) &&arr[x][y-i]==current && flag2==false)
			{
				cnt++;
			}
			else {
				flag2=true;
			}
			
			if(flag1==true && flag2==true)
			{
				
				break;
				
			}
		}
		if(cnt==5) {flag=true; return;}
		// up + left  , down + right
		
		cnt=1;
		flag1=false;
		flag2=false;
		for(int i=1;i<19;i++)
		{
			
			if(isBoundary(x-i,y-i) && arr[x-i][y-i]==current && flag1==false)
			{
				cnt++;
			}
			else {
				flag1=true;
			}
			
			
			if(isBoundary(x+i,y+i) &&arr[x+i][y+i]==current && flag2==false)
			{
				cnt++;
			}
			else {
				flag2=true;
			}
			
			if(flag1==true && flag2==true)
			{
				
				break;
				
			}
		}
		if(cnt==5) {flag=true; return;}
		// up + right
		
		cnt=1;
		flag1=false;
		flag2=false;
		for(int i=1;i<19;i++)
		{
			
			if(isBoundary(x-i,y+i) && arr[x-i][y+i]==current && flag1==false)
			{
				cnt++;
			}
			else {
				flag1=true;
			}
			
			
			if(isBoundary(x+i,y-i) &&arr[x+i][y-i]==current && flag2==false)
			{
				cnt++;
			}
			else {
				flag2=true;
			}
			
			if(flag1==true && flag2==true)
			{
				
				break;
				
			}
		}
		if(cnt==5) {flag=true; return;}
	
		
	}
}

