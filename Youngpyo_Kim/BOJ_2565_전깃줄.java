import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static Pole[] poles;
	public static class Pole{
		int s, e;
		Pole(int s, int e){
			this.s = s;
			this.e = e;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		poles = new Pole[n];
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			poles[i] = new Pole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(poles, new Comparator<Pole>(){
			@Override
			public int compare(Pole a, Pole b) {
				if(a.s != b.s)
					return a.s - b.s;
				else
					return a.e - b.s;
			}
		});
		
		int[] tmp = new int[n];
		for(int i = 0; i < n; i++) {
			tmp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(poles[i].e > poles[j].e) { 
					tmp[i] = Math.max(tmp[i], tmp[j]+1);
				}				
			}
		}
		
		int ans = 0;
		for(int i = 0; i < poles.length; i++) {
			ans = Math.max(ans, tmp[i]);
		}
		
		System.out.println(n-ans);
	}
}


