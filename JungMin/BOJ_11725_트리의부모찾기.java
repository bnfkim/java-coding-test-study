package algorithm;

import java.util.*;

public class 트리의부모찾기11725 {
	static int N;
	public static void main(String[] args)
	{
		
		Scanner sc= new Scanner(System.in);
		
		
		N=sc.nextInt();
		
		ArrayList<ArrayList<Integer>> tree =new ArrayList<>();
		for(int i=0;i<N;i++)
		{
			tree.add(new ArrayList<>());
		}

		
//		[ [] [4,6] [4] [5] [1,2,7] [3] [] [1,6] [4] ]
	
		for(int i=0;i<N-1;i++)
		{

