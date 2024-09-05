package BinarySearch;

public class BinarySearchQuestions {
    public static void main(String[] args) {
        int [] a=new int[]{1,2,3,5,6,8};
        System.out.println(binarySearchIterative(new int[]{1,2,3,5,6,8},5));
        System.out.println(binarySearchRecursive(a,5,0, a.length-1));

        System.out.println(findLowerBound(a,7));
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
            int mid=low+(high-low);
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
            int mid=low+(high-low);
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
}
