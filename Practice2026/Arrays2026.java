package Practice2026;

import java.util.*;

public class Arrays2026 {

    static class Number implements Comparable<Number>{
        int val;
        int freq;

        public Number(int val, int freq){
            this.val=val;
            this.freq=freq;
        }

        public int compareTo(Number that){
            return that.freq-this.freq;
        }
    }

    public static void main(String[] args) {
       /* int [] a=new int[]{1,2,3,4,5,6,7};
        rotate(a,3);
        for(int i:a) {
            System.out.println(i);
        } */
        int [] a=new int[]{1,1,2};
     //   System.out.println("size " + removeDuplicates(a));
        System.out.println(groupAnagramsWithoutSorting(new String[]{"eat","tea","tan","nat"}));
    }


    // 189. Rotate Array
    public static void rotate(int[] nums, int k) {
        int n=nums.length;


        for(int i=0;i<k/2;i++){
            int temp=nums[i];
            nums[i]=nums[k-i-1];
            nums[k-i-1]=temp;
        }

        int left=k,right=n-1;
        while(left<right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

        for(int l=0;l<n/2;l++){
            int temp=nums[l];
            nums[l]=nums[n-l-1];
            nums[n-l-1]=temp;
        }


    }


    // remove duplicates from an array
    //[1,1,2]

    public static int removeDuplicates(int[] nums) {
        Set<Integer> set=new HashSet<>();
        int index=0;
        for(int i=0;i<nums.length;i++){
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                nums[index]=nums[i];
                index++;
            }
        }
        return index;
    }

    public static int removeDuplicatesOptimal(int[] nums) {
        //1 1 2
        int j=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[j]){
                nums[j+1]=nums[i];
                j++;
            }
        }
        return j+1;
    }


    public static List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length==0) return new ArrayList<>();
        Map<String,List<String>> map=new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char [] c= strs[i].toCharArray();
            Arrays.sort(c);
            String temp=new String(c);
            if(!map.containsKey(temp)){
                map.put(temp,new ArrayList<>());
            }
            map.get(temp).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }

    public static List<List<String>> groupAnagramsWithoutSorting(String[] strs) {
        if(strs.length==0) return new ArrayList<>();
        Map<String,List<String>> map=new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char [] c= strs[i].toCharArray();
//            Arrays.sort(c);
            int [] freqArray=prepareArray(c);
            StringBuilder sb=new StringBuilder();
            char c1='a';
            for(int j=0;j<freqArray.length;j++){
                sb.append(c1);
                sb.append(freqArray[j]);
                c1++;
            }
           // String temp=new String(c);
            if(!map.containsKey(sb.toString())){
                map.put(sb.toString(),new ArrayList<>());
            }
            map.get(sb.toString()).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }

    private static int[] prepareArray(char[] c) {
        int [] freq=new int[26];
        for(int i=0;i<c.length;i++){
            freq[c[i]-'a']++;
        }
        return freq;
    }

    public int [] topKFrequentElements(int [] nums, int k){
        // Input: nums = [1,1,1,2,2,3], k = 2

        Map<Integer,Integer> map=new HashMap<>();
        for(int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }

        PriorityQueue<Arrays2026.Number> pq=new PriorityQueue<>();

        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            Arrays2026.Number num=new Number(entry.getKey(),entry.getValue());
            pq.offer(num);
        }
        //int index=0;
        int [] ans=new int[k];
        for(int i=0;i<k;i++){
            ans[i]=pq.poll().val;
        }
        return ans;
    }



}
