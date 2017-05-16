package com.toptal;


import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 *  Average algorithm for Knight Tour not very efficient but good brute force basic solution for lesser chess board
 */

public class KnightTour {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "toptal"
            + File.separator + KnightTour.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    private static int boardSize = 8;
    private static int[][] board;
    private static int[][] jumps = new int[][]{{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};

    //Used for second solution
    private static Point lastPosition;
    private static final int KNIGHT_MOVES_COUNT = 8;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        boardSize = in.nextInt();
        board = new int[boardSize][boardSize];
        // fill1(0, 0, 1); //Basic and good upto 8X8 chess board

        long time = System.nanoTime();
        boolean solved = tour(0, 0); // This is very efficient and
        time = System.nanoTime() - time;

        if (solved) {
            System.out.println("Solution was found!");
        } else {
            System.out.println("Solution was not found! ");
        }

        System.out.println(String.format("Computing time: %.2f ms.\nStart position: x=%d, y=%d. Last position: x=%d, y=%d.\n",
                time / 1000000.f, 0, 0, lastPosition.x, lastPosition.y));

        System.out.println("Chessboard:");
        printBoard();
    }


    /**
     * Solve chessboard with greedy algorithm.
     * @param row Start row position.
     * @param col Start column position.
     * @return True if puzzle was solved. Otherwise false.
     */
    private static boolean tour(int row, int col){
        int currentRow = row;
        int currentCol = col;

        board[row][col] = 1;

        for (int move = 2; move <= (boardSize * boardSize); move++) {
            boolean[] possibleMoves = new boolean[KNIGHT_MOVES_COUNT];
            int[] countedMoves = new int[KNIGHT_MOVES_COUNT];

            // Compute available moves from current position
            for (int i = 0; i < KNIGHT_MOVES_COUNT; i++) {
                int nextRow = currentRow + jumps[i][0];
                int nextCol = currentCol + jumps[i][1];

                if (!inRangeAndEmpty(nextRow, nextCol) || isVisited(nextRow, nextCol)) {
                    continue;
                }

                possibleMoves[i] = true;
                countedMoves[i] = countMoves(nextRow, nextCol);
            }

            // Get index of move that have minimum of next possible moves
            int minimum = KNIGHT_MOVES_COUNT + 1;
            int index = -1;
            for (int i = 0; i < KNIGHT_MOVES_COUNT; i++) {
                if (possibleMoves[i]) {
                    if (index < 0 || minimum == 0 || countedMoves[i] < minimum && countedMoves[i] != 0) {
                        minimum = countedMoves[i];
                        index = i;
                    }
                }
            }

            // If this index is -1 - solution was not found . Set last visited position and return false.
            if (index < 0) {
                lastPosition = new Point(currentCol, currentRow);
                return false;
            }

            // Set new position for next iteration
            currentRow = currentRow + jumps[index][0];
            currentCol = currentCol + jumps[index][1];

            board[currentRow][currentCol] = move;
        }

        // Solution was found. Set last visited position and return true.
        lastPosition = new Point(currentCol, currentRow);
        return true;
    }

    /**
     * Count number of possible moves from tile
     * @param row Start row position.
     * @param col Start columnt position.
     * @return Number of possible moves from start position.
     */
    private static int countMoves(int row, int col) {
        int possibleMoves = 0;

        // Compute available moves for position
        for (int secondIteration = 0; secondIteration < KNIGHT_MOVES_COUNT; secondIteration++) {
            int nextRow = row + jumps[secondIteration][0];
            int nextCol = col + jumps[secondIteration][1];

            if (!inRangeAndEmpty(nextRow, nextCol) || isVisited(nextRow, nextCol)) {
                continue;
            }

            possibleMoves++;
        }

        return possibleMoves;
    }

    /**
     * Check if tile was already visited.
     * @param row
     * @param col
     * @return True if position [row, col] was visited. Otherwise false.
     */
    private static boolean isVisited(int row, int col) {
        return board[row][col] != 0;
    }


    private static boolean inRangeAndEmpty(int ty,int tx) { // check if coordinates are within the board
        return ty >= 0 && tx>=0 && ty< boardSize && tx< boardSize && board[ty][tx]==0; // and the square is empty
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

    /////////////////////////////////////////////////////LESS EFFICIENT SOLUTION//////////////////////////////////////////////////////

    /**
     * The recursive function that fills the board
     * @param y
     * @param x
     * @param counter
     */
    private static void fill1(int y,int x,int counter){
        if(board[y][x] == 0){
            board[y][x] = counter; //Fill the square
            if(counter == boardSize * boardSize){ //Was this the last empty square?
                printBoard();                 // Yes, print the board...
                System.exit(1);                  // ...and exit
            }

            for(int[] jump : jumps){//otherwise, try all the empty neighbours in turn
                int ty= y+jump[0];
                int tx = x+jump[1];
                if(inRangeAndEmpty(ty,tx)){
                    fill1(ty, tx, counter + 1);    // *** RECURSION! ***
                }
            }

            board[y][x] = 0; // if we get here, all the neighbours failed,
            // so reset the square and return
        }
    }


}

