package com.interview.bit.math.numbertheory;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortedPermutationRank {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println(findRank(in.next()));
    }

    public static int findRank(String a) {
        Long r=1l;
        int l = a.length();
        long f=factorial(l);
        for(int i=0;i<l;i++){
            f /= l - i;
            r += (findSmallerInRight(a, i, l - 1)  * f)%1000003;

        }
        r %= 1000003;
        return r.intValue();
    }

    public static long factorial(int n){
        long f=1;
        for(int i=1;i<=n;i++){
            f*=i;
        }
        return f;
    }

    public static int findSmallerInRight(String a, int low, int high){
        int countRight = 0;
        for(int i = low + 1; i <= high; i++){
            if(a.charAt(i) < a.charAt(low))
                countRight++;
        }
        return countRight;
    }


}

