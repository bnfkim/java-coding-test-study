import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1092_배 {
    // 15116km,	329ms
    static int crainNum, boxNum;
    static int[] crains;
    static List<Integer> boxes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        crainNum = Integer.parseInt(st.nextToken());

        crains = new int[crainNum];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < crainNum; i++) {
            crains[i] = Integer.parseInt(st.nextToken());
        }

        boxes = new ArrayList<>();
        boxNum = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < boxNum; j++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        solve();

    }

    private static void solve() {
        int time = 1;
        int lastCrain = crainNum - 1;
        int lastBox = boxNum - 1;
        int idx = lastCrain;
        int bIdx = lastBox;
        Arrays.sort(crains);
        Collections.sort(boxes);

        if (boxes.get(lastBox) > crains[lastCrain]) {
            System.out.println(-1);
            return;
        }

        while (!boxes.isEmpty()) {
            int curBox = boxes.get(bIdx);
            if (crains[idx] >= curBox) {
                boxes.remove(bIdx);
                idx--;
            }
            bIdx--;

            if (idx == -1 || bIdx == -1) {
                if (boxes.isEmpty()) {
                    break;
                }
                time++;
                idx = lastCrain;
                bIdx = boxes.size() - 1;
            }
        }
        System.out.println(time);
    }
}

