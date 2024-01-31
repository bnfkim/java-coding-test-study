import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class CCTV {
    int num;
    int x;
    int y;

    CCTV(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}

public class BOJ_15683_감시 {
    static int n,m; //사무실 크기
    static int[][] office; //사무실
    static int[][] copyOffice; //방문한 곳
    static int[] directions; //순열 담는 배열
    static ArrayList<CCTV> cctvList; //cctv 종류,위치 담는 list

    //위치(idx) => 북(0), 동(1), 남(2), 서(3)
    static int[][] dir = {{0,-1}, {1,0}, {0,1}, {-1,0}};


    static int minSize = Integer.MAX_VALUE; //사각 지대의 최소 크기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //세로
        m = Integer.parseInt(st.nextToken()); //가로

        office = new int[n][m];
        copyOffice = new int[n][m];
        cctvList = new ArrayList<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if(office[i][j]>=1 && office[i][j]<=5) {
                    cctvList.add(new CCTV(office[i][j], j, i));
                }
            }
        }
        directions = new int[cctvList.size()];
        permutation(0);
        System.out.println(minSize);
    }

    /**
     * cctv 갯수만큼 cctv 방향으로 순열조합을 만듬
     * cctv1(4방향) cctv2(2방향) cctv3(4방향) cctv4(1방향)
     */
    static void permutation(int depth) {
        if(depth == cctvList.size()) { //순열 조합을 다 뽑았으면 cctv 사각지대 확인하는 메서드 실행
            setCopyOffice();

            for(int i=0; i< cctvList.size(); i++) {
                int d = directions[i];
                CCTV cctv = cctvList.get(i);
                checkDirection(cctv, d);
            }

            checkBlindOffice();

            return;
        }

        for(int i=0; i<4; i++) {
            directions[depth] = i;
            permutation(depth+1);
        }
    }
    private static void setCopyOffice() {
        copyOffice = new int[n][m];

        for (int i = 0; i < n; i++) {
            System.arraycopy(office[i], 0, copyOffice[i], 0, m);
        }
    }

    static void checkDirection(CCTV cctv, int d) {
        if(cctv.num == 1) {
            if(d == 0) watch(cctv, 0); //북
            else if (d == 1) watch(cctv, 1); //동
            else if (d == 2) watch(cctv, 2); //남
            else if (d == 3) watch(cctv, 3); //서
        } else if (cctv.num == 2) {
            if(d == 0 || d==2) {
                watch(cctv, 0);
                watch(cctv, 2);
            } else {
                watch(cctv, 1);
                watch(cctv, 3);
            }
        } else if (cctv.num == 3) {
            if (d==0) {
                watch(cctv, 0);
                watch(cctv, 1);
            } else if (d == 1) {
                watch(cctv, 1);
                watch(cctv, 2);
            } else if (d == 2) {
                watch(cctv, 2);
                watch(cctv, 3);
            } else if (d == 3) {
                watch(cctv, 3);
                watch(cctv, 0);
            }
        } else if (cctv.num == 4) {
            if (d==0) {
                watch(cctv, 0);
                watch(cctv, 1);
                watch(cctv, 2);
            } else if (d == 1) {
                watch(cctv, 1);
                watch(cctv, 2);
                watch(cctv, 3);
            } else if (d == 2) {
                watch(cctv, 2);
                watch(cctv, 3);
                watch(cctv, 0);
            } else if (d == 3) {
                watch(cctv, 3);
                watch(cctv, 0);
                watch(cctv, 1);
            }
        } else if (cctv.num == 5) {
            watch(cctv, 0);
            watch(cctv, 1);
            watch(cctv, 2);
            watch(cctv, 3);
        }
    }

    static void watch (CCTV cctv, int d) {
        int nx = cctv.x + dir[d][0];
        int ny = cctv.y + dir[d][1];

        while(!oufOfRange(nx, ny)) {
            if(copyOffice[ny][nx] == 6) break;

            copyOffice[ny][nx] = -1;

            nx += dir[d][0];
            ny += dir[d][1];
        }
    }

    static boolean oufOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= m || y >= n;
    }

    static void checkBlindOffice () {
        int size = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyOffice[i][j] != 0) continue;
                size++;
            }
        }
        minSize = Math.min(minSize, size);
    }
}