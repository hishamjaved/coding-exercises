package com.interviewcake;


import java.io.*;
import java.util.*;

/**
 Given an array of integers, find the highest product you can get from three of the integers.
 The input arrayOfInts will always have at least three integers.
 */

class Meeting {

    int startTime;
    int endTime;

    public Meeting(int startTime, int endTime) {
        // number of 30 min blocks past 9:00 am
        this.startTime = startTime;
        this.endTime   = endTime;
    }

    public String toString() {
        return String.format("(%d, %d)", startTime, endTime);
    }
}

public class MergeMeetingSlots {



    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + MergeMeetingSlots.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        ArrayList<Meeting> meetings = new ArrayList<>();
        for(int i=1;i<=n;i++){
            meetings.add(new Meeting(in.nextInt(),in.nextInt()));
        }

        for(Meeting m: solution(meetings)){
            System.out.println(m.startTime+","+m.endTime);
        }

    }


    /**
     * Time Complexity O(n lg n) because we got unsorted list of meeting otherwise it could be O(n)
     * it is better than O(n^2)
     * @param meetings
     * @return
     */
    public static ArrayList<Meeting> solution(ArrayList<Meeting> meetings) {
        if(meetings.size()<1){
            throw new IllegalArgumentException("Can't merge empty meeting list");
        }
        else if(meetings.size()==1){
            return meetings;
        }

        Collections.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.startTime - o2.startTime;
            }
        });

        ArrayList<Meeting> mergedList = new ArrayList<>();
        mergedList.add(meetings.get(0));

        for(Meeting currentMeeting: meetings){
            Meeting m = mergedList.get(mergedList.size()-1);
            if(m.endTime>=currentMeeting.startTime && m.endTime<=currentMeeting.endTime){
                m.endTime = currentMeeting.endTime;
            }
            else{
                mergedList.add(currentMeeting);
            }
        }
        return mergedList;
    }


}

