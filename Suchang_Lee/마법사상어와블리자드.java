import java.util.*;

public class 마법사상어와블리자드{
    //형변환
    static int N; //격자의 정보
    static int M;
    static int pivot;
    static int [][] arr;
    static int [][] board;
    static int sum;
    static int [][] directions = {{0,-1},{1,0},{0,1},{-1,0}};
    static int [][] crashDirections = {{-1,0},{1,0},{0,-1},{0,1}};
    static int d;
    static HashMap <Integer, Point> hm = new HashMap<>();
    static int answer;
    static ArrayList<Integer> sharkList = new ArrayList<>();
    static int s;
    public static  void main (String [] args ) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N][N];
        board = new int[N][N];


        makeArr();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
//                numArr[board[i][j]] = arr[i][j];
            }
        }

//        for(int i = 1; i < numArr.length; i++) {
//            if (numArr[i] == 0) break;
//            arrayList.add(numArr[i]);
//        }

//        System.out.println(arrayList.toString());
        for (int c = 0; c< M; c++) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            int d = sc.nextInt() - 1;
            int s = sc.nextInt();
            int dx = N/2;
            dx+= crashDirections[d][0] * s;
            int dy = N/2;
            dy+= crashDirections[d][1] * s;
            for (int j = 1; j  <= s; j++) {
                arr[dx][dy] = 0;
                dx -= crashDirections[d][0];
                dy -= crashDirections[d][1];
            }
            arrayList.add(0);
            Integer [] numArr = new Integer[N*N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(arr[i][j] != 0) numArr[board[i][j]] = arr[i][j];
                }
            }
            for(int i = 1; i < numArr.length; i++) {
                if (numArr[i] == null) continue;
                arrayList.add(numArr[i]);
            }

//            System.out.println(arrayList.toString());
            //구슬 뿌시기
            while (true) {
                boolean isPop = false;
                Stack<Integer> stack = new Stack<>();
                int pivot =0;
                if (arrayList.size()>0) pivot = arrayList.get(arrayList.size() - 1);
                int count = 1;
                for(int a = arrayList.size() - 2; a >=0; a--) {
                    int next = arrayList.get(a);
                    if (pivot == next) {
                        count+=1;
                    }
                    else {
                        if (count < 4) {
                            for(int t = 0 ; t < count; t++)
                                stack.add(pivot);
                        }
                        else {
                            if (pivot == 1) answer += count;
                            else if (pivot == 2) answer += count * 2;
                            else if (pivot == 3) answer += count * 3;
                            isPop = true;
                        }
                        pivot = next;
                        count = 1;
                    }
                }
                if(!isPop) break;
                arrayList.clear();
                arrayList.add(0);
                while(!stack.isEmpty()) arrayList.add(stack.pop());
            }
//            System.out.println(arrayList.toString());
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(0);

            int pivot = 0;
            int count = 1;

            if (arrayList.size() > 1) {
                pivot = arrayList.get(1);
                for (int t = 2; t < arrayList.size(); t++) {
                    int next = arrayList.get(t);
                    if (pivot == next) {
                        count += 1;
                    } else {
                        temp.add(count);
                        temp.add(pivot);
                        count = 1;
                        pivot = next;
                    }
                }
                temp.add(count);
                temp.add(pivot);
            }
            arrayList.clear();
//            System.out.println(temp.toString());
            for(int t = 0 ; t < Math.min(temp.size(),N*N ); t++) {
                Point p = hm.get(t);
                int x = temp.get(t);
                arrayList.add(x);
                arr[p.x][p.y] = x;
            }

            for (int t = temp.size(); t < N*N; t++) {
                Point p = hm.get(t);
                arr[p.x][p.y] = 0;
            }
//            System.out.println(arrayList.toString());
//            System.out.println(c+1);
//            for (int z = 0; z < N; z++) {
//                System.out.println(Arrays.toString(arr[z]));
//            }
//            System.out.println("");
        }
        System.out.println(answer);

    }
    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void makeArr() {
        int x = (N-1) / 2;
        int y = (N-1) / 2;
        int d = 0;
        int count= 1;
        int length = 1;
        hm.put(0, new Point(x,y));
        while (true) {
            if (length == N) {
                for (int i = N -1; i >=1; i--) {
                    x += directions[d][0];
                    y += directions[d][1];
                    hm.put(count,new Point(x,y));
                    board[x][y] = count++;
                }
                break;
            }
            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < length; j++) {
                    x += directions[d][0];
                    y += directions[d][1];
                    hm.put(count,new Point(x,y));
                    board[x][y] = count++;

                }
                d = d + 1 == 4 ? 0 : d + 1;
            }
            length++;
        }

    }
}
