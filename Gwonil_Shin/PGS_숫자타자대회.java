class Solution {
    int[][]dp;
    public int solution(String numbers) {
        int answer = Integer.MAX_VALUE;
        int N=numbers.length();
        dp=new int[N+1][12*13];

        for(int idx=1;idx<=N;idx++){
            int number=getNumber(numbers.charAt(idx-1));


            if(idx==1){
                //3과 number 넘기기
                int dist=getDist(5,number);

                int max=Math.max(3,number);
                int min=Math.min(3,number);

                dp[idx][max*12+min]=dist;

                //5와 number 넘기기
                dist=getDist(3,number);

                max=Math.max(5,number);
                min=Math.min(5,number);

                dp[idx][max*12+min]=dist;
            }
            else{
                for(int i=0;i<12;i++){
                    for(int j=i+1;j<12;j++){
                        int flag=j*12+i;

                        if(dp[idx-1][flag]==0) continue;

                        //i와 number
                        if(i!=number){
                            int dist=getDist(j,number);

                            int max=Math.max(i,number);
                            int min=Math.min(i,number);

                            if(dp[idx][max*12+min]==0){
                                dp[idx][max*12+min]=dp[idx-1][flag]+dist;
                            }
                            else{
                                dp[idx][max*12+min]=Math.min(dp[idx][max*12+min],dp[idx-1][flag]+dist);
                            }
                        }
                        //j와 number
                        if(j!=number){
                            int dist=getDist(i,number);

                            int max=Math.max(j,number);
                            int min=Math.min(j,number);

                            if(dp[idx][max*12+min]==0){
                                dp[idx][max*12+min]=dp[idx-1][flag]+dist;
                            }
                            else{
                                dp[idx][max*12+min]=Math.min(dp[idx][max*12+min],dp[idx-1][flag]+dist);
                            }
                        }
                    }
                }
            }
        }

        for(int i=0;i<12;i++){
            for(int j=i+1;j<12;j++){
                int flag=j*12+i;
                if(dp[N][flag]==0) continue;
                answer=Math.min(answer,dp[N][flag]);
            }
        }

        return answer;
    }

    public int getDist(int from,int to){
        int[] fromAxis=getAxis(from);
        int[] toAxis=getAxis(to);



        int dx=Math.abs(fromAxis[0]-toAxis[0]);
        int dy=Math.abs(fromAxis[1]-toAxis[1]);

        int cross=Math.min(dx,dy);
        int weight=cross*3+(Math.max(dx,dy)-cross)*2;

        return (weight==0)?1:weight;
    }

    public int getNumber(char number){
        int n;
        if(number=='*'){
            n=9;
        }
        else if(number=='#'){
            n=11;
        }
        else if(number=='0'){
            n=10;
        }
        else{
            n=number-'1';
        }

        return n;
    }

    public int[] getAxis(int num){
        int[] axis=new int[2];
        axis[0]=num/3;
        axis[1]=num%3;

        return axis;
    }
}