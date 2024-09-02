package Arrays;

import java.util.Arrays;

public class ArrayQuestions {
    public static void main(String[] args) {
        removeDuplicatesFromASortedArray(new int[] {1,1,2,2,2,3,3});
    }

    public static void removeDuplicatesFromASortedArray(int [] a){
        int i=0,j=1;
        while(i<a.length && j<a.length){
            if(a[i] ==a[j])
                j++;
            else if(a[i] != a[j]) {
                int temp = a[i+1];
                a[i+1]=a[j];
                a[j]=temp;
                i++;
                j++;
            }
        }
        for(int k=0;k<=i;k++)
            System.out.println(a[k]);
    }

    public static int BruteForcelongestSubarrayWithSumK(int [] a,int k) {
        return 0;
    }
}
