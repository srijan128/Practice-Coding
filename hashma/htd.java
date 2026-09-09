package hashma;

import java.util.ArrayList;
import java.util.List;

public class htd {
    private int size=16;
    private Nod [] dataSet;

    class Nod{
        String key;
        int value;

        Nod next;

        public Nod(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public htd(Nod[] dataSet) {
        this.dataSet = new Nod[this.size];
    }

    public int hash(String key){
        int prime=23;
        int sum=0;
        char [] ch=key.toCharArray();
        for(char c: ch){
            sum+=Character.getNumericValue(c)*23;
        }
        return sum%dataSet.length;
    }

    public boolean containsKey(String key){
        int index=hash(key);
       Nod n=dataSet[index];
       while(n!=null){
           if(n.key.equals(key))
               return true;
           n=n.next;
       }
       return false;
    }

    public List<String> getAllKeys(){
        List<String> keysList=new ArrayList<>();
       for(int i=0;i<dataSet.length;i++){
           Nod temp=dataSet[i];
           while(temp!=null) {
               keysList.add(temp.key);
               temp = temp.next;
           }
       }
       return keysList;
    }

    public void set(String key, int value){
        int index=hash(key);
        Nod newNode=new Nod(key,value);
        if(dataSet[index]==null){
            dataSet[index]=newNode;
        }else{
            Nod temp=dataSet[index];
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=newNode;
        }
    }
}
