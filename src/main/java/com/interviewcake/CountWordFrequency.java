package com.interviewcake;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 You want to build a word cloud, an infographic where the size of a word corresponds to how often it appears in the body of text.
 To do this, you'll need data. Write code that takes a long string and builds its word cloud data in a hash map ? , where the keys are words and the values are the number of times the words occurred.

 Think about capitalized words. For example, look at these sentences:

 "After beating the eggs, Dana read the next step:"
 "Add milk and eggs, then add flour and sugar."
 What do we want to do with "After", "Dana", and "add"? In this example, your final hash map should include one "Add" or "add" with a value of 22. Make reasonable (not necessarily perfect) decisions about cases like "After" and "Dana".

 Assume the input will only contain words and standard punctuation.

 Hint: Don't use regex to split the words it is performance dent.
 */

public class CountWordFrequency {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + CountWordFrequency.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        String str = readSentence(in,in.nextInt());
        System.out.println(str+" >> "+countFrequency(str));
        str = readSentence(in, in.nextInt());
        System.out.println(str+" >> "+countFrequency(str));
        str = readSentence(in, in.nextInt());
        System.out.println(str+" >> "+countFrequency(str));
        str = readSentence(in,in.nextInt());
        System.out.println(str+" >> "+countFrequency(str));
    }

    private static String readSentence(Scanner in,int len){
        String str="";
        for(int i=0;i<len;i++){
            str+=" "+in.next();
        }
        return str.trim();
    }

    public static Map<String,Integer> countFrequency(String inputString) {
        Map<String, Integer> wordsToCounts = new HashMap<String, Integer>();
        // iterates over each character in the input string, splitting
        // words and passing them to addWordToHashMap()

        String currentWord = "";
        for (int i = 0; i < inputString.length(); i++) {
            char character = inputString.charAt(i);

            // if we reached the end of the string we check if the last
            // character is a letter and add the last word to our hash map
            if (i == inputString.length() - 1) {
                if (Character.isLetter(character)) currentWord += character;
                if (!currentWord.isEmpty()) addWordToHashMap(wordsToCounts,currentWord);

                // if we reach a space or emdash we know we're at the end of a word
                // so we add it to our hash map and reset our current word
            } else if (character == ' ' || character == '\u2014') {
                if (!currentWord.isEmpty()) addWordToHashMap(wordsToCounts,currentWord);
                currentWord = "";

                // we want to make sure we split on ellipses so if we get two periods in
                // a row we add the current word to our hash map and reset our current word
            } else if (character == '.') {
                if (i < inputString.length() - 1 && inputString.charAt(i + 1) == '.') {
                    if (!currentWord.isEmpty()) addWordToHashMap(wordsToCounts,currentWord);
                    currentWord = "";
                }

                // if the character is a letter or an apostrophe, we add it to our current word
            } else if (Character.isLetter(character) || character == '\'') {
                currentWord += character;

                // if the character is a hyphen, we want to check if it's surrounded by letters
                // if it is, we add it to our current word
            } else if (character == '-') {
                if (i > 0 && Character.isLetter(inputString.charAt(i-1))
                        && Character.isLetter(inputString.charAt(i+1))) {
                    currentWord += character;
                }
            }
        }
        return wordsToCounts;
    }

    private static void addWordToHashMap(Map<String,Integer> wordsToCounts,String word) {

        // if the word is already in the hash map we increment its count
        if (wordsToCounts.containsKey(word)) {
            wordsToCounts.put(word, wordsToCounts.get(word) + 1);

            // if a lowercase version is in the hash map, we know our input word must be uppercase
            // but we only include uppercase words if they're always uppercase
            // so we just increment the lowercase version's count
        } else if (wordsToCounts.containsKey(word.toLowerCase())) {
            int newCount = wordsToCounts.get(word.toLowerCase()) + 1;
            wordsToCounts.put(word.toLowerCase(), newCount);

            // if an uppercase version is in the hash map, we know our input word must be lowercase.
            // since we only include uppercase words if they're always uppercase, we add the
            // lowercase version and give it the uppercase version's count
        } else if (wordsToCounts.containsKey(capitalize(word))) {
            int newCount = wordsToCounts.get(capitalize(word)) + 1;
            wordsToCounts.put(word, newCount);
            wordsToCounts.remove(capitalize(word));

            // otherwise, the word is not in the hash map at all, lowercase or uppercase
            // so we add it to the hash map
        } else {
            wordsToCounts.put(word, 1);
        }
    }

    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}

