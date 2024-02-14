import java.io.*;

public class BOJ_1013_Contact {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			sb.append(br.readLine().matches("(100+1+|01)+") ? "YES\n" : "NO\n");
		}

		System.out.println(sb.toString());
	}
}
