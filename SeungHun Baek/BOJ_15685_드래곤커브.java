import java.util.*;
import java.io.*;

public class BOJ_15685_드래곤커브 {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Dragon {
        boolean order;
        int d, g, currentG;
        Point S, E;
        ArrayDeque<Point> que;

        Dragon(int x, int y, int d, int g) {
            this.S = new Point(x, y);
            this.E = new Point(S.x+dx[d], S.y+dy[d]);
            this.d = d;
            this.g = g;
            this.order = true;
            this.que = new ArrayDeque<>();
            this.que.add(this.S);
            this.que.add(this.E);
            this.currentG = 0;
        }

        void move() {
            do {
                int stt = order ? 0 : que.size() - 1;
                int end = order ? que.size() -1 : 0;
                int sign = order ? 1 : -1;

                for (int idx = stt; idx == end; idx=sign ) {
                    // Point p = que.
                }
                currentG++;
            } while (currentG < g);
        }

        void generate() {
            this.d++;
            this.d %= 4;
            this.order = this.order ? false : true;
            this.currentG++;
        }

        Point rotatePoint(Point s, Point e) {
            int X = e.x-s.y+e.y;
            int Y = e.y+s.x-e.x;
            return new Point(X, Y);
        }

    }
    static int N;
    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    }
}