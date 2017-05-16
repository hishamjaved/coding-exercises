package com.interview.bit.math.baseconversion;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExcelColumnTitle {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int columnNumber = in.nextInt();
        System.out.println(excelColumnTitle(columnNumber));
    }

    private static String excelColumnTitle(int a) {

        StringBuilder t = new StringBuilder();
        while(a>26){
            if(a%26==0){
                t.append((char)(26+64));
                a=a-1;
            }
            else{
                t.append((char)((a%26)+64));
            }
            a = a/26;
        }
        if(a<=26 && a!=0){
            t.append((char)(a+64));
        }
        return t.reverse().toString();
    }

}

