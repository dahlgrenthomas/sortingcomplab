import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KMP_String_Matching {

    public static int longestDupSubstring(String s){
        String res = "";
        int g = 0;
        int fred = 1;

        for (int i = 0; i < s.length(); i++) {
            if(i + fred > s.length()){
                g++;
            }
            else if (s.substring(g,i).contains(s.substring(i, i + fred))){
                while(i + fred <= s.length() && s.substring(g,i).contains(s.substring(i, i + fred))){
                    res = s.substring(i , i + fred);
                    fred++;
                    //System.out.println(i + "  " + (i+indy));
                    System.out.println(res);
                    System.out.println("This is the base string "+ s.substring(g,i));
                }

            }

        }
        return res.length();
    }
    // Driver program to test above function
    public static void main(String args[])
    {
        String txt = "110000000000001000";
        // int length = txt.length();
        // int lps[] = new int[length];
        //double dub = (double)9/ (double) 3;
        System.out.println(longestDupSubstring(txt));
        System.out.println(Helper.lengthLongestRepeatedSubstring(txt));

    }

}
// This code has been contributed by Amit Khandelwal.