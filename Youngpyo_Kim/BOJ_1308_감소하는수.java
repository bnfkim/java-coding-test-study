import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Long> v = new ArrayList<>();

    public static void bt(long idx, int cnt) {
        if (cnt > 10)
            return;

        v.add(idx);

        for (int i = 0; i < 10; i++) {
            if (idx % 10 > i)
                bt(idx * 10 + i, cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10; i++)
            bt(i, 1);

        Collections.sort(v);

        if (v.size() <= n)
            System.out.println(-1);
        else
            System.out.println(v.get(n));
    }
}
