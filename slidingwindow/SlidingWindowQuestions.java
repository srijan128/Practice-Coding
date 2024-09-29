package slidingwindow;

import java.util.*;

public class SlidingWindowQuestions {

    public static void main(String[] args) {
        System.out.println(SWmaxSumOfSubArrayOfSizeKDistinctElements(new int[]{2,3,5,2,9,7,1},3));
        System.out.println(firstNegativeNumberInEveryWindowOfSizeK(new int[]{12, -1, -7, 8, -15, 30, 16, 28},3));
        System.out.println(Arrays.toString(firstNegativeNumberInEveryWindowOfSizeKPattern(new int[]{12, -1, -7, 8, -15, 30, 16, 28}, 3)));
        System.out.println(countOccurrencesOfAnagramInGivenStringUsingMap("forxxorfxdofr","for"));
        System.out.println(maximumOfAllSubArraysOfSizeK(new int[]{10,7,8,11},2));
        System.out.println(longestSubstringWithKUniqueCharacters("abcba",6));
        System.out.println(longestSubstringWithAllUniqueCharacters("xxx"));
        System.out.println(containsNearbyDuplicate(new int[]{0,1,2,3,4,0,0,7,8,9,10,11,12,0},1));
        System.out.println(longestSubstringWithEqual1And0("01100",5));
    }

    //leetcode- 2461. Maximum Sum of Distinct Subarrays With Length K
    public static int maxSumOfSubArrayOfSizeKDistinctElements(int [] a, int k){
        // 2 3 5 2 9 7 1
        int maxSum=Integer.MIN_VALUE;
        for(int i=0;i<=a.length-k;i++){
            int sum=0;
            for(int j=i;j<i+k;j++){
                if(j==i)
                    sum+=a[j];
                else if(j>i && a[j-1]!=a[j]) {
                    sum += a[j];
                } else{
                    sum=0;
                }
            }
            if(sum>maxSum)
                maxSum=sum;
        }
        return maxSum;
    }

    public static int SWmaxSumOfSubArrayOfSizeKDistinctElements(int [] a, int k){
        // 2 3 5 2 9 7 1
        int maxSum=Integer.MIN_VALUE;
        int start=0,windowStart=0;
        while(start<=a.length-k){
            int sum=0;
            for(int i=windowStart;i<windowStart+k;i++){
                if(i==windowStart || (a[i-1]!=a[i]))
                    sum+=a[i];
                else{
                    sum=0;
                }
            }
            maxSum=Math.max(sum,maxSum);
            windowStart+=1;
            start++;
        }
        return maxSum;
    }


    //First negative in every window of size k

    public static ArrayList<Integer> firstNegativeNumberInEveryWindowOfSizeK(int [] a, int k){
        //A[] = {12, -1, -7, 8, -15, 30, 16, 28}
        ArrayList<Integer> ans=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        int index=0,windowStart=0;
        while(index<k){
            if(a[index]<0)
                queue.offer(a[index]);
            index++;
        }
        ans.add(queue.isEmpty()?0:queue.peek());
         for(int i=1;i<=a.length-k;i++){
             if(a[i-1]<0)
                 queue.poll();
             if(a[i+k-1]<0)
                 queue.offer(a[i+k-1]);
             ans.add(queue.isEmpty()?0:queue.peek());
         }
         return ans;
    }

    public static int [] firstNegativeNumberInEveryWindowOfSizeKPattern(int [] a, int k){
        //A[] = {12, -1, -7, 8, -15, 30, 16, 28}
        int [] ans=new int[a.length-k+1];
        Queue<Integer> queue=new LinkedList<>();
        int wS=0,wE=0,index=0;
        while(wE<a.length){
            // Calculation till window is reached
            if(a[wE]<0)
                queue.offer(a[wE]);
            if(wE-wS+1<k)
                wE++;
            // Evaluate the calculation when the window size is matched
            // slide the fixed window
            else if(wE-wS+1==k){
                if(queue.isEmpty())
                    ans[wS]=0;
                else {
                    ans[wS]=queue.peek();
                    if(Objects.equals(a[wS], queue.peek()))
                        queue.poll();
                }
                wE++;
                wS++;
            }
        }
        return ans;
    }


