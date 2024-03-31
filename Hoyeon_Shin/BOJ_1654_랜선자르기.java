import java.io.*;
import java.util.*;

/**
 * 
 * 17456KB	208ms
 *
 */
public class Main {
	static int K, N;
	static long[] lanLine;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		
		K = Integer.parseInt(line[0]);
		N = Integer.parseInt(line[1]);
		
		lanLine = new long[K];
		for (int i = 0; i < K; ++i) {
			lanLine[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(lanLine);
		
		// 최대 랜선의 길이를 max라고 하자.
		// max로 잘랐을 때 n개가 안나오면 줄이고, n개 이상 나오면 늘려본다.
		long start = 1;
		long end = lanLine[lanLine.length - 1];
		while (start <= end) {
			long mid = (start + end) / 2;
			long curCnt = count(mid);
			
			// N개 이상 나온다면 길이를 늘려 시도한다.
			if (curCnt >= N) {
				start = mid + 1;
			} 
			
			// N개 미만으로 나온다면 길이를 줄여본다.
			else {
				end = mid - 1;
			}
		}
		
		System.out.println(end);
	}
	
	// lanLine을 cut 만큼으로 잘랐을 때 몇개가 나올 수 있는지 계산해 반환한다.
	public static long count(long cut) {
		long result = 0;
		for (int i = 0, size = lanLine.length; i < size; ++i) {
			result += lanLine[i] / cut;
		}
		return result;
	}
}
