package com.google.codejam2016;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * See Problem Description here
 * https://code.google.com/codejam/contest/6254486/dashboard#s=p2
 * Created by HISHAM on 4/4/2017.
 */
public class Jamcoin {
    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "input" + File.separator + Jamcoin.class.getSimpleName() + File.separator;

    private static final String input = "C-large-practice.in";
    private static final String output = "C-large-practice.out";

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
                if(!isPrime(new BigInteger(dInitNum.toString()))){
                    BigInteger[] divisors = new BigInteger[9];
                    boolean isValidBaseNumber = true;
                    for(int i=2;i<=10;i++){
                        divisors[i-2] = convertBinaryToOtherBase(next.toString(), Integer.toString(i));
                        if(isPrime(divisors[i-2])){
                            isValidBaseNumber=false;
                            break;
                        }
                        divisors[i-2] = getLowestDivisor(divisors[i-2]);
                        if(divisors[i-2]==null){
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

    private static BigInteger convertBinaryToOtherBase(String binary, String radix ) {
        int count = binary.length()-1;
        int p = 0;
        BigInteger result = new BigInteger("0");
        while(count >= 0) {
            if(binary.charAt(count)=='1'){
                if(p==0){
                    result = result.add(new BigInteger("1"));
                }
                else{
                    BigInteger power = new BigInteger(radix);
                    for(int k=0;k<p-1;k++){
                        power = power.multiply(new BigInteger(radix));
                    }
                    result = result.add(power);
                }
            }
            p++;
            count--;
        }
        return result;
    }

    private static BigInteger getLowestDivisor(BigInteger num){
        BigInteger lowestDivisor=null;
        BigInteger sqrtN = new BigInteger("1000");
        for (BigInteger i = new BigInteger("2"); i.compareTo(sqrtN) < 1; i = i.add(BigInteger.ONE)) {
            if (BigInteger.ZERO.equals(num.mod(i)) ){
                lowestDivisor = i;
                break;
            }
        }
        return lowestDivisor;
    }

    private static boolean isPrime(BigInteger number) {
        if (!number.isProbablePrime(5))
            return false;

        BigInteger two = new BigInteger("2");
        if (!two.equals(number) && BigInteger.ZERO.equals(number.mod(two)))
            return false;
        //Just to skip this big number and fetch next
        return true;
    }


    public static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for(;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }
}
