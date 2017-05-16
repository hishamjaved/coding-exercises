package com.interview.bit;

import java.util.*;

public class Factorization {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        listOfFactors(n);
        //listOfFactors(n).forEach(System.out::println);
    }

    private static ArrayList<Integer> listOfFactors(int n){
        ArrayList<Integer> factors = new ArrayList<>();
        for(int i=1;i<=Math.sqrt(n);i++){
            System.out.println("................................................................");
            System.out.println("i: "+i);
            if(n%i==0){
                System.out.println("Add i, n%i==0: "+n+"%"+i+"==0 >> "+n%i);
                factors.add(i);
                System.out.println("i!=Math.sqrt(n): " + i + "!=" + Math.sqrt(n) + " >> " + (i!=Math.sqrt(n)));
                if(i!=Math.sqrt(n)){
                    System.out.println("Add n/i, "+n+"/"+i+" >> "+n/i);
                    factors.add(n/i);
                }
                System.out.println(factors.toString());
            }
        }
        //factors.sort(Integer::compareTo);
        //Collections.sort(factors);

        factors.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
                //return o1==o2?0:o1>o2?1:-1;
            }
        });

        System.out.println(factors.toString());
        return factors;
    }
}

