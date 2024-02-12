package boj;

/*
 * BOJ_11505_구간 곱 구하기.java
 * [BOJ]11505/골드1/560ms/2h/김동진
 * 
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11505_구간곱구하기 {

	static long[] input;
	static long[] tree;
	
	static int N, M, K;
	
	static final long mod = 1000000007;
	
	/**
	 * 
	 * tree 를 초기화
	 * @param start		입력 데이타의 시작 index
	 * @param end		입력 데이타의 끝 index
	 * @param node		tree 의 중간 관리 값 또는 input 데이타를 저장할 index
	 * 					start == end -> leaf node(tree[node]에 input[start] 데이타를 저장)
	 * 					start != end -> 중간 node 이므로 관리값, start ~ end 까지의 관리 값이 저장됨
	 * @return
	 */
	
	public static long init(int start, int end, int node) {
		//System.out.println(" 현재 node : " + node);
		if(start == end) { // leaf node
			return tree[node] = input[start];
		}
		
		// 중간 node 이므로, 하위 두 노드의 관리할 값을 처리
		int mid = (start + end) >> 1;		// 나누기 2보다 시프트 이동이 더 빠름

		//return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
		return tree[node] = init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1) % mod;
	}
	
	/**
	 * 구간 합
	 * @param start		트리 관리 값의 시작 index
	 * @param end		트리 관리 값의 끝 index
	 * @param node		tree index
	 * @param left		구간 합을 구할 시작 index
	 * @param right		구간 합을 구할 끝 index
	 * @return
	 */
	
	public static long sum(int start, int end, int node, int left, int right) {
		
		// 범위 밖
		if(left > end || right < start) {
			return 0;
		}
		
		// 구간 합을 구할 범위 내
		if(left <= start && end <= right) {
			System.out.println(tree[node] + " 더함 ! ");
			return tree[node];
		}
		
		// 탐색할 데이터가 start ~ end 사이에 있지만 딱 맞는 데이타가 아니므로 탐색
		int mid = (start + end) >> 1;
		return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
		
	}
	
	public static long mul(int start, int end, int node, int left, int right) {
		
		// 범위 밖
		if(left > end || right < start) {
			return 1;
		}
		
		// 구간 합을 구할 범위 내
		if(left <= start && end <= right) {
			//System.out.println(tree[node] + " 더함 ! ");
			return tree[node];
		}
		
		// 탐색할 데이터가 start ~ end 사이에 있지만 딱 맞는 데이타가 아니므로 탐색
		int mid = (start + end) >> 1;
		return mul(start, mid, node*2, left, right) * mul(mid+1, end, node*2+1, left, right) % mod;
		
	}
	
	/**
	 * update
	 * @param start		트리 관리 값의 시작 index
	 * @param end		트리 관리 값의 끝 index
	 * @param node		tree index
	 * @param index		update 할 input 데이터의 index
	 * @param diff		update 할 데이터의 차이 값
	 */
	
	
	/* 리프노드에서 루트로 올라가면서 갱신 */
	public static long update(int start, int end, int node, int index, long next) {
		
		if(index < start || index > end) {		// 범위 밖
			return tree[node];
		}
		
		
		// update 할 범위 내에 있는 node	

		if(start == end) { 			// 단말 노드
			return tree[node] = next;
		}
		
		
		// 하위 노드 update
		int mid = (start + end) >> 1;
		return tree[node] = update(start, mid, node * 2, index, next) * update(mid + 1, end, node * 2 + 1, index, next) % mod;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//int N, M, K;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new long[N + 1];
		tree = new long[N << 2];
		
		for(int i = 1; i <= N; i++) {
			input[i] = Long.parseLong(br.readLine());
		}
		
		// 트리 생성
		init(1, N, 1);

		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			
			if(a == 1) {
				update(1, N, 1, b, c);
				input[b] = c;
			}else if(a == 2) {
				//System.out.println("Result : " + mul(1, N, 1, b, (int)c));
				sb.append(mul(1, N, 1, b, (int) c)).append("\n");
			}
			
		}
		System.out.println(sb);
	}
}
