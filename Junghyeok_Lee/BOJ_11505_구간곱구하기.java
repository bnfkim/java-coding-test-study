package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11505_구간곱구하기 {
	static int N,M,K;
	static long[] input;
	static long[] tree;
	
	public static long init(int start, int end, int node) {
		if( start == end) {	//leaf 노드 
			return tree[node] = input[start];
		}
		//중간 노드이므로 하위 두 노드의 관리할 값을 처리 
		int mid  = (start+end)>>1;
		return  tree[node] = (init(start, mid, node*2) * init(mid+1, end, node*2+1))%1000000007;
	}
	
	public static long gob(int start, int end, int node, int left, long right) {
		//범위 밖
		if(left>end || right < start) {
			return 1;
		}
		//구간 합을 구할 범위 내
		if(left <=start && end <=right) {
			return tree[node];
		}
		//탐색할 데이터가 start~end 사이에 있지만 딱 맞는 데이타가 아니므로 탐색
		int mid = (start+end)>>1;
		return (gob(start, mid, node*2, left, right)*gob(mid+1,end,  node*2+1, left, right))%1000000007;
		
	}

	
	public static long update(int start, int end, int node, int index, int diff) {
		if( index< start || index > end) {
			return tree[node];
		}

		if( start == end ) {			
			input[index]=diff;
			return tree[node]=input[index];
		}
		
		int mid = (start+end)>>1;
		return tree[node] = (update(start, mid, node*2, index, diff)*update(mid+1, end, node*2+1, index, diff))%1000000007;
		
	} 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		input = new long[N];
		tree = new long[N<<2];
		
		for(int i=0;i<N;i++) {
			input[i] = Long.parseLong(br.readLine());
		}
		
		for(int i=0;i<N<<2;i++) {
			tree[i] = 1;
		}
		
		init(0,N-1,1);
		StringBuilder  sb = new StringBuilder(K*3);
		for(int i=0;i<M+K;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==1) {
				update(0,N-1,1,b-1,c);
			}else if(a==2) {
				sb.append(gob(0,N-1,1,b-1,c-1)+"\n");
			}
		}
		System.out.println(sb);
		
	}

}
