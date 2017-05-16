package com.google.codejam2016;

import java.io.*;
import java.util.Scanner;

/**
 * See Problem Description Here
 * https://code.google.com/codejam/contest/6254486/dashboard#s=p1
 * Created by HISHAM on 4/4/2017.
 */
public class Pancakes {
    private static final String dirPath = System.getProperty("user.dir") + File.separator +
            "input" + File.separator + Pancakes.class.getSimpleName() + File.separator;
    private static final String input = "B-large-practice.in";
    private static final String output = "B-large-practice.out";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(dirPath+input))));
        PrintStream printStream = new PrintStream(new FileOutputStream(dirPath+output));
        System.setOut(printStream);

        int count = in.nextInt();
        for(int i=1; i<=count; i++){
            System.out.printf("Case #%d: %d%n", i, maneuver(new StringBuilder(in.next())));
        }
    }

    /**
     * ----+---+
     * ++++-+++-
     * -----+++-
     * ++++++++-
     * ---------
     * +++++++++
     *
     * ----+---+
     * +++++---+
     * --------+
     * +++++++++
     *
     * -+-+-+-+
     * ++-+-+-+ flipTop(1) -+ > +-
     * ---+-+-+ flipTop(2) ++ > --
     * ++++-+-+ flipTop(3)  --- > +++
     * -----+-+ flipTop(4)  ++++ > ----
     * ++++++-+ flipTop(5)  ----- > +++++
     * -------+ flipTop(6)  ++++++ > ------
     * ++++++++ flipTop(7)  ------- > +++++++
     *
     * @param panStack
     * @return
     */
    private static int maneuver(StringBuilder panStack){
        //Create a string of same length which has all positives
        StringBuilder finalState = new StringBuilder("");
        for(int i=0;i<panStack.length();i++){
            finalState.append("+");
        }

        if(panStack.equals(finalState) || panStack.length()==0){
            return 0;
        }
        else if(panStack.length()==1 && panStack.toString().equals("-")){
            return 1;
        }
        else{
            int i=0;
            int top;
            while(!panStack.toString().equals(finalState.toString())){
                top=0;
                while(panStack.length()>top+1
                        && panStack.charAt(top)==panStack.charAt(top+1)){
                    top++;
                }
                flipTop(panStack,top+1);
                i++;
            }
            return i;
        }
    }

    private static void flipTop(StringBuilder panStack, int top){
        for(int i=0;i<top;i++){
           if(panStack.charAt(i)=='+'){
               panStack.setCharAt(i,'-');
           }
           else{
               panStack.setCharAt(i,'+');
           }
        }
    }
}
