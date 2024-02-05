package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BOJ_2910_빈도정렬 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, int[]> hm = new HashMap<Integer,int[]>();
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int n = Integer.parseInt(st.nextToken());
			int[] tmp = hm.get(n);
			if(tmp==null) {
				int[] t = {1,i};
				hm.put(n, t);
			}else {
				hm.get(n)[0]++;
			}
		}
		int[][] arr = new int[hm.size()][3];
		int idx=0;
		for(Integer key : hm.keySet()) {
			arr[idx][0]=key;
			int[] tmp = hm.get(key);
			arr[idx][1]=tmp[0];
			arr[idx][2]=tmp[1];
			idx++;
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]!=o2[1]) {
					return Integer.compare(o2[1], o1[1]);
				}else {
					return Integer.compare(o1[2], o2[2]);
				}
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i=0,size=arr.length;i<size;i++) {
			for(int j=0;j<arr[i][1];j++) {
				sb.append(arr[i][0]+" ");
			}
		}
		System.out.println(sb);

	}

}
