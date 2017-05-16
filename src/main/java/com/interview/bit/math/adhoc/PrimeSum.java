package com.interview.bit.math.adhoc;


import java.util.*;

public class PrimeSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(primesum(n).toString());
    }

    private static ArrayList<Integer> primesum(int a) {
        ArrayList<Integer> result = new ArrayList<>();
        int n;
        for(int m=1; m<=a/2;m++){
            if(isPrimeNumber(m)){
                if(m*m==a){
                    result.add(m);
                    result.add(m);
                    break;
                }
                else{
                    n = a-m;
                    if(isPrimeNumber(n) && m+n==a){
                        result.add(m);
                        result.add(n);
                        break;
                    }
                }
            }
        }
        return result;
    }


    private static ArrayList<Integer> listOfPrimeNumbers(int n){
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        for(int i=1; i <=n;i++){
            if(isPrimeNumber(i)){
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }



    private static boolean isPrimeNumber(int a) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {
                factors.add(i);
                if (a / i != Math.sqrt(a)) {
                    factors.add(a / i);
                }
                if (factors.size() > 2)
                    break;
            }
        }
        return factors.size() == 2;
    }
}

