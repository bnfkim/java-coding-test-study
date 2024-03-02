package 알고리즘연습.boj;

import java.io.*;
import java.util.*;

/**
 * @algorithm simulation
 * @time O(N^2) -> 196 ms
 * @memory O(N^2) -> 16132 KB
 */
public class BOJ_21609_상어중학교 {
    private static int N, M, ans;
    private static int[][] map;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    private static boolean[][] visited;
    private static int rainbowCnt;
    private static int groupSize;
    private static final int BLANK = -2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        while (bomb()) {
            gravity();
            turnLeft();
            gravity();
        }

        sb.append(ans);
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    /**
     * 블록 그룹이 있는지 탐색
     * -> 있으면, 가장 큰 블록 그룹을 터뜨림
     */
    private static boolean bomb() {
        // 먼저 배열 순회하면서 블록 그룹 대표 블록을 물색 (우선순위큐 사용)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> { // 대표 블록 담는 큐
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1]; // 열 오름차순 (대표블록은 가장 작은 블록)
            }
            return o1[0] - o2[0]; // 행 오름차순
        });

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    pq.add(new int[]{i, j, map[i][j]}); // (y,x,block color)
                }
            }
        }

        // 폭파그룹 선택시 필요한 변수 선언 / 초기화
        int bombGroupSize = -1;
        int bombRainbowCnt = -1;
        int bombY = -1;
        int bombX = -1;
        if (pq.isEmpty()) { // 만약 대표 블록이 될 일반 블록이 없는 경우는 -> 오토플레이 불가
            return false;
        }

        visited = new boolean[N][N];
        // PQ에서 하나씩 꺼내서 dfs 돌면서 (사이즈 + 무지개블록 개수) 카운트하고
        while (!pq.isEmpty()) {
            int[] bossBlock = pq.poll(); // 대표 블록 큐

            int y = bossBlock[0];
            int x = bossBlock[1];
            int color = bossBlock[2];
            initRainbowVisit();

            // 해당 블록이 탐색 가능하면 -> 탐색 시작
            if (!visited[y][x]) {
                rainbowCnt = 0;
                groupSize = 0;
                visited[y][x] = true;
                dfs(y, x, color);

                // 매번 비교하면서 폭파할 블록 선택 (대표블록 y, x 저장/갱신)
                // 필요시 폭파대상그룹 갱신
                if (groupSize > bombGroupSize) { // 이번에 탐색한 그룹이 더 크면
                    bombGroupSize = groupSize;
                    bombRainbowCnt = rainbowCnt;
                    bombY = y;
                    bombX = x;
                } else if (groupSize == bombGroupSize) { // 만약 사이즈가 같으면
                    if (rainbowCnt > bombRainbowCnt) { // 무지개블록 개수가 더 많으면
                        bombGroupSize = groupSize;
                        bombRainbowCnt = rainbowCnt;
                        bombY = y;
                        bombX = x;
                    } else if (rainbowCnt == bombRainbowCnt) { // 무지개블록 개수도 같으면
                        if (bombY < y) {
                            bombGroupSize = groupSize;
                            bombRainbowCnt = rainbowCnt;
                            bombY = y;
                            bombX = x;
                        } else if (bombY == y) {
                            if (bombX < x) {
                                bombGroupSize = groupSize;
                                bombRainbowCnt = rainbowCnt;
                                bombY = y;
                                bombX = x;
                            }
                        }
                    }
                }
            }
        }
        if (bombGroupSize < 2) { // 사이즈가 2가 안되면 stop
            return false;
        }
        // 폭파할 블록의 대표블록을 기준으로 dfs 돌면서 폭파 (바로 map에 업데이트)
        visited = new boolean[N][N];
        if (bombY != -1 && bombX != -1) {
            groupSize = 0;
            visited[bombY][bombX] = true;
            exec(bombY, bombX, map[bombY][bombX]);
        }
        ans += (int) Math.pow(groupSize, 2);
        return true;
    }

    private static void exec(int y, int x, int color) {
        map[y][x] = BLANK;
        groupSize++;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (canGo(ny, nx, color)) {
                visited[ny][nx] = true;
                exec(ny, nx, color);
            }
        }
    }

    private static void dfs(int y, int x, int color) {
        groupSize++;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (canGo(ny, nx, color)) {
                if (map[ny][nx] == 0) { // 만약 탐색대상이 rainbow블록이면
                    rainbowCnt++;
                }
                visited[ny][nx] = true;
                dfs(ny, nx, color);
            }
        }
    }

    private static void initRainbowVisit() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    visited[i][j] = false;
                }
            }
        }
    }

    /**
     * 배열에 중력을 적용
     */
    private static void gravity() {
        int[] temp = new int[N];

        for (int x = 0; x < N; x++) {
            int gravityIdx = 0;
            Arrays.fill(temp, BLANK);
            for (int y = N - 1; y >= 0; y--) {
                if (map[y][x] == BLANK) {
                    continue;

                    // 만약 검정 블록을 만나면, gravityIdx를 검정 블록 위치로 갱신하고 그 자리에 입력
                } else if (map[y][x] == -1) {
                    gravityIdx = N - y - 1;
                }
                temp[gravityIdx++] = map[y][x];
            }
            // 중력 적용상황 업데이트
            for (int i = 0; i < N; i++) {
                map[N - i - 1][x] = temp[i];
            }

        }
    }

    /**
     * 배열을 반시계 방향으로 90도 회전시킴
     */
    private static void turnLeft() {
        // 단순함. y가 x로 되면 됨.
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N - j - 1][i] = map[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            map[i] = temp[i].clone();
        }
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    private static boolean canGo(int y, int x, int blockType) {
        return inRange(y, x) && canGoBlock(y, x, blockType);
    }

    private static boolean canGoBlock(int y, int x, int blockType) {
        return map[y][x] != -1 && ((map[y][x] == 0 || map[y][x] == blockType) && !visited[y][x]); // 방문X면서 색깔이 같거나 or 무지개블록이거나
    }
}
