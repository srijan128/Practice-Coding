package recursion;

import java.util.*;

public class RecursionProblems {
    public static void main(String[] args) {
       /* ArrayList<Integer> al=new ArrayList<>(List.of(1,2,3));
        Collections.sort(al);
        reverseAnArrayUsingRecursion(al);
        System.out.println(al);
        System.out.println(Arrays.toString(reverseAnArrayUsingRec(new int[]{1,2,3},0)));
        System.out.println(sumOf1stNNaturalNosWithoutSecondVariable(6));
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
        mergeSort(new int[]{5,3,2});
        System.out.println();
        System.out.println(combSum(new int[]{2,4},4));
        subsetSum(new int[]{3,1,2});
        findNonRepeatingSubsets(new int[]{1,1});
        printPermutations(new int[]{1,2});
       // permutationsWithoutUsingExtraSpace(new ArrayList<>(List.of(1,2,3)),2);
        System.out.println(Arrays.toString(generateFibonacciNumbers(3)));
        int [] dp=new int[4];
        Arrays.fill(dp,-1);
        System.out.println(fibonacciUsingDP(3,dp)); */
        int [] nums=new int[]{1,2};
        System.out.println(subsetsWithDupSet(nums));
    }

    public static int reverseANumber(int n,int res){
        //5 4 3 2 1
        if(n==0)
            return res;
        res=res*10+(n%10);
        return reverseANumber(n/10,res);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> out=new ArrayList<>();
        Arrays.sort(nums);
        subsets(nums,0,ans,out);
        return ans;
    }

    public static void subsets(int [] nums,int index,List<List<Integer>> ans,List<Integer> out){
        ans.add(new ArrayList<>(out));
        for(int i=index;i<nums.length;i++){
            if(i != index && nums[i]==nums[i-1]) continue;
            out.add(nums[i]);
            subsets(nums,i+1,ans,out);
            out.remove(out.size()-1);
        }
    }


    public static List<List<Integer>> subsetsWithDupSet(int[] nums) {
        Set<List<Integer>> ans=new HashSet<>();
        List<Integer> out=new ArrayList<>();
        Arrays.sort(nums);
        subsetsSet(nums,0,ans,out);
        return new ArrayList<>(ans);
    }

    public static void subsetsSet(int [] nums,int index,Set<List<Integer>> ans,List<Integer> out){
        if(index==nums.length){
            ans.add(new ArrayList<>(out));
            return;
        }
        out.add(nums[index]);
        subsetsSet(nums,index+1,ans,out);
        out.remove(out.size()-1);
        subsetsSet(nums,index+1,ans,out);
    }

    public static int[] generateFibonacciNumbers(int n) {
        // Write your code here.
        int[] memo = new int[n];
        for (int i = 0; i < n; i++) {
            memo[i]=fib(i);
        }
        return memo;
    }

    static int fib(int n)
    {
        int f[] = new int[n+2];

        int i;
        f[0] = 0;
        f[1] = 1;

        for (i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    public static int fibonacciUsingDP(int n,int [] dp){
        if(n<=1)
            return n;
        if(dp[n]!=-1)
            return dp[n];
        else
            return dp[n]=fibonacciUsingDP(n-1,dp)+fibonacciUsingDP(n-2,dp);
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

    public static int sumOf1stNNaturalNos(int n,int sum){
        if(n<1)
            return sum;
        sum+=n;
        return sumOf1stNNaturalNos(n-1,sum);
    }

    public static int sumOf1stNNaturalNosWithoutSecondVariable(int n){
        if(n<1)
            return 0;
        return n+sumOf1stNNaturalNosWithoutSecondVariable(n-1);
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

    static boolean isSubsetSum(int set[], int n, int sum)
    {
        boolean [][] t=new boolean[n+1][sum+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0)
                    t[i][j]=false;
                if(j==0)
                    t[i][j]=true;
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<sum+1;j++){
                if(set[i-1]<=j){
                    t[i][j]=t[i-1][j-set[i-1]] || t[i-1][j];
                }else{
                    t[i][j]=t[i-1][j];
                }
            }
        }
        return t[n][sum];
    }

    static boolean equalSumPartition(int set[], int n){
        int sum=0;
        for(int i:set)
            sum+=i;
        if(sum%2!=0)
            return false;
        else return isSubsetSum(set,n,sum/2);
    }


    static int countOfSubsetsWithAGivenSum(int [] a,int sum){
        int [][] t=new int[a.length+1][sum+1];
        for(int i=0;i<a.length+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0)
                    t[i][j]=0;
                if(j==0)
                    t[i][j]=1;
            }
        }

        for(int i=1;i<a.length+1;i++){
            for(int j=1;j<sum+1;j++){
                if(a[i-1]<=j){
                    t[i][j]=t[i-1][j-a[i-1]] + t[i-1][j];
                }else{
                    t[i][j]=t[i-1][j];
                }
            }
        }
        return t[a.length][sum];
    }

