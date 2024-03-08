import java.io.*;
import java.util.*;

public class BOJ_23796 {
    static final int MAP_SIZE = 8;
    static long[][] map;
    static long[][] result;
    static ArrayList<ArrayList<Long>> nums;
    static String command;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new long[MAP_SIZE][MAP_SIZE];
        result = new long[MAP_SIZE][MAP_SIZE];

        for (int i = 0; i < MAP_SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nums = new ArrayList<>();
        for(int i=0; i<MAP_SIZE; i++) {
            nums.add(new ArrayList<>());
        }

        command = br.readLine();
        switch (command) {
            case "U":
                printUp();
                break;
            case "D":
                printDown();
                break;
            case "L":
                printLeft();
                break;
            case "R":
                printRight();
                break;
        }

        //출력
        System.out.println(sb.toString().trim());
    }

    static void printUp() {
        for (int c = 0; c < MAP_SIZE; c++) {
            long n1 = 0;
            for (int r = 0; r < MAP_SIZE; r ++) {
                if(map[r][c] == 0) continue;

                if(n1 == 0) {
                    n1 = map[r][c];
                    continue;
                }

                if(n1 == map[r][c]) {
                    nums.get(c).add(n1 * 2);
                    n1 = 0;
                } else {
                    nums.get(c).add(n1);
                    n1 = map[r][c];
                }
            }
            if(n1 != 0) nums.get(c).add(n1);
        }

        for(ArrayList<Long> r : nums) {
            while(r.size() != MAP_SIZE) {
                r.add(0L);
            }
        }

        for (int j = 0; j < MAP_SIZE; j++) {
            for (int i = 0; i < MAP_SIZE; i++) {
                sb.append(nums.get(i).get(j)).append(" ");
            }
            sb.append("\n");
        }
    }
    static void printDown() {
        for (int c = 0; c < MAP_SIZE; c++) {
            long n1 = 0;
            for (int r = MAP_SIZE - 1; r >= 0; r--) {
                if(map[r][c] == 0) continue;

                if(n1 == 0) {
                    n1 = map[r][c];
                    continue;
                }

                if(n1 == map[r][c]) {
                    nums.get(c).add(n1 * 2);
                    n1 = 0;
                } else {
                    nums.get(c).add(n1);
                    n1 = map[r][c];
                }
            }
            if(n1 != 0) nums.get(c).add(n1);
        }

        for(ArrayList<Long> r : nums) {
            while(r.size() < MAP_SIZE) {
                r.add(0L);
            }
        }

        for (int j = MAP_SIZE - 1; j >= 0; j--) {
            for (int i = 0; i < MAP_SIZE; i++) {
                sb.append(nums.get(i).get(j)).append(" ");
            }
            sb.append("\n");
        }
    }

    static void printLeft() {
        for (int r = 0; r < MAP_SIZE; r++) {
            long n1 = 0;
            for (int c = 0; c < MAP_SIZE; c ++) {
                if(map[r][c] == 0) continue;

                if(n1 == 0) {
                    n1 = map[r][c];
                    continue;
                }

                if(n1 == map[r][c]) {
                    nums.get(r).add(n1 * 2);
                    n1 = 0;
                } else {
                    nums.get(r).add(n1);
                    n1 = map[r][c];
                }
            }
            if(n1 != 0) {
                nums.get(r).add(n1);
            }
        }

        for(ArrayList<Long> r : nums) {
            while(r.size() != MAP_SIZE) {
                r.add(0L);
            }
        }

        for (int j = 0; j < MAP_SIZE; j++) {
            for (int i = 0; i < MAP_SIZE; i++) {
                sb.append(nums.get(j).get(i)).append(" ");
            }
            sb.append("\n");
        }
    }

    static void printRight() {
        for (int r = 0; r < MAP_SIZE; r++) {
            long n1 = 0;
            for (int c = MAP_SIZE - 1; c >= 0 ; c--) {
                if(map[r][c] == 0) continue;

                if(n1 == 0) {
                    n1 = map[r][c];
                    continue;
                }

                if(n1 == map[r][c]) {
                    nums.get(r).add(n1 * 2);
                    n1 = 0;
                } else {
                    nums.get(r).add(n1);
                    n1 = map[r][c];
                }
            }
            if(n1 != 0) {
                nums.get(r).add(n1);
            }
        }

        for(ArrayList<Long> r : nums) {
            while(r.size() < MAP_SIZE) {
                r.add(0L);
            }
        }

        for (int j = 0; j < MAP_SIZE; j++) {
            for (int i = MAP_SIZE - 1; i >= 0; i--) {
                sb.append(nums.get(j).get(i)).append(" ");
            }
            sb.append("\n");
        }
    }
}
