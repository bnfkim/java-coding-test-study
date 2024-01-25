import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n;

    static boolean visited[],find;
    static List<Integer> answer;
    static List<String> op;

    static void find_max(int level) {
        if(level==n+1) {
            find=true;
            for(int x:answer) {
                System.out.print(x);
            }
            System.out.println();
            return;
        }

        for(int i=9;i>=0;i--) {
            if(visited[i]) {
                continue;
            }

            if(answer.isEmpty()) {
                visited[i]=true;
                answer.add(i);

                find_max(level+1);

                if(find) {
                    return;
                }

                visited[i]=false;
                answer.remove(level);
                continue;
            }

            if(op.get(level-1).equals(">")) {
                if(answer.get(level-1)>i) {
                    visited[i]=true;
                    answer.add(i);

                    find_max(level+1);

                    if(find) {
                        return;
                    }

                    visited[i]=false;
                    answer.remove(level);
                }
            }
            else {
                if(answer.get(level-1)<i) {
                    visited[i]=true;
                    answer.add(i);

                    find_max(level+1);

                    if(find) {
                        return;
                    }

                    visited[i]=false;
                    answer.remove(level);
                }
            }
        }

    }

    static void find_min(int level) {
        if(level==n+1) {
            find=true;
            for(int x:answer) {
                System.out.print(x);
            }
            System.out.println();
            return;
        }

        for(int i=0;i<10;i++) {
            if(visited[i]) {
                continue;
            }

            if(answer.isEmpty()) {
                visited[i]=true;
                answer.add(i);

                find_min(level+1);

                if(find) {
                    return;
                }

                visited[i]=false;
                answer.remove(level);
                continue;
            }

            if(op.get(level-1).equals(">")) {
                if(answer.get(level-1)>i) {
                    visited[i]=true;
                    answer.add(i);

                    find_min(level+1);

                    if(find) {
                        return;
                    }

                    visited[i]=false;
                    answer.remove(level);
                }
            }
            else {
                if(answer.get(level-1)<i) {
                    visited[i]=true;
                    answer.add(i);

                    find_min(level+1);

                    if(find) {
                        return;
                    }

                    visited[i]=false;
                    answer.remove(level);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        op=new ArrayList<>(n);

        for(int i=0;i<n;i++) {
            op.add(st.nextToken());
        }

        visited=new boolean[10];
        answer=new ArrayList<>(n+1);
        find_max(0);

        visited=new boolean[10];
        answer=new ArrayList<>(n+1);
        find=false;
        find_min(0);
    }
}
