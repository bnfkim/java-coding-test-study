package Yeonsoo_Joo;

import java.io.*;
import java.util.*;

class Node{
    int x;
    int y;
    int dis;
    public Node(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}

public class BOJ_16236_아기상어 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static PriorityQueue<Node> fishes;
    static Node cur; // 아기 상어 위치
    static int size; // 아기 상어 크기
    static boolean isEatable; // 먹을 게 더 있는지 확인
    static int count; // 먹은 물고기 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    cur = new Node(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }

        size = 2;
        int answer = 0;
        while (true) {
            isEatable = true;
            visited = new boolean[N][N];
            fishes = new PriorityQueue<>((o1, o2) ->
                (o1.dis != o2.dis) ? Integer.compare(o1.dis, o2.dis) : (o1.x != o2.x ? Integer.compare(o1.x, o2.x) : Integer.compare(o1.y, o2.y))
            );

            bfs(cur);

            if (!isEatable) {
                break;
            }

            // 물고기 먹기
            Node food = fishes.poll();
            count++;
            map[food.x][food.y] = 0;
            cur = new Node(food.x, food.y, 0);
            answer += food.dis;

            // 몸집 키우기
            if (count == size) {
                size++;
                count = 0;
            }
        }

        System.out.println(answer);
    }

    private static void bfs(Node cur) {
        Queue<Node> queue = new LinkedList<>();
        visited[cur.x][cur.y] = true;
        queue.add(cur);

        // 먹이 (완전) 탐색
        while (!queue.isEmpty()) {
            Node pos = queue.poll();
            int x = pos.x;
            int y = pos.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] > size) {
                    continue;
                }
                if (map[nx][ny] != 0 && map[nx][ny] < size) {
                    fishes.offer(new Node(nx, ny, pos.dis + 1));
                }
                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny, pos.dis + 1));
            }
        }
        if(fishes.isEmpty()) {
            isEatable = false;
        }
    }
}
