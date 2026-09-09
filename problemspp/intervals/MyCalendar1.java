package problemspp.intervals;

import java.util.TreeMap;

public class MyCalendar1 {

    TreeMap<Integer,Integer> map;

    public MyCalendar1() {
        map=new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        Integer key=map.lowerKey(endTime);
        if(key !=null && startTime<=map.get(key)-1)
            return false;
        map.put(startTime,endTime);
        return true;
    }
}
