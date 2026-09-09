package revisionpp;

import abstractclass.A;

import java.util.ArrayList;
import java.util.List;



public class PracticePlace {

    // find min index and swap at the end of each inner loop iteration
    // target should be keep minimum at first
    public static void selectionSort(int [] a){
        int min=0;
        for(int i=0;i<a.length;i++){
            min=i;
            // trying to find the index with min value in each iteration
            for(int j=i+1;j<a.length;j++){
                if(a[j]<a[min]){
                    min=j;
                }
            }
            // swap once the min index is found with the i
            int temp=a[i];
            a[i]=a[min];
            a[min]=temp;
        }
        for(int i:a){
            System.out.print(i+",");
        }
    }

    // Bubble the largest element to last place
    public static void bubbleSort(int [] a){
        // 5,4,3,2,1
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
            if(i!=a.length-1)
                System.out.print(",");
        }
    }

    // Insertion sort
    // Try to insert the largest in the right place
    public static void insertionSort(int [] a){
        //5,4,3
        for(int i=0;i<a.length;i++){
            int j=i;
            while(j>0 && a[j-1]>a[j]){
                int temp=a[j];
                a[j]=a[j-1];
                a[j-1]=temp;
                j--;
            }
        }
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
            if(i!=a.length-1)
                System.out.print(",");
        }
    }

    public static void mergeSort(int [] a, int low,int high){
        // low==high is sufficient but just for safety check we use > so that if low crosses high we return
        if(low>=high) return;
        int mid=low+(high-low)/2;
        mergeSort(a,low,mid);
        mergeSort(a,mid+1,high);
        merge(a,low,mid,high);
    }

    public static void merge(int [] a, int low,int mid, int high){
        int left=low;
        int right=mid+1;
        List<Integer> temp=new ArrayList<>();
        while(left<=mid && right<=high){
            if(a[left]<=a[right]){
                temp.add(a[left]);
                left++;
            }else{
                temp.add(a[right]);
                right++;
            }
        }
        while(left<=mid){
            temp.add(a[left]);
            left++;
        }
        while(right<=high){
            temp.add(a[right]);
            right++;
        }

        for(int i=low;i<=high;i++){
            // i -low very imp
            a[i]=temp.get(i-low);
        }
    }

    // Better than merge sort as no extra space is required
    public static void quickSort(int [] a, int low, int high){
        if(low<high){
            // partition index is the pivot element being placed at correct position
            // lower than a[partition index] on the left
            // higher on the right
            int partitionIndex=findPartitionIndex(a,low,high);
            // left array sort
            quickSort(a,low,partitionIndex-1);
            // right array sort
            quickSort(a,partitionIndex+1,high);
        }
    }

    private static int findPartitionIndex(int[] a, int low, int high) {
        int pivot=a[low];
        int i=low;
        int j=high;
        while(i<j){
            while(a[i]<=pivot && i<high)
                i++;
            while(a[j]>pivot && j>low)
                j--;
            if(i<j) {
                int temp = a[i];
                a[i]=a[j];
                a[j]=temp;
            }

        }
        int temp1=a[low];
        a[low]=a[j];
        a[j]=temp1;
        return j;
    }




    public static void main(String[] args) {
        //insertionSort(new int [] {5,4,3,2,1});
        int [] a=new int[]{5,2,2,1,5,8,7};
        quickSort(a,0,a.length-1);
        for(int i:a){
            System.out.println(i);
        }
    }
}
