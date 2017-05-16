package com.crossover.trial;

/**
 * Created by HISHAM on 4/2/2017.
 */
public class PrimeNumber {
    public static void main(String[] args){


        int n = 11;
        System.out.println(n+" is prime number? "+ isPrime1(n));
        System.out.println(n+" is prime number? "+ isPrime2(n));
        System.out.println("..............................................................");

        n = 4;
        System.out.println(n+" is prime number? "+ isPrime1(n));
        System.out.println(n+" is prime number? "+ isPrime2(n));
        System.out.println("..............................................................");

        n = 9;
        System.out.println(n+" is prime number? "+ isPrime1(n));
        System.out.println(n+" is prime number? "+ isPrime2(n));
        System.out.println("..............................................................");

        n = 16;
        System.out.println(n+" is prime number? "+ isPrime1(n));
        System.out.println(n+" is prime number? "+ isPrime2(n));
        System.out.println("..............................................................");

        n = 25;
        System.out.println(n+" is prime number? "+ isPrime1(n));
        System.out.println(n+" is prime number? "+ isPrime2(n));
        System.out.println("..............................................................");

        n = 36;
        System.out.println(n+" is prime number? "+ isPrime1(n));
        System.out.println(n+" is prime number? "+ isPrime2(n));
        System.out.println("..............................................................");

        n = 1000033;
        System.out.println(n+" is prime number? "+ isPrime1(n));
        System.out.println(n+" is prime number? "+ isPrime2(n));
        System.out.println("..............................................................");

//        n = 1500450271;
//        System.out.println(n+" is prime number? "+ isPrime1(n));
//        System.out.println(n+" is prime number? "+ isPrime2(n));
//        System.out.println("..............................................................");
    }

    public static boolean isPrime1(int n){
        int i=2;
        while(i<n){
            if(n%i==0){
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean isPrime2(int n){
        int i=2;
        while(i < Math.sqrt(n)){
            if(n%i==0){
                return false;
            }
            i++;
        }
        return true;
    }
}
