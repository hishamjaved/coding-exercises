package com.interviewcake;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 Write a function fib() that a takes an integer n and returns the nth fibonacci number.
 */

public class Fibonnaci {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + Fibonnaci.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        long s = System.currentTimeMillis();
        System.out.println(fib(n));
        System.out.println("O(n) Time:"+ (System.currentTimeMillis()-s));
        s = System.currentTimeMillis();
        System.out.println(fibMemRecursive(n));
        System.out.println("Half Recursive approach using memoize total Time:"+ (System.currentTimeMillis()-s));
        s = System.currentTimeMillis();
        System.out.println(fibRecursive(n));
        System.out.println("Full Recursive approach Time:"+ (System.currentTimeMillis()-s));
    }


    public static int fib(int n) {
        int prevPrev = 0;
        int prev = 1;
        int current=0;
        System.out.print(prevPrev+" "+prev);
        for(int i=1;i<n;i++){
            current = prev + prevPrev;
            if(i<n-1)
                System.out.print(" "+current);
            prevPrev = prev;
            prev = current;
        }
        System.out.println("");
        return current;
    }

    public static int fibRecursive(int n) {
        if(n<0){
            throw new IllegalArgumentException("N can't be less than 0");
        }
        else if(n==0 || n==1){
            return n;
        }
        return fibRecursive(n-1)+fibRecursive(n-2);
    }


    private  static HashMap<Integer,Integer> memo = new HashMap<>();
    public static int fibMemRecursive(int n) {
        if(n<0){
            throw new IllegalArgumentException("N can't be less than 0");
        }
        else if(n==0 || n==1){
            // base case: 0 or 1
            return n;
        }

        // see if we've already calculated this
        if(memo.containsKey(n)){
            return memo.get(n);
        }

        int result = fibRecursive(n-1)+fibRecursive(n-2);
        // memoize
        memo.put(n,result);
        return result;
    }


}

