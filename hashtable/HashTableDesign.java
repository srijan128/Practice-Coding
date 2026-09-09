package hashtable;

import java.util.ArrayList;
import java.util.List;

public class HashTableDesign {

    private int size=16;
    private HashTableNode [] hashTableNodes;

    public class HashTableNode{
        private String key;
        private Integer value;

        private HashTableNode next;

        public HashTableNode(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTableDesign() {
        this.hashTableNodes = new HashTableNode[this.size];
    }

    public int hash(String key){
        int hash=0;
        char [] keyArray=key.toCharArray();
        for(char c:keyArray){
            int ascii= Character.getNumericValue(c);
            hash=(hash+ ascii*23);
        }
        return hash%hashTableNodes.length;
    }

    public void print(){
        for(int i=0;i<hashTableNodes.length;i++){
            System.out.println(i+":");
            HashTableNode temp=hashTableNodes[i];
            while(temp!=null){
                System.out.println("   {" + temp.key + "= " + temp.value + "}");
                temp=temp.next;
            }
        }
    }

    public void set(String key, int value){
        int index=hash(key);
        HashTableNode newNode=new HashTableNode(key,value);
        if(hashTableNodes[index] ==null){
            hashTableNodes[index]=newNode;
        }else{
            HashTableNode temp=hashTableNodes[index];
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=newNode;
        }
    }

    public int get(String key){
        int index=hash(key);
        HashTableNode temp=hashTableNodes[index];
        while(temp!=null){
            if(temp.key==key)
                return temp.value;
            temp=temp.next;
        }
        return 0;
    }

    public List<String> getAllKeys(){
        List<String> keyList=new ArrayList<>();
        for(int i=0;i<hashTableNodes.length;i++){
            HashTableNode temp=hashTableNodes[i];
            while(temp!=null){
                keyList.add(temp.key);
                temp=temp.next;
            }
        }
        return keyList;
    }
}
