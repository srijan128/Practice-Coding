package problemspp;

import linkedlist.ChildAndNextNode;
import linkedlist.Node;
import linkedlist.RandomNode;
import problemspp.sortcharbasedonfreq.CustomPair;

import java.util.*;

public class PracticeImpPP {

    public static String[] keypad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> map = new TreeMap<>();

    // Q: Reverse an array using recursion GFG
    public void reverseArray(int arr[]) {
        // code here
        int i = 0;
        int j = arr.length - 1;
        reverse(arr, 0);
        System.out.println("reversed array using recursion " + Arrays.toString(arr));
    }

    public void reverse(int arr[], int i) {
        if (i >= arr.length / 2)
            return;
        swap(arr, i, arr.length - i - 1);
        reverse(arr, i + 1);
    }



    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Q: Reverse a string
    public static void reverseAString(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            res = s.charAt(i) + res;
        }
        System.out.println(res);
    }

    // Q: reverse a number
    public static void reverseANumber(int n) {
        int res = 0;
        while (n > 0) {
            res = res * 10 + (n % 10);
            n = n / 10;
        }
        System.out.println(res);
    }

    // Q: Selection sort
    public static void selectionSort(int[] arr) {
        // code here
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    // Insertion sort
    public static void insertionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = i;
            while (j > 0 && a[j - 1] > a[j]) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
                j--;
            }
        }
    }

    // merge sort
    public static void mergeSort(int[] arr, int low, int high) {
        if (low >= high)
            return;
        int mid = (low + high) / 2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = findPartitionIndex(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private static int findPartitionIndex(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (arr[i] <= pivot && i <= high - 1)
                i++;
            while (arr[j] > pivot && j >= low + 1)
                j--;
            // Always forget this below line of i<j. Swap till i<j
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, low, j);
        return j;
    }

    public static boolean checkPalindrome(String s, int i) {
        if (i >= s.length() / 2)
            return true;
        if (s.charAt(i) != s.charAt(s.length() - i - 1))
            return false;
        return checkPalindrome(s, i + 1);
    }

    // Q. Reverse a linked list iteratively
    public static Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node after = curr.next;
            curr.next = prev;
            prev = curr;
            curr = after;
        }
        return prev;
    }


    // Q. Reverse a linked list recursive
    public static Node reverseRecursive(Node head) {
        if (head == null || head.next == null)
            return head;
        Node reversedHead = reverseRecursive(head.next);
        // Reversing each links from behind one by one
        Node headNext = head.next;
        headNext.next = head;
        head.next = null;
        return reversedHead;
    }


    // Q. Detect loop in a linked list
    public static boolean detectLoop(Node head) {
        // Add code here
        if (head == null || head.next == null)
            return false;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // Q. Starting point of a loop
    public static Node startOfALoop(Node head) {
        // Add code here
        if (head == null || head.next == null)
            return null;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }


    // Q. find the length of the loop in a LL
    public static int lengthOfLoopInLL(Node head) {
        // Add code here
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return findLength(slow, fast);

            }
        }
        return 0;
    }

    private static int findLength(Node slow, Node fast) {
        int count = 1;
        fast = fast.next;
        while (slow != fast) {
            count++;
            fast = fast.next;
        }
        return count;
    }


    // Q. Sum of two linked lists
    public Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(-1);
        Node temp = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) sum += l1.value;
            if (l2 != null) sum += l2.value;
            Node newNode = new Node(sum % 10);
            carry = sum / 10;
            temp.next = newNode;
            temp = temp.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) {
            temp.next = new Node(carry);
            temp = temp.next;
            temp.next = null;
        }
        return dummy.next;
    }

    /* Q. Segregate even and odd in LL
    Two questions:
    1. segregate even and odd as per index in the given LL considering index 1 as odd and index 2 as even, group all the nodes with
    odd indices together followed by the nodes with even indices, and return the reordered list.
    2. segregate even and odd as per values in the given LL nodes
     */

    // Q1

    public Node oddEvenList(Node head) {
        if (head == null || head.next == null) return head;
        else {
            Node odd = head;
            Node even = head.next;
            Node evenHead = even;
            while (odd.next != null && even.next != null) {
                odd.next = odd.next.next;
                odd = odd.next;
                even.next = even.next.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    // Q2
    public static Node segregateEvenOdd(Node head) {
        Node odd = new Node(-1);
        Node even = new Node(-1);
        Node oddHead = odd;
        Node ans = even;
        Node temp = head;
        while (temp != null) {
            if (temp.value % 2 == 0) {
                even.next = temp;
                even = even.next;
            } else {
                odd.next = temp;
                odd = odd.next;
            }
            temp = temp.next;
        }
        even.next = null;
        odd.next = null;
        even.next = oddHead.next;
        return ans.next;
    }


    //Q234. Palindrome Linked List. Very Important

    public boolean isPalindrome(Node head) {
        if (head == null) return true;
        Node slow = head;
        Node fast = head;
        Node prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;

            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        // for odd number of nodes in LL
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.value != prev.value)
                return false;
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }


    // Q. Given the heads of two singly linked-lists headA and headB, return the
    // node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

    public Node getIntersectionNode(Node headA, Node headB) {
        Node t1 = headA;
        Node t2 = headB;
        while (t1 != t2) {
            t1 = (t1 != null) ? t1.next : headB;
            t2 = (t2 != null) ? t2.next : headA;
        }
        return t1;
    }

    // Remove nth node from the end Linked List
    public Node removeNthFromEnd(Node head, int n) {
        Node first = head;
        Node second = head;
        Node prev = null;
        // maintain gap of n btwn first and second pointer
        while (n > 0 && second != null) {
            n--;
            second = second.next;
        }
        // Scenario 1: n doesnt reach 0 but second reaches null
        // The no of nodes in the LL are less than n
        if (n != 0) {
            return head;
        }
        // The no of nodes in equal to n
        // Hence we need to remove the head node
        if (second == null) {
            head = head.next;
            return head;
        }
        while (second != null) {
            second = second.next;
            prev = first;
            first = first.next;
        }
        // deleting nth node from the end
        prev.next = first.next;
        first.next = null;
        return head;
    }

    // Another solution of remove nth Node from the end
    public Node removeNthFromEndApproach2(Node head, int n) {


        Node second = head;
        while (second != null && n > 0) {
            n--;
            second = second.next;
        }

        if (second == null) {
            if (n > 0)
                return head;
            if (n == 0) {
                Node temp = head;
                head = head.next;
                temp.next = null;
                return head;
            }
        }
        Node first = head;
        Node prev = null;
        while (second != null) {
            prev = first;
            first = first.next;
            second = second.next;
        }

        prev.next = first.next;
        first.next = null;
        return head;
    }

    // Merge two sorted linked lists VVV Imp

    public Node mergeTwoLists(Node list1, Node list2) {
        Node dummy = new Node(-1);
        Node temp = dummy;
        while (list1 != null && list2 != null) {
            if (list1.value <= list2.value) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 != null)
            temp.next = list1;
        else
            temp.next = list2;
        return dummy.next;
    }

    // Clone a linked list with next and random pointer

    public RandomNode copyRandomList(RandomNode head) {
        // create a copy of each node after the same

        RandomNode temp = head;
        while (temp != null) {
            RandomNode n = new RandomNode(temp.val);
            n.next = temp.next;
            temp.next = n;
            // 1
            temp = temp.next.next;
        }

        // cloning the random ptr
        temp = head;
        while (temp != null) {
            if (temp.random != null) {
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }

        // segregating the lists
        temp = head;
        RandomNode original = head;
        RandomNode duplicate = head.next;
        RandomNode clonedHead = duplicate;
        while (duplicate.next != null) {
            original.next = original.next.next;
            duplicate.next = duplicate.next.next;
            original = original.next;
            duplicate = duplicate.next;
        }
        original.next = null;
        return clonedHead;
    }

    public static ChildAndNextNode flattenLinkedList(ChildAndNextNode head) {
        //Write your code here
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively flatten the
        // rest of the linked list
        ChildAndNextNode mergedHead = flattenLinkedList(head.next);
        head = merge(head, mergedHead);
        return head;
    }


    /* REPRACTICE */
    public static ChildAndNextNode merge(ChildAndNextNode list1, ChildAndNextNode list2) {
        ChildAndNextNode dummyNode = new ChildAndNextNode(-1);
        ChildAndNextNode res = dummyNode;

        // Merge the lists based on data values
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                res.child = list1;
                res = list1;
                list1 = list1.child;
            } else {
                res.child = list2;
                res = list2;
                list2 = list2.child;
            }
            res.next = null;
        }

        // Connect the remaining
        // elements if any
        if (list1 != null) {
            res.child = list1;
        } else {
            res.child = list2;
        }

        // Break the last node's
        // link to prevent cycles
        if (dummyNode.child != null) {
            dummyNode.child.next = null;
        }

        return dummyNode.child;
    }

    // Q. Reverse nodes in groups of k

    public Node reverseKGroup(Node head, int k) {
        Node curr = head;
        int len = calculateLength(head);
        int groups = len / k;
        Node curHead = head;
        Node prevHead = null;
        Node ans = null;
        for (int i = 0; i < groups; i++) {
            Node prev = null;
            for (int j = 0; j < k; j++) {
                Node after = curr.next;
                curr.next = prev;
                prev = curr;
                curr = after;
            }
            if (prevHead == null) {
                ans = prev;
            } else {
                prevHead.next = prev;
            }
            prevHead = curHead;
            curHead = curr;
        }
        prevHead.next = curHead;
        return ans;
    }

    private int calculateLength(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public Node reverseKGroup1(Node head, int k) {
        int len = calculateLength(head);
        Node curHead = head;
        Node prevHead = null;
        Node ans = null;
        int groups = len / k;
        for (int i = 0; i < groups; i++) {
            Node curr = curHead;
            Node prev = null;
            Node after = null;
            for (int j = 0; j < k; j++) {
                after = curr.next;
                curr.next = prev;
                prev = curr;
                curr = after;
            }
            if (prevHead == null) {
                ans = prev;
            } else {
                prevHead.next = prev;
            }
            prevHead = curHead;
            curHead = curr;
        }
        prevHead.next = curHead;
        return ans;
    }


    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null) return head;
        int len = calculateLength(head);
        if (k%len == 0) return head;
        k = k % len;
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = head;
        int end = len - k;
        Node temp = head;
        while (end >= 2) {
            temp = temp.next;
            end--;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }


    // STACK

    // 496. Next Greater Element I
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> s = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[nums1.length];
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && nums2[i] >= s.peek()) {
                s.pop();
            }
            map.put(nums2[i], (s.isEmpty()) ? -1 : s.peek());
            s.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    // 503. Next Greater Element II

    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> s = new Stack<>();
        int[] nge = new int[nums.length];
        for (int i = nums.length * 2 - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= nums[i % nums.length]) {
                s.pop();
            }
            if (i < nums.length) {
                nge[i] = (s.isEmpty()) ? -1 : s.peek();
            }
            s.push(nums[i % nums.length]);
        }
        return nge;
    }

    // The Celebrity Problem
    // In gfg the problem is potrayed wrongly. The input is given in such a way that celebrity knows himself also
    // hence checking if(knowMe[i]==mat.length && iKnow[i]==1)
    // but actually celebrity should not know anyone and everyone should know celebrity
    // then condition should be if(knowMe[i]==mat.length-1 && iKnow[i]==0)
    public static int celebrity_Brute(int mat[][]) {
        // code here
        int [] knowMe=new int[mat.length];
        int [] iKnow=new int[mat.length];
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==1){
                    knowMe[j]++;
                    iKnow[i]++;
                    }
            }
        }

        for(int i=0;i<mat.length;i++){
            if(knowMe[i]==mat.length && iKnow[i]==1)
                return i;
        }
        return -1;
    }

    public static int celebrity_optimal(int mat[][]) {
        // code here
        int low=0,high=mat.length-1;
        for(int i=0;i<mat.length;i++){
            if(mat[low][high]==1)
                low++;
            else if(mat[high][low]==1)
                high--;
            else{
                low++;
                high--;
            }
        }
        if(low>high) return -1;
        else{
            for(int i=0;i<mat.length;i++){
                if(mat[low][i]==0 && mat[i][low]==1)
                    return low;
                else
                    return -1;
            }
        }
        return -1;
    }


    // 32. Longest Valid Parentheses
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        // below line is most important part of logic
        st.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    res = Math.max(res, (i - st.peek()));
                }
            }
        }
        return res;
    }


    public static int largestRectangleArea(int[] heights) {
        // 2 1 5 6 2 3
        Stack<Integer> stack=new Stack<>();
        int maxArea=Integer.MIN_VALUE;
        for(int i=0;i<heights.length;i++){
            while(!stack.isEmpty() && heights[stack.peek()]>=heights[i]){
                int elementIndex=stack.pop();
                int pSI=(stack.isEmpty())?-1:stack.peek();
                int nSI=i;
                int area=heights[elementIndex]*(nSI-pSI-1);
                maxArea=Math.max(maxArea,area);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int elementIndex=stack.pop();
            int pSI=(stack.isEmpty())?-1:stack.peek();
            int nSI=heights.length;
            int area=heights[elementIndex]*(nSI-pSI-1);
            maxArea=Math.max(maxArea,area);
        }

        return maxArea;
    }


    // Q. Next permutation
    public static void nextPermutation(int[] a) {
        // [1,3,2]
        // find the index point of the curve
        int n = a.length;
        int index = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            reverse(a, 0, n - 1);
        } else {

            // find the next smaller number just greater the index element
            for (int i = n - 1; i > index; i--) {
                if (a[i] > a[index]) {
                    swap1(a, i, index);
                    break;
                }
            }
            // swap till the element before the index to get the next permutation
            reverse(a, index + 1, n - 1);
        }
    }

    public static void swap1(int[] a, int i, int index) {
        int temp = a[i];
        a[i] = a[index];
        a[index] = temp;
    }

    public static void reverse(int[] a, int i, int j) {
        while (i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }

    public static void swap2(int[] nums1, int i, int[] nums2, int j) {
        int temp = nums1[i];
        nums1[i] = nums2[j];
        nums2[j] = temp;
    }

    public static void mergeTwoSortedArrays(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = 0;
        while (i >= 0 && j < n) {
            if (nums1[i] > nums2[j]) {
                swap2(nums1, i, nums2, j);
                i--;
                j++;
            } else {
                break;
            }
        }
        for (int k = 0; k < n; k++) {
            nums1[m + k] = nums2[k];
        }
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }


    // Q. 238. Product of Array Except Self

    public int[] productExceptSelf(int[] nums) {
        int[] prefixProd = new int[nums.length];
        int[] suffixProd = new int[nums.length];
        int[] ans = new int[nums.length];

        prefixProd[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            prefixProd[i] = prefixProd[i - 1] * nums[i - 1];
        }

        suffixProd[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            suffixProd[i] = nums[i + 1] * suffixProd[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            ans[i] = prefixProd[i] * suffixProd[i];
        }

        return ans;
    }

    public void sortColors(int[] a) {
        // [2,0,2,1,1,0]
        int mid = 0, start = 0;
        int end = a.length - 1;
        while (mid <= end) {
            if (a[mid] == 0) {
                swap2(a, start, mid);
                mid++;
                start++;
            } else if (a[mid] == 1) {
                mid++;
            } else {
                swap2(a, mid, end);
                end--;
            }
        }


    }

    public void swap2(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    // 1. Two Sum
    public static int[] twoSum(int[] nums, int target) {
        // [2,7,11,15]
        int l = 0, r = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    // 15. 3Sum V Imp
    // With Duplicates
    public static List<List<Integer>> threeSum(int[] nums) {
        // [-1,0,1,2,-1,-4]
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> checkForThird = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int numsK = -(nums[i] + nums[j]);
                if (checkForThird.contains(numsK)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(numsK);
                    Collections.sort(list);
                    set.add(list);
                } else {
                    checkForThird.add(nums[j]);
                }
            }
        }
        return new ArrayList<>(set);
    }


    // 15. 3Sum V Imp Most Optimal
    // With Duplicates
    public static List<List<Integer>> threeSumOptimal(int[] nums) {
        // [-1,0,1,2,-1,-4]
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    ans.add(list);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        return ans;
    }

    // 11. Container With Most Water
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int water = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, water);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }

        }
        return max;
    }

    public static int inversionCount(int arr[]) {
        // Your Code Here
    int count=mS(arr,0,arr.length-1);
    return count;
    }

    public static int mS(int [] a, int low,int high){
        int count=0;
        if(low>=high) return count;
        int mid=low+(high-low)/2;
        count+=mS(a,low,mid);
        count+=mS(a,mid+1,high);
        count+=mergeTwo(a,low,mid,high);
        return count;
    }

    private static int mergeTwo(int[] a, int low, int mid, int high) {
        int left=low;
        int right=mid+1;
        int count=0;
        ArrayList<Integer> al=new ArrayList<>();
        while(left<=mid && right<=high){
            if(a[left]<=a[right]){
                al.add(a[left]);
                left++;
            }else{
                al.add(a[right]);
                count+=mid-left+1;
                right++;
            }
        }
        while(left<=mid){
            al.add(a[left]);
            left++;
        }
        while(right<=high){
            al.add(a[right]);
            right++;
        }
        for(int i=low;i<=high;i++){
            a[i]=al.get(i-low);
        }
        return count;
    }

    public static String frequencySort(String s) {
        Map<Character,Integer> map=new HashMap<>();
        PriorityQueue<CustomPair> pq=new PriorityQueue<>();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }

        for(Map.Entry<Character,Integer> entry: map.entrySet()){
            pq.add(new CustomPair(entry.getKey(),entry.getValue()));
        }

        while(!pq.isEmpty()){
            CustomPair cp=pq.poll();
            for(int j=0;j<cp.freq;j++){
                sb.append(cp.c);
            }
        }
        return sb.toString();
    }

    // rain water Trapping leetcode
    // using prefix and suffix array
    // better solution to be understood
    public int trap(int[] height) {
        int[] prefix = new int[height.length];
        int[] suffix = new int[height.length];
        prefix[0] = height[0];
        int totalWater = 0;
        for (int i = 1; i < height.length; i++) {
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        }
        suffix[suffix.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }
        for (int i = 0; i < height.length; i++) {
            totalWater += Math.min(prefix[i], suffix[i]) - height[i];
        }
        return totalWater;
    }

    // 242. valid anagram
    public boolean isAnagram(String s, String t) {
        String s1 = s.toLowerCase();
        String t1 = t.toLowerCase();
        int[] freqArray = new int[26];
        for (char c : s1.toCharArray()) {
            freqArray[c - 'a']++;
        }
        for (char c : t1.toCharArray()) {
            freqArray[c - 'a']--;
        }
        for (int i : freqArray) {
            if (i != 0)
                return false;
        }
        return true;
    }


    // 49. Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (String s : strs) {
            char[] temp = s.toCharArray();
            Arrays.sort(temp);
            String s1 = new String(temp);
            if (map.containsKey(s1)) {
                map.get(s1).add(s);

            } else {
                List<String> mapVal = new ArrayList<>();
                mapVal.add(s);
                map.put(s1, mapVal);
            }
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }


    // 229. Majority Element II
    // Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
    public static List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (ans.size() == 2) {
                break;
            } else if ((ans.isEmpty() || ans.get(0) != nums[i]) && map.get(nums[i]) > nums.length / 3)
                ans.add(nums[i]);
        }
        if (ans.size() == 2 && Objects.equals(ans.get(0), ans.get(1)))
            ans.remove(1);
        return ans;
    }

    public static List<Integer> majorityElementTufUsingMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        int min = (int) (nums.length / 3) + 1;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (ans.size() == 2) {
                break;
            }
            if (map.get(nums[i]) == min)
                ans.add(nums[i]);
        }
        Collections.sort(ans);
        return ans;
    }


    public static List<Integer> majorityElementTufUsingMooreAlgo(int[] nums) {
        int el1 = Integer.MIN_VALUE;
        int el2 = Integer.MIN_VALUE;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count1 == 0 && nums[i] != el2) {
                el1 = nums[i];
                count1 = 1;
            } else if (count2 == 0 && nums[i] != el1) {
                el2 = nums[i];
                count2 = 1;
            } else if (nums[i] == el1) count1++;
            else if (nums[i] == el2) count2++;
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        int min = (int) (nums.length / 3) + 1;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == el1) count1++;
            if (nums[i] == el2) count2++;
        }
        if (count1 >= min) ans.add(el1);
        if (count2 >= min) ans.add(el2);
        Collections.sort(ans);
        return ans;
    }


    // Zig zag matrix traversal
    public static ArrayList<Integer> printMatrix(int[][] mat) {

        ArrayList<ArrayList<Integer>> diagonals = new ArrayList<>();
        int n = mat.length;
        int m = mat[0].length;
        // Calculate total diagonals and initialize empty arraylist for each one
        for (int i = 0; i < n + m - 1; i++) {
            ArrayList<Integer> diagonal = new ArrayList<>();
            diagonals.add(diagonal);
        }

        // Based on the traversal as per the addition of odd even nature of element indexes
        // we add elements from back if it odd and in case of even we add elements from the front

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // odd case
                if ((i + j) % 2 != 0) {
                    diagonals.get(i + j).add(mat[i][j]);
                    // even case
                } else {
                    diagonals.get(i + j).add(0, mat[i][j]);
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        // Adding elements from diagonals to ans based on order
        for (int i = 0; i < n + m - 1; i++) {
            while (diagonals.get(i).size() != 0) {
                ans.add(diagonals.get(i).get(0));
                diagonals.get(i).remove(0);
            }
        }
        return ans;
    }


    // 73. Set Matrix Zeroes

    public void setZeroes(int[][] matrix) {
        /*
           1 1 1
           1 0 1
           1 1 1
         */
        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] == 0 || col[j] == 0)
                    matrix[i][j] = 0;
            }
        }
    }


    public void setZeroesWithoutExtraSpace(int[][] matrix) {
        int col0 = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0)
                    matrix[i][0] = 0;
                else {
                    if (j == 0)
                        col0 = 0;
                    else
                        matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0)
                        matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        }

        if (col0 == 0) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }


    }

    public static void setZeroesBest(int[][] matrix) {
        boolean setfirstRow = false, setFirstCol = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) setfirstRow = true;
                    if (j == 0) setFirstCol = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (setfirstRow)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        if (setFirstCol)
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
    }


    /*

    74. Search a 2D Matrix
    You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.
     */
