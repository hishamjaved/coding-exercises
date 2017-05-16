package com.google.codejam2017;

import java.io.*;
import java.util.*;

public class Pancakes {
    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codejam2017"
            + File.separator + Pancakes.class.getSimpleName() + File.separator;

    private static final String input = dirPath + "A-large-practice.in";
    private static final String output = dirPath + "A-large-practice.out";
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        PrintStream ps = new PrintStream(new FileOutputStream(output));
        System.setOut(ps);
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            System.out.printf("Case #%d: %s%n", i, findMinimumFlips(new StringBuilder(in.next()), in.nextInt()));
        }
    }

    private static String findMinimumFlips(StringBuilder s,int k){
        StringBuilder required = new StringBuilder("");
        for(int i=0;i<s.length();i++){
            required.append("+");
        }
        final String impossible = "IMPOSSIBLE";
        int flips = 0;
        if(required.equals(s)){
            return Integer.toString(flips);
        }
        else{
            StringBuilder subString = new StringBuilder("");
            while(!required.toString().equals(s.toString())){
                int i = s.indexOf("-");
                int e = i + k;
                if(e<=s.length()){
                    subString.setLength(0);

                    subString.append(s.substring(i, e));


                    for(int c=0;c<subString.length();c++){
                        char altChar = subString.charAt(c)=='-'?'+':'-';
                        s.setCharAt(i,altChar);
                        i++;
                    }
                    flips++;
                }
                else{
                    flips=-1;
                    break;
                }
            }
        }
        if(flips==-1){
            return impossible;
        }
        else{
            return String.valueOf(flips);
        }
    }
}