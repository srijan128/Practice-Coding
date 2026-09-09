package Practice2026;

import com.sun.source.tree.Tree;
import problemspp.TreeNode;

import java.util.*;

public class Prac2026 {
    public static void main(String[] args) {
        int [] a=new int[]{2,6};
      //  subsets(a);
       // permutationsOfArray(a);
       // combinationSum(a,6);
       // numTilePossibilities("AAB");
       // letterTilePossibilities("AB");
       // System.out.println(getHappyString1(2,1));
       // System.out.println(addOperatorsAndCheck("123",6));
//        TreeNode node=new TreeNode(10);
//        node.left=new TreeNode(5);
//        node.right=new TreeNode(11);
//        node.right.left=new TreeNode(6);
//        System.out.println(sumOfLeftLeaves(node));
        TreeNode node=new TreeNode(3);
        node.right=new TreeNode(20);
        node.right.left=new TreeNode(15);
        System.out.println(isBalanced(node));
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> ans=new HashSet<>();
        List<Integer> out=new ArrayList<>();
        Arrays.sort(candidates);
        cS(candidates,0,out,ans,target);
        return new ArrayList<>(ans);
    }

    public static void cS(int [] a, int index, List<Integer> out,Set<List<Integer>> ans, int target){
      /*  if(index==a.length){
            if(target==0){
                ans.add(new ArrayList<>(out));
            }
            return;
        }

        if(a[index]<=target){
            out.add(a[index]);
            cS(a,index,out,ans,target-a[index]);
            out.remove(out.size()-1);

        }

        cS(a,index+1,out,ans,target); */

        if(target<0)
            return;
        if(target==0){
            ans.add(new ArrayList<>(out));
            return;
        }



        for(int i=index;i<a.length;i++){
            out.add(a[i]);
            cS(a,i,out,ans,target-a[i]);
            out.remove(out.size()-1);
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> out=new ArrayList<>();
        findSubsets(nums,0,out,ans);
        return ans;
    }

    private static void findSubsets(int [] nums,int index,List<Integer> out,List<List<Integer>> ans){
        // if(index==nums.length){
        ans.add(new ArrayList<>(out));
        //    return;
        //  }

       /* out.add(nums[index]);
        findSubsets(nums,index+1,out,ans);
        out.remove(out.size()-1);
        findSubsets(nums,index+1,out,ans); */

        for(int i=index;i<nums.length;i++){
            if(i!=index && nums[i-1]==nums[i]) continue;
            out.add(nums[i]);
            findSubsets(nums,i+1,out,ans);
            out.remove(out.size()-1);
        }
    }


    public static List<List<Integer>> permutationsOfArray(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> out=new ArrayList<>();
        boolean [] freq=new boolean[nums.length];
        collectPermutations(nums,freq,ans,out);
        return ans;
    }

    public static void collectPermutations(int[] nums,boolean [] freq,List<List<Integer>> ans,List<Integer> out){

        if(out.size()==nums.length){
            ans.add(new ArrayList<>(out));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(!freq[i]){
                freq[i]=true;
                out.add(nums[i]);
                collectPermutations(nums,freq,ans,out);
                out.remove(out.size()-1);
                freq[i]=false;
            }
        }
    }

    public static List<String> addOperatorsAndCheck(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) return result;
        backtrack(result, "", num, target, 0, 0, 0);
        return result;
    }


    private static void backtrack(List<String> result, String path, String num, int target, int index, long current, long previous) {
        if (index == num.length()) {
            if (current == target) {
                result.add(path);
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') break;
            long val = Long.parseLong(num.substring(index, i + 1));
            if (index == 0) {
                backtrack(result, path + val, num, target, i + 1, val, val);
            } else {
                backtrack(result, path + "+" + val, num, target, i + 1, current + val, val);
                backtrack(result, path + "-" + val, num, target, i + 1, current - val, -val);
                backtrack(result, path + "*" + val, num, target, i + 1, current - previous + (previous * val), previous * val);
            }
        }
    }


    public List<String> findWordsInMatrix(char[][] matrix, String[] words) {
        Set<String> result = new HashSet<>();
        for (String word : words) {
            if (exist(matrix, word)) {
                result.add(word);
            }
        }
        return new ArrayList<>(result);
    }

    private boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        char temp = board[r][c];
        board[r][c] = '*';
        boolean found = dfs(board, word, r + 1, c, index + 1) ||
                dfs(board, word, r - 1, c, index + 1) ||
                dfs(board, word, r, c + 1, index + 1) ||
                dfs(board, word, r, c - 1, index + 1);
        board[r][c] = temp;
        return found;
    }

