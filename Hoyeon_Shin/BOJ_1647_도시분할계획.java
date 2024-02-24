import java.io.*;
import java.util.*;

class Road implements Comparable<Road> {
	int v1, v2;
	int cost;
	
	Road (int v1, int v2, int cost) {
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Road anotherRoad) {
		if (this.cost == anotherRoad.cost)
			return 0;
		else
			return this.cost - anotherRoad.cost;
	}
}

public class Main {
	static int N, M;
	static Road[] roads;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 도로 정보 입력
		roads = new Road[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			roads[i] = new Road(from, to, cost);
		}
		
		// 비용 기준으로 오름차순으로 도로 정렬
		Arrays.sort(roads);
		
		// union find 사용을 위해 parents 초기화
		parents = new int[N + 1];
		for (int i = 1; i <= N; ++i) {
			parents[i] = i;
		}
		
		// 사이클이 생기지 않도록 (N-2) 개의 도로를 선택하면
		// 최소 유지비를 가지는 마을 2개로 나뉜다.
		int roadCount = 0;
		int totalCost = 0;
		for (int i = 0; i < M; ++i) {
			if (roadCount == N - 2)
				break;
			
			Road cur = roads[i];
			if (find(cur.v1) == find(cur.v2))
				continue;
			
			union(cur.v1, cur.v2);
			roadCount++;
			totalCost += cur.cost;
		}
		
		System.out.println(totalCost);
	}
	
	static int find(int x) {
		if (x == parents[x])
			return x;
		
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		
		if (rootx != rooty) parents[rootx] = rooty;
	}
}
