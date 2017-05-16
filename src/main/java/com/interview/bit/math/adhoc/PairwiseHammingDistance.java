package com.interview.bit.math.adhoc;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PairwiseHammingDistance {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewbit"
            + File.separator + PairwiseHammingDistance.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Large1.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=1;i<=n;i++){
            a.add(in.nextInt());
        }
        System.out.println(hammingDistance(a));
        //System.out.println(hammingDistance2(a));
    }


//    /**
//     * Website Solution not working properly
//     */
//    private static int hammingDistance2(final List<Integer> A) {
//        int inputSize = A.size();
//        int mod = 1000000007;
//        int sum = 0;
//        for (int bitPosition = 0; bitPosition < 31; bitPosition++) {
//            int cntBitOne = 0, cntBitZero = 0;
//            for(int i = 0; i < inputSize; i++) {
//                if (A.get(i)==0 & (1 << bitPosition)==0) cntBitOne++;
//                else cntBitZero++;
//            }
//            sum = sum + ((2 * cntBitOne * cntBitZero) % mod);
//            if (sum >= mod) sum = sum - mod;
//        }
//        return sum;
//    }

    /**
     * My solution
     * @param A
     * @return
     */
    public static int hammingDistance(final List<Integer> A) {
        int sum = 0;
        List<String> a = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < A.size(); i++) {
            a.add(Integer.toBinaryString(A.get(i)));
            if(A.get(i)>max){
                max=A.get(i);
            }
        }

        int x;
        int y;
        int maxLength = Integer.toBinaryString(max).length();
        String zeros="";
        for(int z=0;z<maxLength;z++){
            zeros+="0";
        }

        for (int i = 0; i < a.size(); i++) {
            a.set(i,(zeros+a.get(i)).substring(a.get(i).length()));
        }
        for (int i = maxLength-1; i>=0 ; i--) {
            x=0;
            y=0;
            for (int j = 0; j < a.size(); j++) {
               if(a.get(j).charAt(i)=='0'){
                   x++;
               }
               else{
                   y++;
               }
            }
            sum+=2*x*y;
        }
        return sum;
    }
}

