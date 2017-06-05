package com.interviewcake;


import java.io.*;
import java.util.*;

/**
 You want to be able to access the largest element in a stack.
 Use the built-in Stack class to implement a new class MaxStack with a function getMax() that returns the largest element in the stack. getMax() should not remove the item.

 Your stacks will contain only integers.


 Complexity
 O(1) time for push(), pop(), and getMax(). O(m) additional space, where m is the number of operations
 performed on the stack.

 Notice that our time-efficient approach takes some additional space, while a lazy approach (simply walking through
 the stack to find the max integer whenever getMax() is called) took no additional space. We've traded some space
 efficiency for time efficiency.

 */




public class MaxStack extends Stack {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + MaxStack.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    private Stack<Integer> maxList;
    Integer max;

    public MaxStack(){
        super();
        maxList = new Stack<>();
        max=0;
    }

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        String cmd;
        MaxStack ms=new MaxStack();
        while(in.hasNext()){
            cmd=in.next();
            if(cmd.equals("PUSH")){
                int item = in.nextInt();
                ms.push(item);
                System.out.println("PUSH: " + item);
                System.out.println("MAX: "+ ms.getMax());
            }
            else if(cmd.equals("POP")){
                System.out.println("POP: "+ ms.pop());
                System.out.println("MAX: "+ ms.getMax());
            }
        }

    }

    @Override
    public Object push(Object item){
        if(maxList.isEmpty()){
            maxList.push((int)item);
        }
        else if((int)item>maxList.peek()){
            maxList.push((int)item);
        }
        return super.push(item);
    }

    @Override
    public Object pop(){
        if (isEmpty()){
           return null;
        }
        Object item = this.peek();
        if((int)item==maxList.peek()){
            maxList.pop();
        }
        super.pop();
        return item;
    }

    public Integer getMax(){
        return maxList.isEmpty()?null:maxList.peek();
    }
}

