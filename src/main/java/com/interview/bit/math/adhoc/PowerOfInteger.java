package com.interview.bit.math.adhoc;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PowerOfInteger {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        System.out.println(isPower(a));
    }

    public static boolean isPower(int a) {
        if(a==1)
            return true;
        int q;
        int k=0;
        int p=0;
        boolean result=false;
        for(int i=2;i<=Math.sqrt(a);i++){
            if(a%i==0){
                p=1;
                q=a;
                while(q>i || q==0){
                    q = (int)Math.floor(q/i);
                    p++;
                }

                if(q==i && a==(int)Math.pow(i,p)){
                    k=i;
                    result=true;
                    break;
                }
            }
        }
        System.out.println(a+" = "+k+"^"+p);
        return result;
    }
}

