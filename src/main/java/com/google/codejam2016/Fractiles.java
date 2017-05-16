package com.google.codejam2016;

import java.io.*;
import java.util.*;

/**
 * See Problem Description Here
 * https://code.google.com/codejam/contest/6254486/dashboard#s=p3
 * Created by HISHAM on 4/5/2017.
 */
public class Fractiles {
    private static final String dirPath = System.getProperty("user.dir") + File.separator +
            "input"+File.separator+Fractiles.class.getSimpleName()+File.separator;
    private static final String input ="D-small-practice.in";
    private static final String output="D-small-practice.out";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(dirPath+input))));
        PrintStream printStream = new PrintStream(new FileOutputStream(dirPath+output));
        //System.setOut(printStream);
        int count = in.nextInt();
        for (int i = 1;i<=count;i++){
            System.out.printf("Case #: %d%n",i);
            findProbableTiles(in.nextInt(), in.nextInt(), in.nextInt());
        }
    }

    private static void findProbableTiles(int k, int c, int s){
        List<String> tiles = new ArrayList<>(combinations(k,c));
        for(String tile:tiles){
            System.out.println(tile);
        }
    }

    private static List<String> combinations(int k,int c){
        String feed="";
        String gs = "";
        String ls ="";
        for(int i=1;i<=k;i++){
            if(i%2==0){
                feed+="G";
            }
            else{
                feed+="L";
            }
            gs+="G";
            ls+="L";
        }

        Set<String> combs = new TreeSet<>();
        combs.add(gs);
        combs.addAll(permute(feed));


        if(k%2==0){
            combs.add(ls);
            return addComplexity(combs,c);
        }
        else{
            feed="";
            for(int i=1;i<=k;i++){
                if(i%2==0){
                    feed+="L";
                }
                else{
                    feed+="G";
                }
            }
            combs.addAll(permute(feed));
            combs.add(ls);

            return addComplexity(combs,c);
        }
    }

    private static List<String> addComplexity(Set<String> combs,int complexity){
        List<String> combList = new ArrayList<>(combs);
        List<String> combClone = new ArrayList<>(combs);
        for(int c=0;c<complexity-1;c++){
            for(int index=0;index<combClone.size();index++){
                String item = combList.get(index);
                StringBuilder sb = new StringBuilder("");
                for(int i=0;i<item.length();i++){
                    if(item.charAt(i)=='G'){
                        sb.append("GGG");
                    }
                    else{
                        sb.append(combClone.get(index));
                    }
                }
                combList.set(index, sb.toString());
            }
        }
        return combList;
    }

    private static Set<String> permute(String chars)
    {
        // Use sets to eliminate semantic duplicates (aab is still aab even if you switch the two 'a's)
        // Switch to HashSet for better performance
        Set<String> set = new TreeSet<String>();

        // Termination condition: only 1 permutation for a string of length 1
        if (chars.length() == 1)
        {
            set.add(chars);
        }
        else
        {
            // Give each character a chance to be the first in the permuted string
            for (int i=0; i<chars.length(); i++)
            {
                // Remove the character at index i from the string
                String pre = chars.substring(0, i);
                String post = chars.substring(i+1);
                String remaining = pre+post;

                // Recurse to find all the permutations of the remaining chars
                for (String permutation : permute(remaining))
                {
                    // Concatenate the first character with the permutations of the remaining chars
                    set.add(chars.charAt(i) + permutation);
                }
            }
        }
        return set;
    }
}
