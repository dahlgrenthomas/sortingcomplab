import java.util.Arrays;

class KMP_String_Matching {

    public static int computeLPSArray(String pat)
    {
		int M = pat.length();
        int lps[] = new int[M];
		
        int len = 0;
        int i = 1;
        lps[0] = 0;
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                if(len <= M/2){
                    lps[i] = len;
                }
                else{lps[i] = 0;
                }
                i++;
            }
            else 
            {
                if (len != 0) {
                    len = lps[len - 1];

                }
                else 
                {
                    if(len <= M/2){
                        lps[i] = len;
                    }
                    else{lps[i] = 0;
                    }
                    i++;
                }
            }
        }

        return Arrays.stream(lps).max().getAsInt();
    }
  
    // Driver program to test above function
    public static void main(String args[])
    {
        String txt = "1110011100111001110";
        int length = txt.length();
        int lps[] = new int[length];
        
        System.out.println(length);
        System.out.println(computeLPSArray(txt, lps));
        for(int i = 0; i < length; i++){
            System.out.println(lps[i]);
        }



    }
}
// This code has been contributed by Amit Khandelwal.