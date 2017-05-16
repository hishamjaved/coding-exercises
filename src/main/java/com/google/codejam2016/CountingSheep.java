package com.google.codejam2016;

import java.io.*;
import java.util.Scanner;

/**
 * See Problem Description
 * https://code.google.com/codejam/contest/6254486/dashboard#s=p0
 * Created by HISHAM on 4/2/2017.
 */
public class CountingSheep {
    private static final String currentDir = System.getProperty("user.dir") + File.separator
            + "input" + File.separator + CountingSheep.class.getSimpleName() + File.separator;
    private static final String input = "A-large-practice.in";
    private static final String output = "A-large-practice.out";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(currentDir + input))));
        PrintStream out = new PrintStream(new FileOutputStream(currentDir + output));
        System.setOut(out);
        int count = in.nextInt();

        for (int i = 1; i <= count; i++) {
            int n = in.nextInt();
            System.out.printf("Case #%d: %s%n", i, solution(n));
        }
    }

    public static String solution(int n) {
        if (n == 0) {
            return "INSOMNIA";
        } else {
            int[] digits = new int[10];
            int found = 0;
            int i = 1;
            Long product;
            String numList;
            int index;
            while (found < 10) {
                product = Long.valueOf(i * n);
                numList = String.valueOf(product);
                for(int c=0;c < numList.length();c++){
                    index = Integer.parseInt(String.valueOf(numList.charAt(c)));
                    if(digits[index]!=1){
                        digits[index]=1;
                        found++;
                        if(found==10){
                            return String.valueOf(product);
                        }
                    }
                }
                i++;
            }
            return "INSOMNIA";
        }
    }
}
