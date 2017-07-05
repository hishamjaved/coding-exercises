package com.codility;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?

 The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. The K-th query (0 ? K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).

 For example, consider string S = CAGCCTA and arrays P, Q such that:

 P[0] = 2    Q[0] = 4
 P[1] = 5    Q[1] = 5
 P[2] = 0    Q[2] = 6
 The answers to these M = 3 queries are as follows:

 The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
 The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
 The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
 Write a function:

 class Solution { public int[] solution(String S, int[] P, int[] Q); }
 that, given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.

 The sequence should be returned as:

 a Results structure (in C), or
 a vector of integers (in C++), or
 a Results record (in Pascal), or
 an array of integers (in any other programming language).
 For example, given the string S = CAGCCTA and arrays P, Q such that:

 P[0] = 2    Q[0] = 4
 P[1] = 5    Q[1] = 5
 P[2] = 0    Q[2] = 6
 the function should return the values [2, 4, 1], as explained above.

 Assume that:

 N is an integer within the range [1..100,000];
 M is an integer within the range [1..50,000];
 each element of arrays P, Q is an integer within the range [0..N ? 1];
 P[K] ? Q[K], where 0 ? K < M;
 string S consists only of upper-case English letters A, C, G, T.
 Complexity:

 expected worst-case time complexity is O(N+M);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

public class GenomicRangeQuery {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + GenomicRangeQuery.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        String s = in.nextLine();
        int m = in.nextInt();
        int[] p = new int[m];
        int[] q = new int[m];
        for(int i=0;i<m;i++){
            p[i]=in.nextInt();
        }
        for(int i=0;i<m;i++){
            q[i]=in.nextInt();
        }
        System.out.println(s);
        System.out.println(Arrays.toString(p));
        System.out.println(Arrays.toString(q));
        System.out.println(Arrays.toString(solution(s,p,q)));
    }


    /**
     * Correctness: 100% Performance:100%
     * A=1, C=2 G=3 T=4
     * @param S
     * @param P
     * @param Q
     * @return
     */
    public static int[] solution(String S, int[] P, int[] Q) {
        int[][] nuc = new int[3][S.length()+1];
        int A,C,G;
        for(int i=0; i<S.length(); i++) {
            A=0;C=0;G=0;
            if(S.charAt(i)=='A') {
                A=1;
            } else if(S.charAt(i)=='C') {
                C=1;
            } else if(S.charAt(i)=='G') {
                G=1;
            }
            nuc[0][i+1]=nuc[0][i]+A;
            nuc[1][i+1]=nuc[1][i]+C;
            nuc[2][i+1]=nuc[2][i]+G;
        }
        int[] result = new int[Q.length];
        for(int i=0; i<Q.length; i++) {
            int from = Q[i]+1;
            int to = P[i];
            if(nuc[0][from]-nuc[0][to]>0) {
                result[i] = 1;
            } else if(nuc[1][from]-nuc[1][to]>0) {
                result[i] = 2;
            } else if(nuc[2][from]-nuc[2][to]>0) {
                result[i] = 3;
            } else {
                result[i] = 4;
            }
        }
        return result;
    }

    /**
     *  Correctness=100% Performance=33%
     *  A=1, C=2 G=3 T=4
     * @param S
     * @param P
     * @param Q
     * @return
     */
    public static int[] solution1(String S, int[] P,int[] Q) {
        int[] r=new int[P.length];
        int index;
        for(int i=0;i<P.length;i++){
            index = S.indexOf("A",P[i]);
            if(index>=P[i] && index<=Q[i]){
                r[i]=1;
                continue;
            }

            index = S.indexOf("C",P[i]);
            if(index>=P[i] && index<=Q[i]){
                r[i]=2;
                continue;
            }

            index = S.indexOf("G",P[i]);
            if(index>=P[i] && index<=Q[i]){
                r[i]=3;
                continue;
            }

            index = S.indexOf("T",P[i]);
            if(index>=P[i] && index<=Q[i]){
                r[i]=4;
            }
        }
        return r;
    }
}

