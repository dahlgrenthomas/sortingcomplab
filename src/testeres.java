import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KMP_String_Matching {

    public static int longestDupSubstring(String s){
        int res = 0;
        int g = 0;
        int fred = 1;

        for (int i = 0; i < s.length(); i++) {
            if(i + fred > s.length()){
                return res;
            }
            else if (s.substring(g,i).contains(s.substring(i, i + fred))){
                while(i + fred <= s.length() && s.substring(g,i).contains(s.substring(i, i + fred))){
                    res = fred;
                    fred++;
                    // System.out.println(res);
                    // System.out.println("This is the base string "+ s.substring(g,i));
                }

            }

        }
        return res;
    }
    // Driver program to test above function
    public static void main(String args[])
    {
        // String txt = Integer.toBinaryString(3);

        // System.out.println(longestDupSubstring(txt));
        // System.out.println(Helper.lengthLongestRepeatedSubstring(txt));

        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000000; i++){
            longestDupSubstring(Integer.toBinaryString(i));
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

}
