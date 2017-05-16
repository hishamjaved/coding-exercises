package com.interview.bit.array.misc;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SpiralOrderMatrix {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewbit"
            + File.separator + SpiralOrderMatrix.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        int m = in.nextInt();
        int n = in.nextInt();

        for(int i=0;i<m;i++){
            a.add(new ArrayList<Integer>());
            for(int j=0;j<n;j++){
                a.get(i).add(in.nextInt());
            }
        }
        spiralOrderDisplay(a).stream().forEach(System.out::println);
    }

    public static ArrayList<Integer> spiralOrderDisplay(ArrayList<ArrayList<Integer>> a) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        int m = a.size();
        int n = a.get(0).size();
        int t=0;
        int b = m-1;
        int l=0;
        int r = n-1;
        int dir=0;
        while(t<=b && l<=r){
            if(dir==0){
                //right
                for(int i=l;i<=r;i++){
                    result.add(a.get(t).get(i));
                }
                t++;
                dir=1;
            }
            else if(dir==1){
                //down
                for(int i=t;i<=b;i++){
                    result.add(a.get(i).get(r));
                }
                r--;
                dir=2;
            }
            else if(dir==2){
                //left
                for(int i=r;i>=l;i--){
                    result.add(a.get(b).get(i));
                }
                b--;
                dir=3;
            }
            else{
                //up
                for(int i=b;i>=t;i--){
                    result.add(a.get(i).get(l));
                }
                l++;
                dir=0;
            }
        }
        return result;
    }
}

