package com.google.codejam2016;

import java.io.*;
import java.util.Scanner;

/**
 * See Problem Description here
 * https://code.google.com/codejam/contest/6254486/dashboard#s=p2
 * Created by HISHAM on 4/4/2017.
 */
public class JamcoinSmall {
    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "input" + File.separator + Jamcoin.class.getSimpleName() + File.separator;

    private static final String input = "C-small-practice.in";
    private static final String output = "C-small-practice.out";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(dirPath+input))));
        PrintStream printStream = new PrintStream(new FileOutputStream(dirPath+output));
        System.setOut(printStream);

        int count = in.nextInt();
        for(int i=1;i<=count;i++){
            System.out.printf("Case #%d:%n", i);
            producer(Integer.parseInt(in.next()), Integer.parseInt(in.next()));
        }
    }

    private static void producer(int n, int j){
        StringBuilder bInitNum = new StringBuilder("1");
        while(bInitNum.length()<n-1){
            bInitNum.append("0");
        }
        bInitNum.append("1");
        Long dInitNum = Long.parseLong(bInitNum.toString(), 2);

        StringBuilder next = new StringBuilder(Long.toBinaryString(dInitNum));
        int validCoins = 0;
        while(next.length()<n+1){
            if(next.charAt(0)=='1' && next.charAt(next.length()-1)=='1'){
                if(!isPrime(dInitNum)){
                    long[] divisors = new long[9];
                    boolean isValidBaseNumber = true;
                    for(int i=2;i<=10;i++){
                        divisors[i-2]=Long.parseLong(next.toString(), i);
                        if(isPrime(divisors[i-2])){
                            isValidBaseNumber=false;
                            break;
                        }
                        divisors[i-2] = getLowestDivisor(divisors[i-2]);
                        if(divisors[i-2]==0){
                            isValidBaseNumber=false;
                            break;
                        }
                    }
                    if(isValidBaseNumber){
                        System.out.print(next + " ");
                        for(int i=0;i<divisors.length;i++){
                            if(i==8)
                                System.out.print(divisors[i]);
                            else
                                System.out.print(divisors[i]+" ");
                        }
                        System.out.println("");
                        validCoins++;
                        if(validCoins==j){
                            break;
                        }
                    }
                }
            }
            dInitNum++;
            next = new StringBuilder(Long.toBinaryString(dInitNum));
        }
    }

    private static long getLowestDivisor(long num){
        long lowestDivisor=0;
        for(int i=2;i<num;i++){
            if(num%i==0){
                lowestDivisor = i;
                break;
            }
        }
        return lowestDivisor;
    }

    private static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }
}
