import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static int N,M,K;
    public static int [][] arr;
    public static int [] directions = {0,90,180,270};
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        arr = new int[N][M];
        for (int i = 0; i < K; i++) {
            //가로 세로가 영역을 넘지 않는지
            int x = sc.nextInt();
            int y= sc.nextInt();
            boolean isFinish = false;
            int [][] temp = new int[x][y];

            for (int dr = 0; dr < x; dr++) {
                for (int dc = 0; dc < y; dc ++) {
                    temp[dr][dc] = sc.nextInt();
                }
            }
//            System.out.println("ss");
//            for (int s = 0; s < temp.length; s++) {
//                System.out.println(Arrays.toString(temp[s]));
//            }
            check(x,y,temp);

        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j]==1)answer++;
            }
        }
        System.out.println(answer);
    }
    static void check(int tx, int ty, int[][] tem) {
        for (int d = 0; d < 4; d++) {
            int [][] temp;
            temp = rotate(tem,directions[d]);
            int x, y;
            if (d % 2 != 0) {
                y = tx;
                x = ty;
            }
            else {
                x = tx;
                y = ty;
            }
//            System.out.println("kk");
//            for (int i = 0; i < temp.length; i++) {
//                System.out.println(Arrays.toString(temp[i]));
//            }
//            System.out.println("");
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
//                    System.out.println(d+":" + x + y);
                    if (x + r<= N && y + c<= M) {
                        boolean isCheck = true;

                        //checking
                        for (int a = r; a < x + r; a++) {
                            for (int b = c; b < y + c; b++) {
                                if (temp[a - r][b - c] == 1) {
                                    if (arr[a][b] == 1) {
                                        isCheck = false;
                                        break;
                                    }
                                }
                            }
                            if (!isCheck) break;
                        }
                        if (!isCheck) continue;

                        //checking -> drawing
                        for (int a = r; a < x + r; a++) {
                            for (int b = c; b < y + c; b++) {
                                if (temp[a - r][b - c] == 1) {
                                    arr[a][b] = 1;
                                }
                            }
                        }
                        return;
                    } else {
                        break;
                    }
                }
            }
        }
    }
    static int[][] rotate(int[][] arr, int degree) {
        int[][] rotate;
        int n = arr.length;
        int m = arr[0].length;
//        System.out.println(degree);
        switch (degree) {
            case 90:
            case 270:
                rotate = new int[m][n];
                break;
            case 0:
            case 180:
                rotate = new int[n][m];
                break;
            default:
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                switch (degree) {
                    case 0:
                        rotate[i][j] = arr[i][j];
                        break;
                    case 90:
                        rotate[i][j] = arr[n-1-j][i];
                        break;
                    case 180:
                        rotate[i][j] = arr[n-1-i][m-1-j];
                        break;
                    case 270:
                        rotate[i][j] = arr[j][m-1-i];
                        break;
                }
            }
        }

        return rotate;
    }

}
