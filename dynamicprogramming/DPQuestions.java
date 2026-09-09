package dynamicprogramming;

import java.util.*;

public class DPQuestions {
    public static void main(String[] args) {
       // System.out.println(fibonacciUsingDPSpaceOptimised(5));
        fibonacci(4);
      //  climbingStairs(3);
          frogJump(new int[]{10,20,30,10});
      //  frogJumpWithDistanceOneToK(new int[]{10,20,30,10},2);
      //  maxSumOfNonAdjacentElements(new int[]{2,1,4,9});
      //  System.out.println(houseRobber(new int[]{1,3,2,1}));
      //  System.out.println(fibUsingSpaceOptimization(5));
        int [][] points=new int[][]{{1,2,5},{3,1,1}};
        System.out.println(ninjaTraining(2,points));
        System.out.println(countDistinctWayToClimbStair(3));
        ninjaTraining();
        ninjaTrainingUsingTabulation();
        int [][] dp=new int[3][2];
        for(int i=0;i<3;i++){
            Arrays.fill(dp[i], -1);
        }

        int matrix[][] = {
                {5, 9, 6},
                {11, 5, 2}
        };

        int n = matrix.length;
        int m = matrix[0].length;

        // Calculate and print the minimum sum path in the matrix
        System.out.println(minSumPath1UsingTabulation(matrix));
        System.out.println("helo");
    }



    public static int fibonacciUsingDPSpaceOptimised(int n){
        //without using extra space
        int prev2=0,prev=1;
        for(int i=2;i<=n;i++){
            int curI=prev+prev2;
            prev2=prev;
            prev=curI;
        }
        // we are returning prev because at last it points to current
        System.out.println(prev);
        // using extra space
        int [] f=new int[n+1];
        f[0]=0;
        f[1]=1;
        for(int i=2;i<n+1;i++)
            f[i]=f[i-1]+f[i-2];
        return f[n];

    }

    public static int fibUsingSpaceOptimization(int n){
        int prev2=0,prev=1;
        for(int i=2;i<=n;i++){
            int curr=prev2+prev;
            prev2=prev;
            prev=curr;
        }
        return prev;
    }

    private static void fibonacci(int n) {
        int [] dp=new int[n+1];
        Arrays.fill(dp,-1);
       int ans= fibonacciUsingMemoization(n,dp);
        System.out.println(ans);
        int [] dp1=new int[n+1];
        Arrays.fill(dp1,-1);
        int ansTab=fibonacciUsingTabulation(n,dp1);
        System.out.println(ansTab);
    }


    public static int fibonacciUsingMemoization(int n,int [] dp){
       if(n<=1) return n;
       if(dp[n] != -1) return dp[n];
       else return dp[n]= fibonacciUsingMemoization(n-1,dp)+fibonacciUsingMemoization(n-2,dp);
    }

    public static int fibonacciUsingTabulation(int n,int [] dp){
        for(int i=0;i<n+1;i++){
            if(i==0 || i==1)
                dp[i]=i;
            else
                dp[i]=dp[i-1]+dp[i-2];
        }
    return dp[n];
    }



    public static void climbingStairs(int n){
        int ans=calculateWaysToReachNthStair(n);
        System.out.println(ans);
        int [] dp=new int[n+1];
        int ans1 = calculateWaysToReachNthStairUsingTabulation(n,dp);
        System.out.println(ans1);
    }

    private static int calculateWaysToReachNthStair(int stair) {
        // standing at stair 0 is 1 way. As we need to count ways
        // similarly when at stair 1 there is only 1 way. jump 1 step to 0
        // else jumping 2 steps takes to -1 which is not possible
        if(stair==0 || stair==1) return 1;
        int jump1 = calculateWaysToReachNthStair(stair-1);
        int jump2 = calculateWaysToReachNthStair(stair-2);
        return jump1+jump2;
    }

