package com.interviewcake;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 In order to win the prize for most cookies sold, my friend Alice and I are going to merge our Girl Scout Cookies orders
 and enter as one unit.
 Each order is represented by an "order id" (an integer).

 We have our lists of orders sorted numerically already, in arrays. Write a function to merge our arrays of orders into
 one sorted array.

 For example:

 int[] myArray     = new int[]{3, 4, 6, 10, 11, 15};
 int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};

 System.out.println(mergeArrays(myArray, alicesArray));
 // prints [1, 3, 4, 5, 6, 8, 10, 11, 12, 14, 15, 19]
 */

public class MergeSortedArrays43 {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "interviewcake"
            + File.separator + MergeSortedArrays43.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int[] a=new int[0];
        int[] b=new int[0];
        int t=0;
        while (t < 2) {
           if(t==0){
               a = new int[in.nextInt()];
               for(int i=0;i<a.length;i++){
                   a[i] = in.nextInt();
               }
           }
           else{
               b = new int[in.nextInt()];
               for(int i=0;i<b.length;i++){
                   b[i] = in.nextInt();
               }
           }
           t++;
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(mergeArrays(a, b)));
    }

    private static int[] mergeArrays(int[] a,int[] b) {
        int[] r=new int[a.length+b.length];
        int i=0;
        int j=0;
        int k=0;
        while(i<a.length || j<b.length){
            if((j==b.length && i<a.length) || (i<a.length && a[i]<b[j])){
                r[k]=a[i];
                i++;
            }
            else if((i==a.length && j<b.length) || (j<b.length && b[j]<a[i])){
                r[k]=b[j];
                j++;
            }
            k++;
        }
        return r;
    }

}

