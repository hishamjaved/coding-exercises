package com.interview.bit.math.numberencoding;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RearrangeArray {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewbit"
            + File.separator + RearrangeArray.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=1;i<=n;i++){
            a.add(in.nextInt());
        }
        arrange2(a);
        a.stream().forEach(System.out::print);
    }

    private static void arrange(ArrayList<Integer> a) {
        ArrayList<Integer> b = (ArrayList<Integer>)a.clone();
        for(int i=0;i<b.size();i++){
            a.set(i,b.get(b.get(i)));
        }
    }

    private static void arrange2(ArrayList<Integer> a) {
        int n = a.size();
        for(int i = 0; i < n; ++i) {
            a.set(i, a.get(i) + (a.get(a.get(i))%n) * n);
        }

        for(int i = 0; i < n; ++i) {
            a.set(i, a.get(i) / n);
        }
    }


}

