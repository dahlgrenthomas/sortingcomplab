public class Helper2 {
	
	public static void main(String [] args) {
		// Testing helper methods
		
	}
	
	/*
	 * Takes an integer number and returns the number of 1s
	 * in its binary representation. 
	 */
	public static int numBinaryOnes(int n){
		String binary = Integer.toBinaryString(n);
		
		//System.out.println(binary);
		
		int count = 0;
		for (int i = 0; i < binary.length(); ++i) {
			if (binary.charAt(i) == '1') {
				count++;
			}
		}
		
		return count;
	}
	
	
	public static int lengthLongestRepeatedSubstring(String binary) {
        int n = binary.length();

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
                if (binary.charAt(j-1) == binary.charAt(i-1) && (j - i) > dp[i - 1][j - 1]){
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
	public static int longestRepeatedSubstring(int number) {
		String binary = Integer.toBinaryString(number);
        int n = binary.length();
        int LCSRe[][] = new int[n + 1][n + 1];
 
        String res = "";
        int res_length = 0;
 
        int i, index = 0;
        for (i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (binary.charAt(i - 1) == binary.charAt(j - 1)
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
                res += binary.charAt(i - 1);
            }
        }
 
        return res.length();
    }

}
