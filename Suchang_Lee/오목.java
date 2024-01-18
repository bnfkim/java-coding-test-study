import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int [][] arr = new int[19][19];
    static int [][] directions = { {1,0},{0,1},{1,1},{-1,1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0 ; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < 19; i++) {
            for (int j =0; j < 19; j++) {
                if (arr[i][j] == 1 || arr[i][j] == 2) {
                    int board =arr[i][j];
                    for (int d = 0; d < 4; d++) {
                        int dx = i + directions[d][0];
                        int dy = j + directions[d][1];
                        int count = 1;
                        while (0<=dx && dx < 19 && 0<=dy && dy <19 && arr[dx][dy] == board) {
                            count += 1;
                            dx += directions[d][0];
                            dy += directions[d][1];
                        }
                        if (count == 5) {
                            if (0<= i - directions[d][0] && i - directions[d][0] < 19 && 0<= j - directions[d][1] && j - directions[d][1] < 19 && arr[i-directions[d][0]][j-directions[d][1]] == board) {
                                continue;
                            }
                            System.out.println(board);
                            System.out.println((i+1)+" "+(j+1));
                            return;
                        }

                    }
                }
            }
        }
        System.out.println(0);



    }


}