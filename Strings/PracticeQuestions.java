package Strings;

import java.util.*;


public class PracticeQuestions {
    public static void main(String[] args) {
        System.out.println("Hello");
        System.out.println(minWindowSubString("fight","it"));
        System.out.println(findAnagramsIndices("CBAEBABACD",5,"ABC",2));
        System.out.println(rearrangeCharactersInAString("aaabc"));
        System.out.println(replaceSpacesWith40(new StringBuilder("hello I am")));
        System.out.println(maxFruits(new int[]{26,25,22,18,31,19,18,25,10,18,13,13,8,14,14,10,1,25,5,30,32,24,22,25,24,2,7,27,3,28,30,20,5,14}));
        System.out.println(checkSubsequenceInAString("ABC","ACD"));
        System.out.println(checkAnagram("aab","baa"));
        System.out.println(leftmostRepeatingCharacter("geeksforgeeks"));
        System.out.println(leftmostNonRepeatingCharacter("apple"));
        System.out.println(sortCharactersInAStringBasedOnFrequencies("FREE"));
        String s="26 25 22 18 31 19 18 25 10 18 13 13 8 14 14 10 1 25 5 30 32 24 22 25 24 2 7 27 3 28 30 20 5 14";
        System.out.println(s.replaceAll("\\s+", ","));
        StringBuilder sb=new StringBuilder("hello I am");
       // int i=0;
        sb.replace(sb.indexOf(" "),sb.indexOf(" "),"@");
//        while(i<sb.length()){
//            if(sb.charAt(i)==' ')
//                sb.replace(sb.indexOf(" "),i,"@");
//            i++;
//        }
        System.out.println(sb);
        String s1="ababccdabc";
        System.out.println(s1.indexOf("abc",6));
        System.out.println(s1.replace("a","e"));
        String ptr="BA";
        String str="AD";
        int [] charCount=new int[4];
        for(int i=0;i<ptr.length();i++){
            charCount[str.charAt(i)-'A']++;
            charCount[ptr.charAt(i)-'A']--;
        }
        Arrays.stream(charCount).forEach(i -> System.out.print(i + " "));
        //vwxqnvpixmsdgadktrymuqqrttscfutwzgcgbfteehgvsbvngxjftgldlhueezxldndbqcomvctl mjucxfcxaukxhrmofzzfcnxhcrynmnpu
        String st1="vwxqnvpixmsdgadktrymuqqrttscfutwzgcgbfteehgvsbvngxjftgldlhueezxldndbqcomvc";
        String st2="xqnvpixmsdgadktrymuqqrttscfutwzgcgbfteehgvsbvngxjftgldlhueezxldndbqcomvc";
        System.out.println("length");
        System.out.println(st1.length() + " " + st2.length());
        System.out.println("leetcode");
        System.out.println(lengthOfLongestSubstringWithoutRepeatingCharacters("pwwkew"));
    }

    public static boolean checkSubsequenceInAString(String s, String sub){
        //A B C //A C
        int i=0,j=0;
        while(i<s.length() && j<sub.length()){
            if(s.charAt(i) == sub.charAt(j))
                j++;
            i++;
        }
        return (j==sub.length());
    }

