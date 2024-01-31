import java.io.*;

public class BOJ_4889_안정적인문자열 {
    static String temp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int ll=0, rr=0, ans=0, cnt=1;
        while (true) {
            ll = 0;
            rr = 0;
            ans = 0;
            temp = br.readLine();

            if (temp.charAt(0) == '-') {
                break;
            }
            
            if (temp.charAt(0) == ' ') {
                System.out.println(cnt++ + ". " + 0);
                continue;
            }

            for (int i = 0, size=temp.length(); i < size; i++ ) {
                if (temp.charAt(i) == '{') {
                    ll++;
                } else {
                    if (ll == 0) {
                        rr++;
                    } else {
                        ll--;
                    }
                }
            }
            ans += rr/2 + ll/2 + rr%2 + ll%2;

            System.out.println(cnt++ + ". " + ans);
        }
    }
}
