package com.codility;


import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 Write a function:

 class Solution { public int solution(int[] A); }
 that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer (greater than 0) that
 does not occur in A.

 For example, given:

 A[0] = 1
 A[1] = 3
 A[2] = 6
 A[3] = 4
 A[4] = 1
 A[5] = 2
 the function should return 5.

 Assume that:

 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [?2,147,483,648..2,147,483,647].
 Complexity:

 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

public class MissingInteger {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + MissingInteger.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }

        System.out.println(Arrays.toString(a));
        System.out.println(solution(a));
    }

    /**
     *
     * @param A
     * @return
     */
    public static int solution(int[] A) {
        Set<Integer> numbers = new HashSet<Integer>();
        for (int number : A) {
            numbers.add(number);
        }
        for (int i = 1;; i++) {
            if (!numbers.contains(i)) {
                return i;
            }
        }
    }
}