    public static int countOccurrencesOfAnagramInGivenString(String s, String t){
        //s = forxxorfxdofr
        //t = for
        int count=0;
        int wS=0,wE=0;
        int [] charFreqT=new int[26];
        int [] charFreq=new int[26];
        //Arrays.fill(charFreqT,-1);
        for(char c: t.toCharArray())
            charFreq[c-'a']++;
        while(wE<s.length()){
            // Calculation till window is reached
                //int [] charFreq=new int[26];
                charFreqT[s.charAt(wE)-'a']++;
                if(wE-wS+1<t.length())
                    wE++;
                // Evaluate the calculation when the window size is matched
                // slide the fixed window
                else if(wE-wS+1==t.length()){
                  //  if(isPossibleSubstring(charFreq,charFreqT))
                    if(Arrays.equals(charFreq,charFreqT))
                        count++;
                    charFreqT[s.charAt(wS)-'a']--;
                    wE++;
                    wS++;
                }

        }
        return count;
    }


    public static int countOccurrencesOfAnagramInGivenStringUsingMap(String s, String t){
        //s = forxxorfxdofr
        //t = for
        int count=0;
        Map<Character,Integer> map=new HashMap<>();
        int wS=0,wE=0;
        for(char c: t.toCharArray())
            map.put(c,map.getOrDefault(c,0)+1);
        int countOfDistinctChar=map.size();
        while(wE<s.length()){
            // Calculation till window is reached
            //int [] charFreq=new int[26];
            if(map.containsKey(s.charAt(wE))) {
                map.put(s.charAt(wE), map.get(s.charAt(wE)) - 1);
                if(map.get(s.charAt(wE))==0)
                 countOfDistinctChar--;
            }
            if(wE-wS+1<t.length())
                wE++;
                // Evaluate the calculation when the window size is matched
                // slide the fixed window
            else if(wE-wS+1==t.length()){
                if(countOfDistinctChar==0) count++;

                if(map.containsKey(s.charAt(wS))){
                    map.put(s.charAt(wS),map.get(s.charAt(wS))+1);
                    if(map.get(s.charAt(wS))==1)
                        countOfDistinctChar+=1;
                }
                wE++;
                wS++;
            }

        }
        return count;
    }



    public static ArrayList<Integer>  maximumOfAllSubArraysOfSizeK(int [] a, int k){
        // 1 3 -1 -3 5 6 7
        int max=Integer.MIN_VALUE;
        int curMax=Integer.MIN_VALUE;
        ArrayList<Integer> ans=new ArrayList<>();
        int wS=0,wE=0;
        Deque<Integer> deque=new ArrayDeque<>();
        while(wE<a.length){
            while (!deque.isEmpty() && deque.getLast()<a[wE])
                deque.pollLast();
            deque.addLast(a[wE]);
            if(wE-wS+1<k)
                wE++;
            else if(wE-wS+1==k){
               ans.add(deque.getFirst());
                if(deque.getFirst() == a[wS]){
                    deque.pollFirst();
                }
                wE++;
                wS++;
            }
        }
        return ans;
    }


    public static int longestSubstringWithKUniqueCharacters(String s,int k){
        int wS=0,wE=0,maxLen=Integer.MIN_VALUE;
        Map<Character,Integer> map=new HashMap<>();
        while (wE<s.length()){
            map.put(s.charAt(wE),map.getOrDefault(s.charAt(wE),0)+1);
            if(map.size()<k)
                wE++;
            else if(map.size()==k){
                maxLen=Math.max(maxLen,wE-wS+1);
                wE++;
            } else {
                while(map.size()>k){
                    map.put(s.charAt(wS),map.get(s.charAt(wS))-1);
                    if(map.get(s.charAt(wS))==0)
                        map.remove(s.charAt(wS));
                    wS++;
                }
                wE++;
            }
        }
        if(maxLen==Integer.MIN_VALUE)
            return s.length();
        return maxLen;
    }

    public static int longestSubstringWithAllUniqueCharacters(String s){
        int wS=0,wE=0,maxLen=Integer.MIN_VALUE;
        Map<Character,Integer> map=new HashMap<>();
        while (wE<s.length()){
            map.put(s.charAt(wE),map.getOrDefault(s.charAt(wE),0)+1);
            if(map.size()>wE-wS+1)
                wE++;
             if(map.size()==wE-wS+1){
                maxLen=Math.max(maxLen,wE-wS+1);
                wE++;
            } else if(map.size()<wE-wS+1) {
                while(map.size()<wE-wS+1){
                    map.put(s.charAt(wS),map.get(s.charAt(wS))-1);
                    if(map.get(s.charAt(wS))==0)
                        map.remove(s.charAt(wS));
                    wS++;
                }
                wE++;
            }
        }
//        if(maxLen==Integer.MIN_VALUE)
//            return s.length();
        return maxLen;
    }


