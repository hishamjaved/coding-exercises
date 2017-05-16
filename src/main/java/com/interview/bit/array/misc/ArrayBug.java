package com.interview.bit.array.misc;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ArrayBug {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewbit"
            + File.separator + ArrayBug.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        ArrayList<Integer> a = new ArrayList<>();
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            a.add(in.nextInt());
        }

        int b = in.nextInt();
        System.out.println(rotateArray(a, b));
    }

    public static ArrayList<Integer> rotateArray(ArrayList<Integer> A, int B) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        B = B%A.size();
        for (int i = 0; i < A.size()-B; i++) {
            ret.add(A.get(i + B));
        }

        for (int i = 0; i < B; i++) {
            ret.add(A.get(i));
        }

        return ret;
    }
}