    private static int calculateWaysToReachNthStairUsingTabulation(int stair,int [] dp) {
        // standing at stair 0 is 1 way. As we need to count ways
        // similarly when at stair 1 there is only 1 way. jump 1 step to 0
        // else jumping 2 steps takes to -1 which is not possible
        for(int i=0;i<stair+1;i++){
            if(i==0 || i==1)
                dp[i]=1;
            else
                dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[stair];
    }


    public static void frogJump(int [] a){
       // int ans=minEnergyRequired(a,a.length-1);
       // System.out.println(ans);
        int [] dp=new int[a.length];
        Arrays.fill(dp,-1);
        int ans1 = minEnergyRequiredUsingMemoization(a,a.length-1,dp);
        System.out.println(ans1);
       // int ans2 = minEnergyRequiredUsingTabulation(a,dp);
       // System.out.println(ans2);
    }

    private static int minEnergyRequired(int[] a,int index) {
        String s="";
        if(index==0) return 0;
        int jump1=minEnergyRequired(a,index-1)+Math.abs(a[index]-a[index-1]);
        int jump2=Integer.MAX_VALUE;
        if(index>1)
          jump2 = minEnergyRequired(a,index-2)+Math.abs(a[index]-a[index-2]);
        return Math.min(jump1,jump2);
    }

    private static int minEnergyRequiredUsingMemoization(int[] a,int index,int [] dp) {
        if(index==0) return 0;
        if(dp[index] != -1) return dp[index];
        int jump1=minEnergyRequiredUsingMemoization(a,index-1,dp)+Math.abs(a[index]-a[index-1]);
        int jump2=Integer.MAX_VALUE;
        if(index>1)
            jump2 = minEnergyRequiredUsingMemoization(a,index-2,dp)+Math.abs(a[index]-a[index-2]);
        return dp[index] = Math.min(jump1,jump2);
    }

    private static int minEnergyRequiredUsingTabulation(int[] a,int [] dp) {
        int jump2=Integer.MAX_VALUE;
        for(int i=0;i<a.length;i++){
            if(i==0)
                dp[i]=0;
            else {
                if(i>1)
                    jump2=dp[i-2]+Math.abs(a[i]-a[i-2]);
                int jump1=dp[i-1]+Math.abs(a[i]-a[i-1]);
                dp[i]=Math.min(jump1,jump2);
            }
        }
        return dp[a.length-1];
    }


    public static void frogJumpWithDistanceOneToK(int [] a,int k){
        int ans=minEnergyRequired(a,a.length-1,k);
        System.out.println(ans);
        int [] dp=new int[a.length];
        Arrays.fill(dp,-1);
        int ans1 = minEnergyRequiredUsingMemoization(a,a.length-1,dp,k);
        System.out.println(ans1);
        int ans2 = minEnergyRequiredUsingTabulation(a,dp,k);
        System.out.println(ans2);
    }

    private static int minEnergyRequired(int[] a,int index,int k) {
        int minSteps=Integer.MAX_VALUE;
        if(index==0) return 0;
        for(int i=1;i<=k;i++) {
            if(index-i>=0) {
                int jump = minEnergyRequired(a, index - i,k) + Math.abs(a[index] - a[index - i]);
                minSteps=Math.min(minSteps,jump);
            }
        }
        return minSteps;
    }

    private static int minEnergyRequiredUsingMemoization(int[] a,int index,int [] dp,int k) {
        int minSteps=Integer.MAX_VALUE;
        if(index==0) return 0;
        if(dp[index] != -1) return dp[index];
        for(int i=1;i<=k;i++) {
            if(index-i>=0) {
                int jump = minEnergyRequired(a, index - i) + Math.abs(a[index] - a[index - i]);
                minSteps=Math.min(minSteps,jump);
            }
        }
        return dp[index] = minSteps;
    }

    private static int minEnergyRequiredUsingTabulation(int[] a,int [] dp,int k) {
        int jump2=Integer.MAX_VALUE;
        for(int i=0;i<a.length;i++){
            if(i==0)
                dp[i]=0;
            else {
                if(i>1)
                    jump2=dp[i-2]+Math.abs(a[i]-a[i-2]);
                int jump1=dp[i-1]+Math.abs(a[i]-a[i-1]);
                dp[i]=Math.min(jump1,jump2);
            }
        }
        return dp[a.length-1];
    }


    public static void maxSumOfNonAdjacentElements(int [] a){
        int ans= calculateMaxSumOfNonAdjacentElements(a,a.length-1);
        System.out.println(ans);
        int [] dp=new int[a.length];
        Arrays.fill(dp,-1);
        int ans1=calculateMaxSumOfNonAdjacentElementsUsingMemoization(a,a.length-1,dp);
        System.out.println(ans1);
        int [] dp1=new int[a.length];
        Arrays.fill(dp,-1);
        int ans2=calculateMaxSumOfNonAdjacentElementsUsingTabulation(a,dp);
        System.out.println(ans2);
    }

    private static int calculateMaxSumOfNonAdjacentElements(int[] a, int index) {
        if(index==0)
            return a[index];
        if(index<0) return 0;
        int pick=a[index]+calculateMaxSumOfNonAdjacentElements(a,index-2);
        int notPick=calculateMaxSumOfNonAdjacentElements(a,index-1);
        return Math.max(pick,notPick);
    }

    private static int calculateMaxSumOfNonAdjacentElementsUsingMemoization(int[] a, int index,int [] dp) {
        if(index==0)
            return a[index];
        if(index<0) return 0;
        if(dp[index]!=-1) return dp[index];
        int pick=a[index] + calculateMaxSumOfNonAdjacentElementsUsingMemoization(a,index-2,dp);
        int notPick= calculateMaxSumOfNonAdjacentElementsUsingMemoization(a, index - 1, dp);
        return dp[index] = Math.max(pick,notPick);
    }

    private static int calculateMaxSumOfNonAdjacentElementsUsingTabulation(int[] a,int [] dp) {
        //dp[0]=a[0];
        for(int i=0;i<a.length;i++) {
            if (i == 0)
                dp[i] = a[0];
            else {
                int pick = a[i];
                if (i > 1)
                    pick += dp[i - 2];
                int notPick = dp[i - 1];
                dp[i] = Math.max(pick, notPick);
            }
        }
        return dp[a.length-1];
    }

    public static long houseRobber(int[] valueInHouse) {
        // Write your code here.
        int [] v1=new int[valueInHouse.length-1];
        int [] v2=new int[valueInHouse.length-1];
        int [] dp1=new int[v1.length];
        Arrays.fill(dp1,-1);
        int [] dp2=new int[v2.length];
        Arrays.fill(dp2,-1);
        v1[0]=valueInHouse[0];
        v2[v2.length-1]=valueInHouse[valueInHouse.length-1];
        for(int i=1;i<=valueInHouse.length-2;i++){
          v1[i]=valueInHouse[i];
          v2[i-1]=valueInHouse[i];
//            if(i!=valueInHouse.length-1)
//
        }
        long ans1=calculateMaxSumOfNonAdjacentElementsUsingTabulation(v1,dp1);
        long ans2=calculateMaxSumOfNonAdjacentElementsUsingTabulation(v2,dp2);
        return Math.max(ans1, ans2);
    }


    public static int ninjaTraining(int n, int points[][]) {

        // Write your code here..
        int [][] dp=new int[n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                dp[i][j] = -1;
            }
        }
        return f(n-1,3,points,dp);
    }

