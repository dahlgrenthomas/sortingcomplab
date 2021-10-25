public class substring {
    public static void main(String args[]){
        long start = System.currentTimeMillis();
        for(int i = 0; i<10000; i++){
            //MaxRepeatNonOverlapSubstrDp(Integer.toBinaryString(i*3000));
            longestRepeatedSubstring(Integer.toBinaryString(i*3000));
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    private static int MaxRepeatNonOverlapSubstrDp(String str)
    {
        int n = str.length();

        int[][] dp = new int[n + 1][n + 1];
        // dp [i,j] denotes the length of substring at i, j which is repeating 
        // and non overlapping

        var index = 0;
        var max = 0;

        for (var i = 1; i <= n; i++)
        {
            for (var j = i + 1; j <= n; j++)
            {
                // essential condition to see if the elements repeats
                if (str.charAt(j-1) == str.charAt(i-1) && (j - i) > dp[i - 1][j - 1]){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (dp[i][j] > max)
                    {
                        max = dp[i][j];
                        index = i - max;
                    }
                }
            }
        }
        return max;
    }
    public static int longestRepeatedSubstring(String str) {
        int n = str.length();
        int LCSRe[][] = new int[n + 1][n + 1];
 
        String res = "";
        int res_length = 0;
 
        int i, index = 0;
        for (i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1)
                        && LCSRe[i - 1][j - 1] < (j - i)) {
                    LCSRe[i][j] = LCSRe[i - 1][j - 1] + 1;
                    if (LCSRe[i][j] > res_length) {
                        res_length = LCSRe[i][j];
                        index = Math.max(i, index);
                    }
                } else {
                    LCSRe[i][j] = 0;
                }
            }
        }
 
        if (res_length > 0) {
            for (i = index - res_length + 1; i <= index; i++) {
                res += str.charAt(i - 1);
            }
        }
 
        return res.length();
    }
    
}
