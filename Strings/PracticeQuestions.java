package Strings;

import java.util.*;


public class PracticeQuestions {
    public static void main(String[] args) {
        System.out.println("Hello");
        System.out.println(checkSubsequenceInAString("ABC","ACD"));
        System.out.println(checkAnagram("aab","baa"));
        System.out.println(leftmostRepeatingCharacter("geeksforgeeks"));
        System.out.println(leftmostNonRepeatingCharacter("apple"));
        System.out.println(sortCharactersInAStringBasedOnFrequencies("FREE"));
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
        Map<Character,Integer> map=new HashMap<>();
        for(char c: s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        List<Character> list=new ArrayList<>(map.keySet());
        list.sort((ob1,ob2) -> map.get(ob2)-map.get(ob1));
        list.forEach(System.out::println);
        for(char c: list){

        }
        return null;
    }
}
