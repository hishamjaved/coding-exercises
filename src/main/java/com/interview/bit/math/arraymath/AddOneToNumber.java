package com.interview.bit.math.arraymath;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddOneToNumber {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "interviewbit"
            + File.separator + AddOneToNumber.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));

        ArrayList<Integer> x = new ArrayList<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            x.add(in.nextInt());
        }
        System.out.println(plusOne(x));
    }

    private static ArrayList<Integer> plusOne(ArrayList<Integer> a) {
        int index = a.size()-1;
        while(a.get(index)==9){
            index--;
            if(index==-1){
                index=0;
                break;
            }
        }

        a.set(index,a.get(index)+1);
        if(index<a.size()-1){
            for(int i=index+1;i<a.size();i++){
                a.set(i,0);
            }
        }

        ArrayList<Integer> b = new ArrayList<>();
        if(a.get(0)==0){
            boolean leadingZero = true;
            for(int i=0;i<a.size();i++){
                if(!(a.get(i)==0 && leadingZero)){
                    b.add(a.get(i));
                    leadingZero=false;
                }
            }
        }
        else{
            if(a.get(0).toString().length()==2){
                b.add(a.get(0)/10);
                b.add(a.get(0)%10);
                for(int i=1;i<a.size();i++){
                    b.add(a.get(i));
                }
            }
            else{
                b.addAll(a);
            }

        }
        return b;

    }
}

