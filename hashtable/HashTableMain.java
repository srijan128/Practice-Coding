package hashtable;

import java.util.HashMap;

public class HashTableMain {

    public static void main(String[] args) {
        HashTableDesign hd=new HashTableDesign();
       // HashTableDesign.HashTableNode hashTableNode=hd.new HashTableNode("name 1",1);
       // HashTableDesign.HashTableNode hashTableNode1=hd.new HashTableNode("name 2",2);
        hd.set("name 1",1);
        hd.set("name 2",2);
        hd.set("name 4",3);
        hd.set("name 40",3);
        System.out.println("hash val =" + hd.hash("name 40"));
        hd.print();
        System.out.println(hd.get("name 1"));
        System.out.println(hd.getAllKeys());
    }

    public Character firstNonRepeatingChar(String s){
        HashMap <Character,Integer> freq=new HashMap<>();
        Character ans=' ';
        for(char c:s.toCharArray()){
            freq.put(c,freq.getOrDefault(c,0)+1);
        }
        for(char c: s.toCharArray()){
            if(freq.get(c)==1){
                ans=c;
                break;
            }
        }
        return ans!=' '?ans:null;
    }
}
