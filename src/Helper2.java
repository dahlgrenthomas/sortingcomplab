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
	
    private static int binaryLength(int i) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(i);
      }
	public static int lengthLongestRepeatedSubstring(int number) {
        int binaryLength = binaryLength(number);
            int longestSubstringLengthSoFar = 0;
            // iterate over possible lengths
            // the longest length is length/2 (rounded down) since they are non-overlapping
            for (int n = 1; n <= binaryLength / 2; ++n) {
          // An integer with the lower n bits set.
          int mask = (1 << n) - 1;
    
          int upperBoundForI = binaryLength - 2 * n + 1;
          int upperBoundForJ = binaryLength - n + 1;
    
                boolean found = false;
                // first index (the first index of the first copy):
          lookingForSubstringOfLengthN:
                for (int i = 0; i < upperBoundForI; i++) {
                    // second index (substrings are non-overlapping):
                    for (int j = i + n; j < upperBoundForJ; j++) {
              if (
                ((number >> i) & mask)
                == ((number >> j) & mask)
              ) {
                            found = true;
                break lookingForSubstringOfLengthN;
                        }
                    }
                }
    
          if (found) {
                    longestSubstringLengthSoFar = n;
                } else {
                    return longestSubstringLengthSoFar;
                }
            }
    
            return longestSubstringLengthSoFar;
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
