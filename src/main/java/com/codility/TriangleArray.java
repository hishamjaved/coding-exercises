package com.codility;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ? P < Q < R < N and:

 A[P] + A[Q] > A[R],
 A[Q] + A[R] > A[P],
 A[R] + A[P] > A[Q].
 For example, consider array A such that:

 A[0] = 10    A[1] = 2    A[2] = 5
 A[3] = 1     A[4] = 8    A[5] = 20
 Triplet (0, 2, 4) is triangular.

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given a zero-indexed array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.

 For example, given array A such that:

 A[0] = 10    A[1] = 2    A[2] = 5
 A[3] = 1     A[4] = 8    A[5] = 20
 the function should return 1, as explained above. Given array A such that:

 A[0] = 10    A[1] = 50    A[2] = 5
 A[3] = 1
 the function should return 0.

 Assume that:

 N is an integer within the range [0..100,000];
 each element of array A is an integer within the range [?2,147,483,648..2,147,483,647].
 Complexity:

 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.



 Execution:
 By sorting the array, we have guaranteed that P+R > Q and Q+R > P (because R is always the biggest). Now what remains,
 is the proof that P+Q > R, that can be found out by traversing the array. The chance to find such a combination is with
 three adjacent values as they provide the highest P and Q.
 */

public class TriangleArray {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + TriangleArray.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] A=new int[n];
        for(int i=0;i<n;i++){
            A[i]=in.nextInt();
            System.out.print(A[i] + ", ");
        }
        System.out.println("");
        System.out.println(solution(A));
        System.out.println(solution2(A));
        System.out.println(solution3(A));
    }

    /**
     * Time Complexity O(n)
     * @param A
     * @return
     */
    public static int solution(int[] A) {
        if(A.length<3)
            return 0;
        Arrays.sort(A);
        int p,q,r;
        for(int i=0;i<A.length-2;i++){
            p = i;
            q = i+1;
            r = i+2;
            if((A[p] + A[q] > A[r]) &&
                    (A[q] + A[r] > A[p]) &&
                    (A[r] + A[p] > A[q]))
                return 1;
        }
        return 0;
    }

    public static int solution3(int[] A){
        Arrays.sort(A);
        int p,q,r;
        for(int i=0;i<A.length-2;i++){
            p=A[i];
            q=A[i+1];
            r=A[i+2];
            if(r-p>q){
                return 1;
            }
        }
        return 0;
    }

    /**
     * Time Complexity O(n^3)
     * @param A
     * @return
     */
    public static int solution2(int[] A) {
        if(A.length<3)
            return 0;

        for(int p=0;p<A.length;p++){
            for(int q=p+1; q < A.length;q++){
                for(int r=q+1;r< A.length;r++){
                    if ((A[p] + A[q] > A[r]) &&
                            (A[q] + A[r] > A[p]) &&
                            (A[r] + A[p] > A[q])){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }



}

