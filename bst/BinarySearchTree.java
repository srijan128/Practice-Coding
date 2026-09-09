package bst;

import java.util.ArrayList;
import java.util.Stack;

public class BinarySearchTree {

     Node root;
     int size;

    class Node {
        public int value;
        public Node left;
        public Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public Node getRoot() {
        return root;
    }

    public boolean insert(int val){
        Node n = new Node(val);
        if(root == null){
            root=n;
            return true;
        }
        Node temp=root;
        while(true){
            if(temp.value == val) return false;
            if(temp.value>val){
                if(temp.left == null){
                    temp.left=n;
                    return true;
                }
                else
                    temp=temp.left;
            }
            else if(temp.value<val){
                if(temp.right == null){
                    temp.right=n;
                    return true;
                }
                else
                    temp=temp.right;

            }
        }

    }


    public boolean contains(int val){
        if(root == null) return false;
        Node temp =root;
        while(temp != null){
            if(temp.value>val)
                temp=temp.left;
            else if(temp.value<val)
                temp=temp.right;
            else
                return true;
        }
        return false;
    }

    public Node contains(Node root, int val){
        if(root == null || root.value==val)
                return root;
        Node ans = null;
        if(root.value>val)
            ans=contains(root.left,val);
        else
            ans=contains(root.right,val);
        return ans;
    }

    ArrayList<Integer> boundaryTraversal(Node node) {
        // code here
        ArrayList<Integer> res=new ArrayList<>();
        if(!isLeaf(node))
            res.add(node.value);
       leftBoundary(node,res);
       insertLeafNode(node,res);
       rightBoundary(node,res);
       return res;
    }

    public void leftBoundary(Node root,ArrayList<Integer> res){
        Node leftNode=root.left;
        while(leftNode!=null){
            if(isLeaf(leftNode))
                break;
            res.add(leftNode.value);
            if(leftNode.left!=null)
                leftNode=leftNode.left;
            else
                leftNode=leftNode.right;
        }
    }

    public void rightBoundary(Node root,ArrayList<Integer> res){
        Node rightNode=root.right;
        Stack<Integer> s=new Stack<>();
        while(rightNode!=null){
            if(isLeaf(rightNode))
                break;
            s.push(rightNode.value);
            if(rightNode.right!=null)
                rightNode=rightNode.right;
            else
                rightNode=rightNode.left;
        }
        while(!s.isEmpty()){
            res.add(s.pop());
        }
    }

    public void insertLeafNode(Node node,ArrayList<Integer> res){
        if(node==null)
            return;
        if(isLeaf(node))
            res.add(node.value);
        insertLeafNode(node.left,res);
        insertLeafNode(node.right,res);
    }

    private boolean isLeaf(Node node) {
        return (node.left == null && node.right == null);

    }

     int largestBst(Node root)
    {
        // Write your code here
        size=0;
        dfs(root);
        return size;


    }

    //min,max,size
     int [] dfs(Node root){
        if(root==null)
            return new int []{Integer.MAX_VALUE,Integer.MIN_VALUE,0};
        int [] left=dfs(root.left);
        int [] right=dfs(root.right);
        if(root.value>left[1] && root.value<right[0]){
            int curSize=left[2]+right[2]+1;
            size=Math.max(curSize,size);
            return new int [] {Math.min(root.value,left[0]),Math.max(root.value,right[1])
                    ,curSize};
        }
        return new int []{Integer.MIN_VALUE,Integer.MAX_VALUE,Math.max(left[2],right[2])};
    }

}
