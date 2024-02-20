class Solution {
    int[][] minimum_step;
    int[][][] dp;
    String tmp;

    int memo_dp(int idx, int left, int right) {
        if (idx >= tmp.length())
            return 0;

        if (dp[idx][left][right] != 0) return dp[idx][left][right];

        int cur = tmp.charAt(idx) - '0';

        if (left == cur || right == cur) {
            return 1 + memo_dp(idx + 1, left, right);
        }

        return dp[idx][left][right] = Math.min(memo_dp(idx + 1, cur, right) + minimum_step[left][cur], memo_dp(idx + 1, left, cur) + minimum_step[right][cur]);
    }
    
    public int solution(String numbers) {
        
        int n = numbers.length();
        minimum_step = new int[10][10];
        dp = new int[n+1][10][10];
        
        for (int i = 0; i < 10; i++) {
            int r, c;
            if (i == 0){
                r = 3;
                c = 1;
            }
            else{
                r = (i - 1) / 3;
                c = (i - 1) % 3;
            }
            for (int j = 0; j < 10; j++) {
                if (i == j) {
                    minimum_step[i][j] = 1;
                    continue;
                }
                int nr, nc;
                if (j == 0){
                    nr = 3;
                    nc = 1;
                }
                else{
                    nr = (j - 1) / 3;
                    nc = (j - 1) % 3;
                }
                int diffr = Math.abs(r - nr), diffc = Math.abs(c - nc);

                minimum_step[i][j] = 3 * Math.min(diffr, diffc) + 2 * Math.abs(diffr - diffc);
            }
        }
        tmp = numbers;

	    return memo_dp(0, 4, 6);
    }
}
