import java.util.Arrays;

public class LRS {

    // return the longest common prefix of s and t
    public static String lcp(String s, String t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i))
                return s.substring(0, i);
        }
        return s.substring(0, n);
    }

    public static int lengthLongestRepeatedSubstring(String binary) {
		int length = 0;
		// iterate over possible lengths
		// the longest length is length/2 (rounded down) since they are non-overlapping
		for (int n = 1; n <= Math.floor(binary.length()/2.0); ++n) {
			//System.out.println("n = " + n);
			boolean found = false;
			// first index (the first index of the first copy): 
			for (int i = 0; i < binary.length() - 2*n + 1; ++i) {
				// second index (substrings are non-overlapping):
				for (int j = i + n; j < binary.length() - n + 1; ++j) {
					// iterating over the substring length:
					int k = 0; // need the index after the loop to see if it finished
					for (; k < n; k++) {
						//System.out.println("i = " + i + ", j = " + j + ", k = " + k);
						if (binary.charAt(i + k) != binary.charAt(j + k)) {
							break;
						}
					}	
					//System.out.println("k = " + k + ",n = " + n);
					if (k == n) {
						found = true; 
					}
				}
			}
			//System.out.println(found);
			if (found) {
				length++;
			} else {
				return length;
			}
		}
		
		return length;
	}
    // return the longest repeated string in s
	public static int lengthLongestRepeatedSubstring2(final String s) {
        if (s.length() == 1) {
            return 0;
        }
        int n = 0;
        int n2 = 1;
        for (String s2 = s.substring(n, n2), s3 = s.substring(n2); s3.contains(s2) || s3.length() > n2 - n; s3 = s.substring(n2)) {
            if (s3.contains(s2)) {
                s2 = s.substring(n, ++n2);
            }
            else {
                s2 = s.substring(++n, ++n2);
            }
        }
        return n2 - n - 1;
    }



    // read in text, replacing all consecutive whitespace with a single space
    // then compute longest repeated substring
    public static void main(String[] args) {
        boolean result = true;
        for(int i = 0; i < 10000000; i++){
            if(lengthLongestRepeatedSubstring(Integer.toBinaryString(i)) != lengthLongestRepeatedSubstring2(Integer.toBinaryString(i))){
                result = false;
            }
        }
        System.out.println(result);

    }
}