import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [][]field=new int[8][8];
    static int N;
    static int[]king,stone;
    static int[][]mv={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        king=new int[2];
        stone=new int[2];

        int kingIndex=convertIndex(st.nextToken().toCharArray());
        king[0]=kingIndex/8;
        king[1]=kingIndex%8;

        int stoneIndex=convertIndex(st.nextToken().toCharArray());
        stone[0]=stoneIndex/8;
        stone[1]=stoneIndex%8;

        N=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            move(br.readLine());
        }

        System.out.println(convertPosition(king[0]*8+king[1]));
        System.out.println(convertPosition(stone[0]*8+stone[1]));
    }

    static void move(String op){
        int mvIdx=convertDir(op);

        int nkx=king[0]+mv[mvIdx][0];
        int nky=king[1]+mv[mvIdx][1];

        if(nkx<0||nkx==8||nky<0||nky==8) return;

        if(nkx==stone[0]&&nky==stone[1]){
            int nsx=stone[0]+mv[mvIdx][0];
            int nsy=stone[1]+mv[mvIdx][1];

            if(nsx<0||nsx==8||nsy<0||nsy==8) return;

            stone[0]=nsx;
            stone[1]=nsy;
        }

        king[0]=nkx;
        king[1]=nky;
    }

    static int convertDir(String op){
        if(op.equals("R")) return 2;
        if(op.equals("L")) return 6;
        if(op.equals("B")) return 4;
        if(op.equals("T")) return 0;
        if(op.equals("RT")) return 1;
        if(op.equals("LT")) return 7;
        if(op.equals("RB")) return 3;
        return 5;
    }
    static int convertIndex(char[] pos){
        int x=7-(pos[1]-'1');
        int y=pos[0]-'A';

        return x*8+y;
    }

    static String convertPosition(int index){
        String pos="";
        int x=index/8;
        int y=index%8;

        pos+=(char)('A'+y);
        pos+=(char)('1'+7-x);

        return pos;
    }
}