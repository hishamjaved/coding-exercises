package com.project.euler;

/***
 * Project Euler: Poker hands, Problem 54
 * http://projecteuler.net/problem=54
 * Each line of the file "poker.txt" contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards.
 * You can assume that all hands are valid (no invalid characters or repeated cards),
 * each player's hand is in no specific order, and in each hand there is a clear winner.
 * The results are written to file "winners.txt"
 ***/

import java.util.*;
import java.io.*;

public class Poker {






    String reason = ""; //updated by each function to write the reason for when a hand wins


    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "projecteuler"
            + File.separator + Poker.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";
    private static final String output = dirPath + "Poker.out";

    public static void main(String[] args) {

        String hand="";

        int royal_flush=0, straight_flush=0, four_kind=0, full_house=0, flush=0, straight=0;
        int two_pairs = 0, one_pair, high_card=0, three_kind = 0;

        Poker game = new Poker();

        try{

            Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
            PrintStream ps = new PrintStream(new FileOutputStream(output));
            System.setOut(ps);
            System.out.printf("Player 1\t\tPlayer 2\t\tWinner\t\t\tReason\n");
            //Read File Line By Line
            while (in.hasNextLine()){
                hand = in.nextLine();
                game.reason="";

                System.out.printf(hand.substring(0, 14));
                System.out.printf("\t\t");
                System.out.printf(hand.substring(15, 29));
                System.out.printf("\t\t");

                royal_flush = game.royal_flush(hand);

                //no royal flush
                if(royal_flush == 0){
                    straight_flush = game.straight_flush(hand);

                    //no straight flush
                    if(straight_flush == 0){

                        four_kind = game.four_kind(hand);

                        //no four kind
                        if (four_kind == 0){
                            full_house = game.full_house(hand);

                            //no full house
                            if(full_house == 0){
                                flush = game.flush(hand);

                                //no flush
                                if(flush == 0){
                                    straight = game.straight(hand);

                                    if(straight == 0){
                                        //check if 3 kind
                                        three_kind = game.n_kind(hand, 4, 0);

                                        if(three_kind == 0){
                                            two_pairs = game.n_kind(hand, 3, 1);

                                            if(two_pairs == 0){
                                                one_pair = game.n_kind(hand, 3, 0);

                                                if(one_pair == 0){
                                                    high_card = game.high_card(hand);
                                                    System.out.printf(game.final_result(high_card, hand));
                                                }
                                                else{
                                                    System.out.printf(game.final_result(one_pair, hand));
                                                }
                                            }
                                            else{
                                                System.out.printf(game.final_result(two_pairs, hand));
                                            }

                                        }
                                        else{
                                            System.out.printf(game.final_result(three_kind, hand));
                                        }

                                    }
                                    else{
                                        System.out.printf(game.final_result(straight, hand));
                                    }
                                }
                                else{
                                    System.out.printf(game.final_result(flush, hand));
                                }
                            }
                            else{
                                System.out.printf(game.final_result(full_house, hand));
                            }
                        }
                        else{
                            System.out.printf(game.final_result(four_kind, hand));
                        }
                    }
                    else{//there is a straight flush
                        System.out.printf(game.final_result(straight_flush, hand));
                    }

                }else{//there is a royal flush
                    System.out.printf(game.final_result(royal_flush, hand));
                }

                System.out.printf("\t\t");
                System.out.printf((game.reason).trim());
                System.out.printf("\n");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


    public Poker(){
    }

    //Royal Flush: Ten 'T', Jack, Queen, King, Ace, in same suit
    public int royal_flush(String hands){

        String h1 = hands.substring(0,14);
        String h2 = hands.substring(15,29);

        int h1_royal = 0, h2_royal = 0;

        //check if the hands contains the following card values
        if(h1.contains("T") && h1.contains("J") && h1.contains("K") && h1.contains("Q") && h1.contains("A"))
            h1_royal = 1;

        if(h2.contains("T") && h2.contains("J") && h2.contains("K") && h2.contains("Q") && h2.contains("A"))
            h2_royal = 1;

        //both players have royal flush
        if(h1_royal == 1 && h2_royal == 1)
            return 3; //tie
        else if (h2_royal == 1){
            reason = "P2 has Royal Flush.";
            return 2; //player 2 wins
        }
        else if (h1_royal == 1){
            reason = "P1 has Royal Flush.";
            return 1; //player 1 wins
        }
        else
            return 0; //no one has royal flush
    }

    //straight flush All cards are consecutive values of same suit.
    public int straight_flush(String hands){

        int flush = flush(hands);

        if(flush == 0)//no one has flush
            return 0; //no one has straigh flush
        else{//check for straight
            int straight = straight(hands);

            //both players have straight flush
            if(straight == 3)
                return 3; //tie
            else if (straight == 2){
                reason = "P2 has Royal Straight Flush.";
                return 2; //player 2 has straight flush
            }else if (straight == 1){
                reason = "P1 has Royal Straight Flush.";
                return 1; //player 1 has straight flush
            }else
                return 0; //no one has straight flush
        }
    }

    //Four of a Kind: Four cards of the same value.
    public int four_kind(String hands){
        //split based on characters, if array return is 5 then you have 4 of a kind
        String[] card_values = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        String h1 = hands.substring(0,14);
        String h2 = hands.substring(15,29);
        int h1_four = 0, h2_four = 0, i=0;
        String h1_kind="", h2_kind="";

        for(i = 0; i< card_values.length; i++){

            if((h1.split(card_values[i])).length == 5){
                h1_four = 1;
                h1_kind = card_values[i];
                break;
            }
        }

        for(i = 0; i< card_values.length; i++){

            if((h2.split(card_values[i])).length == 5){
                h2_four = 1;
                h2_kind = card_values[i];
                break;
            }
        }
        //both players have four kind
        if(h1_four == 1 && h2_four == 1)
            return 3; //tie
        else if (h2_four == 1){
            reason = "P2 has Four Kind of "+h2_kind;
            return 2; //player 2 has four kind
        }else if (h1_four == 1){
            reason = "P1 has Four Kind of "+h1_kind;
            return 1; //player 1 has four kind
        }else
            return 0; //no one has four kind

    }
    //Full House: Three of a kind and a pair.
    public int full_house(String hands){
        //split based on characters, if array return is 4 then you have 3 of a kind
        String[] card_values = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        String h1 = hands.substring(0,14);
        String h2 = hands.substring(15,29);
        int h1_three = 0, h2_three = 0, i=0, h1_pair=0, h2_pair=0;
        String h1_kind_value="",h2_kind_value=""; //to keep the value of the card pair

        for(i = 0; i< card_values.length; i++){

            if((h1.split(card_values[i])).length == 4){
                h1_three = 1;
                h1_kind_value = card_values[i];
                break;
            }
        }

        for(i = 0; i< card_values.length; i++){

            if((h2.split(card_values[i])).length == 4){
                h2_three = 1;
                h2_kind_value = card_values[i];
                break;
            }
        }
        //check for a pair in both hands if they have 3 of the same kind
        if(h1_three == 1){
            for(i = 0; i< card_values.length; i++){

                if((h1.split(card_values[i])).length == 3){
                    h1_pair = 1;
                    break;
                }
            }
        }
        if(h2_three == 1){

            for(i = 0; i< card_values.length; i++){

                if((h2.split(card_values[i])).length == 3){
                    h2_pair = 1;
                    break;
                }
            }
        }

        //both players have full house.
        if(h1_pair == 1 && h2_pair == 1){
            //check which has the highest 3 kind value.
            if(Integer.parseInt(h1_kind_value) < Integer.parseInt(h2_kind_value)){
                reason = "P1 has a Full House. With three "+h2_kind_value+".";
                return 2; //player 2 has full house
            }
            else if(Integer.parseInt(h1_kind_value) > Integer.parseInt(h2_kind_value)){
                reason = "P1 has a Full House. With three "+h1_kind_value+".";
                return 1; //player 1 has full house
            }
            return 3; //tie
        }else if (h2_pair == 1){
            reason ="P2 has a Full House.";
            return 2; //player 2 has full house
        }else if (h1_pair == 1){
            reason ="P1 has a Full House.";
            return 1; //player 1 has full house
        }else
            return 0; //no one has full house
    }

    //Flush: All cards are of same suit.
    public int flush(String hands){

        String h1 = hands.substring(0,14);
        String h2 = hands.substring(15,29);

        int h1_equal = 1, h2_equal = 1;

        //use == if comparing primitive types
        //check the suit characters in the string
        for(int i = 1; i<13;i=i+3){
            //check next couple of card suit as long as the one before equals to the next.
            if(h1_equal==1 && h1.charAt(i) == h1.charAt(i+3))
                h1_equal = 1;
            else
                h1_equal = 0;
            if(h2_equal==1 && h2.charAt(i) == h2.charAt(i+3))
                h2_equal = 1;
            else
                h2_equal = 0;
        }

        //both players have flush
        if(h1_equal == 1 && h2_equal == 1)
            return 3; //tie
        else if (h2_equal == 1){
            reason = "P2 has a Flush. All cards of same suit";
            return 2; //player 2 has flush
        }else if (h1_equal == 1){
            reason = "P1 has a Flush. All cards of same suit";
            return 1; //player 1 has flush
        }else
            return 0; //no one has flush
    }

    //Straight: All cards are consecutive values.
    public int straight(String hands){

        int[] h = new int[10];
        int m = 0;

        //convert cards to their decimal values
        for(int i = 0; i< hands.length(); i+=3)
            h[m++] = card_value(hands.charAt(i));

        //seperate players hands
        int[] h1 = Arrays.copyOfRange(h, 0, 5);
        int[] h2 = Arrays.copyOfRange(h, 5, 10);

        //sort arrays
        Arrays.sort(h1);
        Arrays.sort(h2);

        //assume sorted
        int h1_ordered = 1, h2_ordered = 1;

        for(int i = 1; i<h1.length -1 ;i++){
            if(h1_ordered==1 && h1[i] == h1[i+1]-1 )
                h1_ordered = 1;
            else
                h1_ordered = 0;
            if(h2_ordered==1 && h2[i] == h2[i+1]-1)
                h2_ordered = 1;
            else
                h2_ordered = 0;
        }

        //both players have straight
        if(h1_ordered == 1 && h2_ordered == 1)
            return 3; //tie
        else if (h2_ordered == 1){
            reason = "P2 has a Straight. All cards are consecutive values.";
            return 2; //player 2 has straight
        }else if (h1_ordered == 1){
            reason = "P1 has a Straight. All cards are consecutive values.";
            return 1; //player 1 has straight
        }else
            return 0; //no one has straight
    }


    public int n_kind(String hands, int kind, int pair){
        //split based on characters, if array return is 4 then you have 3 of a kind
        String[] card_values = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        String h1 = hands.substring(0,14);
        String h2 = hands.substring(15,29);
        int h1_kind = 0, h2_kind = 0, i=0;
        int h1_pair = 0, h2_pair = 0;

        int h1_value = 0, h2_value = 0;

        for(i = 0; i< card_values.length; i++){
            if((h1.split(card_values[i])).length == kind){
                if(h1_pair == pair){
                    h1_kind = 1;

                    if(card_value((card_values[i]).charAt(0)) > h1_value)
                        h1_value = (card_values[i]).charAt(0);

                    break;
                }
                else
                    h1_pair++;

            }
        }

        for(i = 0; i< card_values.length; i++){

            if((h2.split(card_values[i])).length == kind){
                if(h2_pair == pair){
                    h2_kind = 1;

                    if(card_value((card_values[i]).charAt(0)) > h2_value)
                        h2_value = (card_values[i]).charAt(0);

                    break;
                }
                else
                    h2_pair++;
            }
        }

        //both players have n kind
        if(h1_kind == 1 && h2_kind == 1){

            if(h1_value == h2_value){
                reason = "P1 and P2 have "+(kind-1)+" kinds & "+(pair+1)+" pairs.";
                return 3; //tie
            }
            //check the kind with the highest value.
            else if(h1_value < h2_value){
                reason = "P2 has "+(kind-1)+" kinds & "+(pair+1)+" pairs.";
                return 2; //player 2 has n kind
            }
            else if(h1_value > h2_value){
                reason = "P1 has "+(kind-1)+" kinds & "+(pair+1)+" pairs.";
                return 1; //player 1 has n kind
            }
        }else if (h2_kind == 1){
            reason = "P2 has "+(kind-1)+" kinds & "+(pair+1)+" pairs.";
            return 2; //player 2 has n kind
        }else if (h1_kind == 1){
            reason = "P1 has "+(kind-1)+" kinds & "+(pair+1)+" pairs.";
            return 1; //player 1 has n kind
        }else
            return 0; //no one has n kind

        return 0;
    }

    // High Card: Highest value card.
    public int high_card(String hands){
        int[] h = new int[10];
        int m = 0;

        for(int i = 0; i< hands.length(); i+=3)
            h[m++] = card_value(hands.charAt(i));

        int[] h1 = Arrays.copyOfRange(h, 0, 5);
        int[] h2 = Arrays.copyOfRange(h, 5, 10);

        //sort arrays
        Arrays.sort(h1);
        Arrays.sort(h2);

        int h1_high = 0, h2_high=0;

        //compare card values
        for(int i= h1.length-1; i>=0 ;i--){
            if(h1[i] > h2[i]){
                h1_high = 1;
                break;
            }else if(h2[i] > h1[i]){
                h2_high = 1;
                break;
            }
        }

        if(h1_high == 1 && h2_high == 1)
            return 3; //tie
        else if(h2_high == 1){
            reason = reason+ " P2 has the highest value card.";
            return 2; //player 2 wins
        }else if(h1_high == 1){
            reason = reason+ " P1 has the highest value card.";
            return 1; //player 1 wins
        }else
            return 0; //tie

    }
    //print out the final winner
    public String final_result (int win, String hand) {
        int highest_hand=0;

        if(win == 1)
            return "P1 wins!";
        else if(win == 2)
            return "P2 wins!";
        else if(win == 3){
            highest_hand = high_card(hand);

            if(highest_hand == 1)
                return "P1 wins!";
            else if(highest_hand == 2)
                return "P2 wins!";
            else if(highest_hand == 3 || highest_hand == 0)
                return "Tie!!!";

        }
        return "Error!";

    }

    public int card_value(char c){
        int v = 0;
        if(c == 'T')
            v = 10;
        else if (c == 'J')
            v = 11;
        else if (c == 'Q')
            v = 12;
        else if (c == 'K')
            v = 13;
        else if (c == 'A')
            v = 14;
        else
            v = Character.getNumericValue(c);

        return v;

    }


}