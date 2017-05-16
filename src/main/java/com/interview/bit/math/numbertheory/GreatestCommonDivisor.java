package com.interview.bit.math.numbertheory;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GreatestCommonDivisor {
    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewbit"
            + File.separator + GreatestCommonDivisor.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=1;i<=n;i++){
            a.add(in.nextInt());
        }

        System.out.println(solve(a));
    }

    public static int solve(ArrayList<Integer> A) {
        int g =0;
        for(int i=0;i<A.size()-1;i++){
            g = gcd(A.get(i),A.get(i+1));
        }
        return g;
    }

    public static int gcd(int a,int b) {

        int r;
        int q;
        if(b>a){
            q=a;
            a=b;
            b=q;
        }

        if(a!=0 && b!=0){
            q = a/b;
            r = a%b;
            a = b;
            b = r;
            while(q>0 && b>0){
                q = a/b;
                r = a%b;
                a = b;
                b = r;
            }
            return a;
        }
        else {
            return a;
        }
    }
}