    public static int [] reverseAnArrayUsingRec(int [] a,int index){
        if(index>=a.length/2)
            return a;
        int temp=a[index];
        a[index]=a[a.length-index-1];
        a[a.length-index-1]=temp;
        return reverseAnArrayUsingRec(a,index+1);
    }


    public static void reverseAnArrayUsingRecursion(ArrayList<Integer> a){
        if(a.size()==1) {
            //System.out.println(a);
            return;
        }
        int temp=a.get(a.size()-1);
        a.remove(a.size()-1);
        reverseAnArrayUsingRecursion(a);
        addInArray(a,temp);
    }

    private static void addInArray(ArrayList<Integer> list, int temp) {
        if(list.isEmpty()) {
            list.add(temp);
            return;
        }
        int val=list.get(list.size()-1);
        list.remove(list.size()-1);
        addInArray(list,temp);
        list.add(val);
    }

    public static void getSubsequencesWhereSumIsK(int [] a, int index,List<Integer> ans,List<List<Integer>> ansList,
                                                  int sum,int k){
        if (index == a.length){
            if(sum==k){
                ansList.add(new ArrayList<>(ans));
                // System.out.println(ans);
            }
            return;
        }
        ans.add(a[index]);
        sum+=a[index];
        getSubsequencesWhereSumIsK(a,index+1,ans,ansList,sum,k);
        ans.remove(Integer.valueOf(a[index]));
        sum-=a[index];
        getSubsequencesWhereSumIsK(a,index+1,ans,ansList,sum,k);
    }

    public static boolean getOnlyOneSubsequenceWhereSumIsK(int [] a, int index,List<Integer> ans,List<List<Integer>> ansList,
                                                  int sum,int k){
        if (index == a.length){
            if(sum==k){
                ansList.add(new ArrayList<>(ans));
                return true;
                // System.out.println(ans);
            }
            return false;
        }
        ans.add(a[index]);
        sum+=a[index];
        if(getOnlyOneSubsequenceWhereSumIsK(a,index+1,ans,ansList,sum,k))
            return true;
        ans.remove(Integer.valueOf(a[index]));
        sum-=a[index];
        if(getOnlyOneSubsequenceWhereSumIsK(a,index+1,ans,ansList,sum,k))
            return true;
        return false;
    }

    public static int countSubsequencesWhereSumIsK(int [] a, int index,List<Integer> ans,
                                                   int sum,int k){
        if (index == a.length){
            if(sum==k){
                return 1;
            }
            return 0;
        }
        ans.add(a[index]);
        sum+=a[index];
        int l= countSubsequencesWhereSumIsK(a,index+1,ans,sum,k);
        ans.remove(Integer.valueOf(a[index]));
        sum-=a[index];
        int r = countSubsequencesWhereSumIsK(a,index+1,ans,sum,k);
        return l+r;
    }

    public static void mergeSort(int [] a){
        int low=0;
        int high=a.length-1;
        divideAndMerge(a,low,high);
            Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    private static void divideAndMerge(int[] a, int low, int high) {
        //Extra safety for code to not break
        // we can check with == also
        if(low>=high)
            return;
        int mid=low+(high-low)/2;
        divideAndMerge(a,low,mid);
        divideAndMerge(a,mid+1,high);
        mergeArrays(a,low,mid,high);
    }

    private static void mergeArrays(int[] a, int low,int mid, int high) {
        List<Integer> temp=new ArrayList<>();
        int left=low;
        int right=mid+1;
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
            a[i]=temp.get(i-low);
        }
    }

    public static List<List<Integer>> combSum(int []a, int target) {
        List<List<Integer>> ansList=new ArrayList<>();
        List<Integer> ans=new ArrayList<>();
        int index=0;
        Arrays.sort(a);
        combinationSum(a,index,target,ans,ansList);
        return ansList;
    }

    public static void combinationSum(int [] a,int index,int target,
                                      List<Integer> ans,List<List<Integer>> ansList){
        if(index==a.length){
            if(target==0){
                ansList.add(new ArrayList<>(ans));
            }
            return;
        }

        if(a[index]<=target){
            ans.add(a[index]);
            combinationSum(a,index,target-a[index],ans,ansList);
            ans.remove(ans.size()-1);
        }
        combinationSum(a,index+1,target,ans,ansList);

    }


    // Better approach of the above problems with unique elements
    // Combination Sum 2