// Remember every row is sorted and every column is sorted respectively

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }

    /*
    Search in a Fully sorted Matrix GFG
    Given a strictly sorted 2D matrix mat[][] of size n x m and a number x. Find whether the number x is present in the matrix or not.
Note: In a strictly sorted matrix, each row is sorted in strictly increasing order, and the first element of the ith row (i!=0) is greater than the last element of the (i-1)th row.

Input: mat[][] = [[1, 5, 9], [14, 20, 21], [30, 34, 43]], x = 14
Output: true
Explanation: 14 is present in the matrix, so output is true.
     */

    public boolean searchInFullySortedMatrix(int[][] mat, int x) {
        // code here
        int low = 0, high = (mat.length * mat[0].length) - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int row = mid / mat[0].length;
            int col = mid % mat[0].length;
            if (mat[row][col] == x)
                return true;
            else if (mat[row][col] < x) low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }

    // 48. Rotate Image 90 degree clockwise
    public static void rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // Transpose of matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // reverse each rows in the transposed matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][cols - 1 - j];
                matrix[i][cols - 1 - j] = temp;
            }
        }
    }


    // 48. Rotate Image 90 degree anticlockwise
    public static void rotateAcw(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // Transpose of matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // reverse each columns in the transposed matrix
        for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < cols; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[rows - i - 1][j];
                matrix[rows - i - 1][j] = temp;
            }
        }
    }

    // Rotate a Matrix by 180 Counterclockwise

    public void rotateMatrix180(int[][] mat) {
        // code here
        int rows = mat.length;
        int cols = mat[0].length;
        // swap rows(0 with n-1)
        for (int i = 0; i < mat.length / 2; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[rows - 1 - i][j];
                mat[rows - 1 - i][j] = temp;
            }
        }

        // reverse each row
        for (int[] row : mat) {
            //int [] a=rows[i];
            // for(int j=0;j<row.length;j++){
            int low = 0, high = row.length - 1;
            while (low < high) {
                int temp = row[low];
                row[low] = row[high];
                row[high] = temp;
                low++;
                high--;
            }
            // }
        }
    }

    /*
    ************************ V V IMP *******************************
    Median of Two Sorted Arrays
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

    The overall run time complexity should be O(log (m+n)).

     */

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int l = 0, h = nums1.length;
        while (l <= h) {
            int m1 = (l + h) / 2;
            // Adding 1 to accomodate both even and odd length cases
            int m2 = ((nums1.length + nums2.length) + 1) / 2 - m1;

            // find border elements on left and right
            int l1 = (m1 == 0) ? Integer.MIN_VALUE : nums1[m1 - 1];
            int r1 = (m1 == nums1.length) ? Integer.MAX_VALUE : nums1[m1];

            int l2 = (m2 == 0) ? Integer.MIN_VALUE : nums2[m2 - 1];
            int r2 = (m2 == nums2.length) ? Integer.MAX_VALUE : nums2[m2];

            if (l1 <= r2 && l2 <= r1) {
                if ((nums1.length + nums2.length) % 2 == 0)
                    return (double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
                else
                    return (double) (Math.max(l1, l2));
            } else if (l2 > r1)
                l = m1 + 1;
            else
                h = m1 - 1;
        }
        return 0.0d;
    }

    /* Basic Binary Search Practice */
    public int search(int[] nums, int target) {
        // [-1,0,3,5,9,12]
       /* int low=0,high=nums.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                low=mid+1;
            else
                high=mid-1;
        }
        return -1; */

        int ans = bs(nums, 0, nums.length - 1, target);
        return ans;
    }

    public int bs(int[] nums, int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            return bs(nums, mid + 1, high, target);
        else
            return bs(nums, low, mid - 1, target);
    }

    /* Lower Bound binary search
        the smallest index such that arr[index]>=target
     */
    public static int lowerBound(int[] arr, int n, int target) {
        // Write your code here
        int low = 0, high = arr.length - 1;
        int ans = arr.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int upperBound(int[] arr, int target) {
        // code here
        int low=0,high=arr.length-1;
        int ans=arr.length;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]<=target)
                low=mid+1;
            else if(arr[mid]>target){
                ans=mid;
                high=mid-1;
            }
        }
        return ans;
    }




    // Search In A rotated sorted array
    public int searchInARotatedSortedArray(int[] nums, int target) {
        /*
        Identify the sorted half and check if the target is there else eliminate the half and
        move to the other half
        */
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


    // Find peak element

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
        int low = 1, high = nums.length - 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid - 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    // First and last occurence in a sorted array
    public int[] searchRange(int[] nums, int target) {
        int lB=lowerBound(nums,0,nums.length-1,target);
        int uB=upperBound(nums,0,nums.length-1,target);
        if(lB==nums.length || nums[lB]!=target)
            return new int[]{-1,-1};
        return new int[]{lB,uB-1};
    }

    public int lowerBound(int[] a,int low,int high,int target){
        // int low=0,high=a.length-1;
        int ans=a.length;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]>=target){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }

    public int upperBound(int[] a,int low,int high,int target){
        // int low=0,high=a.length-1;
        int ans=a.length;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(a[mid]>target){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }

    // 153. Find Minimum in Rotated Sorted Array
    /*
    Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.
     */
    public int findMin(int[] nums) {
        //[4,5,6,7,0,1,2]
        int low = 0, high = nums.length - 1;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= nums[low]) {
                ans = Math.min(nums[low], ans);
                low = mid + 1;
            } else if (nums[mid] <= nums[high]) {
                ans = Math.min(nums[mid], ans);
                high = mid - 1;
            }
        }
        return ans;
    }

    // Koko eating bananas
    public int minEatingSpeed(int[] piles, int h) {
        int maxInPiles = findMax(piles);
        int low = 1, high = maxInPiles;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int totalHoursTaken = calculateTime(piles, mid);
            if (totalHoursTaken <= h) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int calculateTime(int[] piles, int mid) {
        int totalHours = 0;
        for (int i : piles) {
            totalHours += Math.ceil((double) i / (double) mid);
        }
        return totalHours;
    }

    private int findMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int i : piles)
            max = Math.max(i, max);
        return max;
    }

    // Aggresive Cows Binary Search
    public static int aggressiveCows(int[] stalls, int k) {
        // code here
        Arrays.sort(stalls);
        int low=1,high=stalls[stalls.length-1]-stalls[0];
        int ans=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(canCowsBePlaced(stalls,mid,k)==true){
                ans=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;
    }

    public static boolean canCowsBePlaced(int [] stalls, int minDistanceBtwnCows, int cows){
        int cowsCount=1,curCoordinate=stalls[0];
        for(int i=1;i<stalls.length;i++){
            if(stalls[i]-curCoordinate>=minDistanceBtwnCows){
                cowsCount++;
                curCoordinate=stalls[i];
            }
        }
        if(cowsCount>=cows){
            return true;
        }
        return false;
    }

    // 20. Valid Parentheses
    /*
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
     */
    public static boolean isValid(String s1) {
        Stack<Character> s = new Stack<>();
        for (char c : s1.toCharArray()) {
            if (c == '{' || c == '[' || c == '(')
                s.push(c);
            else if (!s.isEmpty() && s.peek() == '(' && c == ')')
                s.pop();
            else if (!s.isEmpty() && s.peek() == '{' && c == '}')
                s.pop();
            else if (!s.isEmpty() && s.peek() == '[' && c == ']')
                s.pop();
            else
                return false;
        }
        return s.isEmpty();
    }


    /*
    50. Pow(x, n)
     */
    public static double myPow(double x, int n) {
        double ans = 1.0;
        //// Take long as the test case for x=2.00000 and n = -2147483648 crosses int boundary
        long m = n;
        if (m < 0) m = -1 * m;
        while (m > 0) {
            if (m % 2 == 1) {
                ans = ans * x;
                m -= 1;
            } else {
                x = x * x;
                m = m / 2;
            }
        }
        if (n < 0) ans = (double) 1.0 / (double) ans;
        return ans;
    }

    /* 69. Sqrt(x)  */

    public int mySqrt(int x) {
        int low = 0, high = x;
        int ans = 1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid <= x) {
                ans = (int) mid;
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return ans;
    }


    // Kadane's Algo -
    public static int maxSubArray(int[] a) {
       /* long sum=0;long max= Long.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(sum>max){
                max=sum;
            }
            if(sum<0){
                sum=0;
            }
        }
        return (int) max; */
        if (a.length == 1) return a[0];
        long sum = a[0];
        long maxSum = a[0];
        for (int i = 1; i < a.length; i++) {
            sum = Math.max(a[i], sum + a[i]);
            maxSum = Math.max(sum, maxSum);
            if (sum < 0) sum = 0;


        }
        return (int) maxSum;
    }

    // 560. Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        // Prefix sum technique
        // count of subarrays with sum-k is equivalent to subsrrays with sum k
        int sum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        /*
        Input: candidates = [2,3,6,7], target = 7
        Output: [[2,2,3],[7]]
         */
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        findAllCombinations(0, candidates, target, ds, ans);
        return ans;
    }

    private static void findAllCombinations(int index, int[] candidates, int target, List<Integer> ds, List<List<Integer>> ans) {
        if (index == candidates.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(ds));
                return;
            }
            return;
        }
        if (candidates[index] <= target) {
            ds.add(candidates[index]);
            findAllCombinations(index, candidates, target - candidates[index], ds, ans);
            ds.remove(ds.size() - 1);
        }

        findAllCombinations(index + 1, candidates, target, ds, ans);
    }

    // TLE in leetcode
    // Better solution below
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> ds = new ArrayList<>();
        findAllCombinations(0, candidates, target, ds, set);
        List<List<Integer>> ans = new ArrayList<>(set);
        return ans;
    }

    private static void findAllCombinations(int index, int[] candidates, int target, List<Integer> ds, Set<List<Integer>> set) {
        if (index == candidates.length) {
            if (target == 0) {
                set.add(new ArrayList<>(ds));
                return;
            }
            return;
        }
        if (candidates[index] <= target) {
            ds.add(candidates[index]);
            findAllCombinations(index + 1, candidates, target - candidates[index], ds, set);
            ds.remove(ds.size() - 1);
        }

        findAllCombinations(index + 1, candidates, target, ds, set);
    }

    public static List<List<Integer>> combinationSum2Optimal(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        findAllCombinationsOptimal(0, candidates, target, ds, ans);

        return ans;
    }

    private static void findAllCombinationsOptimal(int index, int[] candidates, int target, List<Integer> ds, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(ds));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i - 1] == candidates[i]) continue;
            if (candidates[i] > target) break;

            ds.add(candidates[i]);
            findAllCombinationsOptimal(i + 1, candidates, target - candidates[i], ds, ans);
            ds.remove(ds.size() - 1);
        }
    }

    // 216. Combination Sum III
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        findCombinations(1, ds, ans, k, n);
        return ans;
    }

    public void findCombinations(int element, List<Integer> ds, List<List<Integer>> ans, int k, int target) {
        if (ds.size() == k) {
            if (target == 0) {
                ans.add(new ArrayList<>(ds));
            }
            return;
        }


        for (int el = element; el <= 9; el++) {
            if (el > target) break;
            ds.add(el);
            findCombinations(el + 1, ds, ans, k, target - el);
            ds.remove(ds.size() - 1);
        }
    }


    // 128. Longest Consecutive Sequence
    public static int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int count = 0, longest = 1, lastSmaller = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 == lastSmaller) {
                count += 1;
                lastSmaller = nums[i];
            } else if (nums[i] != lastSmaller) {
                count = 1;
                lastSmaller = nums[i];
            }
            longest = Math.max(longest, count);
        }
        return longest;
    }


    public static int longestConsecutiveOptimised(int[] nums) {
        int longestLength = 0;
        Map<Integer, Boolean> numberTravelledMap = new HashMap<>();
        for (int num : nums) {
            numberTravelledMap.put(num, Boolean.FALSE);
        }

        for (int num : nums) {
            int currentLength = 1;

            // Check in forward direction
            int nextNum = num + 1;
            while (numberTravelledMap.containsKey(nextNum) &&
                    numberTravelledMap.get(nextNum) == false) {

                currentLength++;
                numberTravelledMap.put(nextNum, Boolean.TRUE);

                // Move to the next number
                nextNum++;
            }

            // Check in reverse direction
            int prevNum = num - 1;
            while (numberTravelledMap.containsKey(prevNum) &&
                    numberTravelledMap.get(prevNum) == false) {

                currentLength++;
                numberTravelledMap.put(prevNum, Boolean.TRUE);

                // Move to the previous number
                prevNum--;
            }

            longestLength = Math.max(longestLength, currentLength);
        }

        return longestLength;
    }


    public static void recurPermute(ArrayList<Integer> ds, int[] nums, ArrayList<ArrayList<Integer>> ans, int freq[]) {
        if (ds.size() == nums.length) {
            ans.add(new ArrayList<>(ds));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (freq[i] != 1) {
                ds.add(nums[i]);
                freq[i] = 1;
                recurPermute(ds, nums, ans, freq);
                freq[i] = 0;
                ds.remove(ds.size() - 1);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> permute(int[] nums) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> ds = new ArrayList<>();
        int[] freq = new int[nums.length];
        //   for (int i = 0; i < nums.size(); i++) freq[i] = 0;
        recurPermute(ds, nums, ans, freq);
        return ans;
    }


    // 46. Permutations
    public List<List<Integer>> permuteWithoutExtraSpace(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        findAllPermutations(0, nums, ans);
        return ans;
    }

    public void findAllPermutations(int index, int[] nums, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> ds = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                ds.add(nums[i]);
            }
            ans.add(ds);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap3(nums, index, i);
            findAllPermutations(index + 1, nums, ans);
            swap3(nums, index, i);
        }
    }

    public void swap3(int[] nums, int index, int i) {
        int temp = nums[index];
        nums[index] = nums[i];
        nums[i] = temp;
    }

    // 78. Subsets(pick and not pick)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        findSubsets(0, nums, ds, ans);
        return ans;
    }

    public void findSubsets(int index, int[] nums, List<Integer> ds, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(ds));
            return;
        }

        ds.add(nums[index]);
        findSubsets(index + 1, nums, ds, ans);
        ds.remove(ds.size() - 1);
        findSubsets(index + 1, nums, ds, ans);
    }


    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty())
            return new ArrayList<>();
        List<String> ansList = new ArrayList<>();
        findCombinations(digits, "", ansList);
        return ansList;
    }

    public static void findCombinations(String s, String ans, List<String> ansList) {

        // find the integer value of first digit hence subtracting 48 from s.charAt(0)

        if (s.isEmpty()) {
            ansList.add(ans);
            return;
        }

        String key = keypad[s.charAt(0) - 48];

        for (int i = 0; i < key.length(); i++) {
            findCombinations(s.substring(1), ans + key.charAt(i), ansList);
        }
    }

    public static String countAndSay(int n) {
        if (n == 1) return "1";
        StringBuilder res = new StringBuilder();
        String s = countAndSay(n - 1);
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            counter++;
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                res.append(counter).append(s.charAt(i));
                counter = 0;
            }

        }
        return res.toString();
    }


    public static int myAtoi(String s) {
        long res=0;
        if(s.isEmpty()) return 0;
        s=s.trim();
        int sign=1;
        int i=0;
        if (i < s.length() &&
                (s.charAt(i) == '-'
                || s.charAt(i) == '+')) {
            sign=(s.charAt(i) == '-')?-1:1;
            i++;
            }
        while(i<s.length() && Character.isDigit(s.charAt(i))){
            res=res*10+(s.charAt(i)-'0');
            if(res*sign>Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if(res*sign<Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            i++;
        }
        return (int) (res*sign);
    }

    // Intervals

    // Merge All Overlapping Intervals
    // Brute Force
    public static List<List<Integer>> mergeOverlappingIntervals(int[][] arr) {
        // after sort (1,3) ,(2,6),(8,9),(8,10),(9,11)
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int start = arr[i][0];
            int end = arr[i][1];
            if (!ans.isEmpty() && end <= ans.get(ans.size() - 1).get(1))
                continue;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j][0] <= end) {
                    end = Math.max(end, arr[j][1]);
                } else {
                    break;
                }
            }
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(end);
            ans.add(list);
        }
        return ans;
    }


    public static int[][] merge1(int[][] intervals) {

        // 1,3 -- 2,6--8,9

        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> result = new ArrayList<>();

        int[] current = intervals[0];
        result.add(current);

        for (int[] interval : intervals) {
            int currEnd = current[1];
            int nextBegin = interval[0];
            int nextEnd = interval[1];
            if (currEnd >= nextBegin)
                // Overlapping intervals,
                // update the end if needed
                current[1] = Math.max(currEnd, nextEnd);
            else {
                // Disjoint intervals,
                // add the new interval to the list
                current = interval;
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][]);
    }


    // Meetings rooms 2. Minimum no of meeting rooms reqd
    // sort data as per start time in array, requires min heap to compare with end time. hence add intervals sorted
    // as per end time
    public int solve(int[][] A) {
        if (A == null || A.length == 0)
            return 0;
        Arrays.sort(A, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(A[0]);
        for (int i = 1; i < A.length; i++) {
            int[] curr = pq.peek();
            if (curr[1] <= A[i][0])
                pq.poll();
            pq.add(A[i]);
        }
        return pq.size();
    }

    // Greedy Algo

    // Activity Selection GFG

    public static int activitySelection(int[] start, int[] finish) {
        // code here.
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            indexList.add(i);
        }
        Collections.sort(indexList, (i, j) -> finish[i] - finish[j]);
        int maxActivityCount = 1;
        int lastEndTime = finish[indexList.get(0)];
        for (int i = 1; i < indexList.size(); i++) {
            if (start[indexList.get(i)] > lastEndTime) {
                maxActivityCount++;
                lastEndTime = finish[indexList.get(i)];
            }
        }
        return maxActivityCount;
    }

    // 435. Non-overlapping Intervals leetcode
    public static int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals, (i, j) -> i[1] - j[1]);
        int lastEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < lastEnd) {
                count++;
            } else {
                lastEnd = intervals[i][1];
            }
        }
        return count;
    }


    // 452. Minimum Number of Arrows to Burst Balloons leetcode
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (i, j) -> (i[1] <= j[1]) ? -1 : 1);
        int count = 1;
        int lastEnd = points[0][1];
        // for(int point []:points){
        for (int i = 1; i < points.length; i++) {
            //  if(point[0]>lastEnd){
            if (points[i][0] > lastEnd) {
                count++;
                lastEnd = points[i][1];
            }
        }
        return count;
    }

    // Job Sequencing Problem(Hard and VV IMP)
    public static ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        // code here
        int n = 0;
        int maxProfit = 0, count = 0;
        for (int i = 0; i < deadline.length; i++) {
            n = Math.max(n, deadline[i]);
        }
        int[] assignedJobs = new int[n + 1];
        Arrays.fill(assignedJobs, -1);
        Integer[] index = new Integer[profit.length];
        for (int i = 0; i < profit.length; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (a, b) -> profit[b] - profit[a]);
        for (int i = 0; i < index.length; i++) {
            int ind = index[i];
            int d = deadline[ind];
            while (assignedJobs[d] != -1) d--;
            if (d == 0) continue;
            count++;
            assignedJobs[d] = ind;
            maxProfit += profit[ind];

        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(count);
        res.add(maxProfit);
        // System.out.println("job seq " + res);
        return res;
    }


    // Police and Thieves  GFG greedy

    public int catchThieves(char[] arr, int k) {
        // code here
        ArrayList<Integer> police = new ArrayList<>();
        ArrayList<Integer> thief = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'P') {
                police.add(i);
            } else
                thief.add(i);
        }

        int p = 0, t = 0, count = 0;
        while (p < police.size() && t < thief.size()) {
            if (Math.abs(police.get(p) - thief.get(t)) <= k) {
                count++;
                p++;
                t++;
            } else if (thief.get(t) < police.get(p)) {
                t++;
            } else {
                p++;
            }
        }
        return count;
    }

    // JUMP GAME 1 leetcode
    public boolean canJump(int[] nums) {
        int finalPos = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= finalPos) {
                finalPos = i;
            }
        }

        return finalPos == 0;
    }

    // 45. Jump Game II

    public int jump(int[] nums) {
        int end = 0;
        int minJumps = 0;
        int maxIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxIndex = Math.max(maxIndex, nums[i] + i);
            if (i == end) {
                minJumps++;
                end = maxIndex;
            }
            if (end >= nums.length - 1) {
                break;
            }
        }
        return minJumps;
    }

    // min no of railways platform

    public static int calculateMinPlatforms(int at[], int dt[], int n) {
        // Write your code here.
        Arrays.sort(at);
        Arrays.sort(dt);
        int a = 0, d = 0, count = 0, max = 0;
        ;
        while (a < at.length) {
            if (at[a] <= dt[d]) {
                count++;
                max = Math.max(count, max);
                a++;
            } else {
                count--;
                d++;
            }
        }
        return max;
    }

    // Longest Subarray With Sum K worka for positive numbers
    // for negatives prefix sum with hashmap needs to used

    public static int longestSubarrayWithSumK(int[] a, long k) {
        // Write your code here
        int l = 0, r = 0, maxLen = 0;
        long sum = 0;
        while (r < a.length) {

            sum += a[r];

            if (sum > k) {
                while (sum > k) {
                    sum -= a[l];
                    l++;
                }
            }
            if (sum == k) {
                maxLen = Math.max(maxLen, r - l + 1);
            }
            r++;
        }
        return maxLen;
    }

    // 424. Longest Repeating Character Replacement
    // at most k times(the most important part

    public static int characterReplacement(String s, int k) {
        int freq[] = new int[26];
        int maxWindow = 0;
        int maxFreq = 0;
        int left = 0, right = 0;

        while (right < s.length()) {

            freq[s.charAt(right) - 'A']++;

            // very important to identify the lower frequency character to be deleted
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            int windowLength = right - left + 1;

            if (windowLength - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            // calculated again because if maxFreq-windowLength>k
            windowLength = right - left + 1;
            maxWindow = Math.max(maxWindow, windowLength);
            right++;
        }
        return maxWindow;
    }


    public static void printSubarray(int[] arr, int i, int j, int n) {
        if (i < 0 || i > j || j >= n) {
            return;
        }
        for (int index = i; index < j; index++) {
            System.out.print(arr[index] + ", ");
        }
        System.out.println(arr[j]);
    }

    public static void solve(int[] arr) {
        int n = arr.length;
        Map<Integer, Boolean> mp = new HashMap<>();
        for (int val : arr) {
            mp.put(val, false);
        }

        int r = 0, l = 0;
        while (r < n) {

            while (r < n && !mp.get(arr[r])) {
                mp.put(arr[r], true);
                r++;
            }
            printSubarray(arr, l, r - 1, n);
            while (r < n && mp.get(arr[r])) {
                mp.put(arr[l], false);
                l++;
            }
        }
    }

    // max consecutive ones three-- leetcode brute force
    // soln - trying to find max size subarray with at most k zeros
    public static int longestOnes(int[] nums, int k) {
        int zeros = 0, len = 0, maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            zeros = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0)
                    zeros++;
                if (zeros <= k) {
                    len = j - i + 1;
                    maxLen = Math.max(maxLen, len);
                } else {
                    break;
                }
            }
        }
        return maxLen;
    }

    public static int longestOnesOptimal(int[] nums, int k) {
        // 1,1,1,0,0,0,1,1,1,1,0] k=2
        int [] hash=new int[2];
        int i=0,j=0,maxCount=0,maxLength=Integer.MIN_VALUE;
        while(j<nums.length){
            hash[nums[j]]++;
           maxCount=Math.max(maxCount,hash[nums[j]]);
           int windowLength=j-i+1;
           if(windowLength-maxCount>k){
               hash[nums[i]]--;
               i++;
           }
           windowLength=j-i+1;
           maxLength=Math.max(maxLength,windowLength);
           j++;
        }
       // System.out.println(" maxLen " + maxLength);
        return maxLength;
    }


    // Tree Problems

    // level order traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int curLevel = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            ans.add(new ArrayList<>());
            for (int i = 0; i < len; i++) {
                TreeNode node = q.poll();
                ans.get(curLevel).add(node.val);
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            curLevel++;
        }
        return ans;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int curLevel = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            ans.add(new ArrayList<>());
            Stack<Integer> st = new Stack<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = q.poll();
                if (curLevel % 2 == 0) {
                    ans.get(curLevel).add(node.val);
                } else {
                    ans.get(curLevel).add(0, node.val);
                }
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            curLevel++;
        }
        return ans;
    }


    public int diameterOfBinaryTree(TreeNode root) {
        int[] maxi = new int[1];
        // As java is pass by value so to update maxi each time we are passing one size array to
        // pass it as reference
        height(root, maxi);
        return maxi[0];
    }

    private int height(TreeNode root, int[] maxi) {
        if (root == null)
            return 0;
        int lh = height(root.left, maxi);
        int rh = height(root.right, maxi);
        // the below line gives the answer because the maximum lh+rh is the diameter of the tree
        maxi[0] = Math.max(maxi[0], lh + rh);
        // but for parent nodes we need to return the highest left height and right height to calculate the diameter
        return 1 + Math.max(lh, rh);
    }


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // preorder and postorder helps to determine root
        //inorder can give left and right if root is known
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        int inOrderLength = inorder.length - 1;
        int preOrderLength = preorder.length - 1;
        return constructTree(preorder, inorder, map, 0, inOrderLength, 0, preOrderLength);

    }

    // Revise again

    private static TreeNode constructTree(int[] preorder, int[] inorder, HashMap<Integer, Integer> map,
                                          int inStart, int inEnd, int preStart, int preEnd) {
        if (inEnd < inStart || preEnd < preStart)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndexInOrder = map.get(root.val);
        int countOfElementsLeftOfRootInOrder = rootIndexInOrder - inStart;
        int leftinStart = inStart;
        int leftinEnd = map.get(root.val) - 1;
        int leftpreStart = preStart + 1;
        int leftpreEnd = preStart + countOfElementsLeftOfRootInOrder;
        root.left = constructTree(preorder, inorder, map, leftinStart, leftinEnd,
                leftpreStart, leftpreEnd);
        int rightinStart = rootIndexInOrder + 1;
        int rightinEnd = inEnd;
        int rightpreStart = preStart + countOfElementsLeftOfRootInOrder + 1;
        int rightpreEnd = preEnd;
        root.right = constructTree(preorder, inorder, map, rightinStart, rightinEnd, rightpreStart, rightpreEnd);
        return root;
    }


    public static TreeNode buildTreePostandIn(int[] postorder, int[] inorder) {
        // preorder and postorder helps to determine root
        //inorder can give left and right if root is known
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        int inOrderLength = inorder.length - 1;
        int postOrderLength = postorder.length - 1;
        return constructTreePostandIn(postorder, inorder, map, 0, inOrderLength, 0, postOrderLength);

    }

    // Revise again

    private static TreeNode constructTreePostandIn(int[] postorder, int[] inorder, HashMap<Integer, Integer> map,
                                                   int inStart, int inEnd, int postStart, int postEnd) {
        if (inEnd < inStart || postEnd < postStart)
            return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIndexInOrder = map.get(root.val);
        int countOfElementsLeftOfRootInOrder = rootIndexInOrder - inStart;
        int leftinStart = inStart;
        int leftinEnd = map.get(root.val) - 1;
        int leftpostStart = postStart;
        int leftpostEnd = postStart + countOfElementsLeftOfRootInOrder - 1;
        root.left = constructTreePostandIn(postorder, inorder, map, leftinStart, leftinEnd,
                leftpostStart, leftpostEnd);
        int rightinStart = rootIndexInOrder + 1;
        int rightinEnd = inEnd;
        int rightpostStart = postStart + countOfElementsLeftOfRootInOrder;
        int rightpostEnd = postEnd - 1;
        root.right = constructTreePostandIn(postorder, inorder, map, rightinStart, rightinEnd, rightpostStart, rightpostEnd);
        return root;
    }


    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode node = q.poll();
                size--;
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
                if (size == 0)
                    ans.add(node.val);
            }
        }
        return ans;
    }


    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            // while(size>0){
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == 0)
                    ans.add(node.val);
                //size--;
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
                /*if(size==0)
                    ans.add(node.val); */
            }
        }
        return ans;
    }


    public static ArrayList<Integer> topView(TreeNode root) {
        // code here
        Map<Integer, Integer> map = new TreeMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<CustomNode> q = new LinkedList<>();
        q.offer(new CustomNode(root, 0));
        while (!q.isEmpty()) {
            CustomNode customNode = q.poll();
            TreeNode node = customNode.node;
            int col = customNode.col;
            if (!map.containsKey(col)) {
                map.put(col, node.val);
            }
            if (node.left != null) {
                q.offer(new CustomNode(node.left, col - 1));
            }
            if (node.right != null) {
                q.offer(new CustomNode(node.right, col + 1));
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }

    public ArrayList<Integer> bottomView(TreeNode root) {
        // Code here
        Map<Integer, Integer> map = new TreeMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<CustomNode> q = new LinkedList<>();
        q.offer(new CustomNode(root, 0));
        while (!q.isEmpty()) {
            CustomNode customNode = q.poll();
            TreeNode node = customNode.node;
            int col = customNode.col;
            //if(!map.containsKey(col)){
            map.put(col, node.val);
            // }
            if (node.left != null) {
                q.offer(new CustomNode(node.left, col - 1));
            }
            if (node.right != null) {
                q.offer(new CustomNode(node.right, col + 1));
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        // traverse col wise and for each column traverse levelwise/rowwise

        dfs(root, 0, 0);
        for (Map.Entry<Integer, TreeMap<Integer, ArrayList<Integer>>> entry : map.entrySet()) {
            TreeMap<Integer, ArrayList<Integer>> levelMap = entry.getValue();
            List<Integer> mergeEachLevelList = new ArrayList<>();
            for (Map.Entry<Integer, ArrayList<Integer>> levelEntry : levelMap.entrySet()) {
                ArrayList<Integer> eachLevelList = levelEntry.getValue();
                // Collections.sort(eachLevelList);
                mergeEachLevelList.addAll(eachLevelList);
            }
            ans.add(mergeEachLevelList);
        }
        return ans;
    }

    private void dfs(TreeNode root, int col, int level) {
        if (root == null)
            return;
        if (!map.containsKey(col)) {
            map.put(col, new TreeMap<>());
        }
        if (!map.get(col).containsKey(level)) {
            map.get(col).put(level, new ArrayList<>());
        }
        map.get(col).get(level).add(root.val);
        dfs(root.left, col - 1, level + 1);
        dfs(root.right, col + 1, level + 1);
    }

    public ArrayList<Integer> boundaryTraversal(TreeNode node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isLeaf(node)) {
            res.add(node.val);
        }
        leftBoundary(node, res);
        findLeafNodes(node, res);
        rightBoundary(node, res);

        return res;
    }

    private void findLeafNodes(TreeNode node, List<Integer> res) {
        if (node == null)
            return;
        if (isLeaf(node))
            res.add(node.val);
        findLeafNodes(node.left, res);
        findLeafNodes(node.right, res);
    }

    private boolean isLeaf(TreeNode node) {
        return (node.left == null && node.right == null);
    }

    private void leftBoundary(TreeNode node, List<Integer> res) {
        TreeNode leftNode = node.left;
        while (leftNode != null) {
            if (isLeaf(leftNode))
                break;
            res.add(leftNode.val);
            if (leftNode.left != null) {
                leftNode = node.left;
            } else
                leftNode = node.right;
        }
    }

    private void rightBoundary(TreeNode node, List<Integer> res) {
        TreeNode rightNode = node.right;
        Stack<Integer> stack = new Stack<>();
        while (rightNode != null) {
            if (isLeaf(rightNode))
                break;
            stack.push(rightNode.val);
            if (rightNode.right != null) {
                rightNode = node.right;
            } else
                rightNode = node.left;
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
    }


    public int amountOfTime(TreeNode root, int start) {
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        TreeNode res = createParentMapAndFindStartNode(root, map, start);
        int maxi = findMaxDistance(res, map);
        return maxi;
    }

    private int findMaxDistance(TreeNode res, HashMap<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> q = new LinkedList<>();
        HashMap<TreeNode, Integer> visited = new HashMap<>();
        q.offer(res);
        int time = 0;
        visited.put(res, 1);
        while (!q.isEmpty()) {
            int size = q.size();
            boolean flagForCheckingBurnInALevel = false;
            for (int i = 0; i < size; i++) {
                TreeNode start = q.poll();
                if (start.left != null && visited.get(start.left) == null) {
                    flagForCheckingBurnInALevel = true;
                    visited.put(start.left, 1);
                    q.offer(start.left);
                }
                if (start.right != null && visited.get(start.right) == null) {
                    flagForCheckingBurnInALevel = true;
                    visited.put(start.right, 1);
                    q.offer(start.right);
                }
                if (parentMap.containsKey(start) && visited.get(parentMap.get(start)) == null) {
                    flagForCheckingBurnInALevel = true;
                    visited.put(parentMap.get(start), 1);
                    q.offer(parentMap.get(start));
                }
            }
            if (flagForCheckingBurnInALevel == true) time++;
        }
        return time;
    }

    private TreeNode createParentMapAndFindStartNode(TreeNode root, HashMap<TreeNode, TreeNode> map, int start) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode res = new TreeNode(-1);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.val == start) res = node;
            if (node.left != null) {
                map.put(node.left, node);
                q.offer(node.left);
            }
            if (node.right != null) {
                map.put(node.right, node);
                q.offer(node.right);
            }
        }
        return res;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode leftChild = lowestCommonAncestor(root.left, p, q);
        TreeNode rightChild = lowestCommonAncestor(root.right, p, q);
        if (leftChild == null) {
            return rightChild;
        } else if (rightChild == null) {
            return leftChild;
        } else {
            return root;
        }
    }


    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (root == null || target == null) return new ArrayList<>();
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        findTargetNodeAndCreateParentMap(root, parentMap, target);
        //  Set<TreeNode> visited=new HashSet<>();
        HashMap<TreeNode, Boolean> visited = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);
        int count = 0;
        List<Integer> list = new LinkedList<>();
        visited.put(target, true);
        while (!q.isEmpty()) {
            int size = q.size();
            if (count == k) {
                break;
            }
            count++;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                //  if(node.left!=null && visited.add(node.left)){
                if (node.left != null && visited.get(node.left) == null) {
                    visited.put(node.left, true);
                    q.offer(node.left);
                }
                if (node.right != null && visited.get(node.right) == null) {
                    visited.put(node.right, true);
                    q.offer(node.right);
                }
                TreeNode parent = parentMap.get(node);
                // if (parent != null && visited.add(parent)) {
                if (parent != null && visited.get(parent) == null) {
                    visited.put(parent, true);
                    q.offer(parent);
                }
            }
        }

    List<Integer> result = new ArrayList<>();
    for (TreeNode node : q) {
        result.add(node.val);
    }

        return result;

}