    public static int f(int day, int last, int [][] task,int [][] dp){
        if(day==0){
            int maxi=0;
            for(int i=0;i<3;i++){
                if(last !=i){
                    maxi=Math.max(maxi,task[0][i]);
                }
            }
            return maxi;
        }
        if(dp[day][last] != -1) return dp[day][last];
        int maxi=0;
        for(int i=0;i<3;i++){
            if(last != i){
                int point=task[day][i] + f(day-1,i,task,dp);
                maxi=Math.max(maxi, point);
            }

        }
        return dp[day][last]=maxi;
    }

    public static long countDistinctWayToClimbStair(int nStairs) {
        // Write your code here.
        long [] dp=new long[nStairs+1];
        Arrays.fill(dp,-1);
        return countDistinctWayToClimb(nStairs,dp);
    }

    public static long countDistinctWayToClimb(int nStairs, long [] dp) {
        // Write your code here.
        if(nStairs == 0 || nStairs == 1)
            return 1;
        if(dp[nStairs] != -1) return dp[nStairs];
        long twoSteps=0;
        // cant declare variable inside a if block without bracket
      //  if(nStairs>1) {
            twoSteps = countDistinctWayToClimb(nStairs-2,dp);
       // }
                  //countDistinctWayToClimbStair(nStairs-2);
        long oneStep=countDistinctWayToClimb(nStairs-1,dp);
        return dp[nStairs] = twoSteps + oneStep;
                //twoSteps + oneStep;
    }

