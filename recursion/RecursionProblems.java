package recursion;

import java.util.*;

public class RecursionProblems {
    public static void main(String[] args) {
        System.out.println(reverseANumber(54,0));
        System.out.println(reverseAString("abc",0,""));
        reverseAString1("abc",0,"");
        System.out.println();
        print1ToN(3);
        System.out.println(factorialOfN(4));
        sortAnArray(new int[]{6,7,11,2},0);
        ArrayList<Integer> arrayList=new ArrayList<>(List.of(7,11,6));
        sortArray(arrayList);
        System.out.println();
        arrayList.forEach(a-> System.out.print(a + " "));
        System.out.println();
        Stack<Integer> stack=new Stack<>();
        stack.push(5);
        stack.push(3);
        stack.push(2);
        System.out.println(stack);
        sortStack(stack);
        System.out.println(stack);
        Stack<Integer> stack1=new Stack<>();
        stack1.push(1);
        stack1.push(2);
//        stack1.push(3);
//        stack1.push(4);
//        stack1.push(5);
//        stack1.push(6);
       // System.out.println(deleteMiddleInStack(stack1));
        Stack<Integer> ans=new Stack<>();
       // reverseStack(stack1,1);
        reverseAStack(stack1);
        System.out.println(stack1);
        ArrayList<String> seq=new ArrayList<>();
        printSubsets(new int[]{12,15});
        System.out.println("--------------------");
        solveSubsequencesWithSpaces("ABC","");
        System.out.println();
        printSubsequencesWithCaseChange("AB","");
        System.out.println();
        ArrayList<String> letterCase=new ArrayList<>();
        letterCasePermutation("a1B2","",letterCase);
        System.out.println(letterCase);
        System.out.println(generateAllBalancedParenthesis(3));
        System.out.println(generateNSizeBinaryNumbersWith1sGreaterThanEqualTo0s(3));
        josephusProblem(40,7);
    }

    public static int reverseANumber(int n,int res){
        //5 4 3 2 1
        if(n==0)
            return res;
        res=res*10+(n%10);
        return reverseANumber(n/10,res);
    }

    public static String reverseAString(String input,int index,String rev){
        if(index==input.length())
            return rev;
        rev=rev+input.charAt(input.length()-1-index);
       // rev=input.charAt(index)+rev;
        return reverseAString(input,index+1,rev);
    }

    public static void reverseAString1(String input,int index,String rev){
        if(index==input.length())
            return;
      //  rev=rev+input.charAt(input.length()-1-index);
        // rev=input.charAt(index)+rev;
        reverseAString1(input,index+1,rev);
        System.out.print(input.charAt(index));
    }

    public static void print1ToN(int n){
        if(n==1) {
            System.out.println(1);
            return;
        }
        print1ToN(n-1);
        System.out.println(n);
    }

    public static int factorialOfN(int n){
        if(n==1)
            return 1;
        return n*factorialOfN(n-1);
    }

    public static void sortAnArray(int [] a,int low){
        if(low==a.length) {
            Arrays.stream(a).forEach(i -> System.out.print(i + " "));
            return;
        }
        int min=Integer.MAX_VALUE,index=-1;
        for(int i=low;i<a.length;i++){
            if(a[i]<min) {
                min = a[i];
                index=i;
            }
        }
        int temp=a[index];
        a[index]=a[low];
        a[low]=temp;
        sortAnArray(a,low+1);
    }

    // Sort an array using recursion
    public static void sortArray(ArrayList<Integer> list){
        if(list.size()==1)
            return;
        int temp=list.get(list.size()-1);
        list.remove(list.size()-1);
        sortArray(list);
        insertInArray(list,temp);
    }

    private static void insertInArray(ArrayList<Integer> list, int temp) {
        if(list.isEmpty() || list.get(list.size()-1)<=temp) {
            list.add(temp);
            return;
        }
        int val=list.get(list.size()-1);
        list.remove(list.get(list.size()-1));
        insertInArray(list,temp);
        list.add(val);
    }


    // Sort a stack using recursion
    public static void sortStack(Stack<Integer> stack){
        if(stack.size()==1)
            return;
        int temp=stack.peek();
        stack.pop();
        sortStack(stack);
        insertInStack(stack,temp);
    }

