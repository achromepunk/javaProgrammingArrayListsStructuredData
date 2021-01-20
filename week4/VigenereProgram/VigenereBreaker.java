import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String result = "";
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            result = result + message.charAt(i);
        }
        
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for(int i = 0; i < key.length; i++){
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(sliceString(encrypted, i, klength));
        }
        
        
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for(String l: fr.lines()){
            dict.add(l.toLowerCase());
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] list = message.split("\\W+");
        int count = 0;
        for(String w: list){
            if(dictionary.contains(w.toLowerCase())){
                count += 1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        char mostCommon = mostCommonCharIn(dictionary);
        String result = "";
        int best = 0;
        
        for(int i = 1; i < 101; i++){
            int key[] = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vb = new VigenereCipher(key);
            String decrypted = vb.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if(best < count){
                best = count;
                result = decrypted;
            }
        }
        
        return result;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[alphabet.length()];
        
        for(String w: dictionary){
            for(int i = 0; i < w.length(); i ++){
                int index = alphabet.indexOf(w.charAt(i));
                if(index != -1){
                    counts[index] = counts[index] + 1; 
                }
            }
        }
        
        int max = 0;
        int pos = 0;
        for(int i = 0; i < counts.length; i++){
            if(max < counts[i]){
                max = counts[i];
                pos = i;
            }
        }
    
        return alphabet.charAt(pos);
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        String bestLang = "";
        int best = 0;
        
        for(String l: languages.keySet()){
            
            String decrypted = breakForLanguage(encrypted, languages.get(l));
            int realWords = countWords(decrypted, languages.get(l));
            if(best < realWords){
                best = realWords;
                bestLang = decrypted;
            }
            
        }
        
        return bestLang;
    }
    
    public void breakVigenere () {
        //Select the data
        System.out.println("select data");
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        //Select the dictionares
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        
        System.out.println("select dictionaries");
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource dic = new FileResource(f);
            languages.put(f.getName(), readDictionary(dic));
        }
        
        //breakForAllLangs
        System.out.println(breakForAllLangs(encrypted, languages));
        
    }
    
    public void breakVigenere_old () {
        //Select the data
        System.out.println("select data");
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        //Select the dictionary
        System.out.println("select dictionaries");
        FileResource di = new FileResource();
        HashSet<String> dictionary = readDictionary(di);
        
        String decrypted = breakForLanguage(encrypted, dictionary);
        //System.out.println(decrypted);
        System.out.println(countWords(decrypted, dictionary));
    }
}
