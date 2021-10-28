public class substring {
    public static void main(String args[]){
        long start = System.currentTimeMillis();

        System.out.println(MaxRepeatNonOverlapSubstrDp("25251000252568010151515102315601510001213102102230502023020560undredhundredmillionbillion"));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    public static int longestDupSubstring(String s){
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(0,i).contains(s.substring(i - res.length(), i+1))) {
                res = s.substring(i - res.length(), i+1);
            }
        }
        return res.length();
    }
    //https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
    private static int MaxRepeatNonOverlapSubstrLps(String str)
    {
        var index = 0;
        var max = 0;
		int n = str.length();

        int[] lps = new int[n];
        for (var start = 0; start < n; start++)
        {
            var i = start;
            for (var j = i + 1; j < n; )
            {
                if (str.charAt(i) == str.charAt(j))
                {
                    // Added for non overlapping
                    if (j - i > i - start)
                    {
                        lps[j] = i - start + 1;
                        if (lps[j] > max)
                        {
                            max = lps[j];
                            index = start;
                        }

                        i++;
                        j++;
                    }
                    else
                    {
                        i = lps[i];
                    }
                }
                else if (i != start) // start is used instead of 0
                {
                    i = lps[i - 1];
                    if (i < start) // set the start
                    {
                        i = start;
                    }
                }
                else
                {
                    lps[j] = 0;
                    j++;
                }
            }
            lps = new int[n];
        }

        return max;
    }
    private static int MaxRepeatNonOverlapSubstrDp(String str)
    {
        int n = str.length();

        int[][] dp = new int[n + 1][n + 1];
        // dp [i,j] denotes the length of substring at i, j which is repeating 
        // and non overlapping
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