    private static void insertInStack(Stack<Integer> stack, int temp) {
        if(stack.isEmpty() || stack.peek()<=temp) {
            stack.push(temp);
            return;
        }
        int val=stack.peek();
        stack.pop();
        insertInStack(stack,temp);
        stack.push(val);
    }

   /* public static int minIndexInRange(int [] a,int low){
        int min=Integer.MAX_VALUE,index=0;
        for(int i=low;i<a.length;i++){
            if(a[i]<min) {
                min = a[i];
                index=i;
            }
        }
        return index;
    } */

    public static Stack<Integer> deleteMiddleInStack(Stack<Integer> stack){
        if(stack.isEmpty())
            return stack;
        int k=(stack.size()+1)/2;
        findMiddle(stack,k);
        return stack;
    }

    private static void findMiddle(Stack<Integer> stack, int k) {
        if(k==1) {
            stack.pop();
            return;
        }
        int val=stack.peek();
        stack.pop();
        findMiddle(stack,k-1);
        stack.push(val);
    }

    public static void reverseStack(Stack<Integer> stack,Stack<Integer> ans){
        if(stack.isEmpty())
            return;
        int val=stack.peek();
        stack.pop();
        ans.push(val);
        reverseStack(stack,ans);
    }

    public static void reverseStack(Stack<Integer> stack,int k){
        if(k>(stack.size()+1)/2)
            return;
        int temp=stack.get(k-1);
        stack.set(k-1,stack.get(stack.size()-(k-1)-1));
        stack.set(stack.size()-(k-1)-1,temp);
        reverseStack(stack,k+1);
    }

    public static void reverseAStack(Stack<Integer> stack){
        if(stack.size()==1)
            return;
        int temp=stack.peek();
        stack.pop();
        reverseAStack(stack);
        stack.push(temp);
    }

    public static int kthSymbolInGrammar(int n, int k) {
        if(n==1 && k==1)
            return 1;
        int mid=(int)Math.pow(2, n-1)/2;
        if(k<=mid)
            return kthSymbolInGrammar(n-1, k);
        return 1 - kthSymbolInGrammar(n-1, k-mid);
    }

    public static void towerOfHanoi(int s,int d,int h,int noOfPlates,int count){
        if(noOfPlates==1){
            System.out.print("Move from" + s + " to " + d);
            return;
        }
        // counts the no of steps required (optional parameter)
        count++;
        // Move n-1 plates from source to helper using destination
        towerOfHanoi(s,h,d,noOfPlates-1,count);
        // Move nth plate from source to destination
        System.out.print("Move from" + s + " to " + d);
        // Move n-1 plates from helper to destination using source
        towerOfHanoi(h,d,s,noOfPlates-1,count);
    }

    public static void towerOfHanoi(int s,int d,int h,int noOfPlates,ArrayList<ArrayList<Integer>> toh){
        if(noOfPlates==1){
            ArrayList<Integer> list1=new ArrayList<>();
            list1.add(s);
            list1.add(d);
            toh.add(list1);
           // System.out.print("Move from" + s + " to " + d);
            return;
        }
        // Move n-1 plates from source to helper using destination
        towerOfHanoi(s,h,d,noOfPlates-1,toh);
        // Move nth plate from source to destination
        ArrayList<Integer> list2=new ArrayList<>();
        list2.add(s);
        list2.add(d);
        toh.add(list2);
       // System.out.print("Move from" + s + " to " + d);
        // Move n-1 plates from helper to destination using source
        towerOfHanoi(h,d,s,noOfPlates-1,toh);
        //return toh;
    }

    void printSubsetsOfAString(String input,String outputSoFar){
        if(input.isEmpty()){
            System.out.print(outputSoFar + " ");
            return;
        }
        String output2=outputSoFar;
        output2+=input.charAt(0);
        printSubsetsOfAString(input.substring(1), outputSoFar);
        printSubsetsOfAString(input.substring(1),output2);
    }


    public static ArrayList<String> subsequences(String str) {
        // Write your code here
        ArrayList<String> list=new ArrayList<>();
        String out="";
        printAllSubsequences(str, out, list);
        return list;
    }

