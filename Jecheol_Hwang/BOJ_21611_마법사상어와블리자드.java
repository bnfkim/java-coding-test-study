package 알고리즘연습.boj;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @intuition 
 * 바라는 게 많은 시뮬레이션 문제. 길을 잃지 않도록 변수명을 잘 선언하고, 로직을 잘 구성한 뒤 코드를 작성해야 할듯.
 * 1. 탐색은 정직하게 달팽이탐색으로 구현할 것.
 * 2. pull 또는 stretch 과정에서 temp 배열 인덱스를 복잡하게 운영하는 것보다는 1차원으로 차곡차곡 쌓아두고, 이를 고대로 다시 달팽이탐색으로 복붙하는게 편할 듯
 * 3. 연속되는 구슬을 터뜨리기 위해 좌표를 저장하는 건, 나중에 길이를 체크하고 되돌아가서 하나하나 터뜨리는 논리이므로 stack 자료구조가 적절할 듯
 *
 * @algorithm simulation
 * @time O(N^4) : N^2 탐색 * 최악의 경우 stack에 N*N개 채워진 상태로 뒤로감기 -> 480 ms
 * @memory O(N^2) : map 저장 * N*N개의 elem 저장하는 stack 운영 -> 77584 KB
 */
public class BOJ_21611_마법사상어와블리자드 {
    private static int N, M, tdx;
    private static int[][] map;
    private static int[] temp;
    private static int oneTotal, twoTotal, threeTotal;
    private static int shark;
    private static int[] dy = {0, -1, 1, 0, 0}; // 상하좌우 1,2,3,4 동치
    private static int[] dx = {0, 0, 0, -1, 1};
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    private static Deque<int[]> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        shark = (N+1) / 2;
        map = new int[N+1][N+1];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            blizzard(d, s, 0, shark, shark);
            do {
                pull();
            } while (bomb());
            stretch();
        }

        sb.append(oneTotal + 2*twoTotal + 3*threeTotal);
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean inRange(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N;
    }
    private static void blizzard(int d, int s, int depth, int y, int x) {
        if (depth == s) {
            return;
        }
        int ny = y + dy[d];
        int nx = x + dx[d];

        if (inRange(ny, nx)) {
            // 터뜨림
            map[ny][nx] = 0;
            blizzard(d, s, depth + 1, ny, nx);
        }
    }

    private static boolean bomb() {
        stack = new ArrayDeque<>();
        int y = shark;
        int x = shark;
        int peek = -1;
        boolean isBomb = false;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                y += dy[LEFT];
                x += dx[LEFT];
                if (inRange(y, x)) {
                    if (peek == map[y][x]) {
                        stack.push(new int[]{y, x});
                    } else {
                        if (stack.size() >= 4) {
                            isBomb = true;
                            // 점수 계산
                            if (peek == 1) {
                                oneTotal += stack.size();
                            } else if (peek == 2) {
                                twoTotal += stack.size();
                            } else if (peek == 3) {
                                threeTotal += stack.size();
                            }

                            // 파괴
                            while (!stack.isEmpty()) {
                                int[] pos = stack.pop();
                                map[pos[0]][pos[1]] = 0;
                            }
                        } else {
                            // 4연속을 충족하지 못하는 구슬은 pass
                            stack = new ArrayDeque<>();
                        }
                        // 지금 꺼는 넣고 다시 시작
                        stack.push(new int[]{y, x});
                        peek = map[y][x];
                    }
                }
            }
            for (int j = 1; j <= i; j++) {
                y += dy[DOWN];
                x += dx[DOWN];
                if (inRange(y, x)) {
                    if (peek == map[y][x]) {
                        stack.push(new int[]{y, x});
                    } else {
                        if (stack.size() >= 4) {
                            isBomb = true;
                            // 점수 계산
                            if (peek == 1) {
                                oneTotal += stack.size();
                            } else if (peek == 2) {
                                twoTotal += stack.size();
                            } else if (peek == 3) {
                                threeTotal += stack.size();
                            }

                            // 파괴
                            while (!stack.isEmpty()) {
                                int[] pos = stack.pop();
                                map[pos[0]][pos[1]] = 0;
                            }
                        } else {
                            // 4연속을 충족하지 못하는 구슬은 pass
                            stack = new ArrayDeque<>();
                        }
                        // 지금 꺼는 넣고 다시 시작
                        stack.push(new int[]{y, x});
                        peek = map[y][x];
                    }
                }
            }
            i++;
            for (int j = 1; j <= i; j++) {
                y += dy[RIGHT];
                x += dx[RIGHT];
                if (inRange(y, x)) {
                    if (peek == map[y][x]) {
                        stack.push(new int[]{y, x});
                    } else {
                        if (stack.size() >= 4) {
                            isBomb = true;
                            // 점수 계산
                            if (peek == 1) {
                                oneTotal += stack.size();
                            } else if (peek == 2) {
                                twoTotal += stack.size();
                            } else if (peek == 3) {
                                threeTotal += stack.size();
                            }

                            // 파괴
                            while (!stack.isEmpty()) {
                                int[] pos = stack.pop();
                                map[pos[0]][pos[1]] = 0;
                            }
                        } else {
                            // 4연속을 충족하지 못하는 구슬은 pass
                            stack = new ArrayDeque<>();
                        }
                        // 지금 꺼는 넣고 다시 시작
                        stack.push(new int[]{y, x});
                        peek = map[y][x];
                    }
                }
            }
            for (int j = 1; j <= i; j++) {
                y += dy[UP];
                x += dx[UP];
                if (inRange(y, x)) {
                    if (peek == map[y][x]) {
                        stack.push(new int[]{y, x});
                    } else {
                        if (stack.size() >= 4) {
                            isBomb = true;
                            // 점수 계산
                            if (peek == 1) {
                                oneTotal += stack.size();
                            } else if (peek == 2) {
                                twoTotal += stack.size();
                            } else if (peek == 3) {
                                threeTotal += stack.size();
                            }

                            // 파괴
                            while (!stack.isEmpty()) {
                                int[] pos = stack.pop();
                                map[pos[0]][pos[1]] = 0;
                            }
                        } else {
                            // 4연속을 충족하지 못하는 구슬은 pass
                            stack = new ArrayDeque<>();
                        }
                        // 지금 꺼는 넣고 다시 시작
                        stack.push(new int[]{y, x});
                        peek = map[y][x];
                    }
                }
            }
        }
        return isBomb;
    }
    private static void pull() {
        temp = new int[N * N];
        
        copy();
        paste();
    }

    private static void paste() {
        tdx = 0;
        int y = shark;
        int x = shark;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                y += dy[LEFT];
                x += dx[LEFT];
                if (inRange(y, x)) {
                    map[y][x] = temp[tdx++];
                }
            }
            for (int j = 1; j <= i; j++) {
                y += dy[DOWN];
                x += dx[DOWN];
                if (inRange(y, x)) {
                    map[y][x] = temp[tdx++];
                }
            }
            i++;
            for (int j = 1; j <= i; j++) {
                y += dy[RIGHT];
                x += dx[RIGHT];
                if (inRange(y, x)) {
                    map[y][x] = temp[tdx++];
                }
            }
            for (int j = 1; j <= i; j++) {
                y += dy[UP];
                x += dx[UP];
                if (inRange(y, x)) {
                    map[y][x] = temp[tdx++];
                }
            }
        }
    }

    private static void copy() {
        tdx = 0;
        int y = shark;
        int x = shark;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                y += dy[LEFT];
                x += dx[LEFT];

                if (inRange(y, x) && map[y][x] > 0) {
                    temp[tdx++] = map[y][x];
                }
            }
            for (int j = 1; j <= i; j++) {
                y += dy[DOWN];
                x += dx[DOWN];

                if (inRange(y, x) && map[y][x] > 0) {
                    temp[tdx++] = map[y][x];
                }
            }
            i++; // 복사 길이 늘림 for 달팽이탐색
            for (int j = 1; j <= i; j++) {
                y += dy[RIGHT];
                x += dx[RIGHT];

                if (inRange(y, x) && map[y][x] > 0) {
                    temp[tdx++] = map[y][x];
                }
            }
            for (int j = 1; j <= i; j++) {
                y += dy[UP];
                x += dx[UP];

                if (inRange(y, x) && map[y][x] > 0) {
                    temp[tdx++] = map[y][x];
                }
            }
        }
    }
    private static void stretch() {
        tdx = 0;
        temp = new int[N * N];
        int peek = -1;
        int cnt = 0;

        int y = shark;
        int x = shark;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                y += dy[LEFT];
                x += dx[LEFT];
                if (inRange(y, x)) {
                    if (peek == map[y][x]) {
                        cnt++;
                    } else {
                        if (cnt > 0) {
                            if (tdx < N * N) {
                                temp[tdx++] = cnt;
                            }
                            if (tdx < N * N) {
                                temp[tdx++] = peek;
                            }
                        }
                        cnt = 0;
                        cnt++;
                        peek = map[y][x];
                    }
                }
            }
            for (int j = 1; j <= i; j++) {
                y += dy[DOWN];
                x += dx[DOWN];
                if (inRange(y, x)) {
                    if (peek == map[y][x]) {
                        cnt++;
                    } else {
                        if (cnt > 0) {
                            if (tdx < N * N) {
                                temp[tdx++] = cnt;
                            }
                            if (tdx < N * N) {
                                temp[tdx++] = peek;
                            }
                        }
                        cnt = 0;
                        cnt++;
                        peek = map[y][x];
                    }
                }
            }
            i++;
            for (int j = 1; j <= i; j++) {
                y += dy[RIGHT];
                x += dx[RIGHT];
                if (inRange(y, x)) {
                    if (peek == map[y][x]) {
                        cnt++;
                    } else {
                        if (cnt > 0) {
                            if (tdx < N * N) {
                                temp[tdx++] = cnt;
                            }
                            if (tdx < N * N) {
                                temp[tdx++] = peek;
                            }
                        }
                        cnt = 0;
                        cnt++;
                        peek = map[y][x];
                    }
                }
            }
            for (int j = 1; j <= i; j++) {
                y += dy[UP];
                x += dx[UP];
                if (inRange(y, x)) {
                    if (peek == map[y][x]) {
                        cnt++;
                    } else {
                        if (cnt > 0) {
                            if (tdx < N * N) {
                                temp[tdx++] = cnt;
                            }
                            if (tdx < N * N) {
                                temp[tdx++] = peek;
                            }
                        }
                        cnt = 0;
                        cnt++;
                        peek = map[y][x];
                    }
                }
            }
        }
        paste();
    }
}
