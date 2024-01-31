import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x;
    int y;
    int s;
    Node(int x, int y, int s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }
}

public class BOJ_16236_아기상어 {
    static int n, size=2, cnt=0, sec=0;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0,};
    static int[] dy = {0,0,1,-1};
    static boolean help;
    static Node shark;
    static PriorityQueue<Node> queue;

    //네번째 고민, 물고기를 하나 잡아서 먹었으면 그 위치로 다시 어떻게 시작할 것인가?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //input
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visit = new boolean[n][n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 9) {
                    shark = new Node(j, i, 0);
                    arr[i][j] = 0;
                }
            }
        }

        while (true) {
            queue = new PriorityQueue<>((o1, o2) -> {
                if(o1.s == o2.s) {
                    if(o1.y == o2.y) return o1.x - o2.x;
                    return o1.y - o2.y;
                }
                return o1.s - o2.s;
            });

            visit = new boolean[n][n];
            help = false;
            bfs(shark);
            if (help) break;
        }
        System.out.println(sec);
    }
    static void bfs(Node pos) {
        queue.add(pos);
        visit[pos.y][pos.x] = true;
        boolean eatFish = false;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            //먹은 것 중에, 물고기인지 + 아기 상어가 먹을 수 있는 물고기 인지
            if(arr[now.y][now.x] != 0 && arr[now.y][now.x] < size) {
                arr[now.y][now.x] = 0; //물고기 먹음
                cnt++; //먹은 갯수 증가
                sec += now.s; //시간 증가
                eatFish = true;

                shark = new Node(now.x, now.y, 0);

                if(cnt == size) {
                    size++;
                    cnt = 0;
                }
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int ns = now.s;

                if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                if(visit[ny][nx] || arr[ny][nx] > size) continue;

                queue.add(new Node(nx, ny, ns+1));
                visit[ny][nx] = true;
            }
        }
        if(!eatFish) help = true;
    }
}