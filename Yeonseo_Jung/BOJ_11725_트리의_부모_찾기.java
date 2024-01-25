import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_11725_트리의_부모_찾기 {

    static int N;
    static boolean[] visited;
    static int[] sol;
    static ArrayList<List<Integer>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sol = new int[N + 1];
        visited = new boolean[N + 1];

        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            tree.get(input[0]).add(input[1]);
            tree.get(input[1]).add(input[0]);
        }
        dfs(1);
        printSolution();
    }

    private static void dfs(int v) {
        visited[v] = true;

        for (int i : tree.get(v)) {
            if (!visited[i]) {
                sol[i] = v;
                dfs(i);
            }
        }
    }

    private static void printSolution() {
        Arrays.stream(sol)
                .skip(2)
                .forEach(System.out::println);
    }
}
