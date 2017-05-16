package com.toptal;


import java.io.*;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Format number into currency format like 2,147,483,647
 */

public class DigitFormat {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "toptal"
            + File.separator + DigitFormat.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        System.out.println(formatNumber(n));
    }

    public static String formatNumber(int n) {

        String[] string = String.valueOf(n).split("");
        StringBuilder num = new StringBuilder();
        int count = 0;

        for (int i = string.length - 1; i >= 0; i --) {
           count++;
            if (count == 3 && i > 0) {
                num.append(string[i]);
                num.append(",");
                count = 0;
            } else {
                num.append(string[i]);
            }

        }
        return num.reverse().toString();
    }
}

