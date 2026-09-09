package problemspp.sortcharbasedonfreq;

public class CustomPair implements Comparable<CustomPair>{
    public char c;
    public int freq;

   public CustomPair(char c, int freq){
        this.c=c;
        this.freq=freq;
    }

    public int compareTo(CustomPair that){
        return that.freq-this.freq;
    }

}
