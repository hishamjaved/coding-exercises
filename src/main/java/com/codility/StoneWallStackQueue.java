package com.codility;


import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 You are going to build a stone wall. The wall should be straight and N meters long, and its thickness should be
 constant; however, it should have different heights in different places. The height of the wall is specified by a
 zero-indexed array H of N positive integers. H[I] is the height of the wall from I to I+1 meters to the right of its
 left end. In particular, H[0] is the height of the wall's left end and H[N?1] is the height of the wall's right end.

 The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular). Your task is to
 compute the minimum number of blocks needed to build the wall.

 Write a function:

 class Solution { public int solution(int[] H); }
 that, given a zero-indexed array H of N positive integers specifying the height of the wall, returns the minimum
 number of blocks needed to build it.

 For example, given array H containing N = 9 integers:

 H[0] = 8    H[1] = 8    H[2] = 5
 H[3] = 7    H[4] = 9    H[5] = 8
 H[6] = 7    H[7] = 4    H[8] = 8
 the function should return 7. The figure shows one possible arrangement of seven blocks.



 Assume that:

 N is an integer within the range [1..100,000];
 each element of array H is an integer within the range [1..1,000,000,000].
 Complexity:

 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

public class StoneWallStackQueue {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + StoneWallStackQueue.class.getSimpleName() + File.separator;
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


    public static int solution(int[] H) {
        int[] heights = Arrays.copyOf(H, H.length + 1);
        Stack<Integer> increasingHeights = new Stack<Integer>();
        int blockNum = 0;
        for (int height : heights) {
            while (!increasingHeights.empty()
                    && increasingHeights.peek() >= height) {
                if (increasingHeights.peek() > height) {
                    blockNum++;
                }
                increasingHeights.pop();
            }
            increasingHeights.push(height);
        }
        return blockNum;
    }


    /**
     * @param H
     * @return
     */
    public static int solution1(int[] H) {
        ArrayList<Integer> list = new ArrayList<>();
        HashSet<Integer> groups = new HashSet<Integer>();
        for(Integer i:H){
            if(i>0){
                if(list.contains(i)){
                    groups.add(i);
                }
                else{
                    list.add(i);
                }
            }
        }
        int total = groups.size();
        int min;
        while(list.size()>0){
            min = getMinimum(list);
            if(min>0){
                total++;
                Integer item=0;
                int i=0;
                for(Iterator<Integer> it=list.iterator();it.hasNext();i++){
                    item = it.next();
                    item = item-min;
                    if(item>0){
                        list.set(i,item);
                    }
                    else{
                        i--;
                        it.remove();
                    }
                }
            }
        }
        return total;
    }

    private static int getMinimum(ArrayList<Integer> list){
        int min=Integer.MAX_VALUE;
        for(Integer i:list){
            if(i!=0 && i<min){
                min=i;
            }
        }
        return min;
    }

}

