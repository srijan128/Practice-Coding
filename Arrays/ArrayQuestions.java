package Arrays;

import java.util.*;

public class ArrayQuestions {
    public static void main(String[] args) {
        removeDuplicatesFromASortedArray(new int[] {1,1,2,2,2,3,3});
        System.out.println(longestSubarrayWithSumK(new int[]{1,2,3,1,1,1,1,4,2,3},3));
        System.out.println(findMajorityElement(new int[]{2,2,1,3,1,1,3,1,1}));
        System.out.println(maxSubarraySum(new int[]{-2,-3,4,-1,-2,1,5,-3}));
        System.out.println(rearrangeElementsBySign());
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, -4, -5));
       // ArrayList<Integer> ans = RearrangebySign(new int []{1,2,-4,-5,3,6});

     //   ans.stream().forEach(System.out::println);
      //  System.out.println(rearrangeElementsBySignVariety2(new int[]{1,2,-4,-5,3,6,-7}));
       // nextPermutationInAArray(new int[]{2,1,5,4,3,0,0});
       // nextPermutationInAArrayList(Arrays.asList(2,1,5,4,3,0,0));
        System.out.println(leadersInAnArray(new int[]{10,22,12,3,0,6}));
        System.out.println("-------------------------");
        System.out.println(optimalLongestConsecutiveSequenceInAGivenArray(new int[]{102,4,100,1,101,3,2,1,1}));
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
        int maxLen=0;
        for(int i=0;i<a.length;i++){
            int sum=0;
            for(int j=i;j<a.length;j++){
                sum+=a[j];
                if(sum==k)
                    maxLen=Math.max(maxLen,j-i+1);
            }
        }
        return maxLen;
    }

    public static int HashinglongestSubarrayWithSumK(int [] a,int k) {
        int maxLen=0,sum=0;
        Map<Integer,Integer> map =new HashMap<>();
        for(int i=0;i<a.length;i++){
            sum+=a[i];
            if(sum==k)
                maxLen=Math.max(maxLen,i+1);
            if(map.containsKey(sum-k)){
                maxLen=Math.max(maxLen,i-map.get(sum-k));
            }
            //else {
            // As we are trying to find the longest array, we dont update the key in the map
            // if the sum is same
            if(!map.containsKey(sum))
                map.put(sum,i);
          //  }
        }
        return maxLen;
    }

    public static int longestSubarrayWithSumK(int [] a, int k){
        int l=0,r=0,sum=0,maxLen=0;
        while(r<a.length){
            sum +=a[r];
            if(sum>k){
                while(sum>k){
                    sum-=a[l];
                    l++;
                }
            }
            if(sum==k){
                maxLen=Math.max(maxLen,r-l+1);
            }
            r++;
        }
        return maxLen;
    }

    // returned index of majority element
    // Moore's Voting Algo
    public static int findMajorityElement(int [] a){
        int res=0,count=1;
        for(int i=1;i<a.length;i++){
            if(a[i]==a[res]){
                count++;
            } else {
                count--;
            }
            if(count==0){
                res=i;
                count=1;
            }
        }
        count=0;
        for(int j=0;j<a.length;j++){
            if(a[j]==a[res]){
                count++;
            }
        }
        if(count>a.length/2)
            return res;
        return -1;
    }

    //Kadane's Algo
    public static int maxSubarraySum(int [] a){
        int sum=a[0];
        int maxSum=Integer.MIN_VALUE;
        for(int i=1;i<a.length;i++){
            sum = Math.max(a[i],sum+a[i]);
            maxSum=Math.max(sum,maxSum);
        }
        return maxSum;
    }

    public static int [] rearrangeElementsBySign(){
        // 1 2 -4 -5 3 6
        int [] a =new int[]{1,2,-4,-5};
        int n =a.length;
        List<Integer> list=new ArrayList<>(Collections.nCopies(n,0));
      //  List<Integer> negList=new ArrayList<>();
        int posIndex=0,negIndex=1;
        for(int i=0;i<a.length;i++){
            if(a[i]>0) {
                list.set(posIndex, a[i]);
                posIndex+=2;
            }
            else {
                list.set(negIndex,a[i]);
                negIndex+=2;
            }
        }
        list.stream().forEach(System.out::println);
        return a;
    }

    public static ArrayList<Integer> RearrangebySign(int [] A) {
        int n = A.length;

        // Define array for storing the ans separately.
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(n, 0));

        // positive elements start from 0 and negative from 1.
        int posIndex = 0, negIndex = 1;
        for (int i = 0; i < n; i++) {

            // Fill negative elements in odd indices and inc by 2.
            if (A[i] < 0) {
                ans.set(negIndex, A[i]);
                negIndex += 2;
            }

            // Fill positive elements in even indices and inc by 2.
            else {
                ans.set(posIndex, A[i]);
                posIndex += 2;
            }
        }

        return ans;
    }

    //There’s an array ‘A’ of size ‘N’ with positive and negative elements (not necessarily equal).
    // Without altering the relative order of positive and negative elements,
    // you must return an array of alternately positive and negative values.
    // The leftover elements should be placed at the very end in the same order as in array A.
   /* public static ArrayList<Integer> rearrangeElementsBySignVariety2(int [] a){
        // 1 2 -4 -5 3 6
        int countPos=0,countNeg=0;
        int posIndex = 0, negIndex = 1;
        for(int i : a){
            if(i>0)
                countPos++;
            else
                countNeg++;
        }
        System.out.println("count " + countPos + " " + countNeg);
        ArrayList<Integer> ans=new ArrayList<>(Collections.nCopies(a.length,0));

        if(countPos>countNeg){
            for(int i=0;i<2*countNeg;i++){
                if(a[i]>0){
                    ans.set(posIndex,a[i]);
                    posIndex+=2;
                } else {
                    ans.set(negIndex,a[i]);
                    negIndex+=2;
                }
            }
            for(int i=countPos;i<a.length;i++){
                ans.set(posIndex,a[countPos]);
                posIndex++;
            }
        }else if(countPos<countNeg){
            for(int i=0;i<2*countPos;i++){
                if(a[i]>0){
                    ans.set(posIndex,a[i]);
                    posIndex+=2;
                } else {
                    ans.set(negIndex,a[i]);
                    negIndex+=2;
                }
            }
            for(int i=negIndex-1;i<a.length;i++){
                ans.set(negIndex-1,a[negIndex-1]);
                negIndex++;
            }
        } else {
            for(int i=0;i<2*countPos;i++){
                if(a[i]>0){
                    ans.set(posIndex,a[i]);
                    posIndex+=2;
                } else {
                    ans.set(negIndex,a[i]);
                    negIndex+=2;
                }
            }
        }
        ans.stream().forEach(System.out::println);
        return new ArrayList<>();
    } */

    public static void nextPermutationInAArray(int [] a){
        //2 1 5 4 3 0 0
        int pivot=-1;

        for(int i=a.length-2;i>=0;i--){
            if(a[i]<a[i+1]) {
                //System.out.println(i);
                pivot = i;
                break;
            }
           // System.out.println(pivot);
        }
        //System.out.println(pivot);
        if(pivot==-1){
            reverseArray(a,0,a.length-1);
        }

        int nextMin=-1;
        for(int i=a.length-1;i>=pivot;i--){
            if(a[i]>a[pivot]) {
                nextMin = i;
                break;
            }
        }
       // System.out.println(nextMin);

        //swap nextMin and pivot-1
        int temp=a[nextMin];
        a[nextMin]=a[pivot];
        a[pivot]=temp;
        reverseArray(a,pivot+1,a.length-1);
       Arrays.stream(a).forEach(System.out::println);


    }

    public static int [] reverseArray(int [] a, int low,int high){
        while(low<=high){
            int temp=a[low];
            a[low]=a[high];
            a[high]=temp;
            low++;
            high--;
        }
        return a;
    }

    public static void nextPermutationInAArrayList(List<Integer> a){
        //2 1 5 4 3 0 0
        int pivot=-1;

        for(int i=a.size()-2;i>=0;i--){
            if(a.get(i)<a.get(i+1)) {
                //System.out.println(i);
                pivot = i;
                break;
            }
            // System.out.println(pivot);
        }
        //System.out.println(pivot);
        if(pivot==-1){
            reverseArrayList(a,0,a.size()-1);
        }

        int nextMin=-1;
        for(int i=a.size()-1;i>=pivot;i--){
            if(a.get(i)>a.get(pivot)) {
                nextMin = i;
                break;
            }
        }
        // System.out.println(nextMin);

        int temp=a.get(nextMin);
        a.set(nextMin,a.get(pivot));
        a.set(pivot,temp);
        reverseArrayList(a,pivot+1,a.size()-1);
        a.forEach(System.out::println);


    }

    public static List<Integer> reverseArrayList(List<Integer> a, int low,int high){
        if(low<high){
            int temp=a.get(low);
            a.set(low,a.get(high));
            a.set(high,temp);
            low++;
            high--;
            return reverseArrayList(a,low,high);
        } else return a;
    }

    public static List<Integer> leadersInAnArray(int [] a){
        // 10 22 12 3 0 6
        int maxVal= a[a.length-1];
        List<Integer> ans=new ArrayList<>();
        ans.add(maxVal);
        for(int i=a.length-2;i>=0;i--){
            if(a[i]>maxVal){
                ans.add(a[i]);
                maxVal=a[i];
            }
        }
        ans.forEach(System.out::println);
        return ans;
    }

    public static int longestConsecutiveSequenceInAGivenArray(int [] a){
        Arrays.sort(a);
        int currCount=0,lastSmaller=Integer.MIN_VALUE,longest=1;
        for(int i=0;i<a.length;i++){
            if(a[i]-1 == lastSmaller){
                currCount+=1;
                lastSmaller=a[i];
            } else if(a[i] != lastSmaller){
                currCount=1;
                lastSmaller=a[i];
            }
            longest=Math.max(longest,currCount);
        }
        return longest;
    }

    public static int optimalLongestConsecutiveSequenceInAGivenArray(int [] a){
        int longest=1;
        Set<Integer> set=new HashSet<>();
        for(int i : a){
            set.add(i);
        }
        for(int i : set){
            int x=i,count=1;
            if(!set.contains(i-1)){
                while(set.contains(x+1)){
                    count++;
                    x=x+1;
                }
            }
            longest=Math.max(count,longest);
        }

        return longest;
    }
 }