    public static List<List<Integer>> combSumBetterApproach(int []a, int target) {
        List<List<Integer>> ansList=new ArrayList<>();
        List<Integer> ans=new ArrayList<>();
        int index=0;
        Arrays.sort(a);
        findCombinations(index,a,target,ansList,ans);
        return ansList;
    }
    public static void findCombinations(int index, int [] a,int target,List<List<Integer>> ansList, List<Integer> ans){
        if(target==0){
            ansList.add(new ArrayList<>(ans));
            return;
        }
        for(int i=index;i<a.length;i++){
            if(i>index && a[i]==a[i-1])
                continue;
            if(a[i]>target)
                break;
            ans.add(a[i]);
            findCombinations(i+1,a,target-a[i],ansList,ans);
            ans.remove(ans.size()-1);
        }
    }

    public static void subsetSum(int [] a){
        List<Integer> ansList=new ArrayList<>();
        findSumForAllSubsets(a,0,0,ansList);
        Collections.sort(ansList);
        System.out.println(ansList);
    }

    private static void findSumForAllSubsets(int[] a, int index, int sum,  List<Integer> ansList) {
        if(index==a.length){
            ansList.add(sum);
            return;
        }
        //ans.add(a[index]);
        sum+=a[index];
        findSumForAllSubsets(a,index+1,sum,ansList);
        sum-=a[index];
        findSumForAllSubsets(a,index+1,sum,ansList);
    }

    public static void findNonRepeatingSubsets(int [] a){
        ArrayList<ArrayList<Integer>> ansList=new ArrayList<>();
        ArrayList<Integer> ans=new ArrayList<>();
        Arrays.sort(a);
        findSubSets(a,0,ans,ansList);
       /* ansList.sort(((o1, o2) -> {
            int minLen=Math.min(o1.size(), o2.size());
            for(int i=0;i<minLen;i++){
                int lexicographicalPosition = o1.get(i).compareTo(o2.get(i));
                if (lexicographicalPosition != 0) {
                    return lexicographicalPosition;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        })); */
        System.out.println(ansList);
    }

    private static void findSubSets(int[] a, int index, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> ansList) {
       // if(index==a.length) {
            ansList.add(new ArrayList<>(ans));
         //   return;
       // }

        for(int i=index;i<a.length;i++){
            if(i!=index && a[i]==a[i-1]) continue;
            ans.add(a[i]);
            findSubSets(a,i+1,ans,ansList);
            ans.remove(ans.size()-1);
        }

    }

    public static  void printPermutations(int [] a){
        ArrayList<ArrayList<Integer>> ansList=new ArrayList<>();
        ArrayList<Integer> ans=new ArrayList<>();
        Set<Integer> set=new HashSet<>();
        generateArrayPermutations(a,ans,ansList,set);
        System.out.println(ansList);
    }

    private static void generateArrayPermutations(int[] a,ArrayList<Integer> ans,
                                                  ArrayList<ArrayList<Integer>> ansList,Set<Integer> set) {
        if(ans.size()==a.length){
            ansList.add(new ArrayList<>(ans));
            return;
        }
        for(int i=0;i<a.length;i++) {
            if(!set.contains(a[i])) {
                ans.add(a[i]);
                set.add(a[i]);
                generateArrayPermutations(a, ans, ansList, set);

                ans.remove(ans.size() - 1);
                set.remove(a[i]);
            }
        }
    }

    static ArrayList<ArrayList<Integer>> permutationsWithoutUsingExtraSpace(ArrayList<Integer> arr, int size) {
        ArrayList<ArrayList<Integer>> ansList=new ArrayList<>();
        getAllPermutations(arr, 0, ansList);
        return ansList;
    }

    public static void getAllPermutations(ArrayList<Integer> arr, int index,
                                          ArrayList<ArrayList<Integer>> ansList){
        if(arr.size()==index){
            ansList.add(new ArrayList<>(arr));
            return;
        }

        for(int i=index;i<arr.size();i++){
            swap(arr,i,index);
            getAllPermutations(arr, index+1, ansList);
            swap(arr,i,index);
        }
    }

    public static void swap(ArrayList<Integer> arr,int i,int index){
        int temp=arr.get(i);
        arr.set(i, arr.get(index));
        arr.set(index, temp);
    }

    public static String reverseString(String str) {
        // Start the recursion with the original string and an empty accumulator
        return reverseHelper(str, "");
    }

    private static String reverseHelper(String str, String accumulator) {
        // Base case: if the input string is empty, return the accumulated result
        if (str.isEmpty()) {
            return accumulator;
        }

        // Recursive step:
        // Take the first character and put it at the FRONT of the accumulator
        // Then pass the rest of the string to the next call
        return reverseHelper(str.substring(1), str.charAt(0) + accumulator);
    }
}
