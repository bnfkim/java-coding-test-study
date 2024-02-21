import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static int t;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			if(br.readLine().matches("(100+1+|01)+"))
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
