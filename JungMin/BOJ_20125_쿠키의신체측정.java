package doit;

import java.util.Scanner;

public class 쿠키의신체측정_20125 {
    static int heart_x;
    static int heart_y;
    static int left_arm;
    static int right_arm;
    static int back;
    static int left_leg;
    static int right_leg;

    static int[][] arr;
    static int N;
    static int[] dx={0,0,-1,1};
    static int[] dy={1,-1,0,0};
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        arr= new int[N][N];

        for(int i=0;i<N;i++)

        {
            String str=sc.next();

            for(int j=0;j<str.length();j++)
            {
                arr[i][j]=str.charAt(j);
            }
        }
        findHeartPos();
        findArmPos();
        findBack();

        System.out.println((heart_x+1)+" "+(heart_y+1));

        System.out.println(left_arm+" "+right_arm+" "+back+" "+(left_leg+1)+" "+(right_leg+1));

    }
    public static void findArmPos()
    {
        int l_arm=heart_y;
        int r_arm=heart_y;

        int idx=0;
        while(true)
        {
            l_arm--;
            if(l_arm >=0 && arr[heart_x][l_arm]=='*')
            {
                left_arm++;
            }
            else
            {
                break;
            }
        }

        idx=0;
        while(true)
        {
            r_arm++;
            if(r_arm <N && arr[heart_x][r_arm]=='*')
            {
                right_arm++;
            }
            else
            {
                break;
            }
        }
    }

    public static boolean isBoundary(int x,int y)
    {
        if(x>=0 && x<N && y>=0 && y<N)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void findHeartPos()
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(arr[i][j]=='*')
                {
                    //System.out.println(i+" "+j);
                    int cnt=0;

                    for(int idx=0;idx<4;idx++)
                    {
                        int nx=i+dx[idx];
                        int ny=j+dy[idx];

                        if(isBoundary(nx,ny) && arr[nx][ny]=='*')
                        {
                            cnt++;
                        }
                    }
                    //System.out.println(cnt);
                    if(cnt==4)
                    {
                        heart_x=i;
                        heart_y=j;
                    }


                }
            }
        }
    }
    public static void findBack()
    {
        int idx=0;
        int y= heart_x;
        while(true)
        {
            y++;
            if(y<N && arr[y][heart_y]=='*')
            {
                back++;
                //System.out.println(back);
            }
            else break;
        }
        //System.out.println(y);
        int leg_x=y;
        int leg_y=heart_y-1;
        //System.out.println(leg_x+""+leg_y);
        while(true)
        {
            leg_x++;
            if(leg_x<N&& arr[leg_x][leg_y]=='*')
            {
                left_leg++;
            }
            else break;
        }
        leg_x=y;
        leg_y=heart_y+1;
        //System.out.println(leg_x+""+leg_y);

        while(true)
        {
            leg_x++;
            if(leg_x<N&& arr[leg_x][leg_y]=='*')
            {
                right_leg++;
            }
            else break;
        }


    }
}

