import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static char[][] star;
    static void print_star(int x, int y, int len){
        // 종료조건
        if(len == 1){
            star[x][y] = '*';
//            star[x+1][y] = '*';
//            star[x+2][y] = '*';
//
//            star[x][y+1] = '*';
//            star[x+2][y+1] = '*';
//
//            star[x][y+2] = '*';
//            star[x+1][y+2] = '*';
//            star[x+2][y+2] = '*';

            return;
        }
        // 1/3으로 줄인 크기만큼 똑같이 만듬
        int new_len = len/3;
        print_star(x, y, new_len);
        print_star(x + new_len, y, new_len);
        print_star(x + new_len*2, y, new_len);

        print_star(x, y + new_len, new_len);
        //print_star(x, y+ new_len, new_len);
        print_star(x + new_len*2, y + new_len, new_len);

        print_star(x, y + new_len*2, new_len);
        print_star(x + new_len, y + new_len*2, new_len);
        print_star(x + new_len*2,  y + new_len*2, new_len);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); //입력값

        //(1)빈 배열 만들기
        star = new char[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(star[i], ' '); //2차원 배열을 ' '으로 초기화
        }

        //(2)배열에 별 넣기
        print_star(0,0, n);

        //(3)배열 별 출력하기
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                sb.append(star[i][j]);
            }
            //마지막 \n은 제외
            if(i != n-1) sb.append("\n");
        }
        System.out.print(sb);
    }
}