    public static void printAllSubsequences(String s, String out, ArrayList<String> res){
        if(s.isEmpty()){
            if(!out.isEmpty())
                res.add(out);
            return;
        }

        printAllSubsequences(s.substring(1), out, res);
        printAllSubsequences(s.substring(1), out+s.charAt(0), res);
    }

    //Get All Unique Subsets
    public static void printSubsets(int [] input) {
        List<Integer> output=new ArrayList<>();
        HashSet<String> ans=new HashSet<>();
        uniqueSubsets(input,0,output,ans);
        System.out.println("-----------------");
        for(String it:ans){
            System.out.print(it + " ");
        }
    }

    private static void uniqueSubsets(int [] nums,int index,List<Integer> output,HashSet<String> set) {
        if(index==nums.length){
            Collections.sort(output);
            set.add(output.toString());
            return;
        }
        output.add(nums[index]);
        uniqueSubsets(nums,index+1,output,set);
        output.remove(output.size()-1);
        uniqueSubsets(nums,index+1,output,set);
    }

    //permutations with spaces leetcode
    public static void solveSubsequencesWithSpaces(String s,String out){
        printSubsequencesWithSpaces(s.substring(1),s.substring(0,1));
    }

    public static void printSubsequencesWithSpaces(String s, String out){
        if(s.isEmpty()){
            if(!out.isEmpty())
                System.out.print(out+",");
            return;
        }
        printSubsequencesWithSpaces(s.substring(1), out+s.charAt(0));
        printSubsequencesWithSpaces(s.substring(1), out+' '+s.charAt(0));
    }


    public static void printSubsequencesWithCaseChange(String s, String out){
        if(s.isEmpty()){
            if(!out.isEmpty())
                System.out.print(out+",");
            return;
        }
        String ch = ("" + s.charAt(0)).toLowerCase();
        String ch2 = ("" + s.charAt(0)).toUpperCase();
        printSubsequencesWithCaseChange(s.substring(1), out+ch);
        printSubsequencesWithCaseChange(s.substring(1), out+ch2);
    }

    public static void letterCasePermutation(String s, String out,ArrayList<String> ans){
        if(s.isEmpty()){
            if(!out.isEmpty())
               // System.out.print(out+",");
                ans.add(out);
            return;
        }
        if(Character.isLetter(s.charAt(0))) {
            String ch = ("" + s.charAt(0)).toLowerCase();
            String ch2 = ("" + s.charAt(0)).toUpperCase();
            letterCasePermutation(s.substring(1), out + ch,ans);
            letterCasePermutation(s.substring(1), out + ch2,ans);
        } else{
            String ch3 = ("" + s.charAt(0));
            letterCasePermutation(s.substring(1), out + ch3,ans);
        }
    }

    public static ArrayList<String> generateAllBalancedParenthesis(int n){
        ArrayList<String> ans=new ArrayList<>();
        int open=n;
        int closed=n;
        String out="";
        recurBalancedParenthesis(open,closed,out,ans);
        return ans;
    }

    private static void recurBalancedParenthesis(int open, int closed, String out, ArrayList<String> ans) {
        if(open == 0 && closed==0){
            ans.add(out);
            return;
        }
        if(open !=0){
            String out1=out+("(");
            recurBalancedParenthesis(open-1,closed,out1,ans);
        }
        if(closed>open){
            String out2=out+(")");
            recurBalancedParenthesis(open,closed-1,out2,ans);
        }
    }

    public static ArrayList<String> generateNSizeBinaryNumbersWith1sGreaterThanEqualTo0s(int n){
        ArrayList<String> ans=new ArrayList<>();
        int ones=0;
        int zeros=0;
        String out="";
        recurNBinaryNumbers(ones,zeros,n,out,ans);
        return ans;
    }

    private static void recurNBinaryNumbers(int ones, int zeros,int n, String out, ArrayList<String> ans) {
        if(n==0){
            ans.add(out);
            return;
        }
        if(ones>zeros){
            String out1=out+("1");
            recurNBinaryNumbers(ones+1,zeros,n-1,out1,ans);
            String out2=out+("0");
            recurNBinaryNumbers(ones,zeros+1,n-1,out2,ans);
        }
        if(ones==zeros){
            String out3=out+("1");
            recurNBinaryNumbers(ones+1,zeros,n-1,out3,ans);
        }
    }

