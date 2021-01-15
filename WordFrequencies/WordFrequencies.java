
/**
 * Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    private void findUnique(){
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        
        for(String w: fr.words()){
            
            w = w.toLowerCase();
            
            if(myWords.contains(w)){
                int index = myWords.indexOf(w);
                myFreqs.set(index, myFreqs.get(index) + 1);
            }
            else{
                myWords.add(w);
                myFreqs.add(1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        
        System.out.println(myWords.size());
        for(String w: myWords){
            //System.out.println(myFreqs.get(myWords.indexOf(w)) + "\t" + w);
        }
        
        int max = 0;
        for(int i: myFreqs){
            if(max < myFreqs.get(i)){
                max = myFreqs.get(i);
            }
        }
        
        System.out.println("max freq " + max); 
        System.out.println("the word most rep " + myWords.get(myFreqs.indexOf(max)));
    
    }
}