    public static int numTilePossibilities(String tiles) {
       List<String> ans=new ArrayList<>();
        StringBuilder out=new StringBuilder();
        char [] c=tiles.toCharArray();
        boolean[] used = new boolean[c.length];
        subsetStrings(c,out,ans,used);
        for(String s: ans){
            System.out.println(s);
        }
        return ans.size();
    }

    public static void subsetStrings(char [] a,StringBuilder out,List<String> ans,boolean[] used) {
        if (out.length() > 0) {
            ans.add(out.toString()); // Use a Set to automatically handle duplicates
        }

        for (int i = 0; i < a.length; i++) {
            if (used[i]) continue; // Can't use the same physical tile twice

            used[i] = true;
            out.append(a[i]);

            subsetStrings(a, out, ans, used); // Recurse

            out.deleteCharAt(out.length() - 1); // Backtrack
            used[i] = false;
        }
    }

    public static int letterTilePossibilities(String s){
        /*
        Input: tiles = "AAB"
        Output: 8
        Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
         */

        boolean [] used =new boolean[s.length()];
        Set<String> set=new HashSet<>();

        possibilities(s,used,set,"");

        for(String s1:set){
            if(!s1.isEmpty())
                System.out.println(s1);
        }
        return set.size()-1;
    }

    private static void possibilities(String s, boolean[] used, Set<String> set,String out) {
        if(set.contains(out)) return;
        set.add(out);
        for(int i=0;i<s.length();i++){
            if(!used[i]){
                used[i]=true;
                possibilities(s,used,set,out+s.charAt(i));
                used[i]=false;

            }
        }
    }

// 1415. The k-th Lexicographical String of All Happy Strings of Length n
    public String getHappyString(int n, int k) {
        List<Character> given=new ArrayList<>();
        List<String> ans=new ArrayList<>();
        given.add('a');
        given.add('b');
        given.add('c');
        helper(given,n,k,ans,new StringBuilder(""));
        if(ans.size()==k){
            return ans.get(ans.size()-1);
        }
        return "";
    }

    public static void helper(List<Character> given,int n, int k,List<String> ans,StringBuilder temp){
        if(ans.size()==k){
            return;
        }
        if(temp.length()==n){
            ans.add(temp.toString());
            return;
        }

        for(int i=0;i<3;i++){
            if(temp.length()==0 || temp.charAt(temp.length() - 1)!=given.get(i)){
                temp.append(given.get(i));
                helper(given,n,k,ans,temp);
                temp.deleteCharAt(temp.length()-1);
            }

            //     char current = given.get(i);
            // // Fix: Check if temp is empty OR current char is different from the last char added
            // if (temp.length() == 0 || temp.charAt(temp.length() - 1) != current) {
            //     temp.append(current);
            //     helper(given, n, k, ans, temp);
            //     temp.deleteCharAt(temp.length() - 1);
            // }
        }
    }


    public static String getHappyString1(int n, int k) {
        List<Character> list=new ArrayList<>();
        List<String> ans=new ArrayList<>();
        for(int i=0;i<2;i++){
            list.add('a');
            list.add('b');
        }
        helper1(n,k,list,new StringBuilder(""),ans);
        if(ans.size()==k){
            return ans.get(ans.size()-1);
        }
        return "";
    }

    private static void helper1(int n, int k, List<Character> list, StringBuilder temp,List<String> ans) {
        if(ans.size()==k){
            return;
        }
        if(temp.length()==n){
            ans.add(temp.toString());
            return;
        }
        for(int i=0;i<2;i++){
            if(temp.length()==0 || temp.charAt(temp.length()-1) != list.get(i)){
                temp.append(list.get(i));
                helper1(n,k,list,temp,ans);
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }


    public static int sumOfLeftLeaves(TreeNode root){
        if(root==null) return 0;

        int sum=0;

        if(root.left !=null && root.left.left==null && root.left.right==null){
            sum+=root.left.val;
        }
        sum+=sumOfLeftLeaves(root.left);
        sum+=sumOfLeftLeaves(root.right);
        return sum;

    }

    public static boolean isBalanced(TreeNode root) {
        int height=getHeight(root);
        return height !=-1;
    }

    public static int getHeight(TreeNode root){
        if(root == null) return 0;

        int leftHeight=getHeight(root.left);
        int rightHeight=getHeight(root.right);
        if(Math.abs(leftHeight-rightHeight)>1)
            return -1;
        if(leftHeight==-1 || rightHeight==-1)
            return -1;
        return 1+Math.max(leftHeight,rightHeight);
    }

}
