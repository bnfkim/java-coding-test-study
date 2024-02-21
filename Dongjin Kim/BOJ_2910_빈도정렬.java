package boj;

/*
 * BOJ_2910_빈도정렬.java
 * [BOJ]2910/실버3/144ms/1h/김동진
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2910_S3_빈도정렬_김동진 {

	static int N, C;
	static int[] arr, num;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> map = new LinkedHashMap<>();

		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			}else {
				map.put(num, 1);
			}
		}
		
		
		List<Integer> key_set = new ArrayList<>(map.keySet());
		
		Collections.sort(key_set, new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {

				return Integer.compare(map.get(o2), map.get(o1));
			}
		});
		
		Iterator<Integer> it = key_set.iterator();
		while(it.hasNext()) {
			int k = it.next();
			for(int i = 0; i < map.get(k); i++) {
				sb.append(k).append(" ");
			}
		}
		System.out.println(sb);
		
	}
}
