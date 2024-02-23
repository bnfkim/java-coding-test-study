import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution{
	static int n, m;
	static int parents[];
	static Edge edges[];
	static ArrayList<Edge> res = new ArrayList<>();
	
	public static class Edge implements Comparable<Edge>{
		int s, e, w;
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		@Override
		public String toString() {
			return "Edge [s=" + s + ", e=" + e + ", w=" + w + "]";
		}
	}
	
	public static void make() {
		parents = new int[n+1];
		for(int i = 1; i <= n; i++)
			parents[i] = i;
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		parents[b] = a;
		return true;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        edges = new Edge[m];
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	edges[i] = new Edge(a,b,c);
        }
        
        Arrays.sort(edges);
        
        make();
        for(int i = 0; i < m; i++) {
        	if(!union(edges[i].s, edges[i].e)) continue;
        	res.add(edges[i]);
        	if(res.size() == n-1)
        		break;
        }
        
        int ans = 0;
        for(int i = 0; i < res.size()-1; i++) {
        	ans += res.get(i).w;        	
        }
        System.out.println(ans);
    }
}
