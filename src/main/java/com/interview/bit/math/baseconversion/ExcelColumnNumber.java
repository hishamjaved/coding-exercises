package com.interview.bit.math.baseconversion;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExcelColumnNumber {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String columnTitle = in.next();
        System.out.println(excelColumnNumber(columnTitle));
    }

    private static int excelColumnNumber(String a) {
        a = a.toUpperCase();
        int columnNumber = 0;
        int idx = 0;
        int p = 0;
        for (int i = a.length()-1; i >=0 ; i--) {
            idx = (int) a.charAt(i) - 65;
            columnNumber += (Math.pow(26, p) * (idx+1));
            p++;
        }
        return columnNumber;
    }

}

