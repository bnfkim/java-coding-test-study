import java.io.*;
import java.util.*;

public class BOJ_2910_빈도정렬 {
	static class Num {
		int firstIdx, cnt;

		Num(int firstIdx) {
			this.firstIdx = firstIdx;
			this.cnt = 1;
		}
	}

	static int N, C;
	static ArrayList<Integer> arr;
	static Map<Integer, Num> map;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new ArrayList<>();
		map = new HashMap<Integer, Num>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			arr.add(val);
			if (!map.containsKey(val)) {
				map.put(val, new Num(i));
			} else {
				map.get(val).cnt++;
			}
		}

		Collections.sort(arr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (map.get(o1).cnt != map.get(o2).cnt) {
					return -Integer.compare(map.get(o1).cnt, map.get(o2).cnt);
				} else {
					return Integer.compare(map.get(o1).firstIdx, map.get(o2).firstIdx);
				}
			}
		});

		StringBuilder sb = new StringBuilder();
		for (int v : arr) {
			sb.append(v).append(" ");
		}
		System.out.println(sb.toString());
	}

}
