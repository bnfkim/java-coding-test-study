package Baekjoon;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/*
시작 시간: 24-02-05 14:30
종료 시간: 24-02-05 15:15
실행 시간: 240ms
메모리: 18556KB

고려사항
1. 전체 숫자를 순서대로 순회하며, 각 숫자가 나타난 첫번째 인덱스, 빈도수, 숫자값을 저장한다
2. 이후 각 숫자를 빈도수와 첫번째 등장 인덱스를 기준으로 sorting한다
3. sorting된 배열을 순회하며, 해당 숫자를 빈도수만큼 반복하여 출력한다

첫번째 등장 인덱스와, 빈도수를 저장해 두는 것이 주요 포인트였습니다.
Number객체가 같다는 것을 숫자의 값이 같은 것으로 판단하기 위해 equals를 오버라이딩하였습니다.
*/

public class BOJ_2910_빈도정렬_S3 {

	static class Number{
		int freq;
		int num;
		int initial;
		
		public Number(int initial, int freq, int num) {
			super();
			this.freq = freq;
			this.num = num;
			this.initial = initial;
		}
		@Override
			public boolean equals(Object obj) {
				return this.num == ((Number)obj).num;
			}
		
	}
	
	static int N, C;
	static int[] input;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		
		List<Number> arr = new LinkedList<Number>();
		
		for(int i = 0; i < N; i++) {
			Number number = new Number(i, 1, input[i]);
			
			if(arr.contains(number)) {
				arr.get(arr.indexOf(number)).freq++;
			}else {
				arr.add(number);
			}
		}
		
		Collections.sort(arr, (o1, o2)->{
			if(o1.freq == o2.freq) return o1.initial - o2.initial;
			return o2.freq - o1.freq; 
		});
		
		StringBuilder sb = new StringBuilder();
		for(Number n : arr) {
			for(int i = 0; i < n.freq; i++)
				sb.append(n.num).append(' ');
		}
		
		System.out.print(sb);
	}

}