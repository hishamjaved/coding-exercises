package com.interview.bit.math.arraymath;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MinStepsInInfiniteGrid {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "interviewbit"
            + File.separator + MinStepsInInfiniteGrid.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            x.add(in.nextInt());
        }

        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            y.add(in.nextInt());
        }

        System.out.println(coverPoints(x, y));
    }

    private static int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
        int ans = 0;
        for (int i = 1; i < X.size(); i++) {
            if ((Math.abs(X.get(i) - X.get(i - 1)) < Math.abs(Y.get(i) - Y.get(i - 1)))) {
                ans += Math.abs(Y.get(i) - Y.get(i - 1));
            } else {
                ans += Math.abs(X.get(i) - X.get(i - 1));
            }
        }
        return ans;
    }
}

