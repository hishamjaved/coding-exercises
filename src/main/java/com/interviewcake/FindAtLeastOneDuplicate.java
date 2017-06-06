package com.interviewcake;


import java.io.*;
import java.util.Scanner;
import java.util.Stack;

/**
 * Find a duplicate, Space Edition™.
 * We have an array of integers, where:
 * <p/>
 * The integers are in the range 1..n1..n
 * The array has a length of n+1n+1
 * It follows that our array has at least one integer which appears at least twice. But it may have several duplicates,
 * and each duplicate may appear more than twice.
 * <p/>
 * Write a function which finds an integer that appears more than once in our array. (If there are multiple duplicates,
 * you only need to find one of them.)
 * <p/>
 * We're going to run this function on our new, super-hip Macbook Pro With Retina Display™. Thing is, the damn thing came
 * with the RAM soldered right to the motherboard, so we can't upgrade our RAM. So we need to optimize for space!
 * <p/>
 * Gotchas
 * We can do this in O(1)O(1) space.
 * <p/>
 * We can do this in less than O(n^2)O(n
 * ?2
 * ?? ) time, while keeping O(1)O(1) space.
 * <p/>
 * We can do this in O(n\lg{n})O(nlgn) time and O(1)O(1) space.
 * <p/>
 * We can do this without destroying the input.
 * <p/>
 * Most O(n\lg{n})O(nlgn) algorithms double something or cut something in half. How can we rule out half of the numbers
 * each time we iterate through the array?
 */

public class FindAtLeastOneDuplicate {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "interviewcake"
            + File.separator + FindAtLeastOneDuplicate.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        String cmd;
        int n = in.nextInt();
        int[] a = new int[n];
        int i = 0;
        while (i < n) {
            a[i] = in.nextInt();
            i++;
        }
        System.out.println(findDuplicate(a));
    }

    private static int findDuplicate(int[] a) {

        int floor = 1;
        int ceiling = a.length - 1;


        while (floor < ceiling) {

            // divide our range 1..n into an upper range and lower range
            // (such that they don't overlap)
            // lower range is floor..midpoint
            // upper range is midpoint+1..ceiling
            int lowerRangeFloor = floor;
            int lowerRangeCeiling = floor + ((ceiling - floor) / 2);
            int upperRangeFloor = lowerRangeCeiling + 1;
            int upperRangeCeiling = ceiling;

            // count number of items in lower range
            int itemsInLowerRange = 0;
            int lowerRangeActualLength = lowerRangeCeiling - lowerRangeFloor + 1;
            for (int i = 0; i < a.length; i++) {
                // is it in the lower range?
                if (a[i] >= lowerRangeFloor && a[i] <= lowerRangeCeiling) {
                    itemsInLowerRange++;
                    if(itemsInLowerRange > lowerRangeActualLength){
                        break;
                    }
                }
            }

            if (itemsInLowerRange > lowerRangeActualLength) {
                // there must be a duplicate in the lower range
                // so use the same approach iteratively on that range
                floor = lowerRangeFloor;
                ceiling = lowerRangeCeiling;
            } else {
                // there must be a duplicate in the upper range
                // so use the same approach iteratively on that range
                floor = upperRangeFloor;
                ceiling = upperRangeCeiling;
            }
        }
        // floor and ceiling have converged
        // we found a number that repeats!
        return floor;
    }

}

