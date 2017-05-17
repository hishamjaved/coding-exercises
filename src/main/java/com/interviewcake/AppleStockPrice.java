package com.interviewcake;


import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 Writing programming interview questions hasn't made me rich. Maybe trading Apple stocks will.
 Suppose we could access yesterday's stock prices as an array, where:

 The values are the price in dollars of Apple stock.
 A higher index indicates a later time.
 So if the stock cost $500 at 10:30am and $550 at 11:00am, then:

 stockPricesYesterday[60] = 500;

 Write an efficient function that takes stockPricesYesterday and returns the best profit
 I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.

 For example:

 int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};

 getMaxProfit(stockPricesYesterday);
 // returns 6 (buying for $5 and selling for $11)

 No "shorting"—you must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).
 */

public class AppleStockPrice {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + AppleStockPrice.class.getSimpleName() + File.separator;
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


    public static int solution(int[] a) {
        if(a.length<2){
            throw new IllegalArgumentException("Getting a profit requires at least two prices");
        }
        int leastPrice = a[0];
        int currentPrice;
        int bestProfit = a[1] - a[0];
        for(int i=1;i<a.length;i++){
            currentPrice = a[i];
            leastPrice = Math.min(currentPrice, leastPrice);
            bestProfit=Math.max((currentPrice - Math.abs(leastPrice)), bestProfit);
        }
        return bestProfit;
    }
}

