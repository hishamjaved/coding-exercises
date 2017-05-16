package com.interview.bit.math.arraymath;


import java.io.*;
import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class MergeIntervals {
    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "interviewbit"
            + File.separator + MergeIntervals.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));

        ArrayList<Interval> intervals = new ArrayList<Interval>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            intervals.add(new Interval(in.nextInt(),in.nextInt()));
        }
        Interval interval = new Interval(in.nextInt(),in.nextInt());
        insert(intervals,interval).stream().forEach(item -> System.out.println("(" + item.start + "," + item.end + ")"));
    }

    public static ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        ArrayList<Integer> linear = new ArrayList<>();
        for(Interval item : intervals){
            linear.add(item.start);
            linear.add(item.end);
        }
        linear.add(newInterval.start);
        linear.add(newInterval.end);
        Collections.sort(linear);

        int start = linear.indexOf(newInterval.start);
        int end = linear.indexOf(newInterval.end);

        Set<Integer> removeItems = new TreeSet<>();
        for(int i=start;i<end;i++){
            if(linear.get(i+1) - linear.get(i)==1){
                removeItems.add(linear.get(i));
                removeItems.add(linear.get(i+1));
            }
        }

        for(Integer item:removeItems){
            linear.remove(item);
        }


        for(int i=0;i<linear.size();i=i+2){
            result.add(new Interval(linear.get(i),linear.get(i+1)));
        }

        return result;
    }
}

