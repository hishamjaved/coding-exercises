package com.interviewcake;


import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 Your company delivers breakfast via autonomous quadcopter drones. And something mysterious has happened.
 Each breakfast delivery is assigned a unique ID, a positive integer. When one of the company's 100 drones takes off
 with a delivery, the delivery's ID is added to an array, deliveryIdConfirmations. When the drone comes back and lands,
 the ID is again added to the same array.

 After breakfast this morning there were only 99 drones on the tarmac. One of the drones never made it back from a
 delivery. We suspect a secret agent from Amazon placed an order and stole one of our patented drones. To track them
 down, we need to find their delivery ID.

 Given the array of IDs, which contains many duplicate integers and one unique integer, find the unique integer.

 The IDs are not guaranteed to be sorted or sequential. Orders aren't always fulfilled in the order they were received,
 and some deliveries get cancelled before takeoff.


 Sample Array: Number can be duplicate only twice or multiple of two
 [21 17 15 21 17 15 16 25 25]
 */

public class FindUniqueAmongDuplicates {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + FindUniqueAmongDuplicates.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=1;i<=n;i++){
            a[i-1]=in.nextInt();
        }
        System.out.println(solution(a));
    }

    /**
     * O(n)
     * @param a
     * @return
     */
    public static int solution(int[] a) {
        if(a.length==1){
            return a[0];
        }
        else if(a.length==0){
            throw new IllegalArgumentException("Can't find unique value from empty array");
        }
        int unique=0;
        //XOR operation cancels each duplicate
        // 10^10=0
        // 10^10^21=21
        // 10^10^10=10

        for(int i=0;i<a.length;i++){
            unique^=a[i];
        }
        return unique;
    }


    /**
     * O(n) with space O(n)
     * @param a
     * @return
     */
    public static int solution1(int[] a) {
        if(a.length==1){
            return a[0];
        }
        else if(a.length==0){
            throw new IllegalArgumentException("Can't find unique value from empty array");
        }

        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

        int unique=0;
        for(int i=0;i<a.length;i++){
            if(map.get(a[i])==null){
                map.put(a[i],1);
            }
            else{
                map.put(a[i],((int)map.get(a[i]))+1);
            }
        }

        for(Map.Entry<Integer,Integer> item:map.entrySet()){
            if(item.getValue()==1){
                unique = item.getValue();
                break;
            }
        }

        return unique;
    }


    /**
     * O(n * log n)
     * @param a
     * @return
     */
    public static int solution2(int[] a) {
        if(a.length==1){
            return a[0];
        }
        else if(a.length==0){
            throw new IllegalArgumentException("Can't find unique value from empty array");
        }

        Arrays.sort(a);

        int unique=0;
        for(int i=0;i<a.length;i++){
            unique = a[i];

            if(i==0 && unique == a[i+1]){
                continue;
            }

            if(i>0 && i<a.length-1 && (unique==a[i+1] || unique==a[i-1])){
                continue;
            }
            break;
        }

        return unique;
    }
}

