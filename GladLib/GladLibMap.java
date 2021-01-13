
/**
 * Write a description of class GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    private HashMap<String, ArrayList<String>> myMap;
   
    //used words
    private ArrayList<String> usedList;
    //totalWordsConsidered quizz 
    private ArrayList<String> categoriesUsed;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        
        // I need to change TimeList to TimeframeList
        String[] categories = {"adjectiveList", "nounList"
            , "colorList", "countryList", "nameList" 
            , "animalList" , "timeframeList", "fruitList"
            , "verbList" };
            
        String[] fileNames = {"adjective.txt", "noun.txt"
            , "color.txt", "country.txt", "name.txt" 
            , "animal.txt" , "timeframe.txt", "fruit.txt"
            , "verb.txt"};
            
        for (int i = 0; i < categories.length; i += 1){
            myMap.put(categories[i], readIt(source + "/" + fileNames[i]));
        }
        
        for(String s: myMap.keySet()){
            System.out.print(s + "\t");
            System.out.println(myMap.get(s));
        }
        
        usedList = new ArrayList<String>();
        //categoriesUsed quizz
        categoriesUsed = new ArrayList<String>();
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        //System.out.println(source.get(index));
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        //check first if label is number
        if(label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        // infinite loop System.out.println(label);
        String label2 = label + "List";
        if(myMap.containsKey(label2)){
            if(!categoriesUsed.contains(label)){
                categoriesUsed.add(label);
            }
            
            return randomFrom(myMap.get(label2));
        }
        else{
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        //add word to the ArrayList of used words && prevent usage again
        while(usedList.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
            //System.out.println("label " + w + "already " + sub);
        }
        
        
        if(!usedList.contains(sub)){
            usedList.add(sub);
            //System.out.println("label " + w + "added " + sub);
        }
        
        
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private void totalWordsInMap(){
        int sum = 0;
        
        for(String s: myMap.keySet()){
            sum += myMap.get(s).size();
        }
        
        System.out.println("\ntotal words in ArrayLists: " + sum);
        
    }
    
    private void totalWordsConsidered(){
        int sum = 0;
        System.out.println(categoriesUsed);
        for(String w: categoriesUsed){
            sum += myMap.get(w+"List").size();
        }
        System.out.println("\nThe total words in the lists used are " + sum);
    }
    
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        totalWordsInMap();
        totalWordsConsidered();
    }
    
    
    
}
