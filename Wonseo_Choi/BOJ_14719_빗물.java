import java.io.*;
import java.util.*;

public class BOJ_14719_빗물 {
    static int[] givenArr;
    static boolean[][] bitArr;
    static int temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        
        givenArr = new int[w];
        bitArr = new boolean[h][w];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < w; i++ ) {
            temp = Integer.parseInt(st.nextToken());
            givenArr[i] = temp;
            for (int j = 0; j < temp; j++ ){
                bitArr[j][i] = true;
            }
        }

        int cnt=0;
        boolean sFlag, eFlag;
        temp = 0;
        for (int j = 0; j < h; j++ ) {
            sFlag = eFlag = false;
            temp = 0;
            for (int i = 0; i < w; i++ ) {
                if (!bitArr[j][i]) {
                    if (sFlag) temp++;
                    // else continue;
                    if (i < w-1 && bitArr[j][i+1]) {
                        sFlag = false;
                        eFlag = true;
                    }
                }
                else {
                    if (i < w-1 && !bitArr[j][i+1]) {
                        sFlag = true;
                        eFlag = false;
                    }
                }
                if (eFlag) {
                    cnt += temp;
                    temp = 0;
                }
            }
            
        }
        System.out.println(cnt);
    }
}
