package com.statement.campus;


import java.io.*;
import java.util.Scanner;

/**
 *We encode a string, s, of space-separated words by performing the following sequence of actions:

 Replace each character with its ASCII value representation.
 Reverse the string.
 For example, the table below shows the conversion from the string Hacker Rank to the ASCII string 729799107101114328297110107:

 Character	H	a	c	k	e	r	 	R	a	n	k
 ASCII Value	72	97	99	107	101	114	32	82	97	110	107
 We then reverse the ASCII string to get the encoded string 701011792823411101701997927.

 For reference, the characters in s correspond to the following ASCII values:

 The value range for A through Z is 65 through 90.
 The value range for a through z is 97 through 122.
 The value of the whitespace character (i.e., ) is 32.
 Complete the decode function in the editor below. It has one parameter:

 Name	Type	Description
 encoded	string	A reversed ASCII string denoting an encoded string s.
 The function must decode the encoded string and return the original unencoded string (i.e., s).

 Input Format
 A string, encoded, denoting the encoded string s.

 Constraints
 1 ? |s| ? 105
 s contains upper and lower case English alphabetic letters and spaces (i.e., [a-zA-Z ]) only.
 Output Format
 Return a string denoting the decoded string (i.e., s).

 Sample Input 0
 23511011501782351112179911801562340161171141148
 Sample Output 0
 Truth Always Wins
 Explanation 0
 We reverse encoded to get 84114117116104326510811997121115328710511011532. We then replace each ASCII value with its corresponding character:

 ASCII	84	114	117	116	104	32	65	108	119	97	121	115	32	87	105	110	115	32
 Character	T	r	u	t	h	 	A	l	w	a	y	s	 	W	i	n	s
 Finally, we return Truth Always Wins as our decoded string.

 Sample Input 1
 2312179862310199501872379231018117927
 Sample Output 1
 Have a Nice Day
 Explanation 1
 We reverse encoded to get 7297118101329732781059910132689712132. We then replace each ASCII value with its corresponding character:

 ASCII	72	97	118	101	32	97	32	78	105	99	101	32	68	97	121	32
 Character	H	a	v	e	 	a	 	N	i	c	e	 	D	a	y
 Finally, we return Have a Nice Day as our decoded string.

 Sample Input 2
 1219950180111108236115111016623101401611235115012312161151110101111127
 Sample Output 2
 Honesty is the Best Policy
 Explanation 2
 We reverse encoded to get 7211111010111511612132105115321161041013266101115116328011110810599121. We then replace each ASCII value with its corresponding character:

 ASCII	72	111	110	101	115	116	121	32	105	115	32	116	104	101	32	66	101	115	116	32	80	111	108	105	99	121
 Character	H	o	n	e	s	t	y	 	i	s	 	t	h	e	 	B	e	s	t	 	P	o	l	i	c	y
 Finally, we return Honesty is the Best Policy as our decoded string.
 */

public class Task2 {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "statementcampus"
            + File.separator + Task2.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        String s = in.next();

        System.out.println(solution(s));
    }


    public static String solution(String encoded) {
        StringBuilder decoded = new StringBuilder();
        encoded = (new StringBuilder(encoded)).reverse().toString();
        int offset;
        int charCode;
        for (int i = 0; i <encoded.length(); i++) {
            offset = 2;
            if (encoded.charAt(i) == '1') {
                offset = 3;
            }
            charCode = Integer.valueOf(encoded.substring(i, i + offset));
            decoded.append((char)charCode);
            i = i + offset - 1;
        }
        return decoded.toString();

    }
}

