package com.general;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class BinarySearch {
    public static void main(String[] args) {
        int[] list = new int[]{4,2,98,9,2,34,56,24,90,1,2,54,71,190,82};

        System.out.println(binarySearch(7,list));
        System.out.println(binarySearch(190,list));
        System.out.println(binarySearch(4,list));
        System.out.println(binarySearch(24,list));
    }

    private static boolean binarySearch(int item,int[] list){
        Arrays.sort(list);
        int floor = -1;
        int ceil=list.length;
        int middle=0;
        boolean found=false;
        while(floor+1<ceil){
            middle=(ceil - floor)/2;
            if(list[floor+middle]==item){
                return true;
            }
            else if(item>list[floor+middle]){
                floor=floor+middle;
            }
            else if(item<list[floor+middle]){
                ceil=ceil-middle;
            }
        }
        return found;
    }
}

