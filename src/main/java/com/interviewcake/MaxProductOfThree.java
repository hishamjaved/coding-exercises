package com.interviewcake;


import java.io.*;
import java.util.Scanner;

/**
 Given an array of integers, find the highest product you can get from three of the integers.
 The input arrayOfInts will always have at least three integers.
 */

public class MaxProductOfThree {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + MaxProductOfThree.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=1;i<=n;i++){
            a[i-1]=in.nextInt();
        }
        System.out.println(solution(a));
        System.out.println(solution2(a));
    }


    public static int solution(int[] a) {
        if(a.length<3){
            throw new IllegalArgumentException("Getting a profit requires at least two prices");
        }


        int first=a[0];
        int second=a[1];
        int third=a[2];

        if(first<second){
            if(first<third){
                first=a[2];
                third=a[0];
            }
            else{
                first=a[1];
                second=a[0];
            }
        }

        if(second<third){
            second=a[2];
            third=a[1];
        }



        for(int i=3;i<a.length;i++){
            if(a[i]>first){
                third = second;
                second = first;
                first = a[i];
                continue;
            }

            if(a[i]>second){
                third=second;
                second=a[i];
                continue;
            }

            if(a[i]>third){
                third=a[i];
            }
        }
        return first*second*third;
    }

    private static int solution2(int[] arrayOfInts){
        if (arrayOfInts.length < 3) {
            throw new IllegalArgumentException("Less than 3 items!");
        }

        // We're going to start at the 3rd item (at index 2)
        // so pre-populate highests and lowests based on the first 2 items.
        // we could also start these as null and check below if they're set
        // but this is arguably cleaner
        int highest = Math.max(arrayOfInts[0], arrayOfInts[1]);
        int lowest  = Math.min(arrayOfInts[0], arrayOfInts[1]);

        int highestProductOf2 = arrayOfInts[0] * arrayOfInts[1];
        int lowestProductOf2  = arrayOfInts[0] * arrayOfInts[1];

        // except this one--we pre-populate it for the first *3* items.
        // this means in our first pass it'll check against itself, which is fine.
        int highestProductOf3 = arrayOfInts[0] * arrayOfInts[1] * arrayOfInts[2];

        // walk through items, starting at index 2
        for (int i = 2; i < arrayOfInts.length; i++) {
            int current = arrayOfInts[i];

            // do we have a new highest product of 3?
            // it's either the current highest,
            // or the current times the highest product of two
            // or the current times the lowest product of two
            highestProductOf3 = Math.max(Math.max(
                            highestProductOf3,
                            current * highestProductOf2),
                    current * lowestProductOf2);

            // do we have a new highest product of two?
            highestProductOf2 = Math.max(Math.max(
                            highestProductOf2,
                            current * highest),
                    current * lowest);

            // do we have a new lowest product of two?
            lowestProductOf2 = Math.min(Math.min(
                            lowestProductOf2,
                            current * highest),
                    current * lowest);

            // do we have a new highest?
            highest = Math.max(highest, current);

            // do we have a new lowest?
            lowest = Math.min(lowest, current);
        }

        return highestProductOf3;
    }

}

