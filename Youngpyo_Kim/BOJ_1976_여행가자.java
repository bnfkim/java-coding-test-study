import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static boolean map[][];	
	static int[] parents;
	static int[] route;
//test
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		map = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					map[i][j] = true;
			}
		}

		init();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == true) {
					union(i,j);
				}
			}
		}

		route = new int[m];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < m; i++) {
			route[i] = Integer.parseInt(st.nextToken()); 
		}

		for(int i = 1; i < m; i++) {
			if(find(route[i-1]-1) != find(route[i]-1)) {
				System.out.println("NO");
				return;
			}
		}

		System.out.println("YES");

		return;
	}

	public static void init() {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	public static int find(int v) {
		if( v == parents[v]) return v;
		return parents[v] = find(parents[v]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
}
