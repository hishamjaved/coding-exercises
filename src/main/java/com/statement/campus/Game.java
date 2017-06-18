package com.statement.campus;

import java.io.*;
import java.util.*;

/**
 If input is 80 then find the minimum multiple of 80 which is a combination of 4's and 0's or only 4's.
 Following are the valid multiples 440,444,400 and 404 is invalid.
 For example 80*5=400

 Find a number n which formula is 2X*Y. where X are the number of fours and Y are the number of zeroes in the least
 multiple of a number.

 For example in the above example the least multiple is 400 where X=1 and Y=2 so the answer of this number will be as:

 X=1,Y=2
 N=(2*1)+2=4


 */

public class Game {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "statementcampus"
            + File.separator + Game.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
           a[i]=in.nextInt();
        }

        System.out.println(Arrays.toString(solution(a)));
    }


    public static int[] solution(int[] a) {
        char[] number;
        String[] result = new String[a.length];
        for(int k=0;k<a.length;k++){
            int i= String.valueOf(a[k]).length();
            while(true){
                number=new char[i];
                number[0]='4';
                for(int j=1;j<i;j++){
                    number[j]='0';
                }
                if(Integer.valueOf(String.valueOf(number))%a[k]==0){
                    result[k]=String.valueOf(number);
                    break;
                }
                for(int j=1;j<i;j++){
                    number[j]='4';
                    if(Integer.valueOf(String.valueOf(number))%a[k]==0){
                        result[k]=String.valueOf(number);
                        break;
                    }
                }
                i++;
            }
        }

        int k=0;
        for(String n:result){
            int x = n.length() - n.replace("4","").length(); //Count 4's
            int y = n.length() - x; //Count 0's
            a[k]=(2*x)+y;
            k++;
        }
        return a;
    }
}