    public static long houseRobberPrac(int[] valueInHouse) {
        ArrayList<Integer> withoutLastHouse=new ArrayList<>(valueInHouse.length-1);
        ArrayList<Integer> without1stHouse=new ArrayList<>(valueInHouse.length-1);
        without1stHouse.add(valueInHouse.length-1,valueInHouse[valueInHouse.length-1]);
        withoutLastHouse.add(0,valueInHouse[0]);
        for(int i=1;i<=valueInHouse.length-2;i++){
            without1stHouse.add(i,valueInHouse[i]);
            withoutLastHouse.add(i,valueInHouse[i]);
        }
        int sum1= calSumForNonAdjacentElements(without1stHouse,without1stHouse.size()-1);
        int sum2= calSumForNonAdjacentElements(withoutLastHouse,withoutLastHouse.size()-1);
        return Math.max(sum1, sum2);
    }

    private static int calSumForNonAdjacentElements(ArrayList<Integer> list, int index) {
        if(index==0)
            return list.get(0);
        if(index<1)
            return 0;
        int pick=list.get(index) + calSumForNonAdjacentElements(list,index-2);
        int notPick=calSumForNonAdjacentElements(list,index-1);
        return Math.max(pick,notPick);
    }

    private static int calSumForNonAdjacentElementsUsingTabulation(ArrayList<Integer> list, int index) {
        int [] dp=new int[list.size()];
        dp[0]=list.get(0);
        if(index<1)
            return 0;
        for(int i=1;i<list.size();i++){
            int pick=list.get(i);
            if(i>1)
                pick+=dp[i-2];
            int notPick=dp[i-1];
            dp[i]=Math.max(pick,notPick);
        }
        return dp[list.size()-1];
    }

    private static void ninjaTraining(){
        int n=3;
        int [][] tasks=new int[][]{{1,2,5},{3,1,1},{3,3,3}};
       // int [][] tasks=new int[][]{{1,2},{3,5}};
        int [][] dp=new int[n][3];
       /* for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = -1;
            }
        } */
        int maxVal = calculateNinjaPoints(tasks,n-1,3,dp);
        System.out.println("Ninja's max points " + maxVal);
    }




    private static int calculateNinjaPoints(int[][] tasks, int day, int last,int [][] dp) {
        if(day == 0){
            int maxi=0;
            for(int i=0;i<3;i++){
                if(i != last){
                    maxi=Math.max(maxi,tasks[0][i]);
                }
            }
            return maxi;
        }
      //  if(dp[day][last] != -1) return dp[day][last];
        int maxi=0;
        for(int i=0;i<3;i++){
            if(i != last) {
                int curVal=tasks[day][i];
                int points = curVal + calculateNinjaPoints(tasks, day - 1, i,dp);
                maxi=Math.max(points,maxi);
            }
        }
        return maxi;
    }


    private static void ninjaTrainingUsingTabulation(){
        int n=3;
       int [][] tasks=new int[][]{{1,2,5},{3,1,1},{3,3,3}};
       // int [][] tasks=new int[][]{{1,2},{3,5}};
        int [][] dp=new int[n][4];
        dp[0][0]=Math.max(tasks[0][1],tasks[0][2]);
        dp[0][1]=Math.max(tasks[0][0],tasks[0][2]);
        dp[0][2]=Math.max(tasks[0][0],tasks[0][1]);
        dp[0][3]=Math.max(tasks[0][0],Math.max(tasks[0][1],tasks[0][2]));

        for(int day=1;day<n;day++){
            for(int last=0;last<4;last++){
                dp[day][last]=0;
                int maxi=0;
                for(int task=0;task<3;task++){
                    if(task != last){
                        int points=tasks[day][task]+dp[day-1][task];
                        maxi=Math.max(points,maxi);
                    }
                }
                dp[day][last]=maxi;
            }
        }
        System.out.println(dp[n-1][3]);
    }

