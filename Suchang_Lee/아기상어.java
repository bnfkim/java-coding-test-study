import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int [][] arr;
    static int [] shark = new int[2];
    static int sharkLevel = 2;
    static int amount = 0;
    static int answer = 0;
    static int [][] directions = {{-1,0},{0,-1},{0,1},{1,0}};
    static int c = Integer.MAX_VALUE;
    static ArrayList<SharkDistance> sharkDistance = new ArrayList<>();
    static Queue<SharkDistance> sharkQueue = new LinkedList<>();
    static PriorityQueue<SharkDistance> SQ = new PriorityQueue<>(1, new SharkComparator());
    static boolean [][] visited ;
    public static void bfs() {

        while(!sharkQueue.isEmpty()) {
            SharkDistance sd = sharkQueue.poll();
            int i = sd.x;
            int j = sd.y;
            int count = sd.d;
            if (arr[i][j] < sharkLevel && arr[i][j] != 0) {
                SQ.add(new SharkDistance(i,j,count));
                continue;
            }
            for (int [] d: directions) {
                int di = i + d[0];
                int dj = j + d[1];
                if (0<=di&&di<arr.length&&0<=dj&&dj<arr[0].length && arr[di][dj] <= sharkLevel && visited[di][dj] == false) {
                    visited[di][dj] = true;
                    sharkQueue.add(new SharkDistance(di,dj,count+1));
                }
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
                if(arr[i][j]==9) {
                    sharkQueue.add(new SharkDistance(i,j,0));
                    arr[i][j] = 0;
                }

            }
        }

        while (true) {
            visited= new boolean[N][N];
            SharkDistance sd = sharkQueue.poll();
            visited[sd.x][sd.y] = true;
            sharkQueue.add(sd);
            bfs();

            if (SQ.size() == 0) {
                System.out.println(answer);
                return;
            }
            amount += 1;
            if (sharkLevel == amount) {
                sharkLevel += 1;
                amount = 0;
            }
            SharkDistance d= SQ.poll();
            answer += d.d;
            arr[d.x][d.y] = 0;
            sharkQueue.add(new SharkDistance(d.x,d.y,0));
            SQ = new PriorityQueue<>(1, new SharkComparator());
        }
    }
    public static class SharkComparator implements Comparator<SharkDistance> {
        public int compare(SharkDistance d1, SharkDistance d2){
            if (d1.d != d2.d) {
                return d1.d -d2.d;
            }
            else {
                if (d1.x != d2.x) return d1.x -d2.x;
                else return d1.y - d2.y;
            }
        }
    }
    public static class SharkDistance {
        int x;
        int y;
        int d;
        SharkDistance(int x,int y,int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}