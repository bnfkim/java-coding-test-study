package algo;

import java.io.*;
import java.util.*;

class Node {
	int v;
	Node next;
	Node(int v, Node next) {
		this.v = v;
		this.next = next;
	}
}

// 20928KB  232ms
public class Main {
	static int N;
	static int[] population;
	static Node[] adjList;
	
	static int[] dpGood;  // dpGood[r]: r을 root로 하는 서브트리에서, r을 우수마을로 선택했을 때의 우수마을 인구수 최대값
	static int[] dpNormal;  // dpNormal[r]: r을 root로 하는 서브트리에서, r을 우수마을로 선택하지 않았을 때 우수마을 인구수 최대값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		adjList = new Node[N + 1];
		dpGood = new int[N + 1];
		dpNormal = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i)
			population[i] = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			adjList[A] = new Node(B, adjList[A]);
			adjList[B] = new Node(A, adjList[B]);
		}
		
		boolean[] visited = new boolean[N + 1];
		search(1, visited);
		System.out.println(dpNormal[1] > dpGood[1] ? dpNormal[1] : dpGood[1]);
	}
	
	// r을 root로 하는 트리의 우수마을 인구수 최대값을 구한다.  
	public static void search(int r, boolean[] visited) {
		if (visited[r]) return;
		visited[r] = true;
		
		for (Node cur = adjList[r]; cur != null; cur = cur.next) {
			if (visited[cur.v]) continue;  // 부모노드 건너뛰기
			if (dpGood[cur.v] == 0) search(cur.v, visited);  // 자식 노드의 dp를 아직 찾지 않았다면 찾기
			
			 dpGood[r] += dpNormal[cur.v];
			 dpNormal[r] += dpNormal[cur.v] > dpGood[cur.v] ? dpNormal[cur.v] : dpGood[cur.v];
		}
		
		dpGood[r] += population[r];
	}
}
