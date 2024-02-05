import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 340ms
public class BOJ_2910_빈도정렬 {
    public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
        HashMap<Integer, Integer> cnt = new HashMap<>();
        HashMap<Integer, Integer> valToIdx = new HashMap<>();

        for(int i = 1; i <= n; i++) {
			int tmp = Integer.parseInt(st.nextToken());
            cnt.put(tmp, cnt.getOrDefault(tmp, 0) + 1);
            if (valToIdx.get(tmp) == null)
                valToIdx.put(tmp, i);
        }

        List<Map.Entry<Integer, Integer>> arr = new ArrayList<>(cnt.entrySet());
        arr.sort((a, b) -> {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue().compareTo(a.getValue());
            }
            return valToIdx.get(a.getKey()).compareTo(valToIdx.get(b.getKey()));
        });

        for (Map.Entry<Integer, Integer> entry : arr) {
            for (int i = 0; i < entry.getValue(); i++)
                System.out.print(entry.getKey() + " ");
        }
    }
}