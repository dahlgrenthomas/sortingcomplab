import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KMP_String_Matching {

    public static int longestDupSubstring(String s){
        String res = "";
        int g = 0;
        int indy = 1;
        for (int i = 0; i < s.length(); i++) {
            if(i + indy > s.length()){
                g++;
            }
            else if (s.substring(g,i).contains(s.substring(i, i + indy))){
                res = s.substring(i , i + indy);
                indy++;
                //System.out.println(i + "  " + (i+indy));
                System.out.println(res);
            }
        }
        return res.length();
    }
    // Driver program to test above function
    public static void main(String args[])
    {
        String txt = "100000010000001";
        // int length = txt.length();
        // int lps[] = new int[length];
        //double dub = (double)9/ (double) 3;
        System.out.println(longestDupSubstring(txt));
        for(int j = 0; j < txt.length()-1; j++){
            //System.out.println(lps[j]);
        }
    }

}
// This code has been contributed by Amit Khandelwal.