    public static boolean checkAnagram(String s, String s1){
        int [] count=new int[256];
        if(s.length() != s1.length()) return false;
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)]++;
            count[s1.charAt(i)]--;
        }
        for(int i=0;i<count.length;i++){
            if(count[i] != 0)
                return false;
        }

        return true;
    }

    public static int leftmostRepeatingCharacter(String s){
        //abbcc
        char x=0;
        int [] count=new int[128];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)]++;
        }
        for(int i=0;i<s.length();i++){
            if(count[s.charAt(i)]>1)
                return i;
        }
        return -1;
    }

    public static int leftmostRepeatingCharacterBetter(String s){
        //abbcc
        char x=0;
        int [] count=new int[128];
        Arrays.fill(count,-1);
        int minIndex=Integer.MAX_VALUE;
        for(int i=0;i<s.length();i++){
            int fi = count[s.charAt(i)];
            if(fi == -1)
                count[s.charAt(i)]=i;
            else
                minIndex=Math.min(minIndex,fi);
        }

        return (minIndex==Integer.MAX_VALUE)?-1:minIndex;
    }

    public static int leftmostNonRepeatingCharacter(String s){
        int [] count=new int[128];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)]++;
        }
        for(int i=0;i<s.length();i++){
            if(count[s.charAt(i)]==1)
                return i;
        }
        return -1;
    }

    public static String sortCharactersInAStringBasedOnFrequencies(String s){
        //FREE //EEFR
        StringBuilder sb=new StringBuilder();
        Map<Character,Integer> map=new HashMap<>();
        for(char c: s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        List<Character> list=new ArrayList<>(map.keySet());
        list.sort((ob1,ob2) -> map.get(ob2)-map.get(ob1));
       // list.forEach(System.out::println);
        for(char c: list){
            for(int i=0;i<map.get(c);i++){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String reverseWordsOfAString(String s){
        // He is Ram
        StringBuilder res=new StringBuilder();
        int startIndex=s.length()-1;
        while(startIndex>=0){
            while(startIndex>=0 && s.charAt(startIndex)==' '){
                startIndex--;
            }
            if(startIndex<0) break;
            int endIndex=startIndex;
            while(startIndex>=0 && s.charAt(startIndex) != ' ')
                startIndex--;
            if(res.length()==0)
                res.append(s.substring(startIndex+1,endIndex+1));
            else {
                res.append(" ");
                res.append(s.substring(startIndex+1,endIndex+1));
            }
        }
        return res.toString();
    }

    public static boolean isValidParenthesis(String s){
        Stack<Character> stack=new Stack<>();
        if(s.length()%2!=0)
            return false;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);

            if(ch=='(' || ch=='[' || ch=='{')
                stack.push(ch);
            else{
                if(stack.isEmpty())
                    return false;
                char top=stack.peek();
                if((ch==')' && top !='(') || (ch==']' && top !='[') || (ch=='}' && top !='{')){
                    return false;
                }else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }




    public static int maxFruits(int []a) {
        // Write your code here.
        Map<Integer,Integer> map=new HashMap<>();
        int l=0,r=0,maxLen=0;
        while(r<a.length){
            map.put(a[r],map.getOrDefault(a[r],0)+1);
            if(map.size()>2) {
                while (map.size() > 2) {
                    int count = map.get(a[l]);
                    map.put(a[l], count-1);
                    if (map.get(a[l]) == 0)
                        map.remove(a[l]);
                    l++;
               }

            }
            else if(map.size()<=2)
                maxLen=Math.max(maxLen,r-l+1);
            r++;
        }
        return maxLen;
    }

    public static StringBuilder replaceSpacesWith40(StringBuilder str) {
        // Write your code here.
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == ' '){
                //sb.append('@');
                sb.append('4');
                sb.append('0');
            }else{
                sb.append(str.charAt(i));
            }
        }
        return sb;
    }

    public static String rearrangeCharactersInAString(String s){
        StringBuilder res=new StringBuilder();
        if(isPossible(s)){
            System.out.println("Possible");
            Comparator<Obj> cmp=(obj1,obj2) ->  -1*(obj1.freq-obj2.freq);
            PriorityQueue<Obj> pq=new PriorityQueue<Obj>(cmp);
            int [] count=new int[26];
            for(char c:s.toCharArray()){
                count[c-'a']++;
            }
            for(char ch='a';ch<='z';ch++){
                int value=ch-'a';
                if(count[value]>0)
                    pq.add(new Obj(ch,count[value]));
            }
            Obj prev=new Obj('#',-1);
            while(!pq.isEmpty()){
                Obj temp=pq.poll();
                res.append(temp.ch);
                temp.freq=temp.freq-1;
                if(prev.freq>0)
                    pq.add(prev);
                prev=temp;
            }
           // System.out.println(res);
            return res.toString();
        }else{
            return "NO SOLUTION";
        }
    }

    private static boolean isPossible(String s) {
        //aaabb
        int maxCount=Integer.MIN_VALUE;
        int [] count=new int[26];
        for(char c:s.toCharArray()){
            count[c-'a']++;
        }
        for(int i=0;i<count.length;i++){
            if(count[i]>maxCount)
                maxCount=count[i];
        }
        return maxCount<=(s.length()-maxCount)+1;
    }


    public static ArrayList<Integer> findAnagramsIndices(String str, int n, String ptr, int m){
        // Write your code here.
        int low=0,high=0;
        StringBuilder sb=new StringBuilder();
        ArrayList<Integer> ans=new ArrayList<>();
        while(high<str.length()){

            sb.append(str.charAt(high));
            if(sb.length()==ptr.length()){
                boolean flag=true;
                int [] charCount=new int[5];
                for(int i=0;i<ptr.length();i++){
                    charCount[sb.charAt(i)-'A']++;
                    charCount[ptr.charAt(i)-'A']--;
                }
                for(int count:charCount){
                    if (count != 0) {
                        flag = false;
                       // break;
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

    public static String minWindowSubString(String s, String t) {
        int windowStart = 0, minLength = Integer.MAX_VALUE, matched = 0, start = 0;
        Map<Character, Integer> tMap = new HashMap<>();

        for(char ch : t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }

        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currChar = s.charAt(windowEnd);
            if(tMap.containsKey(currChar)) {
                tMap.put(currChar, tMap.get(currChar) - 1);
                if(tMap.get(currChar) >= 0) {
                    matched += 1;
                }
            }

            while(matched == t.length()) {
                if(minLength > (windowEnd - windowStart + 1)) {
                    minLength = windowEnd - windowStart + 1;
                    start = windowStart;
                }

                char leftChar = s.charAt(windowStart);
                windowStart += 1;

                if(tMap.containsKey(leftChar)) {
                    if(tMap.get(leftChar) == 0) {
                        matched -= 1;
                    }
                    tMap.put(leftChar, tMap.get(leftChar) + 1);
                }
            }
        }

        if(minLength > s.length()) {
            return "";
        }
        return s.substring(start, start + minLength);
    }

    public static String minSubString(String a, String b) {
        //given a = ninjas
        //b=sin
        //ninjas injas
        int low=0,high=a.length(),end=a.length();
        List<String> list=new ArrayList<>();
        while(high>=b.length()){
            if(isPossibleSubstring(a.substring(low),b,low)){
                list.add(a.substring(low));
                low=low+1;
            }

            if(isPossibleSubstring(a.substring(0,end),b,end)){
                list.add(a.substring(0,end));
                end=end-1;
            }
            high--;
        }
        list.forEach(l -> System.out.println(l + " "));
        int minVal=Integer.MAX_VALUE;
        String s1="";
        for(String s: list){
            if(s.length()<minVal){
                s1=s;
            }
        }
        return "abc";
    }

    private static boolean isPossibleSubstring(String a, String b, int low) {
        int [] countA=new int[26];
        int [] countB=new int[26];
        for(int i=0;i<a.length();i++){
            countA[a.charAt(i)-'a']++;
        }

        for(int i=0;i<b.length();i++){
            countB[b.charAt(i)-'a']++;
        }

        for(int i=0;i<countB.length;i++){
            if(countA[i]<countB[i]) {
               // System.out.print((char)(i+'a') + " ");
                return false;
            }
        }
        return true;
    }

    public static String minWindowSubStringSlidingWindow(String s, String t) {
        int windowStart=0,windowEnd=0,minLen=Integer.MAX_VALUE,matched=0,startIndex=-1;
        Map<Character,Integer> map=new HashMap<>();
        for(char c: t.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(windowEnd=0;windowEnd<s.length();windowEnd++){
            char currChar=s.charAt(windowEnd);
            if(map.containsKey(currChar)){
                map.put(currChar,map.get(currChar)-1);
                if(map.get(currChar)>=0)
                    matched+=1;
            }
            while(matched==t.length()){
                if(minLen>(windowEnd-windowStart+1)){
                    minLen=windowEnd-windowStart+1;
                    startIndex=windowStart;
                }
                char leftChar=s.charAt(windowStart);
                windowStart+=1;
                if(map.containsKey(leftChar)){
                    if(map.get(leftChar)==0)
                        matched-=1;
                    map.put(leftChar,map.get(leftChar)+1);
                }
            }
        }
        if(minLen > s.length()) {
            return "";
        }
        return s.substring(startIndex, startIndex + minLen);
    }

    // 3. Longest Substring Without Repeating Characters

    public static int lengthOfLongestSubstringWithoutRepeatingCharacters(String s) {
        //abcabcbb
        if(s.isEmpty()) return 0;
        int low=0,high=0,maxLen=Integer.MIN_VALUE,len=0;
        Map<Character,Integer> map=new HashMap<>();
        while(high<s.length()){
            map.put(s.charAt(high),map.getOrDefault(s.charAt(high),0)+1);
            if(map.size()>high-low+1)
                high++;
            else if(map.size()==high-low+1) {
                maxLen=Math.max(maxLen,high-low+1);
                high++;
            }else {
                while(map.size()<high-low+1){
                    map.put(s.charAt(low),map.get(s.charAt(low))-1);
                    if(map.get(s.charAt(low))==0)
                        map.remove(s.charAt(low));
                    low++;
                }
                high--;
            }
        }
        return maxLen;
    }
}

class Obj {
    char ch;
    int freq;
    public Obj(char ch,int freq){
        this.ch=ch;
        this.freq=freq;
    }
}
