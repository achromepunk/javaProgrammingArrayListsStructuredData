
/**
 * Write a description of class wordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.File;
import edu.duke.*;

public class wordsInFiles {

    private HashMap<String, ArrayList<String>> words;
    
    public wordsInFiles(){
        words = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String w: fr.words()){
            if(!words.containsKey(w)){
                words.put(w, new ArrayList<String>());
                words.get(w).add(f.getName());
            }
            else{
                words.get(w).add(f.getName());
            }
        }
    }
    
    private void buildWordFileMap(){
        DirectoryResource dr = new DirectoryResource();
        
        for(File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int max = 0;
        
        for(String w: words.keySet()){
            if(max < words.get(w).size()){
                max = words.get(w).size();
            }
        }
    
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> list = new ArrayList<String>();
        
        for(String w: words.keySet()){
            if(words.get(w).size() == number){
                list.add(w);
            }
        }
        
        return list;
    }
    
    private void printFilesIn(String word){
        for(String f: words.get(word)){
            System.out.println(f);
        }
    }
    
    public void fullMapNumbers(){
    
        words.clear();
        buildWordFileMap();
        for(String w: words.keySet()){
            System.out.println(words.get(w).size() +"\t" + w);
        }
    }
    
    //only for testing purposes
    public void test(){
        words.clear();
        System.out.println("cleared");
        buildWordFileMap();
        
        System.out.println("created");
        
        ArrayList<String> wordsInNFiles = wordsInNumFiles(4);
        for(String s: wordsInNFiles){
            //System.out.println(s);
        }
        
        
        //System.out.println(wordsInNFiles.size());
        
        System.out.println("*******");
        printFilesIn("tree");
        
        
        
    }
}