    public static int maxUniqueToysEqualsK(String s,int k){
        int wS=0,wE=0,maxLen=Integer.MIN_VALUE;
        Map<Character,Integer> map=new HashMap<>();
        while (wE<s.length()){
            map.put(s.charAt(wE),map.getOrDefault(s.charAt(wE),0)+1);
            if(map.size()<k)
                wE++;
            else if(map.size()==k){
                maxLen=Math.max(maxLen,wE-wS+1);
                wE++;
            } else {
                while(map.size()>k){
                    map.put(s.charAt(wS),map.get(s.charAt(wS))-1);
                    if(map.get(s.charAt(wS))==0)
                        map.remove(s.charAt(wS));
                    wS++;
                }
                wE++;
            }
        }
        if(maxLen==Integer.MIN_VALUE)
            return s.length();
        return maxLen;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        // AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT
        List<String> ans=new ArrayList<>();
        if(s.length()<10)
            return ans;
        Map<String,Integer> map =new HashMap<>();
        for(int i=0;i<=s.length()-10;i++){
            String sub=s.substring(i,i+10);
            map.put(sub,map.getOrDefault(sub,0)+1);
        }
        for(Map.Entry<String,Integer> entry: map.entrySet()){
            if(entry.getValue()>1)
                ans.add(entry.getKey());
        }
        return ans;
    }

    public int minSubArrayLen(int target, int[] nums) {
        /*
        target = 7, nums = [2,3,1,2,4,3]
         */
        int low=0,high=0,minLen=Integer.MAX_VALUE,sum=0;
        while(high<nums.length){
            sum+=nums[high];
            if(sum<target)
                high++;
            else {
                while (sum >= target) {
                    minLen = Math.min(minLen, high - low + 1);
                    sum-=nums[low];
                    low++;
                }
                high++;
            }
        }
        if(minLen==Integer.MAX_VALUE)
            return 0;
        return minLen;
    }

    public static boolean containsNearbyDuplicate(int[] a, int k) {
        /*
        nums = [1,2,3,1], k = 3
         */
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<a.length;i++){
            if(!map.containsKey(a[i]))
                map.put(a[i],i);
            else{
                if(Math.abs(map.get(a[i])-i)<=k)
                    return true;
                else
                    map.put(a[i],i);
            }
        }
        return false;
    }

    //395. Longest Substring with At Least K Repeating Characters
    public int longestSubstring(String s, int k) {
        //ababbc
        return helper(s.toCharArray(),0,s.length(),k);
    }

    private int helper(char[] s, int start, int end, int k) {
        if(end-start<k) return 0;
        int [] count=new int[26];
        for(int i=0;i<end;i++){
            count[s[i]-'a']++;
        }
        for(int i=0;i<end;i++){
            if(count[s[i]-'a']<k) {
                int j = i + 1;
                while(j<end && count[s[i]-'a']<k){
                    j++;
                }
                return Math.max(helper(s,start,i,k),helper(s,j,end,k));
            }
        }
        return end-start;
    }

    public static int longestSubstringWithEqual1And0(String s, int n) {
        int low=0,high=0,sum=0,maxLen=Integer.MIN_VALUE,len=0;
        char [] ch=s.toCharArray();
        int [] a=new int[s.length()];
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0')
                a[i]=-1;
            else
                a[i]=1;
        }
        System.out.println(Arrays.toString(a));
        for(int i=0;i<a.length;i++){
            sum+=a[i];
            if(sum==0)
                maxLen=Math.max(maxLen,i+1);
            if(map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            }else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    public List<Integer> findAnagrams(String s, String p) {
        //s = "cbaebabacd", p = "abc"
        int low=0,high=0;
        StringBuilder sb=new StringBuilder();
        List<Integer> ans=new ArrayList<>();
        while(high<s.length()){
            sb.append(s.charAt(high));
            if(sb.length()==p.length()){
                boolean flag=true;
                int [] charCount=new int[26];
                for(int i=0;i<p.length();i++){
                    charCount[sb.charAt(i)-'a']++;
                    charCount[p.charAt(i)-'a']--;
                }
                for(int count:charCount){
                    if(count != 0) {
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    ans.add(low);
                sb.deleteCharAt(0);
                low++;

            }
            high++;
        }
        return ans;
    }
}
