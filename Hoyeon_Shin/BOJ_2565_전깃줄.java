import java.io.*;
import java.util.*;

class Line {
	int a;
	int b;
	Line(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

public class Main {
	static int N;  // 전깃줄 개수
	static Line[] lines;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		lines = new Line[N];
		
		// 전기줄 입력
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			lines[i] = new Line(a, b);
		}
		
		// a에 연결된 위치를 기준으로 전선 정렬
		Arrays.sort(lines, (l1, l2) -> {
			return l1.a - l2.a;
		});
		
		// seq[i] : line[i] 까지의 가장 긴 증가하는 부분수열 길이
		int[] seq = new int[N];
		int maxSeq = 0;
		for (int i = 0; i < N; ++i) {
			seq[i] = 1;
			
			for (int j = 0; j < i; ++j) {
				if (lines[j].b < lines[i].b) {
					seq[i] = Math.max(seq[j] + 1, seq[i]);
				}
			}
			
			maxSeq = Math.max(maxSeq, seq[i]);
		}
		
		System.out.println(N - maxSeq);
	}
}
