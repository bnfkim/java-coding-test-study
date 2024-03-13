import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main{
    static int N,M;
    static Set<Integer>[] party; //각 파티별 사람들을 담은 set
    static Set<Integer> true_people=new HashSet<>(); //진실을 알고 있는 사람들
    static boolean[] check;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        party=new HashSet[M];
        check=new boolean[M];

        st=new StringTokenizer(br.readLine());

        for(int i=0,size=Integer.parseInt(st.nextToken());i<size;i++) {
            true_people.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0;i<M;i++) {
            party[i]=new HashSet<>();
            st=new StringTokenizer(br.readLine());
            for(int j=0,size=Integer.parseInt(st.nextToken());j<size;j++) {
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        while(true) {
            boolean changed=false; //이번 회차에 진실을 알게된 사람이 추가됐는지 확인

            for(int i=0;i<M;i++) {
                if(check[i]) continue; //이미 체크한 것은 화인할 필요가 없음 (why? 이미 해당 파티의 모든 인원은 true_people에 들어가있음)

                for(int x:true_people) {
                    if(party[i].contains(x)) { //진실을 아는 사람이 파티에 있다면

                        for(int p:party[i]) { //그 파티의 모든 사람은 진실을 알게된다,
                            true_people.add(p);
                        }

                        check[i]=true; //해당 파티는 더이상 확인할 필요가 없다.
                        changed=true; //변화가 되었다.
                        break;
                    }
                }
            }

            if(!changed) break;
        }

        int result=0;
        for(int i=0;i<M;i++) { //체크가 안됐다 = 진실을 아는 사람이 없다 = 거짓말 할수 있다.
            if(!check[i]) result++;
        }


        System.out.println(result);
    }
}
