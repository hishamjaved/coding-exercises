package com.interviewcake;


import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Write a function for doing an in-place ? shuffle of an array.
 * The shuffle must be "uniform," meaning each item in the original array must have the same probability of ending up in
 * each spot in the final array.
 * <p/>
 * Assume that you have a function getRandom(floor, ceiling) for getting a random integer that is >= floor and <= ceiling.
 * <p/>
 * NOTE:
 * Also, the name "shuffle" can be slightly misleading—the point is to arrive at a random ordering of the items from the
 * original array. Don't fixate too much on preconceived notions of how you would "shuffle" e.g. a deck of cards.
 */

public class InplaceShuffle35 {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "interviewcake"
            + File.separator + InplaceShuffle35.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = in.nextInt();
        }
        System.out.println("ORIGINAL: " + Arrays.toString(a));
        a = solution(a);
        System.out.println("1ST SHUFFLE: " + Arrays.toString(a));
        a = solution(a);
        System.out.println("2ND SHUFFLE: " + Arrays.toString(a));
        a = solution(a);
        System.out.println("3RD SHUFFLE: " + Arrays.toString(a));
    }

    private static Random random = new Random();

    /**
     * this would give us a uniform shuffle.
     * @param a
     * @return
     */
    public static int[] solution(int[] a) {
        int l = a.length;
        int randomIndex;
        int tmp;
        for (int i = 0; i<l-1; i++) {
            randomIndex = getRandom(i,l);
            if(i==randomIndex){
                continue;
            }
            tmp = a[i];
            a[i]=a[randomIndex];
            a[randomIndex] = tmp;
        }
        return a;
    }

    public static int getRandom(int floor,int ceiling){
        return random.nextInt(ceiling-floor)+floor;
    }
}

