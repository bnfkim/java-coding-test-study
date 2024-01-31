package Yeonsoo_Joo;

import java.io.*;
import java.util.*;

class Tree {

    int x;
    int y;
    int age;

    public Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }
}

public class BOJ_16235_나무재테크 {

    static int N, M, K;
    static int[][] A;
    static int[][] food;
    static Deque<Tree> trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 기본 양분의 양
        food = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                food[i][j] = 5;
            }
        }

        // 추가될 양분의 양
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // M개의 나무를 땅에 심음
        trees = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.offer(new Tree(x, y, age));
        }

        // K년 지난 후
        for (int year = 0; year < K; year++) {
            ss();
            fw();
        }
        System.out.println(trees.size());
    }

    private static void ss() {
        List<Tree> deadTrees = new ArrayList<>();
        Deque<Tree> next = new LinkedList<>();
        // 봄: 나이만큼 양분 먹기
        for (Tree tree : trees) {
            if (food[tree.x][tree.y] < tree.age) {
                deadTrees.add(tree);
            } else {
                food[tree.x][tree.y] -= tree.age;
                next.addLast(new Tree(tree.x, tree.y, tree.age + 1));
            }
        }
        // 여름: 죽은 나무를 양분으로
        for (Tree tree : deadTrees) {
            food[tree.x][tree.y] += tree.age / 2;
        }
        trees = next;
    }

    private static void fw() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        // 가을: 나무 번식
        List<Tree> newTrees = new ArrayList<>();
        for (Tree tree : trees) {
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            if (age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue;
                    }
                    newTrees.add(new Tree(nx, ny, 1));
                }
            }
        }
        // 어린 나무를 앞으로
        for (Tree tree : newTrees) {
            trees.addFirst(tree);
        }
        // 겨울: 양분 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                food[i][j] += A[i][j];
            }
        }
    }
}
