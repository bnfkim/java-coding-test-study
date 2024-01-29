import java.util.*;
import java.io.*;

// BOJ_1759_암호 만들기.java
// [BOJ]1759/골드5/208ms/1.5h/김동진

public class bj_1759 {

    static int L, C;
    static boolean[] visited;
    static char[] alps;
    static ArrayList<String> ans;
    static List<Character> set1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alps = new char[C + 1];
        ans = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= C; i++) {
            alps[i] = st.nextToken().charAt(0);
        }
        set1 = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));


        Arrays.sort(alps, 1, C + 1);
        solve( 1, 1, "");

        for (String aa : ans) {
            System.out.println(aa);
        }

    }

    static void solve(int start, int cnt, String word) {

        // cnt -> 문자열 길이
        if(cnt == L + 1) {
            if(check(word)) {
                if(!ans.contains(word)) ans.add(word);
                return;
            }
        }
        for(int i = start; i <= C; i++){
            solve(i + 1, cnt + 1, word + alps[i]);
        }
    }

    static boolean check(String s){
        int mo = 0, ja = 0;
        for(int i = 0; i < s.length(); i++){
            if(set1.contains(s.charAt(i))){
                mo++;
            }else ja++;
        }
        return mo >= 1 && ja >= 2;
    }

}
