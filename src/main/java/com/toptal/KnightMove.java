package com.toptal;


import java.io.*;
import java.util.*;

/**
 * Not recommended
 */

public class KnightMove {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "toptal"
            + File.separator + KnightMove.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    private final int ROW = 8;
    private final int COL = 8;
    private final char FROM = 'a';
    private final char TO = 'h';

    private final int fx[] = {1, 1, -1, -1, 2, 2, -2, -2};// king movement to row
    private final int fy[] = {2, -2, 2, -2, 1, -1, 1, -1};// king movement to col following row

    int sx, sy; // source coordination
    int dx, dy; // destination coordination

    public static void main(String args[]) throws IOException{
        KnightMove obj = new KnightMove();
        obj.beginProcess();
    }

    void beginProcess() throws IOException{
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        Map<Character, Integer> col_map = new HashMap<Character, Integer>();

        int i=0;
        for(char ch=FROM; ch<=TO; ch++){
            col_map.put(new Character(ch), new Integer(i));
            i++;
        }

        while(in.hasNext()){
            StringTokenizer token = new StringTokenizer(in.nextLine());

            if(token.countTokens() == 2){
                String source = token.nextToken();
                String destination = token.nextToken();

                if(source.length() == 2 && destination.length() == 2){

                    try{
                        sx = Integer.parseInt(Character.toString(source.charAt(1)));
                        char c1 = source.charAt(0);

                        dx = Integer.parseInt( Character.toString(destination.charAt(1)));
                        char c2 = destination.charAt(0);

                        if(c1>=FROM && c1<=TO && c2>=FROM && c1<=TO && sx>0 && sx<=ROW && dx>0 && dx<=COL){
                            sx = sx - 1;
                            sy = (int) col_map.get(c1);

                            dx = dx - 1;
                            dy = (int) col_map.get(c2);

                            ChessBoard board = new ChessBoard(ROW, COL);

                            System.out.println("To get from "+source+" to "+destination+" takes "+bfs(board)+" knight moves.");
                        }
                    }catch(NumberFormatException ne){
                        // number format problem
                    }

                }// invalid movement string
            }// invalid input
        }
    }

    int bfs(ChessBoard board){
        int distance[][] = new int[ROW][COL];
        distance[sx][sy] = 0;
        board.setCellValue(sx, sy, 1);

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair<Integer, Integer>(sx, sy));

        while(!queue.isEmpty()){
            Pair top = queue.poll();

            for(int i=0; i<ROW; i++){
                int tx = ((int) top.getFirst()) + fx[i];
                int ty = ((int) top.getLast()) + fy[i];

                if(tx>=0 && tx<ROW && ty>=0 && ty<COL && board.getCellValue(tx, ty)==0){
                    distance[tx][ty] = distance[(int)top.getFirst()][(int)top.getLast()] + 1;
                    board.setCellValue(tx, ty, 1);
                    queue.add(new Pair<Integer, Integer>(tx, ty));
                }else{
                    if(tx == dx && ty == dy){
                        break;
                    }
                }
            }
        }

        return distance[dx][dy];
    }
}

class ChessBoard{
    private int Grid[][];

    public ChessBoard(int row, int col){
        Grid = new int[row][col];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                Grid[i][j] = 0;
            }
        }
    }

    void setCellValue(int x, int y, int v){
        Grid[x][y] = v;
    }

    int getCellValue(int x, int y){
        return Grid[x][y];
    }
}

class Pair<F, L>{
    private final F first;
    private final L last;

    public Pair(F f, L l){
        first = f;
        last = l;
    }

    F getFirst(){
        return first;
    }

    L getLast(){
        return last;
    }
}

