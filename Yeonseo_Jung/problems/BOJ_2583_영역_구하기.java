package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2583_영역_구하기 {

    // 92ms
    static final int COORDINATES_NUMBER = 4;
    static int M, N, K;
    static boolean[][] paper;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int area;
    static ArrayList<Integer> region;

    static void fill(int fromX, int fromY, int toX, int toY) {
        for (int i = M - toY; i < M - fromY; i++) {
            for (int j = fromX; j < toX; j++) {
                paper[i][j] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        paper = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int firstX = Integer.parseInt(st.nextToken());
            int firstY = Integer.parseInt(st.nextToken());
            int lastX = Integer.parseInt(st.nextToken());
            int lastY = Integer.parseInt(st.nextToken());

            fill(firstX, firstY, lastX, lastY);
        }

        getAnswer();
    }

    private static void getAnswer() {
        region = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                area = 0;
                search(i, j);
                if (area != 0) {
                    region.add(area);
                }
            }
        }
        int size = region.size();
        System.out.println(size);
        Collections.sort(region);
        for (int i = 0; i < size; i++) {
            System.out.print(region.get(i) + " ");
        }
    }

    private static void search(int x, int y) {
        if (x < 0 || x >= M || y < 0 || y >= N) {
            return;
        }

        if (paper[x][y]) {
            return;
        }

        paper[x][y] = true;
        area += 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            search(nx, ny);
        }
    }
}
