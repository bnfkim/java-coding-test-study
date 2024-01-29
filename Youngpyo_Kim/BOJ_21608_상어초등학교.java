import java.io.*;
import java.util.*;

class BOJ_21608_상어초등학교 {
    static int[][] board = new int[21][21];
    static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[] score = {0, 1, 10, 100, 1000};
    static HashMap<Integer, ArrayList<Integer>> friendship;
    static int n, ans = 0;

    static class Info {
        int close, space, r, c;

        Info(int close, int space, int r, int c) {
            this.close = close;
            this.space = space;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[21][21];
        friendship = new HashMap<>();

        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            friendship.put(cur, new ArrayList<>(Arrays.asList(a, b, c, d)));
            findPlace(cur, a, b, c, d);
        }

        calc();
        System.out.println(ans);
    }

    static void findPlace(int cur, int a, int b, int c, int d) {
        ArrayList<Info> possible = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    int empty = 0, close = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + delta[k][0], nc = j + delta[k][1];
                        if (nr < 0 || nr >= n || nc < 0 || nc >= n)
                            continue;
                        if (board[nr][nc] == 0)
                            empty++;
                        else if (board[nr][nc] == a || board[nr][nc] == b || board[nr][nc] == c || board[nr][nc] == d)
                            close++;
                    }
                    possible.add(new Info(close, empty, i, j));
                }
            }
        }

        Collections.sort(possible, new Comparator<Info>() {
            @Override
            public int compare(Info a, Info b) {
                if (a.close != b.close)
                    return b.close - a.close;
                else if (a.space != b.space)
                    return b.space - a.space;
                else if (a.r != b.r)
                    return a.r - b.r;
                else
                    return a.c - b.c;
            }
        });
        
        Info res = possible.get(0);
        board[res.r][res.c] = cur;
    }

    static void calc() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                ArrayList<Integer> list = friendship.get(board[i][j]);
                int a = list.get(0), b = list.get(1), c = list.get(2), d = list.get(3);

                for (int k = 0; k < 4; k++) {
                    int nr = i + delta[k][0], nc = j + delta[k][1];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n)
                        continue;
                    if (a == board[nr][nc] || b == board[nr][nc] || c == board[nr][nc] || d == board[nr][nc])
                        cnt++;
                }

                ans += score[cnt];
            }
        }
    }
}
