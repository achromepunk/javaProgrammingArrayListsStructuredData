
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay(){
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
        
    }
    
    private void update(String person){
        if(names.contains(person)){
            int index = names.indexOf(person);
            counts.set(index, counts.get(index) + 1);
        }
        else{
            names.add(person);
            counts.add(1);
            
        }
    }
    
    private void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String l: fr.lines()){
            int index = l.indexOf(".");
            if(index != -1){
                update(l.substring(0,index));
            }
        }
    }

    private void charactersWithNumParts(int num1, int num2){
        
        for(String s: names){
            int index = names.indexOf(s);
            if(counts.get(index) > num1 && counts.get(index) < num2){
                System.out.println(counts.get(index) + "\t" + s);
            }
        }
    
    }
    
    public void tester(){
        int num1 = 10;
        int num2 = 15;
        
        findAllCharacters();
        charactersWithNumParts(num1 ,  num2);
    }
}
