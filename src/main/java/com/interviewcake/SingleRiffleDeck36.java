package com.interviewcake;


import java.io.*;
import java.util.*;

/**
 * I figured out how to get rich: online poker.
 I suspect the online poker game I'm playing shuffles cards by doing a single " riffle ? ."

 To prove this, let's write a function to tell us if a full deck of cards shuffledDeck is a single riffle of two other
 halves half1 and half2.

 We'll represent a stack of cards as an array of integers in the range 1..521..52 (since there are 5252 distinct cards
 in a deck).

 Why do I care? A single riffle is not a completely random shuffle. If I'm right, I can make more informed bets and get
 rich and finally prove to my ex that I am not a "loser with an unhealthy cake obsession" (even though it's too late now
 because she let me go and she's never getting me back).

 Gotchas
 Watch out for bugs because your index is outside an array! Will your function ever try to grab the 0th item from an
 empty array, or the nth item from an array with n elements (where the last index would be n-1n?1)?

 We can do this in O(n)O(n) time and O(1)O(1) additional space.

 Did you come up with a recursive solution? Keep in mind that you may be incurring a hidden space cost (probably O(n))
 in the call stack ? ! You can avoid this using an iterative approach.

 Breakdown
 How can we re-phrase this problem in terms of smaller subproblems?

 Breaking the problem into smaller subproblems will clearly involve reducing the size of at least one of our stacks of
 cards. So to start, let's try taking the first card out of shuffledDeck.

 What should be true of this card if shuffledDeck is a riffle of half1 and half2?

 If shuffledDeck is a riffle of half1 and half2, then the first card from shuffledDeck should be either the same as the
 first card from half1 or the same as the first card from half2.

 If we're looking at our decks face-up, from above (so we only see the top card), this could be a single riffle:

 Looking at three face up stacks of cards, the top card of the shuffled deck is 10, the top card of half 1 is 7,
 and the top card of half 2 is 10.

 While this could not:
 Looking at three face up stacks of cards, the top card of the shuffled deck is 32, the top card of half 1 is 7, and the
 top card of half 2 is 10.

 Now that we know the first card checks out, how do we get to our subproblem?

 Let's "throw out" the top card from shuffledDeck as well as the card it matched with from the top of half1 or half2.
 Those cards are now "accounted for."

 Now we're left with a smaller version of the original problem, which we can solve using the same approach! So we keep
 doing this over and over until we exhaust shuffledDeck. If we get to the end and each card "checks out," we return true.

 How do we implement this in code?

 Now that we have a problem that's the same as the original problem except smaller, our first thought might be to use
 recursion. All we need is a base case ? . What's our base case?

 We stop when we run out of cards in our shuffledDeck. So that's our base case: when we've checked all cards in
 shuffledDeck, we return true because we know all of the cards have been "accounted for."

 */

public class SingleRiffleDeck36 {

    public static void main(String[] args) throws FileNotFoundException {

        //Prepare shuffled deck
        ArrayList<Integer> shuffledDeck = new ArrayList<>();
        ArrayList<Integer> half1 = new ArrayList<>();
        ArrayList<Integer> half2 = new ArrayList<>();
        int i=0;
        Integer card;
        Random rand = new Random();
        while(shuffledDeck.size()<52){
            card = rand.nextInt(53);
            if(card<1 || card>52 || shuffledDeck.contains(card)){
                continue;
            }
            shuffledDeck.add(card);
            if(shuffledDeck.size()%2==0){
                half2.add(card);
            }
            else{
                half1.add(card);
            }
        }

        System.out.println("Is it single riffle deck? " + isSingleRiffleDeck(shuffledDeck,half1,half2));

        //Now we changed the order of first half and check the riffle
        Integer tmp = half1.get(12);
        half1.set(12,half2.get(12));
        System.out.println("Is it single riffle deck? " + isSingleRiffleDeck(shuffledDeck, half1, half2));

        //Now we reset with old value of first half
        half1.set(12,tmp);
        System.out.println("Is it single riffle deck? " + isSingleRiffleDeck(shuffledDeck,half1,half2));
    }

    public static boolean isSingleRiffleDeck(ArrayList<Integer> shuffledDeck,ArrayList<Integer> half1,ArrayList<Integer> half2){
        int half1Index = 0;
        int half2Index = 0;
        for(Integer card:shuffledDeck){
            if(half1Index < half1.size()
                    && card.equals(half1.get(half1Index))){
                half1Index++;
            }
            else if(half2Index < half2.size()
                && card.equals(half2.get(half2Index))){
                half2Index++;
            }
            else{
                return false;
            }
        }
        return true;
    }
}

