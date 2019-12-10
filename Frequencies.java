/*
Richard Saint-Fleur
CISC 3130 Assignment 3
Frequencies.java
 */
package frequencies;

import java.util.*;
import java.io.*;

public class Frequencies {

    public static void main(String[] args) throws Exception {
        
        PrintStream ps = new PrintStream(new File ("output.txt"));
        Map < String, Integer > map = new HashMap < > (); 
        Scanner sc = new Scanner(new File("song.txt")); // used to read user input
        
        ps.println("WORD FREQUENCY:" + "\n");
        while(sc.hasNext()){
            String song = sc.nextLine();

            String[] tokens = song.split(" "); // split based on space

            for(String token: tokens) {

                String word = token.toUpperCase();
                if (map.containsKey(word)) {
                    int count = map.get(word); // get word count 
                    map.put(word, count + 1); // override word count
                } else {
                    map.put(word, 1); // initial word count to 1
                    }
            }
        }
        //display the data
        Set < String > keys = map.keySet(); // list of unique words because it's a Set

        TreeSet < String > sortedKeys = new TreeSet < > (keys); // ascending order of words

        for(String str: sortedKeys) {
            ps.println(str + " : " + map.get(str));
        }
    }   
}