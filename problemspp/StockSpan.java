package problemspp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class StockSpanner {

    List<Integer> list;
    Stack<Integer> stack;

    public StockSpanner() {
        list=new ArrayList<>();
        stack=new Stack<>();
    }

    public int next(int price) {
        list.add(price);

        while(!stack.isEmpty() && list.get(list.size()-1)>=list.get(stack.peek()))
            stack.pop();
        int curIndex=list.size()-1;
        int lastGreaterIndex=(stack.isEmpty())?-1:stack.peek();
        int ans=curIndex-lastGreaterIndex;
        stack.push(curIndex);
        return ans;
    }
}
