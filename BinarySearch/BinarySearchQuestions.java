package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchQuestions {
    public static void main(String[] args) {
        int [] a=new int[]{1,2,3,5,6,8};
        //System.out.println(Math.ceil((double) 2 /3));
        System.out.println(leastWeightCapacity(new int[]{1,2,3,4,5,6,7,8,9,10},5));
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

    public static int findNthRootUsingBinarySearch(int n, int m) {
        // Write your code here.
        if(m==0) return 0;
        int low=1,high=m;
        while(low<=high){
            int mid=low+(high-low)/2;
            int midN=calcPower(mid,n,m);
            if(midN==1) return mid;
            else if(midN==2) high=mid-1;
            else low=mid+1;
        }
        return -1;
    }

    public static int calcPower(int mid,int n,int m){
        long ans=1;
        //handling overflow scenario
        // anytime the multiplication exceeds the given value return 2 to handle overflow
        for(int i=1;i<=n;i++){
            ans=ans*mid;
            if(ans>m){
                return 2;
            }
        }
        if(ans==m) return 1;
        return 0;
    }


    /*
    A monkey is given ‘n’ piles of bananas, where the 'ith' pile has ‘a[i]’ bananas. An integer ‘h’ is also given,
     which denotes the time (in hours) in which all the bananas should be eaten.
Each hour, the monkey chooses a non-empty pile of bananas and eats ‘m’ bananas.
If the pile contains less than ‘m’ bananas,
then the monkey consumes all the bananas and won’t eat any more bananas in that hour.

Find the minimum number of bananas ‘m’ to eat per hour so that the monkey can eat all the bananas within ‘h’ hours.

Example:
Input: ‘n’ = 4, ‘a’ =  [3, 6, 2, 8] , ‘h’ = 7
Output: 3
Explanation: If ‘m’ = 3, then
The time taken to empty the 1st pile is 1 hour.
The time taken to empty the 2nd pile is 2 hour.
The time taken to empty the 3rd pile is 1 hour.
The time taken to empty the 4th pile is 3 hour.
Therefore, a total of 7 hours is taken.
It can be shown that if the rate of eating bananas is reduced, they can’t be eaten in 7 hours.
     */
    public static int minimumRateToEatBananas(int []a, int givenHours) {
        //a = 3 6 2 8 givenHours=7
        int high=Integer.MIN_VALUE;
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<a.length;i++){
            high=Math.max(high,a[i]);
        }
        int low=1;
        while(low<=high){
            int mid=low+(high-low)/2;
            int totalTimeTakenConsideringMidAsAnswer=findTimeTaken(mid,a);
            if(totalTimeTakenConsideringMidAsAnswer<=givenHours) {
                ans = mid;
                high=mid-1;
            }
            else low=mid+1;

        }
        return ans;
    }


    public static int findTimeTaken(int mid, int[] a) {
        int totalTimeTaken=0;
        for(int i=0;i<a.length;i++){
            totalTimeTaken+= (int) Math.ceil((double)a[i] / (double)mid);
        }
        return totalTimeTaken;
    }

        /*
    Problem statement
You are given 'n' roses and you are also given an array 'arr' where 'arr[i]'
denotes that the 'ith' rose will bloom on the 'arr[i]th' day.

You can only pick already bloomed roses that are adjacent to make a bouquet.
You are also told that you require exactly 'k' adjacent bloomed roses to make a single bouquet.

Find the minimum number of days required to make at least 'm' bouquets each containing 'k' roses.
Return -1 if it is not possible.
Example :
Input: n = 9,  arr = [ 1, 2, 1, 2, 7, 2, 2, 3, 1 ], k = 3, m = 2
Output: 3.

Explanation: This is because on the 3rd day: all the roses with 'arr[i]' less than equal to 3 have already
bloomed, this means every rose except the 5th rose has bloomed. Now we can form the first bouquet from the
first three roses and the second bouquet from the last three roses.
     */
public static int roseGarden(int[] a, int noOfFlowersRequiredPerBouquet, int minNoOfBouquets) {
        int low=Integer.MAX_VALUE;
        int high=Integer.MIN_VALUE;
        if(noOfFlowersRequiredPerBouquet*minNoOfBouquets>a.length)
            return -1;
        for(int i=0;i<a.length;i++){
            low=Math.min(a[i],low);
        }

        for(int i=0;i<a.length;i++){
        high=Math.max(a[i],high);
        }
        //Range Calculation done
        while(low<=high){
            int mid=low+(high-low)/2;
            if(checkMinNoOfBouquetsForADay(a,mid,noOfFlowersRequiredPerBouquet,minNoOfBouquets)){
                high=mid-1;
            }else low=mid+1;
        }
        return low;
    }
    private static boolean checkMinNoOfBouquetsForADay(int[] a, int mid, int noOfFlowersRequiredPerBouquet, int minNoOfBouquets) {
        int noOfBouquets=0,count=0;
        for(int i:a){
            if(i<=mid)
                count++;
            else{
                noOfBouquets+=count/noOfFlowersRequiredPerBouquet;
                count=0;
            }
        }
        //check for the last outstanding flowers in the array when else is not evaluated
        noOfBouquets+=count/noOfFlowersRequiredPerBouquet;
        return noOfBouquets==minNoOfBouquets;
    }

    /*
You are given an array of integers 'arr' and an integer 'limit'.

Your task is to find the smallest positive integer divisor, such that upon dividing all the
elements of the given array by it, the sum of the division's result is less than or equal
to the given integer's limit.

Note:
Each result of the division is rounded to the nearest integer greater than or equal
to that element. For Example, 7/3 = 3.
     */
    public static int smallestDivisor(int arr[], int limit) {
        // Write your coder here
        int low=1,high=findMax(arr);
        while(low<=high){
            int mid=low+(high-low)/2;
            if(checkSumWithLimit(arr,limit,mid))
                high=mid-1;
            else
                low=mid+1;
        }
        return low;
    }

    private static int findMax(int [] a){
        int max=Integer.MIN_VALUE;
        for(int i:a)
            max=Math.max(max,i);
        return max;
    }

    private static boolean checkSumWithLimit(int [] a,int limit,int mid){
        int sum=0;
        for(int i:a){
            sum+=Math.ceil((double)i/(double)mid);
            if(sum>limit) return false;
        }
        return sum<=limit;
    }

    /*
    You are the owner of a Shipment company. You use conveyor belts to ship packages
    from one port to another. The packages must be shipped within 'd' days.
The weights of the packages are given in an array 'weights'.
The packages are loaded on the conveyor belts every day in
the same order as they appear in the array. The loaded weights must not exceed the maximum weight
capacity of the ship.
Find out the least-weight capacity so that you can ship all the packages within 'd' days.
//5 4 5 2 3 4 5 6
     */

    public static int leastWeightCapacity(int[] weights, int d) {
        // Write your code here.
        int sum=0;
        int low=findMax(weights);
        for(int i:weights)
            sum+=i;
        int high=sum;
        while(low<=high){
            int mid=low+(high-low)/2;
            int noOfDays=checkDaysRequiredBasedOnCapacity(mid, weights, d);
            if(noOfDays<=d)
                high=mid-1;
            else
                low=mid+1;
        }
        return low;
    }


    private static int checkDaysRequiredBasedOnCapacity(int mid,int [] a,int d){
        int days=1,sumOfWeights=0;
        for(int i=0;i<a.length;i++){
            if(sumOfWeights+a[i]>mid) {
                days++;
                sumOfWeights = a[i];
            }
            else {
             //   day++;
                sumOfWeights+=a[i];
               // sumOfWeights=0;
            }
        }
        return days;
    }

}
