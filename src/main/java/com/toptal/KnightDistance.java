package com.toptal;


import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *  NOT Very good algorithm for Knight distance
 */

public class KnightDistance {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "toptal"
            + File.separator + KnightDistance.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    private static int boardSize = 8;
    private static int[][] board;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        boardSize = in.nextInt();
        board = new int[boardSize][boardSize];
        long time = System.nanoTime();
        fill();
        System.out.println(String.format("Computing time: %.2f ms.\n", (System.nanoTime() - time) / 1000000.f));
        printBoard();
    }

    private static void fill(){
        for (int a = 0; a < boardSize; a++) {
            for (int b = 0; b <boardSize; b++) {
                movesRequired(a,b);
                board[a][b] = knightsDistance(a,b);
            }
        }
    }

    private static int movesRequired(int a, int b) {
        int x = 0;
        int y = 0;
        int count = 0;
        if (!((((a - x) % 2) ^ ((y - b) % 2))==0))
            return -1;
        while (x != a && y != b) {
            int distX = a-x;
            int distY = b-y;
            if (Math.abs(distX) > Math.abs(distY)) {
                x += distX>0?2:-2;
                y += distY>0?1:-1;
            } else {
                x += distX>0?1:-1;
                y += distY>0?2:-2;
            }
            count++;
        }
        return count;
    }

    private static int knightsDistance(int x, int y) {
        int t, delta, moves;

        if (x < y) {
            t = x;
            x = y;
            y = t;
        }

        // 0 <= y <= x
        delta = x - y;
        if (y > delta) {
            moves = delta + 2 * ((y - delta) / 3);
        } else {
            moves = delta - 2 * ((delta - y) / 4);
        }
        return moves;
    }

    /**
     * print the board using nice ASCII art ('+' and '-')
     */
    private static void printBoard(){
        int scale = String.valueOf(boardSize * boardSize).length();
        System.out.printf(mc(boardSize, "+" + mc(scale, "-")) + "+%n");
        for(int r=0;r< boardSize;r++){
            for(int c=0;c< boardSize;c++){
                String elem = String.format("%"+scale+"d", board[r][c]);
                System.out.print("|"+elem);
            }
            System.out.printf("|%n" + mc(boardSize, "+" + mc(scale, "-"))+"+%n");
        }
    }

    private static String mc(int times,String c){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<times;i++){
            sb.append(c);
        }
        return sb.toString();
    }


}

