import java.lang.reflect.Array;
import java.util.*;

//14:31시작
//15:05끝
// 시도1 :시간초과
// 시도2 :시간초과
// 시도3: 해결
public class Main {
    static int N;
    static int M;
    static int K;
    static int [][] directions = {{1,0},{1,1},{1,-1},{0,1},{0,-1},{-1,1},{-1,-1},{-1,0}};
    static int [][] sun;
    static int [][] land;
    static ArrayList <Trees> treesList = new ArrayList<>();
    static Deque<Integer> dieTreesList = new LinkedList<>();
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        sun = new int[N][N];
        land = new int[N][N];

        //양분과 땅 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sun[i][j] =sc.nextInt();
                land[i][j] = 5;
            }
        }

        //나무 리스트 구하기
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt() - 1;
            int y=  sc.nextInt() - 1;
            int z= sc.nextInt();
            treesList.add(new Trees(x,y,z));
        }

        //나이로 오름차순
        Collections.sort(treesList, (t1,t2) -> t1.age - t2.age);

        //K년만큼 실행
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(treesList.size());

    }

    public static void spring() {
        int k =0;
        for (Trees t : treesList) {
            int x = t.x;
            int y = t.y;
            int age =t.age;
            if (age <= land[x][y]) {
                land[x][y] -= t.age;
                t.age += 1;
            }
            else {
                t.isDie = true;
                dieTreesList.add(k);
            }
            k++;
        }
    }

    public static void summer() {
        while (!dieTreesList.isEmpty()){
            int dieIndex = dieTreesList.pollLast();
            Trees t = treesList.get(dieIndex);
            int fuel = t.age/2;
            land[t.x][t.y] += fuel;
        }
    }

    public static void autumn() {
        ArrayList<Trees> temp = new ArrayList<>();
        for (Trees t: treesList) {
            if (t.isDie) continue;
            int age =t.age;
            int x = t.x;
            int y = t.y;
            if (age % 5 == 0) {
                for (int []d: directions) {
                    int dx = x + d[0];
                    int dy = y + d[1];
                    if (0<=dx&&dx<land.length&&0<=dy&&dy<land[0].length) {
                        temp.add(new Trees(dx,dy,1));
                    }
                }
            }
        }
        for (Trees t : treesList) {
            if(t.isDie) continue;
            temp.add(t);
        }
        treesList = temp;
    }

    public static void winter(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                land[i][j] += sun[i][j];
            }
        }
    }


    public static class Trees {
        int x;
        int y;
        int age;
        boolean isDie;
        public Trees (int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.isDie = false;
        }
    }
}