    public static void josephusProblem(int noOfPeople, int kthPersonKilledEachCircle){
        ArrayList<Integer> peopleList=new ArrayList<>();

        for(int i=1;i<=noOfPeople;i++)
            peopleList.add(i);
        int startIndexOfKiller=0;
        //As we putting the people in an array, therefore the person going to be killed each time is 1 less than kthPersonKilledEachCircle
        // because array indexes start from 0
        kthPersonKilledEachCircle=kthPersonKilledEachCircle-1;
        int survivor = recurJosephusProblem(peopleList,startIndexOfKiller,kthPersonKilledEachCircle);
        System.out.println("Survivor " + survivor);
    }

    private static int recurJosephusProblem(ArrayList<Integer> peopleList,int startIndexOfKiller, int kthPersonKilledEachCircle) {
        if(peopleList.size()==1){
            return peopleList.get(0);

        }
        startIndexOfKiller=((startIndexOfKiller+kthPersonKilledEachCircle)%peopleList.size());
        peopleList.remove(startIndexOfKiller);
        return  recurJosephusProblem(peopleList,startIndexOfKiller,kthPersonKilledEachCircle);
    }

    public static int knapsack01(int [] wt,int [] val,int weightKnapsack,int sizeOfWeightArray){
        //size of wt array = size of val array
        if(sizeOfWeightArray==0 || weightKnapsack==0){
            return 0;
        }
        if(wt[sizeOfWeightArray-1]<=weightKnapsack){
            return Math.max(val[sizeOfWeightArray-1]+knapsack01(wt,val,weightKnapsack-wt[sizeOfWeightArray-1],sizeOfWeightArray-1)
                    ,knapsack01(wt,val,weightKnapsack,sizeOfWeightArray-1));
        } else {
            return knapsack01(wt,val,weightKnapsack,sizeOfWeightArray-1);
        }
    }

    public static int knapsack01UsingBasicDP(int [] wt,int [] val,int weightKnapsack,int sizeOfWeightArray){
        int [][] t=new int[sizeOfWeightArray+1][weightKnapsack+1];
        for(int i=0;i<sizeOfWeightArray+1;i++){
            for(int j=0;j<weightKnapsack+1;j++){
                t[i][j]=-1;
            }
        }
        return knapsack01UsingMemoization(wt,val,weightKnapsack,sizeOfWeightArray,t);
    }

    public static int knapsack01UsingMemoization(int [] wt,int [] val,int weightKnapsack,int sizeOfWeightArray,int [][] t){
        //size of wt array = size of val array
        if(sizeOfWeightArray==0 || weightKnapsack==0){
            return 0;
        }
        if(t[sizeOfWeightArray][weightKnapsack]!=-1)
            return t[sizeOfWeightArray][weightKnapsack];
        if(wt[sizeOfWeightArray-1]<=weightKnapsack){
            return t[sizeOfWeightArray][weightKnapsack] = Math.max(val[sizeOfWeightArray-1]+knapsack01UsingMemoization(wt,val,weightKnapsack-wt[sizeOfWeightArray-1],sizeOfWeightArray-1,t)
                    ,knapsack01UsingMemoization(wt,val,weightKnapsack,sizeOfWeightArray-1,t));
        } else {
            return t[sizeOfWeightArray][weightKnapsack]= knapsack01UsingMemoization(wt,val,weightKnapsack,sizeOfWeightArray-1,t);
        }
    }


    public static int knapsack01UsingTopDown(int [] wt,int [] val,int weightKnapsack,int sizeOfWeightArray){
        //size of wt array = size of val array
        int [][] t=new int[sizeOfWeightArray+1][weightKnapsack+1];
        for(int i=0;i<sizeOfWeightArray+1;i++){
            for(int j=0;j<weightKnapsack+1;j++){
                if(i==0 || j==0)
                    t[i][j]=0;
            }
        }
        for(int i=1;i<sizeOfWeightArray+1;i++){
            for(int j=1;j<weightKnapsack+1;j++){
                    if(wt[i-1]<=j){
                    t[i][j]=Math.max(val[i-1]+t[i-1][j-wt[i-1]],t[i-1][j]);
                    }
                    else{
                         t[i][j]=t[i-1][j];
                    }
            }
        }
        return t[sizeOfWeightArray][weightKnapsack];
    }


}
