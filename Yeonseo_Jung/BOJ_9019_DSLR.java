import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9019_DSLR {
    static int N, src, tar;
    static String[] cmds = {"D", "S", "L", "R"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            tar = Integer.parseInt(st.nextToken());

            String result = getMinSol(src, tar);
            System.out.println(result);
        }
    }

    private static String getMinSol(int src, int tar) {
        Map<Integer, String> cmdsMap = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(src);
        cmdsMap.put(src, "");

        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (num == tar)
                return cmdsMap.get(num);

            for (String cmd : cmds) {
                int nextNum = getNextNum(num, cmd);
                if (!cmdsMap.containsKey(nextNum)) {
                    queue.offer(nextNum);
                    cmdsMap.put(nextNum, cmdsMap.get(num) + cmd);
                }
            }
        }
        return "";
    }

    private static int getNextNum(int num, String cmd) {
        switch (cmd) {
            case "D":
                return (num * 2) % 10000;
            case "S":
                return num == 0 ? 9999 : num - 1;
            case "L":
                return (num % 1000) * 10 + num / 1000;
            case "R":
                return (num / 10) + (num % 10) * 1000;
            default:
                return -1;
        }
    }
}