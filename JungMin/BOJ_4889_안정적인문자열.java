package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 문자열4889 {

	public static void main(String[] args) throws IOException{

		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		

		int idx=1;
		while(true)
		{
			
			String str= bf.readLine();
		
			if(str.contains("-"))
			{
				break;
			}
			
			Stack<Character> st = new Stack<>();
			int cnt=0;
			for(int i=0;i<str.length();i++)
			{
				char ch = str.charAt(i);
				
				if(ch=='{')
				{
					st.add(ch);
				}
				else
				{
					if(st.empty())
{
						cnt++;
						st.add('{');
						continue;
}
					else
					{
						st.pop();
					}
				}
				
			}
			if(!st.isEmpty())
			{
				cnt+=st.size()/2;
			}
			System.out.println(idx+". "+cnt);
			idx++;
			
		}
		
	}

}

