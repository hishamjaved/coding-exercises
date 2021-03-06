package com.toptal;


import java.awt.*;
import java.io.*;
import java.util.*;

/**
 *  VERY GOOD EFFICIENT ALGORITHM TO FIND SHORTEST PATH BETWEEN TWO POINTS *
 */

public class KnightMinTurns {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "toptal"
            + File.separator + KnightMinTurns.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    private static int boardSize = 8;
    private static int[][] board;
    private static int[][] jumps = new int[][]{{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
    private static Point lastPosition;
    private static final int KNIGHT_MOVES_COUNT = 8;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        boardSize = in.nextInt();
        board = new int[boardSize][boardSize];
        // fill1(0, 0, 1); //Basic and good upto 8X8 chess board

        long time = System.nanoTime();
        Point source = new Point(in.nextInt(),in.nextInt());
        Point destination = new Point(in.nextInt(), in.nextInt());

        int turns = countMoves(source,destination); // This is very efficient and
        System.out.println(turns);
        //printBoard();

        System.out.println(String.format("Computing time: %.2f ms.\nStart position: x=%d, y=%d. Last position: x=%d, y=%d.\n",
                (System.nanoTime() - time) / 1000000.f, source.x, source.y, lastPosition.x, lastPosition.y));
    }


    private static boolean valid(int x, int y)
    {
        return y >= 0 && x>=0 && y< boardSize && x< boardSize;
    }

    private static int countMoves(Point s,Point d){
        //Conversion of negative points
        //int xOffset =Math.abs(s.x<d.x && s.x<0?s.x:(s.x>d.x && d.x<0?d.x:0)) ;
        //int yOffset =Math.abs(s.y<d.y && s.y<0?s.y:(s.y>d.y && d.y<0?d.y:0)) ;

        //If 0,0 is in the center of chessboard
        int xOffset = boardSize/2;
        int yOffset = boardSize/2;

        s.setLocation(s.x+xOffset,s.y+yOffset);
        d.setLocation(d.x+xOffset,d.y+yOffset);
        // create a queue and enqueue first node
        if (d.x == s.x && d.y == s.y){
            lastPosition = d;
            return 0;
        }
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[]{s.x,s.y,0});
        // run till queue is not empty
        while (!q.isEmpty())
        {
            // pop front node from queue and process it
            int[] node = q.peek();
            q.poll();

            int x = node[0];
            int y = node[1];
            int move = node[2];
            lastPosition = new Point(x,y);

            if(move>100000000){
                return -2;
            }

            // if destination is reached, return distance
            if (x == d.x && y == d.y)
                return move;

            // Skip if location is visited before
            if (board[x][y]==0)
            {
                // mark current node as visited
                board[x][y]=move;

                // check for all 8 possible movements for a knight
                // and enqueue each valid movement into the queue
                for (int[] jump:jumps)
                {
                    // Get the new valid position of Knight from current
                    // position on chessboard and enqueue it in the
                    // queue with +1 distance
                    int x1 = x + jump[0];
                    int y1 = y + jump[1];

                    if (valid(x1, y1))
                        q.add(new int[]{x1,y1,move+1});
                }
            }
        }

        // return INFINITY if path is not possible
        return -1;
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

