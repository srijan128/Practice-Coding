package graphprac;

public class VideoPair implements Comparable<VideoPair>{
    String id;
    int freq;

    public VideoPair(String id, int freq) {
        this.id = id;
        this.freq = freq;
    }


    @Override
    public int compareTo(VideoPair that) {
        if(this.freq== that.freq)
            return this.id.compareTo(that.id);
        return this.freq-that.freq;
    }
}
