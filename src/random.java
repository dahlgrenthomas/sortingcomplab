import java.util.ArrayList;
import java.util.Collections;

public class random {
    public static void main(String args[]){
        ArrayList<Integer> buckets = new ArrayList<>();
        ArrayList<Integer> sort = new ArrayList<>();
        buckets.add(100002);
        buckets.add(5);
        buckets.add(10003);
        buckets.add(2345);
        buckets.add(233);


        long start = System.nanoTime();
		
        for(int i = 0; i < buckets.size(); i++){
            int index = Collections.binarySearch(sort, buckets.get(i));
            sort.add(index, buckets.get(i));
        }
        long end = System.nanoTime();
        System.out.println(start - end);



    }
    
}
