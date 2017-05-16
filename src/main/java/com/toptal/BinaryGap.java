package com.toptal;


import java.io.*;
import java.util.Scanner;

/**
 *      A binary gap within a positive integer N is any maximal
        sequence of consecutive zeros that is surrounded by ones
        at both ends in the binary representation of N.
        Args:
        - N: integer within the range [1..2,147,483,647]
 */

public class BinaryGap {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "toptal"
            + File.separator + BinaryGap.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        System.out.println(solution(n));
    }


    /**
     * Efficient O(log n)
     * @param N
     * @return
     */
    public static int solution(int N) {
        if(N<=0){
            return 0;
        }
        String binary = Integer.toBinaryString(N);
        int i = binary.length()-1;
        while(binary.charAt(i)=='0') {
            i--;
        }
        int gap = 0;
        int counter = 0;
        for(; i>=0; i--) {
            if(binary.charAt(i)=='1') {
                gap = Math.max(gap, counter);
                counter = 0;
            } else {
                counter++;
            }
        }
        gap = Math.max(gap, counter);
        return gap;
    }

    /**
     * O(n)
     * @param N
     * @return
     */
    public static int binaryGap(int N){
        String bin_representation = Integer.toBinaryString(N);
        System.out.println(bin_representation);
        int max_gap = 0;
        int gap_counter = 0;
        boolean gap_started = false;
        if(bin_representation.startsWith("1") && bin_representation.contains("0")){
            for(int i = bin_representation.indexOf("0")-1;i < bin_representation.length();i++){
                char symbol = bin_representation.charAt(i);
                if (symbol == '1'){
                    if(gap_counter > max_gap){
                        max_gap = gap_counter;
                    }
                    gap_counter = 0;
                    gap_started = true;
                }
                else if(gap_started){
                    gap_counter += 1;
                }
            }
        }

        return max_gap;
    }

}

