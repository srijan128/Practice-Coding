package problemspp.intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class MyCalendarTwo {
    private List<int[]> bookings;
   private TreeMap<Integer,Integer> overlappedMap;

   private TreeMap<Integer,Integer> sweepLineMap;

   private int maxAllowed;
    public MyCalendarTwo() {
        bookings = new ArrayList<>();
        overlappedMap = new TreeMap<>();
        sweepLineMap=new TreeMap<>();
        maxAllowed=2;
    }



    public boolean book(int start, int end) {
        //if event is present in overlapped then return false
        Integer prevVal = overlappedMap.lowerKey(end);
        if(prevVal!=null && start <= overlappedMap.get(prevVal)-1){
            return false;
        }
        // insert into bookings and if it is overlapping with
        // any booking then insert into overlapped map
        for(int booking[] : bookings){
            // booking[0], start
            // booking[1], end
            int commStart = Math.max(booking[0],start);
            int commEnd = Math.min(booking[1],end);
            if(commStart<commEnd){
                overlappedMap.put(commStart,commEnd);
            }
        }
        bookings.add(new int[]{start,end});
        return true;
    }

    // Sweep Line Algo
    public boolean bookSweepLine(int first, int last){
        sweepLineMap.put(first,sweepLineMap.getOrDefault(first,0)+1);
        sweepLineMap.put(last,sweepLineMap.getOrDefault(last,0)-1);
        int overlappedCount=0;
        for(Map.Entry<Integer,Integer> entry:sweepLineMap.entrySet()){
            overlappedCount+=entry.getValue();
            if(overlappedCount>maxAllowed){
                sweepLineMap.put(first,sweepLineMap.get(first)-1);
                sweepLineMap.put(last,sweepLineMap.get(last)+1);

                if(sweepLineMap.get(first)==0){
                    sweepLineMap.remove(first);
                }

                if(sweepLineMap.get(last)==0){
                    sweepLineMap.remove(last);
                }
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo m=new MyCalendarTwo();
        System.out.println(m.book(20,30));
        System.out.println(m.book(25,35));
        System.out.println(m.book(24,31));
    }
}
