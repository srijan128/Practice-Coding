package heaps;

import java.util.PriorityQueue;

public class Heap {

    public int [] heap;
    public int heapSize;

    public int capacity;

    public Heap(int [] a){
     heap=a;
     heapSize=a.length;
     capacity=a.length;
    }

    public void buildHeap(){
        for(int i=(heapSize/2)-1;i>=0;i--){
            heapify(i);
        }
    }

    public void swap(int i,int j){
        int temp=heap[i];
        heap[i]=heap[j];
        heap[j]=temp;
    }

    public void heapify(int index) {
        int largest=index;
        int leftChildIndex=2*index+1;
        int rightChildIndex=2*index+2;
        if(leftChildIndex<heapSize && heap[leftChildIndex]>heap[largest]){
            largest=leftChildIndex;
        }
        if(rightChildIndex<heapSize && heap[rightChildIndex]>heap[largest]){
            largest=rightChildIndex;
        }
        if(largest != index){
            swap(largest,index);
            heapify(largest);
        }
    }

    public void printTree(){
        for(int i=0;i<heapSize;i++){
                System.out.print(heap[i]);
                if(i!=heapSize-1)
                    System.out.print(",");
        }
        System.out.println();
    }

    public int extractMax(){
        if(heapSize==0){
            System.out.println("Heap is empty");
            return -1;
        }
        int max=heap[0];
        heap[0]=heap[heapSize-1];
        heapSize--;
        heapify(0);
        return max;
    }

    public void extractMaxAndStore(){
        if(heapSize==0){
            System.out.println("Heap is empty");
        }
        int max=heap[0];
        heap[0]=heap[heapSize-1];
        heap[heapSize-1]=max;
        heapSize--;
        heapify(0);
    }

    public void increaseKey(int index, int value){
        if(index<0 || index>=heapSize || heap[index]>=value){
            System.out.println("Wrong operation");
        }
        heap[index]=value;
        int parentIndex=(int)Math.ceil(index/2.0-1);
        while(index>0 && heap[index]>heap[(int)Math.ceil(index/2.0-1)]){
            swap(index,(int)Math.ceil(index/2.0-1));
            index=(int)Math.ceil(index/2.0-1);
        }
    }

    public void decreaseKey(int index,int value){
        if(index<0 || index>=heapSize || heap[index]<=value){
            System.out.println("Wrong operation");
        }
        heap[index]=value;
        heapify(index);
    }

    // Insertion
    public void insertInHeap(int value){
        if(heapSize+1>capacity){
            increaseCapacity();
        }
        heapSize+=1;
        heap[heapSize-1]=value;
        int index=heapSize-1;
        while(index>0 && heap[index]>heap[(int)Math.ceil(index/2.0-1)]){
            swap(index,(int)Math.ceil(index/2.0-1));
            index=(int)Math.ceil(index/2.0-1);
        }
    }

    private void increaseCapacity() {
        capacity*=2;
        int [] newHeap=new int[capacity];
        for(int i=0;i<heapSize;i++){
            newHeap[i]=heap[i];
        }
        heap=newHeap;
    }

    public int[] heapSort(){
        buildHeap();
        int size=heapSize;
        for(int i=0;i<size;i++){
            extractMaxAndStore();
        }
        return heap;
    }


    public static void main(String[] args) {
        int [] a=new int[]{10,5,20,6,11};
        Heap h=new Heap(a);
        int [] res = h.heapSort();
        for(int i:res){
            System.out.print(i + " , ");
        }
       /* h.buildHeap();
        h.printTree();
      //  System.out.println("removed max element " + h.extractMax());
       // h.printTree();
        h.increaseKey(4,15);
        h.printTree();
        h.decreaseKey(0,4);
        h.printTree();
        h.insertInHeap(18);
        h.printTree(); */
    }
}
