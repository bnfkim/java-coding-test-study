import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		List<Integer> nums = new ArrayList<>();  // 입력되는 숫자 저장
		Map<Integer, Integer> counter = new HashMap<>();  // 숫자의 빈도수 저장
		Map<Integer, Integer> index = new HashMap<>();  // 처음 등장한 인덱스 저장
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			int key = Integer.parseInt(st.nextToken());
			
			if (!index.containsKey(key))
				index.put(key, i);
			
			counter.put(key, counter.getOrDefault(key, 0) + 1);
			nums.add(key);
		}
		
		// nums 를 counter의 빈도수를 참고해 정렬
		// 만약 빈도수가 같다면 먼저 등장한 순으로 정렬
		Collections.sort(nums, new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				if (counter.get(i1) == counter.get(i2)) {
					return index.get(i1) - index.get(i2);
				}
				return counter.get(i2) - counter.get(i1);
			}
		}); 
		
		// 결과 출력
		for (Integer num: nums) {
			System.out.print(num + " ");
		}
	}
}
