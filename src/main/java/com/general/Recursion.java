package com.general;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Recursion {
    public static void main(String[] args) {
        //System.out.println(sum(6));
        //System.out.println(combine(6));
        //System.out.println(factorial(6));
        //System.out.println(fibonacci(6));
        //traverseString("1234").stream().forEach(System.out::println);

        int len = 3;
        String input = "1234567";
        permuteWithRep(input,len).stream().forEach(System.out::println);



//        Set<String> set = subsets(input,len);
//        HashMap<String,Set<String>> map = new HashMap<>();
//        for(String s:set){
//            Set<String> pset = permuteWithRep(s,len);
//            System.out.println("\n........................"+s+".......................");
//            pset.stream().forEach(i -> System.out.print(i + "; "));
//
//            map.put(s,pset);
//        }
    }


    /**
     * This method dont use recursion and very efficient
     * Permutation with character repetition and length k, produce all possible sets with or without repetition of items
     * @param input string
     * @param k len of combination
     * @return set of unique combinations
     */
    private static Set<String> permuteWithRep(String input, int k) {
        int n = input.length();

        int[] indexes = new int[k];
        int total = (int) Math.pow(n, k);

        char[] snapshot = new char[k];
        StringBuilder sb = new StringBuilder();
        Set<String> set = new TreeSet<>();
        while (total-- > 0) {
            for (int i = 0; i < k; i++){
                snapshot[i] = input.charAt(indexes[i]);
            }

            sb.setLength(0);
            for(int i = 0; i < snapshot.length; i ++){
                sb.append(snapshot[i]);
            }
            set.add(sb.toString());

            for (int i = 0; i < k; i++) {
                if (indexes[i] >= n - 1) {
                    indexes[i] = 0;
                } else {
                    indexes[i]++;
                    break;
                }
            }
        }
        return set;
    }


    /**
     * This method used recursion
     * Permutation with out repetition of same length of input string but with out repetition of characters
     * e.g. for "367" it will generate 367; 376; 637; 673; 736; 763; but it will not generate combinations of 333,337,666
     * @param chars
     * @return
     */
    private static Set<String> permuteWithOutRep(String chars)
    {
        // Use sets to eliminate semantic duplicates (aab is still aab even if you switch the two 'a's)
        // Switch to HashSet for better performance
        Set<String> set = new TreeSet<String>();

        // Termination condition: only 1 permutation for a string of length 1
        if (chars.length() == 1)
        {
            set.add(chars);
        }
        else
        {
            // Give each character a chance to be the first in the permuted string
            for (int i=0; i<chars.length(); i++)
            {
                // Remove the character at index i from the string
                String pre = chars.substring(0, i);
                String post = chars.substring(i+1);
                String remaining = pre+post;

                // Recurse to find all the permutations of the remaining chars
                for (String permutation : permuteWithOutRep(remaining))
                {
                    // Concatenate the first character with the permutations of the remaining chars
                    set.add(chars.charAt(i) + permutation);
                }
            }
        }
        return set;
    }


    private static Set<String> subsets(String ss,int len){
        return subsets(ss,0,len);
    }

    private static Set<String> subsets(String ss){
        return subsets(ss,0,3);
    }

    private static Set<String> subsets(String ss,int idx,int len){
        Set<String> s = new TreeSet<>();
        //Combine current index character + each rest of the characters
        for(int i=idx;i<=ss.length()-len;i++){
            if(i + len-1<=ss.length()){
                s.add(ss.charAt(idx) +  ss.substring(i+1, i + len));
            }
        }

        if(idx==ss.length() - len) {
            return s;
        }
        s.addAll(subsets(ss, ++idx, len));
        return s;
    }

    private static Set<String> traverseString(String ss) {
        return traverseString(ss, 0);
    }

    private static Set<String> traverseString(String ss,int idx){
        Set<String> s = new TreeSet<>();
        s.add(ss.substring(idx, idx + 1));
        if(idx==ss.length()-1){
            return s;
        }
        s.addAll(traverseString(ss,++idx));
        return s;
    }

    private static int sum(int n){
        if(n==1)
            return 1;
        // [6 + sum(5)], [5 + sum(4)], [4 + sum(3)], [3 + sum(2)], [2 + sum(1)]
        // [6 + sum(5)], [5 + sum(4)], [4 + sum(3)], [3 + sum(2)], [2 + 1]
        // [6 + sum(5)], [5 + sum(4)], [4 + sum(3)], [3 + 3]
        // [6 + sum(5)], [5 + sum(4)], [4 + 6]
        // [6 + sum(5)], [5 + 10]
        // [6 + 15]
        // 21
        return n + sum(n-1);
    }

    private static String combine(int n){
        if(n==1)
            return "1";
        //[combine(5) + 6], [combine(4) + 5], [combine(3) + 4], [combine(2) + 3], [combine(1) + 2]
        //[combine(5) + 6], [combine(4) + 5], [combine(3) + 4], [combine(2) + 3], [1 + 2]
        //[combine(5) + 6], [combine(4) + 5], [combine(3) + 4], [1 + 2 + 3]
        //[combine(5) + 6], [combine(4) + 5], [1 + 2 + 3 + 4]
        //[combine(5) + 6], [1 + 2 + 3 + 4 + 5]
        //[1 + 2 + 3 + 4 + 5 + 6]
        return  combine(n-1)+String.valueOf(n);
    }

    private static int factorial(int n){
        if(n==1)
            return 1;
        // [6 * factorial(5)], [5 * factorial(4)], [4 * factorial(3)], [3 * factorial(2)], [2 * factorial(1)]
        // [6 * factorial(5)], [5 * factorial(4)], [4 * factorial(3)], [3 * factorial(2)], [2 * 1]
        // [6 * factorial(5)], [5 * factorial(4)], [4 * factorial(3)], [3 * 2]
        // [6 * factorial(5)], [5 * factorial(4)], [4 * 6]
        // [6 * factorial(5)], [5 * 24]
        // [6 * 120]
        // 720
        return  n*factorial(n - 1);
    }

    /**
     * https://www.youtube.com/watch?v=dsmBRUCzS7k
     * @param n
     * @return
     */
    private static int fibonacci(int n){
        if(n==0){
            return 0;
        }
        else if(n==1){
            return 1;
        }
        else{
            // See Explanation https://www.youtube.com/watch?v=dsmBRUCzS7k
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }
}

