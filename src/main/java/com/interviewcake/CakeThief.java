package com.interviewcake;


import java.io.*;
import java.util.*;

/**
 You are a renowned thief who has recently switched from stealing precious metals to stealing cakes because of the
 insane profit margins. You end up hitting the jackpot, breaking into the world's largest privately owned stock of
 cakes—the vault of the Queen of England.
 While Queen Elizabeth has a limited number of types of cake, she has an unlimited supply of each type.

 Each type of cake has a weight and a value, stored in objects of a CakeType class:

 class CakeType {

 int weight;
 int value;

 public CakeType(int weight, int value) {
 this.weight = weight;
 this.value  = value;
 }

 }
 Java
 For example:

 // weighs 7 kilograms and has a value of 160 pounds
 new CakeType(7, 160);

 // weighs 3 kilograms and has a value of 90 pounds
 new CakeType(3, 90);

 You brought a duffel bag that can hold limited weight, and you want to make off with the most valuable haul possible.

 Write a function maxDuffelBagValue() that takes an array of cake type objects and a weight capacity, and returns the
 maximum monetary value the duffel bag can hold.

 For example:

 CakeType[] cakeTypes = new CakeType[]{
 new CakeType(7, 160),
 new CakeType(3, 90),
 new CakeType(2, 15),
 };

 int capacity = 20;

 maxDuffelBagValue(cakeTypes, capacity);
 // returns 555 (6 of the middle type of cake and 1 of the last type of cake)

 Weights and values may be any non-negative integer. Yes, it's weird to think about cakes that weigh nothing or duffel
 bags that can't hold anything. But we're not just super mastermind criminals—we're also meticulous about keeping our
 algorithms flexible and comprehensive.
 */




public class CakeThief {

    static class InfinityException extends RuntimeException {
        public InfinityException() {
            super("Max value is infinity!");
        }
    }

    class CakeType {

        int weight;
        int value;

        public CakeType(int weight, int value) {
            this.weight = weight;
            this.value  = value;
        }

    }

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + CakeThief.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        List<CakeType> list = new ArrayList<>();
        int capacity = in.nextInt();
        int size = in.nextInt();
        CakeThief ct = new CakeThief();
        while(size>0){
            list.add(ct.create(in.nextInt(), in.nextInt()));
            size--;
        }
        long time = System.currentTimeMillis();
        System.out.println(ct.maxDuffelBagValue(list.toArray(new CakeType[list.size()]), capacity));
        System.out.println(System.currentTimeMillis()-time);
        System.out.println("---------------------------------------------------");
        time = System.currentTimeMillis();
        System.out.println(ct.solution(list,capacity));
        System.out.println(System.currentTimeMillis()-time);
    }

    public CakeType create(int weight,int value){
        return new CakeType(weight,value);
    }

    /**
     * Performance efficient
     * Time Complexity: O(n*k)
     * Space Complexity: O(k)
     * @param cakeTypes
     * @param weightCapacity
     * @return
     */
    public long maxDuffelBagValue(CakeType[] cakeTypes, int weightCapacity) {

        // we make an array to hold the maximum possible value at every
        // duffel bag weight capacity from 0 to weightCapacity
        // starting each index with value 0
        long[] maxValuesAtCapacities = new long[weightCapacity + 1];

        for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {

            // set a variable to hold the max monetary value so far for currentCapacity
            long currentMaxValue = 0;

            for (CakeType cakeType : cakeTypes) {

                // if a cake weighs 0 and has a positive value the value of our duffel bag is infinite!
                if (cakeType.weight == 0 && cakeType.value != 0) {
                    throw new InfinityException();
                }

                // if the current cake weighs as much or less than the current weight capacity
                // it's possible taking the cake would give get a better value
                if (cakeType.weight <= currentCapacity) {

                    // so we check: should we use the cake or not?
                    // if we use the cake, the most kilograms we can include in addition to the cake
                    // we're adding is the current capacity minus the cake's weight. we find the max
                    // value at that integer capacity in our array maxValuesAtCapacities
                    long maxValueUsingCake = cakeType.value + maxValuesAtCapacities[currentCapacity - cakeType.weight];

                    // now we see if it's worth taking the cake. how does the
                    // value with the cake compare to the currentMaxValue?
                    currentMaxValue = Math.max(maxValueUsingCake, currentMaxValue);
                }
            }

            // add each capacity's max value to our array so we can use them
            // when calculating all the remaining capacities
            maxValuesAtCapacities[currentCapacity] = currentMaxValue;
        }

        System.out.println(Arrays.toString(maxValuesAtCapacities));

        return maxValuesAtCapacities[weightCapacity];
    }

    /**
     * Less efficient but acceptable performance
     * Time Complexity: O(n log n)
     * Space Complexity: No extra space used
     * @param cakes
     * @param originalCapacity
     * @return
     */
    public int solution(List<CakeType> cakes,int originalCapacity) {
        int total=0;
        int capacity = originalCapacity;
        Collections.sort(cakes, new Comparator<CakeType>() {
            @Override
            public int compare(CakeType o1, CakeType o2) {
                return (int) ((o2.value / o2.weight) - (o1.value / o1.weight));
            }
        });

        for(CakeType cake:cakes){

            // if a cake weighs 0 and has a positive value the value of our duffel bag is infinite!
            if (cake.weight == 0 && cake.value != 0) {
                throw new InfinityException();
            }

            if(capacity>0 && cake.weight>capacity){
                continue;
            }

            if(cake.weight>0){
                total+= Math.floor(capacity/cake.weight)*cake.value;
                capacity = capacity%cake.weight;
            }

            if(capacity==0){
                break;
            }
        }

        for(CakeType cake:cakes){
            if(cake.weight>0 && originalCapacity%cake.weight==0){
               total = Math.max(total,(int)(Math.floor(originalCapacity/cake.weight)*cake.value));
            }
        }

        return total;
    }
}

