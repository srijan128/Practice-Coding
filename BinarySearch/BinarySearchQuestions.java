package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchQuestions {
    public static void main(String[] args) {
        int [] a=new int[]{1,2,3,5,6,8};
        System.out.println(binarySearchIterative(new int[]{1,2,3,5,6,8},5));
        System.out.println(binarySearchRecursive(a,5,0, a.length-1));

        System.out.println(findLowerBound(a,7));
        System.out.println(findFloorInAnArray(new int[]{3,17,23},12));
        System.out.println(Arrays.toString(firstAndLastOccurrenceOfAElement(new int[]{2, 4, 6, 8, 8, 8, 11, 13}, 8)));
    }

    public static int binarySearchIterative(int [] a,int element){
        int low =0,high=a.length-1;
        while(low<=high){
            int mid =low+(high-low)/2;
            if(a[mid]==element){
                return mid;
            }else if(a[mid]>element)
                high=mid-1;
            else low=mid+1;
        }
        return -1;
    }

    public static int binarySearchRecursive(int [] a,int element,int low,int high){
        if(low>high)
            return -1;
        int mid=low+(high-low)/2;
        if(a[mid]==element)
            return mid;
        else if(a[mid]>element)
           return binarySearchRecursive(a,element,low,mid-1);
        else return binarySearchRecursive(a,element,mid+1,high);

    }

    // Given an element x, find the smallest index a[index]>=x
    // if the lower bound doesn't exist its hypothetical index is array length
    public static int findLowerBound(int [] a,int x){
        int ans =a.length;
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]>=x) {
                ans = mid;
                high = mid - 1;
            }
            else if(a[mid]<x){
                low=mid+1;
            }
        }
        return ans;
    }

    // Given an element x, find the smallest index a[index]>x
    // if the upper bound doesn't exist its hypothetical index is array length
    public static int findUpperBound(int [] a,int x){
        int ans =a.length;
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]>x) {
                ans = mid;
                high = mid - 1;
            }
            else {
                low=mid+1;
            }
        }
        return ans;
    }

/*
Problem statement
You are given a sorted array 'arr' of distinct values and a target value 'm'.
You need to search for the index of the target value in the array.

Note:
1. If the value is present in the array, return its index.
2. If the value is absent, determine the index where it would be inserted in the array while maintaining the sorted order.
3. The given array has distinct integers.
4. The given array may be empty.
 */
    public static int searchInsert(int [] a, int x){
        // Write your code here.
        int ans =a.length;
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]>=x) {
                ans = mid;
                high = mid - 1;
            }
            else if(a[mid]<x){
                low=mid+1;
            }
        }
        return ans;
    }

    //Smallest Number greater than equal to given number
    public static int findCeilingInAnArray(int [] a, int x){
        // Write your code here.
        int ans =a.length;
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]>=x) {
                ans = a[mid];
                high = mid - 1;
            }
            else if(a[mid]<x){
                low=mid+1;
            }
        }
        return (ans!=a.length) ? ans:-1;
    }


    //largest number smaller than equal to given input x
    public static int findFloorInAnArray(int [] a, int x){
        // Write your code here.
        int ans =-1;
        int low=0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]>x) {
                high = mid - 1;
            }
            else if(a[mid]<=x){
                ans = a[mid];
                low=mid+1;
            }
        }
        return ans;
    }

    public static int [] firstAndLastOccurrenceOfAElement(int [] a,int x){
        //2 4 6 8 8 8 11 13
        int low=0,high=a.length-1;
        int firstOccurrence=-1;
        int lastOccurrence=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]>x){
                high=mid-1;
            }else if(a[mid]<x) {
                low=mid+1;
            } else {
                firstOccurrence=mid;
                high=mid-1;
            }
        }
        int low1=0,high1=a.length-1;
        while(low1<=high1){
            int mid=low1+(high1-low1)/2;
            if(a[mid]>x){
                high1=mid-1;
            }else if(a[mid]<x) {
                low1=mid+1;
            }else {
                lastOccurrence=mid;
                low1=mid+1;
            }
        }

        System.out.println("fr" + firstOccurrence);
        System.out.println("lr" + lastOccurrence);
        return new int[]{firstOccurrence,lastOccurrence};
    }

    public static int searchInARotatedSortedArray(int [] a,int x){
        //12 15 18 2 4
        int low =0,high=a.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]==x) return mid;
            if(a[low]<=a[mid]){
                if(x>=a[low] && x<=a[mid]){
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }else {
                if(x>=a[mid] && x<=a[high]){
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
        }
        return -1;
    }

    public static boolean searchInARotatedSortedArrayContainingDuplicates(int []A, int key) {
        int low =0;
        int high=A.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(A[mid]==key) return true;
            if(A[low]==A[mid] && A[mid]==A[high]){
                low++;
                high--;
                continue;
            }
            if(A[low]<=A[mid]){
                if(A[low]<=key && key<=A[mid])
                    high=mid-1;
                else
                    low=mid+1;
            } else if(A[low]>A[mid]){
                if(A[mid]<=key && key<=A[high])
                    low=mid+1;
                else
                    high=mid-1;

            }
        }
        return false;
    }

    public static int minimumInARotatedSortedArray(int [] a){
        int low=0,high=a.length-1,min=Integer.MAX_VALUE;
        while(low<=high){
            int mid=low+(high-low)/2;
            //In case the array is sorted. Binary search is not required
            if(a[low]<=a[high]){
                min=Math.min(min,a[low]);
                break;
            }
            if(a[low]<=a[mid]){
                min=Math.min(min,a[low]);
                mid=low+1;
            }{
                min=Math.min(min,a[mid]);
                high=mid-1;
            }
        }
        return min;
    }

    public static int findOutTimesTheArrayIsRotated(int [] a){
        // 3 4 5 1 2
        int low=0,high=a.length-1,min=Integer.MAX_VALUE,index=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[low]<=a[high]){
               if(a[low]<min){
                   index=low;
                   min=a[low];
               }
                break;
            }
            if(a[low]<=a[mid]){
                if(a[low]<min){
                    index=low;
                    min=a[low];
                }
                mid=low+1;
            }{
                if(a[mid]<min){
                    index=mid;
                    min=a[mid];
                }
                high=mid-1;
            }
        }
        return index;
    }

    public static int singleElementInASortedArray(int [] a){
        // 1 1 2 2 3 3 4 5 5 6 6
        if(a.length==1)
            return a[0];
        if(a[a.length-1] !=a[a.length-2])
            return a[a.length-1];
        if(a[0] !=a[1])
            return a[0];
        int low=1,high=a.length-2;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid] != a[mid-1] && a[mid] !=a[mid+1])
                return mid;
            //left hand side even odd index of elements
            else if(mid%2==0 && a[mid+1]==a[mid] || mid%2==1 && a[mid-1]==a[mid])
                low=mid+1;
            //right hand side odd even index of elements
            else high=mid-1;
        }
        return -1;
    }

    public static int findPeakElement(ArrayList<Integer> arr) {
        // 1 8 1 5 3
        if(arr.size()==1) return 0;
        if(arr.get(0)>arr.get(1))
            return 0;
        if(arr.get(arr.size()-1)>arr.get(arr.size()-2))
            return arr.get(arr.size()-1);
        int low=1,high=arr.size()-2;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr.get(mid)>arr.get(mid-1) && arr.get(mid)>arr.get(mid+1))
                return mid;
            else if(arr.get(mid)>arr.get(mid-1) && arr.get(mid)<arr.get(mid+1))
                low=mid+1;
            else
                high=mid-1;
        }
        return -1;
    }
}
