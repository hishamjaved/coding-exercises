package com.design.patterns;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Behavioral Patterns
 */
public class Strategy {
    public static void main(String[] args) {
        CompressionContext ctx = new CompressionContext();
        //we could assume context is already set by preferences
        ctx.setCompressionStrategy(new RarCompressionStrategy());
        //get a list of files...
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add("file1.txt");
        fileList.add("file2.doc");
        ctx.createArchive(fileList);
    }
}

//com.design.patterns.Strategy Interface
interface CompressionStrategy {
    public void compressFiles(ArrayList<String> files);
}

class ZipCompressionStrategy implements CompressionStrategy {
    public void compressFiles(ArrayList<String> files) {
        //using ZIP approach
        Iterator<String> i = files.listIterator();
        while(i.hasNext()){
            System.out.println(i.next()+".zip");
        }
    }
}

class RarCompressionStrategy implements CompressionStrategy {
    public void compressFiles(ArrayList<String> files) {
        //using RAR approach
        Iterator<String> i = files.listIterator();
        while(i.hasNext()){
            System.out.println(i.next()+".rar");
        }
    }
}

class CompressionContext {
    private CompressionStrategy strategy;
    //this can be set at runtime by the application preferences
    public void setCompressionStrategy(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    //use the strategy
    public void createArchive(ArrayList<String> files) {
        strategy.compressFiles(files);
    }
}

