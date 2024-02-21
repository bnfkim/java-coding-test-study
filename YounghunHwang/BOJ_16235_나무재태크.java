import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16235_나무재태크 {

    static int N, M, K;
    static int[][] map;
    static int[][] A;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static Deque<Tree> trees;
    static List<Tree> deadTrees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], 5);
        }

        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        trees = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        for (int i = 0; i < K; i++) {
            year();
        }

        System.out.println(trees.size());
    }

    private static void year() {
        spring();
        summer();
        fall();
        winter();
    }

    private static void spring() {
        deadTrees = new ArrayList<>();
        Deque<Tree> newTrees = new LinkedList<>();

        for (Tree tree : trees) {
            if (map[tree.x][tree.y] < tree.age) {
                deadTrees.add(tree);
                continue;
            }
            map[tree.x][tree.y] -= tree.age;
            tree.age++;
            newTrees.add(tree);
        }

        trees = newTrees;
    }

    private static void summer() {
        for (Tree tree : deadTrees) {
            map[tree.x][tree.y] += tree.age / 2;
        }
    }

    private static void fall() {
        List<Tree> matureTrees = new ArrayList<>();
        for (Tree tree : trees) {
            if (tree.age % 5 != 0) {
                continue;
            }
            matureTrees.add(tree);
        }

        for (Tree matureTree : matureTrees) {
            for (int i = 0; i < 8; i++) {
                int nx = matureTree.x + dx[i];
                int ny = matureTree.y + dy[i];
                if (isOutOfRange(nx, ny)) {
                    continue;
                }
                trees.addFirst(new Tree(nx, ny, 1));
            }
        }
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 1 || x > N || y < 1 || y > N;
    }

    private static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }

    static class Tree {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}