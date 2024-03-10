import java.util.*;

class Solution {
    static boolean[] isChecked=new boolean[100000000];
    static int max=100000000;
    static List<Integer>[] nums = new ArrayList[9];

    public int solution(int N, int number) {

        for(int i=1;i<=8;i++){
            nums[i]=new ArrayList<>();
        }

        int setting=init(N,number);
        if(setting!=0){
            return setting;
        }

        for(int level=2;level<=8;level++){
            for(int i=1;i<level;i++){
                int j=level-i;

                for(int a:nums[i]){
                    for(int b:nums[j]){
                        if(add(a+b,level,number)||add(a-b,level,number)||add(a*b,level,number)) return level;

                        if(b!=0&& add(a/b,level,number)) return level;
                    }
                }

            }
        }


        return -1;
    }

    static int init(int N,int number){
        int cur=N;
        int cnt=1;
        do{
            if(cur==number){
                return cnt;
            }

            nums[cnt].add(cur);
            isChecked[cur]=true;
            cur=cur*10+N;
        }while(++cnt<9);

        return 0;
    }

    static boolean add(int x,int level,int number){
        if(x<0||x>=max) return false;
        if(isChecked[x]) return false;

        nums[level].add(x);
        isChecked[x]=true;

        return x==number;
    }
}