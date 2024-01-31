import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21608_상어초등학교 {

    static int N;
    static int[][] map;
    static List<Student> students;
    static boolean[] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        visited = new boolean[N * N + 1];
        students = new ArrayList<>();
        for (int i = 1; i <= N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            Set<Integer> likes = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                likes.add(Integer.parseInt(st.nextToken()));
            }
            students.add(new Student(no, likes));
        }

        solve();
    }

    private static void solve() {
        for (Student student : students) {
            // 조건1
            List<int[]> candidates = case1(student);
            if (candidates.size() == 1) {
                int[] pos = candidates.get(0);
                setPos(pos[0], pos[1], student);
                continue;
            }
            // 조건2
            candidates = case2(candidates);
            if (candidates.size() == 1) {
                int[] pos = candidates.get(0);
                setPos(pos[0], pos[1], student);
                continue;
            }
            // 조건3
            case3(candidates, student);
        }

        int sum = calculateSum();
        System.out.println(sum);
    }

    private static boolean isLikeStudent(Student student) {
        for (int no : student.likes) {
            if (visited[no]) {
                return true;
            }
        }
        return false;
    }

    private static void setPos(int x, int y, Student student) {
        map[x][y] = student.no;
        visited[student.no] = true;
        student.setPos(new int[]{x, y});
    }

    private static List<int[]> case1(Student student) {
        List<int[]> result = new ArrayList<>();

        // 좋아하는 학생이 아무도 안 앉아있으면 빈 공간 모두 반환
        if (!isLikeStudent(student)) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] == 0) {
                        result.add(new int[]{i, j});
                    }
                }
            }
            return result;
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                int likeCount = getLikeCount(i, j, student);
                if (likeCount == max) {
                    result.add(new int[]{i, j});
                }
                if (likeCount > max) {
                    result = new ArrayList<>();
                    result.add(new int[]{i, j});
                    max = likeCount;
                }
            }
        }

        return result;
    }

    private static int getLikeCount(int x, int y, Student student) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfRange(nx, ny)) {
                continue;
            }
            if (student.likes.contains(map[nx][ny])) {
                count++;
            }
        }

        return count;
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 1 || x > N || y < 1 || y > N;
    }

    private static List<int[]> case2(List<int[]> candidates) {
        int max = 0;
        List<int[]> result = new ArrayList<>();
        for (int[] pos : candidates) {
            int blankCount = getBlankCount(pos[0], pos[1]);
            if (blankCount == max) {
                result.add(new int[]{pos[0], pos[1]});
            }
            if (blankCount > max) {
                result = new ArrayList<>();
                result.add(new int[]{pos[0], pos[1]});
                max = blankCount;
            }
        }

        return result;
    }

    private static int getBlankCount(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfRange(nx, ny)) {
                continue;
            }
            if (map[nx][ny] == 0) {
                count++;
            }
        }

        return count;
    }

    private static void case3(List<int[]> candidates, Student student) {
        int[] pos = candidates.get(0);
        setPos(pos[0], pos[1], student);
    }

    private static int calculateSum() {
        int sum = 0;
        for (Student student : students) {
            int[] pos = student.pos;
            int likeCount = getLikeCount(pos[0], pos[1], student);
            sum += (int) Math.pow(10, likeCount - 1);
        }

        return sum;
    }

    static class Student {
        int no;
        Set<Integer> likes;
        int[] pos;

        public Student(int no, Set<Integer> likes) {
            this.no = no;
            this.likes = likes;
        }

        public void setPos(int[] pos) {
            this.pos = pos;
        }
    }
}
