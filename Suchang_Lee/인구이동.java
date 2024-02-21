import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int L;
    static int R;
    static int [][] arr;
    static boolean [][] visited;
    static int count = 0;
    public static class Location {
        int x;
        int y;
        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] directions = { {1,0},{0,1},{-1,0},{0,-1}};

    static Queue<Location> q = new LinkedList<>();
    static ArrayList<ArrayList<Location>> plusList;
    public static int bfs(int sum, ArrayList <Location> arrList) {
        while (!q.isEmpty()) {
            Location l = q.poll();
            for (int [] d : directions) {
                int dx = l.x + d[0];
                int dy = l.y + d[1];
                if (0<=dx && dx < N && 0<=dy && dy < N && (L<=Math.abs(arr[l.x][l.y]-arr[dx][dy])&&Math.abs(arr[l.x][l.y]-arr[dx][dy])<=R )&& visited[dx][dy] == false) {
                    q.add(new Location(dx,dy));
                    sum += arr[dx][dy];
                    visited[dx][dy] = true;
                    arrList.add(new Location(dx,dy));
                }
            }
        }
        plusList.add(arrList);
        return sum;
    }

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        while (true) {
            plusList = new ArrayList<>();
            visited = new boolean[N][N];
            ArrayList <Integer> sumList = new ArrayList<> ();
            int [][] tempArr = new int[N][N];
            for (int i = 0; i < N; i ++) {
                System.arraycopy(arr[i], 0, tempArr[i], 0, N);
            }
//
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == false) {
                        q.add(new Location(i,j));
                        int sum = arr[i][j];
                        ArrayList<Location> arrList = new ArrayList<>();
                        visited[i][j] = true;
                        arrList.add(new Location(i,j));
                        sumList.add(bfs(sum,arrList));
                    }
                }
            }

            for (int i = 0; i < plusList.size(); i++) {
                ArrayList<Location> tmp = plusList.get(i);
                int sum = sumList.get(i);
                int len = tmp.size();
                int avg = sum /len;
                for (int j = 0; j < len; j++) {
                    Location tmpL = tmp.get(j);
                    arr[tmpL.x][tmpL.y] = avg;
                }
            }

            boolean isFinish = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(arr[i][j] != tempArr[i][j]) {
                        isFinish = false;
                        break;
                    }
                }
            }
            if (isFinish == true) {
//				System.out.println(Arrays.deepToString(arr));
                System.out.println(count);
                return ;
            }
//			System.out.println(Arrays.deepToString(arr));
//			System.out.println(Arrays.deepToString(tempArr));
            count += 1;
            //구역 나눈애들끼리 다시 뿌리기
        }


    }
}