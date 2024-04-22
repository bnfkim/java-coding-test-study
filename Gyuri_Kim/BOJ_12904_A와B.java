import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        while(S.length() < T.length()) {
            if(T.endsWith("B")) {
                T = T.substring(0, T.length() - 1);
                T = new StringBuilder(T).reverse().toString();
            } else {
                T = T.substring(0, T.length() - 1);
            }
        }
        System.out.println(S.equals(T) ? 1 : 0);
    }
}
