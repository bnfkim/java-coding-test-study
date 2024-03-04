import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int n, c;
    static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new int[n];
        for(int i = 0; i < n; i++){
            board[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(board);

        int left = 1, right = board[board.length-1] - board[0], ans = 0;
        while(left <= right){
            int mid = (left+right)/2;
            if(check(mid)){
                ans = mid;
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }

        System.out.println(ans);
    }

    //C개의 공유기를 설치할 수 있는지 없는지 확인
    public static boolean check(int mid){
        int res = 1, cur = 0;
        for(int i = 1; i < n; i++){
            if(board[i] - board[cur] >= mid){
                res++;
                cur = i;
            }
        }
        return (res >= c) ? true : false;
    }
}

