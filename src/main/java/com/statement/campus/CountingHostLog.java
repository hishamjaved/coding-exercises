package com.statement.campus;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**

 */

public class CountingHostLog {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "statementcampus"
            + File.separator + CountingHostLog.class.getSimpleName() + File.separator;


    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String filename;
        filename = scan.nextLine()+".txt";
        HashMap<String,Integer> hosts = countHosts(dirPath+filename);
        writeOutput(dirPath+"records_"+filename,hosts);

    }




    public static HashMap<String,Integer> countHosts(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        HashMap<String,Integer> hosts = new HashMap<>();
        int endIndex;
        Integer hostCount;
        while((line = br.readLine())!=null){
            endIndex = line.indexOf(" ");
            if(endIndex>=0){
                line = line.substring(0,endIndex);
                hostCount = hosts.get(line);
                if(hostCount==null){
                    hosts.put(line,1);
                }
                else{
                    hosts.put(line,hostCount+1);
                }
            }
        }
        br.close();
        return hosts;
    }

    public static void writeOutput(String fileName,HashMap<String,Integer> hosts) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
        for(Map.Entry e:hosts.entrySet()){
            writer.write(e.getKey() + " " + e.getValue() + System.getProperty("line.separator"));
        }
        writer.close();
    }
}

