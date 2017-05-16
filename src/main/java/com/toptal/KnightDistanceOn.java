package com.toptal;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Mathematical Solution to find distance from 0,0
 */

public class KnightDistanceOn {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "toptal"
            + File.separator + KnightDistanceOn.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        Point source = new Point(in.nextInt(),in.nextInt());
        Point destination = new Point(in.nextInt(),in.nextInt());
        long time = System.nanoTime();
        System.out.println(evalMoves(source, destination));
        System.out.println(String.format("Computing time: %.2f ms.\n", (System.nanoTime() - time) / 1000000.f));

    }

    private static int evalMoves(Point s,Point d) {
        if(s.x==d.x && s.y==d.y){
            return 0;
        }

        int xOffset =Math.abs(s.x<d.x && s.x<0?s.x:(s.x>d.x && d.x<0?d.x:0)) ;
        int yOffset =Math.abs(s.y<d.y && s.y<0?s.y:(s.y>d.y && d.y<0?d.y:0)) ;

        s.setLocation(s.x+xOffset,s.y+yOffset);
        d.setLocation(d.x+xOffset,d.y+yOffset);

        if(s.x>0){
            d.x+=s.x;
            s.x=0;
        }
        if(s.y>0){
            d.y+=s.y;
            s.y=0;
        }


        Point tmp;
        if((d.x<s.x && d.y<=s.y) || (d.x<=s.x && d.y<s.y)){
            tmp = d;
            d = s;
            s = tmp;
        }
        return evalMovesInner(d);
    }

    private static int evalMovesInner(Point d) {
        int distance = d.x + d.y;
        if (distance != 0) {
            if (d.x == 0) {
                d.x=1;
                d.y = Math.abs(d.y-2);
                return 1 + evalMovesInner(d);
            }
            if (d.y == 0) {
                d.x = Math.abs(d.x-2);
                d.y=1;
                return 1 + evalMovesInner(d);
            }
        }

        if (distance == 0) {
            return 0;
        } else if (distance == 3) {
            return 1;
        } else if (distance == 2) {
            return 2;
        } else if (distance == 1) {
            return 3;
        }
        if (d.x > d.y) {
            int moves = d.y / 2;
            d.x = Math.abs(d.x - (moves * 2));
            d.y = Math.abs(d.y - moves);

            return moves + evalMovesInner(d);
        } else {
            int moves = d.x / 2;
            d.y = Math.abs(d.y - (moves * 2));
            d.x = Math.abs(d.x - moves);
            return moves + evalMovesInner(d);
        }
    }


}