    public static int dpWithMazeObstaclesUsingTabulation(int n, int m, ArrayList<ArrayList<Integer>> mat){
        int mod= 1000000007;
        int [][] dp =new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i < 0 || j < 0) dp[i][j]=0;
                else if(i >= 0 && j >= 0 && mat.get(i).get(j) == -1) dp[i][j]=0;
                else if(i==0 && j==0) dp[i][j]=1;
                else {
                    int up=0,left=0;
                    if(i>0)
                        up=dp[i-1][j];
                    if(j>0)
                        left=dp[i][j-1];
                    dp[i][j]=(up+left)%mod;
                }
            }
        }
        return dp[n-1][m-1];
    }


    public static int minSumPath(int[][] grid) {
        // Write your code here.

        int n =grid.length;
        int m=grid[0].length;
        int [][] dp=new int[n][m];
        for(int[] row :dp){

            Arrays.fill(row,-1);
        }
        
        return calculateMinSumPath(n-1,m-1,grid,dp);


    }

    public static int calculateMinSumPath(int i,int j,int[][] grid,int [][] dp){

        if(i==0 && j==0) return grid[i][j];
        if(i<0 || j<0) return (int) Math.pow(10,9);
        if(dp[i][j] != -1) return dp[i][j];
        // else{
        int up=grid[i][j] + calculateMinSumPath(i-1,j,grid,dp);
        int left=grid[i][j] + calculateMinSumPath(i,j-1,grid,dp);
        return dp[i][j]=Math.min(up, left);
        //}
    }

    public static int calculateMinSumPathUsingTabulation(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        int [][] dp=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 && j==0) dp[i][j]=grid[i][j];
                else {
                    int up = grid[i][j];
                    if (i > 0)
                        up += dp[i - 1][j];
                    else up += (int) Math.pow(10, 9);
                    int left = grid[i][j];
                    if (j > 0)
                        left += dp[i][j - 1];
                    else left += (int) Math.pow(10, 9);
                    dp[i][j] = Math.min(left, up);
                }
            }
        }
        return dp[n-1][m-1];

      /*  if(i==0 && j==0) return grid[i][j];
        if(i<0 || j<0) return (int) Math.pow(10,9);
        if(dp[i][j] != -1) return dp[i][j];
        // else{
        int up=grid[i][j] + calculateMinSumPath(i-1,j,grid,dp);
        int left=grid[i][j] + calculateMinSumPath(i,j-1,grid,dp);
        return dp[i][j]=Math.min(up, left); */
        //}
    }

    public static int minSumPath1(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        return calculate(n-1,m-1,grid);
    }

    private static int calculate(int i, int j, int[][] a) {
        if(i==0 && j==0) return a[i][j];
        if(i<0 || j<0) return (int) Math.pow(10,9);
        int up=a[i][j]+calculate(i-1,j,a);
        int left=a[i][j]+calculate(i,j-1,a);
        return Math.min(up,left);
    }

    public static int minSumPath1UsingTabulation(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int [][] dp=new int[n][m];
        for(int [] row: dp){
            Arrays.fill(row,-1);
        }

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(i==0 && j==0) dp[i][j]=grid[i][j];
                else {
                    int down = grid[i][j];
                    if (i > 0) {
                        down += dp[i - 1][j];
                    } else down += (int) Math.pow(10, 9);
                    int right = grid[i][j];
                    if (j > 0) {
                        right += dp[i][j - 1];
                    } else right += (int) Math.pow(10, 9);
                    dp[i][j] = Math.min(down, right);
                }
            }
        }
        return dp[n-1][m-1];
    }

}
