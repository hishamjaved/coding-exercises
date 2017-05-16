package com.interview.bit.math.arraymath;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PointsInsideRectangle {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewbit"
            + File.separator + PointsInsideRectangle.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));

        ArrayList<Integer> rx = new ArrayList<>();
        ArrayList<Integer> ry = new ArrayList<>();
        ArrayList<Integer> lx = new ArrayList<>();
        ArrayList<Integer> ly = new ArrayList<>();
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            rx.add(in.nextInt());
        }

        n = in.nextInt();
        for(int i=0;i<n;i++){
            ry.add(in.nextInt());
        }

        n = in.nextInt();
        for(int i=0;i<n;i++){
            lx.add(in.nextInt());
        }

        n = in.nextInt();
        for(int i=0;i<n;i++){
            ly.add(in.nextInt());
        }
        System.out.println(numberOfInsidePoints(rx,ry,lx,ly));
    }

    private static int numberOfInsidePoints(ArrayList<Integer> rx, ArrayList<Integer> ry,
                                            ArrayList<Integer> lx, ArrayList<Integer> ly) {
        int innerPoints = 0;
        Collections.sort(rx);
        Collections.sort(ry);

        Collections.sort(lx);
        Collections.sort(ly);


        for(int p=0;p<lx.size();p++){
            if((rx.get(0)<=lx.get(p) && rx.get(rx.size()-1)>=lx.get(p))
                    && (ry.get(0)<=ly.get(p) && ry.get(ry.size()-1)>=ly.get(p))){
                innerPoints++;
            }
        }


        return lx.size() - innerPoints;
    }
}

