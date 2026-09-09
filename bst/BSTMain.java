package bst;

import java.util.*;

public class BSTMain {

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
        connectSticks(new int[]{2,4,3});
        String [] s="/a//b////c/d//././/..".split("/");
        for(String i : s) {
            if(Objects.equals(i, ""))
            System.out.println("-");
            else
                System.out.println(i);
        }

      /* BinarySearchTree myBST = new BinarySearchTree();


        myBST.insert(2);
        myBST.insert(1);
        myBST.insert(3); */

        /*
            THE LINES ABOVE CREATE THIS TREE:
                         2
                        / \
                       1   3
        */



        // ROOT MUST BE PUBLIC FOR THESE LINES TO WORK
     /*   System.out.println("Root: " + myBST.root.value);
        System.out.println("\nRoot->Left: " + myBST.root.left.value);
        System.out.println("\nRoot->Right: " + myBST.root.right.value);
        System.out.println("\nTree contains " + myBST.contains(myBST.root, 3).value);
        */

        /*
            EXPECTED OUTPUT:
            ----------------
            Root: 2

            Root->Left: 1

            Root->Right: 3

        */

    }

    public static int connectSticks(int[] sticks) {
        int cost = 0;
        // ToDo: Write Your Code Here.
        PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        for(int i: sticks){
            pq.offer(i);
        }
        int sum=0, tSum=0;
        while(!pq.isEmpty()){
            sum= pq.poll() + pq.poll();
            tSum+=sum;
            if(!pq.isEmpty()){
                pq.offer(sum);
            }
            sum=0;
        }
        System.out.println(tSum);
        return tSum;
    }


    public static String simplifyPath(String path) {
        // Create a stack to store the simplified path components
        Stack<String> stack = new Stack<>();

        // Split the input path string using '/' as a delimiter
        for (String p : path.split("/")) {
            if (p.equals("..")) {
                // If the component is '..', pop the last component from the stack
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!p.isEmpty() && !p.equals(".")) {
                // If the component is not empty and not '.', push it onto the stack
                stack.push(p);
            }
        }

        // Create a StringBuilder to build the simplified path
        StringBuilder result = new StringBuilder();



        // Reconstruct the simplified path by popping components from the stack
        for (String dir : stack) {
            // Insert '/' before each component to ensure correct path format
            result.append("/");
            result.append(dir);
        }

        // If the result is empty, return '/', otherwise return the simplified path
        return result.length() == 0 ? "/" : result.toString();
    }

}

