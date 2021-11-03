import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KMP_String_Matching {

    public static int computeLPSArray(String pat, int lps[])
    {
		int M = pat.length();
		
        int i = 0;
        int j = 1;
        lps[0] = 0;
        while (j < M) {
            if (pat.charAt(j) == pat.charAt(i)) {
                i++;
                if(i <= M/2){
                    lps[j] = i;
                }
                else{lps[j] = 0;
                }
                j++;
            }
            else 
            {
                if (i != 0) {
                    i = lps[i - 1];

                }
                else 
                {
                    if(i <= M/2){
                        lps[j] = i;
                    }
                    else{lps[j] = 0;
                    }
                    j++;
                }
            }
        }

        return Arrays.stream(lps).max().getAsInt();
    }
    // Driver program to test above function
    public static void main(String args[])
    {
        String txt = "babana";
        // int length = txt.length();
        // int lps[] = new int[length];
        //double dub = (double)9/ (double) 3;
        System.out.println(repeatedSubstringPattern(txt));
        for(int j = 0; j < txt.length()-1; j++){
            //System.out.println(lps[j]);
        }
    }

}
// This code has been contributed by Amit Khandelwal.