//    private List<Integer> findNodesAtDistanceK(TreeNode res, HashMap<TreeNode, TreeNode> parentMap,int k) {
//        HashMap<TreeNode,Integer> visited=new HashMap<>();
//        Queue<TreeNode> q=new LinkedList<>();
//        q.offer(res);
//        int count=0;
//        List<Integer> list=new LinkedList<>();
//        visited.put(res,1);
//        while(!q.isEmpty()){
//            int size=q.size();
//            if(count==k){
//                break;
//            }
//            count++;
//            for(int i=0;i<size;i++){
//                TreeNode node=q.poll();
//                if(node.left!=null && visited.get(node.left)==null){
//                    visited.put(node.left,1);
//                    q.offer(node.left);
//                }
//                if(node.right!=null && visited.get(node.right)==null){
//                    visited.put(node.right,1);
//                    q.offer(node.right);
//                }
//                if(parentMap.containsKey(res) && visited.get(parentMap.get(res))==null){
//                    visited.put(parentMap.get(res),1);
//                    q.offer(parentMap.get(res));
//                }
//            }
//        }
//        for(int i=0;i<q.size();i++)
//            list.add(q.poll().val);
//        return list;
//    }

    private void findTargetNodeAndCreateParentMap(TreeNode root,HashMap<TreeNode,TreeNode> parentMap, TreeNode target) {
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode node=q.poll();
            if(node.left!=null){
                parentMap.put(node.left,node);
                q.offer(node.left);
            }if(node.right!=null){
                parentMap.put(node.right,node);
                q.offer(node.right);
            }
        }
    }

    static TreeNode prev;
    static TreeNode head;
    public static TreeNode BTtoDLL(TreeNode root) {
        // Write your code here
        //head = null;
       // prev = null;
        findHead(root);

        return head;
    }

    public static void findHead(TreeNode root) {

        // Base case.
        if (root == null) {
            return;
        }

        findHead(root.left);

        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }

        prev = root;

        findHead(root.right);
    }


    /*
    Input: root = [1,2,3,null,null,4,5]
    Output: [1,2,3,null,null,4,5]
     */

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "";
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        StringBuilder sb=new StringBuilder();
        while(!q.isEmpty()){
            TreeNode node=q.poll();
            if(node==null){
                sb.append("n ");
                continue;
            }
            sb.append(node.val + " ");
            q.offer(node.left);
            q.offer(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) return null;
        Queue<TreeNode> q=new LinkedList<>();
        String[] values = data.split(" ");
        TreeNode root=new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for(int i=1;i<values.length;i++){
            TreeNode parent=q.poll();
            if(!values[i].equals("n")){
                TreeNode left=new TreeNode(Integer.parseInt(values[i]));
                parent.left=left;
                q.offer(left);
                //++i;
            }
            if(!values[++i].equals("n")){
                TreeNode right=new TreeNode(Integer.parseInt(values[i]));
                parent.right=right;
                q.offer(right);
               // i++;
            }
        }
        return root;
    }

    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        final Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            final TreeNode node=q.poll();
            final TreeNode temp=node.left;
            node.left=node.right;
            node.right=temp;
            if(node.left!=null) q.offer(node.left);
            if(node.right!=null) q.offer(node.right);
        }
        return root;
    }

    // correct program
    // commented TreeNode class does not have a next pointer
  /*  public Node connect(Node root) {
        if(root==null)  return null;
        Node prev=null;
        Queue<Node> q=new LinkedList<>();
        Node temp=root;
        q.offer(temp);
        while(!q.isEmpty()){
            int size=q.size();
            prev=null;
            for(int i=0;i<size;i++){
                Node node=q.poll();
                if(prev !=null){
                    prev.next=node;
                }
                prev=node;
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            }
        }
        return root;
    } */


    static List<Integer> findNextSmallerElement(List<Integer> arr) {
        int n = arr.size();

        // Stores the next smaller elements, initialized with -1
        List<Integer> nextSmaller = new ArrayList<>(Collections.nCopies(n, -1));


        // Monotonic stack to keep track of indices
        Stack<Integer> stk = new Stack<>();

        // Iterate through the array
        for (int i = 0; i < n; i++) {

            // Maintain a decreasing order in the stack
            while (!stk.isEmpty() && arr.get(i) < arr.get(stk.peek())) {
                nextSmaller.set(stk.pop(), arr.get(i)); // Assign the next smaller element
            }

            // Push the current index onto the stack
            stk.push(i);
        }

        return nextSmaller;
    }





    public static void main(String[] args) {
        reverseAString("src");
        reverseANumber(123);
        mergeSort(new int[]{5, 4, 3, 2}, 0, 3);
        System.out.println(checkPalindrome("MADSM", 0));
        int[] a = new int[]{4, 9, 1, 0};
        quickSort(a, 0, 3);
        System.out.println(Arrays.toString(a));
        int[] b = new int[]{1, 3, 2};
        nextPermutation(b);
        System.out.println(Arrays.toString(b));
        List<List<Integer>> ans = threeSumOptimal(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(ans);
        int[] nums = {3, 2, 2, 2, 3};
        List<Integer> ans1 = majorityElement(nums);
        System.out.println(ans1);
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        printMatrix(mat);

        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        //  System.out.println(myPow(3,3));
        System.out.println(maxSubArray(new int[]{-1, -2}));
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSum2Optimal(new int[]{1, 1, 2}, 2));
        System.out.println(myPow(2.00000, -2));
        System.out.println(longestConsecutiveOptimised(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(permute(new int[]{1, 2}));
        System.out.println(countAndSay(4));
        nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        mergeTwoSortedArrays(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        setZeroesBest(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
        jobSequencing(new int[]{4, 1, 1, 1}, new int[]{20, 10, 40, 30});
        System.out.println(letterCombinations("23"));
        System.out.println(mergeOverlappingIntervals(new int[][]{{1, 3}, {2, 6}, {8, 9}, {9, 11}, {8, 10}}));
        //System.out.println(Arrays.toString(merge1(new int[][]{{1, 3}, {2, 4}, {3, 5}, {6, 7}})));

        System.out.println(Arrays.toString(merge1(new int[][]{{1, 3}, {2, 6}, {8, 9}})));
       /* TreeMap<Integer,Integer> map=new TreeMap<>();
        map.put(10,20);
        map.put(5,7);
       // map.put(15,25);
        System.out.println(map.get(map.lowerKey(25))); */

        int[] arr = {5, 2, 3, 5, 4, 3};
        solve(arr);
        longestOnes(new int[]{1, 0, 0, 1}, 1);

        buildTree(new int[]{3,4,5},new int[]{4,3,5});

     /*   List<Integer> al2 = new ArrayList<>();
        al2.add(100);
//        al.add(200);
//        al.add(150);

        ListIterator<Integer> li = al2.listIterator(al2.size());
        while (li.hasPrevious()) {
            int p = li.previous();
            System.out.println(p + " " + li.nextIndex() + " " + li.previousIndex());
        }

        List<Integer> al1 = new ArrayList<>();
        al1.add(100);
//        al1.add(200);
//        al1.add(150);

        ListIterator<Integer> li1 = al1.listIterator();
        while (li1.hasNext()) {
            System.out.println(li1.nextIndex() + " " + li1.previousIndex());
            int p = li1.next();
            //System.out.println(li1.next() + " " + li1.nextIndex() + " " + li1.previousIndex());
            System.out.println(p + " " + li1.nextIndex() + " " + li1.previousIndex());
        } */


        /*ArrayList<String> al = new ArrayList<>();
        al.add("A");
        al.add("B");
        al.add("C");
        al.add("D");

        // Obtain a ListIterator
        ListIterator<String> i = al.listIterator();

        System.out.println("Iterating through the list:");
        while (i.hasNext()) {
            System.out.println(i.next());
        }


        Map<Integer, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);

        // adding entries to the map
        linkedHashMap.put(1, "One");
        linkedHashMap.put(2, "Two");
        linkedHashMap.put(3, "Three");

        // accessing entry 1
        linkedHashMap.get(2);


        // iterating over the map to demonstrate access order
        System.out.println("Entries in LinkedHashMap with access order:");
        for (Map.Entry<Integer, String> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        Map<Integer, String> hm = new HashMap<>();
        hm.put(1,"a");
        hm.put(1,"b");
        hm.put(2,"c");
        for(Map.Entry<Integer,String> entry:hm.entrySet()){
            System.out.println(entry.getKey() + " ----- " + entry.getValue());
        }

        Set<String> s=new TreeSet<>();
        s.add(null);
        s.add("a");
        s.forEach(System.out::println); */
//        int size=5;
//        List<Integer> nextSmaller = new ArrayList<>(Collections.nCopies(size, -1));
//        System.out.println("next Smaller " + nextSmaller);
        List<Integer> list=List.of(4, 8, 5, 2, 25);
        findNextSmallerElement(new ArrayList<Integer>(list));
        System.out.println(celebrity_Brute(new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 1}}));
        System.out.println(frequencySort("tree"));
        System.out.println(myAtoi("42"));
        System.out.println(longestOnesOptimal(new int[]{1,1,1,0,0,0,1,1,1,1,0},2));
    }
}
