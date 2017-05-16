package com.crossover.trial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class SuperStack {

    static void superStack(String[] operations) {
        ArrayList<Long> stack=new ArrayList<Long>();
        long k;
        int e;
        for(int c=0;c<operations.length;c++){
            String[] tokens = operations[c].split(" ");
            if(tokens.length>0){
                if(tokens[0].equalsIgnoreCase("push") && tokens.length==2 && isNumeric(tokens[1])){
                    push(stack, Integer.valueOf(tokens[1]));
                }
                else if(tokens[0].equalsIgnoreCase("pop")){
                    pop(stack);
                }
                else if(tokens[0].equalsIgnoreCase("inc") && tokens.length==3
                        && isNumeric(tokens[1]) && isNumeric(tokens[2])){
                    e = Integer.parseInt(tokens[1]);
                    k = Long.parseLong(tokens[2]);
                    if(e>=1 && e <= stack.size()){
                        inc(stack, e, k);

                    }
                }
            }
        }
    }

    private static void push(ArrayList<Long> stack,long k) {
        stack.add(stack.size(), k);
        System.out.println(k);
    }
    private static void pop(ArrayList<Long> stack) {
        if(stack.isEmpty()){
            System.out.println("EMPTY");
        }
        else{
            stack.remove(stack.size() - 1);
            if(stack.isEmpty()){
                System.out.println("EMPTY");
            }
            else{
                System.out.println(stack.get(stack.size() - 1));
            }
        }
    }
    private static void inc(ArrayList<Long> stack,int e,long k) {
        int index=0;

        for (final ListIterator<Long> i = stack.listIterator(); i.hasNext();) {
            if(index>=stack.size() - e){
                long element = i.next()+k;
                i.set(element);
            }
            index++;
        }
        System.out.println(stack.get(stack.size() - 1));
    }

    private static boolean isNumeric(String num){
        try{
            long a = Long.parseLong(num);
            return true;
        }
        catch(NumberFormatException ex){
            return false;
        }


    }


    /**
     * DO NOT MODIFY THIS METHOD!
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int _operations_size = Integer.parseInt(in.nextLine().trim());
        String[] _operations = new String[_operations_size];
        String _operations_item;
        for(int _operations_i = 0; _operations_i < _operations_size; _operations_i++) {
            try {
                _operations_item = in.nextLine();
            } catch (Exception e) {
                _operations_item = null;
            }
            _operations[_operations_i] = _operations_item;
        }

        superStack(_operations);
    }
}
