package com.interview.bit.math.adhoc;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FizzBuzz {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int A = in.nextInt();
        System.out.println(fizzBuzz(A).toString());
    }

    public static ArrayList<String> fizzBuzz(int A) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= A; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(String.valueOf(i));
            }

        }
        return list;
    }
}

