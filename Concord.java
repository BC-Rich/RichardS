/*
Richard Saint-Fleur
CISC 3130
Homework 3
 */
package concord;

import java.util.*;
import java.io.*;

public class Concord {

    public static void main(String[] args) throws Exception {
        
        Concordence concord = new Concordence();
        
        concord.add("Spiderman.txt");
        System.out.println("WORD FREQUENCY:" + "\n");
        Scanner sc = new Scanner(new File("Spiderman.txt"));
        
        String s = "";
        
        while(sc.hasNext()){ 
        s = sc.next().toUpperCase();
        
        Map<Integer,String> word = new HashMap<>();
        word.put(concord.count(s), s);
        
        Set<Map.Entry<Integer,String>> st = word.entrySet();
           
        for(Map.Entry< Integer,String> me:st){ 
           System.out.print(me.getKey()+":"); 
           System.out.println(me.getValue());
        }
        
        }
        
    }
    
}

class Token {
    
    private String fileName = "";
    private String word = "";
    int wordCount = 0;
    
    public Token(){   
    }
    
    public Token(String fileName, String word, int wordCount){
        this.fileName = fileName;
        this.word = word;
        this.wordCount = wordCount;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public String getWord(){
        return word;
    }
    
    public int getWordCount(){
        return wordCount;
    }
    
    public void setFile(String fileName){
        this.fileName = fileName;
    }
    
    public void setWord(String word){
        this.word = word;
    }
    
    public void setWordCount(int wordCount){
        this.wordCount = wordCount;
    }
    
    @Override
    public String toString(){
        return "\n" + "File: " + getFileName() + "\n" + "Word: " + getWord() + 
                "\n" + "Word Number: " + getWordCount() + "\n";
    }
        
}

class Tokenizer {
    
    private Scanner sc = null;
    private String fileName;
    private int currentWordCount;
    
    public Tokenizer(String fileName){
        this.fileName = fileName;
        this.currentWordCount = 0;
        File file = new File(fileName);
        
        try {
            sc = new Scanner(file);
        } 
        catch(FileNotFoundException e){
            System.out.println("File Not Found!");
        }       
    }
    
    public Token next(){
        Token token = null;
        if(sc != null && sc.hasNext()){
            token = new Token();
            token.setFile (this.fileName);
            token.setWord(sc.next());
            token.setWordCount(this.currentWordCount);
        }
        return token;
    }
    
    public boolean hasNext(){
        boolean hasNextToken = false;
        
        if(sc != null && sc.hasNext()){
            hasNextToken = true;
            this.currentWordCount++;
        }
        else{
            sc.close();
        }
        return hasNextToken;
    }
}

class Concordence {
    private Map<String, List<Token>> myMap = null;
    
    public Concordence(){
        this.myMap = new HashMap<String, List<Token>>();
    }

    public void add(String fileName){
        Tokenizer tokenized = new Tokenizer(fileName);
        List<Token> tokens = null;
        
        while(tokenized.hasNext()){
            Token tk = tokenized.next();
            String currentToken = tk.getWord().toUpperCase();
            if(myMap.containsKey(currentToken)){
                tokens = myMap.get(currentToken);
            }
            else{
                tokens = new ArrayList<Token>();
            }
            tokens.add(tk);
            myMap.put(currentToken,tokens);
        }
        
    }
    
    public String ToString(){
        StringBuilder myString = new StringBuilder();
        for(String theWord: myMap.keySet()){
            theWord = theWord.toUpperCase();
            myString.append("\nThe Word ").append(theWord).append(" Appears In"
                    + " These Tokens: ");
              
            List<String> tokens = new ArrayList<String>();
            for(Token t: myMap.get(theWord)){
               tokens.add(t.toString());
            }
            myString.append(String.join("", tokens));
        }
        return myString.toString();
    }
    
    public int count(String myCount){
        int count = 0;
        if(myCount != null){
            myCount = myCount.toUpperCase();
            if(myMap.containsKey(myCount)){
                count = myMap.get(myCount).size();
            }
        }
        return count;
    }
    
}