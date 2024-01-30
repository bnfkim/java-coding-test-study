import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1288_새로운불면증치료법
 */
public class SWEA_1288_새로운불면증치료법 {
    static int t, n;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        t = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= t; test_case++){
            st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            int visited = 0, counter = 0;

            while(visited != ((1<<10)-1)){                
                for(int i = n*++counter; i > 0; i /= 10)
                    visited |= (1<<(i%10));
            }

            System.out.printf("#%d %d\n", test_case, n*counter);
        }

    }
}