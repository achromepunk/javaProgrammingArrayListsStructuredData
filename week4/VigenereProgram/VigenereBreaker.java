import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACEd WITH YOUR CODE
        StringBuilder fragment = new StringBuilder();
        
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            fragment.append(message.charAt(i));
        }
        
        String result = fragment.toString();
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        
        //athens_keyflute.txt; “flute”; {5, 11, 20, 19, 4}.
        
        
        for(int i = 0; i < klength; i++){
            String fragment = sliceString(encrypted, i, klength);
            CaesarCracker cb = new CaesarCracker(mostCommon);
            key[i] = cb.getKey(fragment);
        }
        
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        char mostCommon = 'e';
        
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        //in this problem we know key length
        int[] key = tryKeyLength(encrypted,4, mostCommon );
        
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(encrypted));
        
        
    }
    
    public HashSet readDictionary(FileResource fr){
        //fr has only one word per line
        HashSet hs = new HashSet();
        for(String l: fr.lines()){
            hs.add(l.toLowerCase());
        }
        
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] result = message.split("\\W+");
        int words = 0;
        
        for(String w: result){
            if(dictionary.contains(w.toLowerCase())){
                words += 1;
            }
        }
        return words;
    }
    
    public void breakForLanguage(String encrypted, HashSet<String> dictionary){
        char mostCommon= 'e';
        //for testing purposes
        int max = 0;
        
        // try all key lengths from 1 to 100 
        for(int i = 1; i < 101; i += 1){
            int[] key = tryKeyLength(encrypted,i, mostCommon );
            
            
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            //count words
            int current = countWords(decrypted, dictionary);
            if(current > max){
                System.out.println("key length: "+ i + " real words: " 
                    + countWords(decrypted, dictionary));
                max = current;
            }
            
            
            
        }
    }
    
    public void breakVigenere2() {
        //WRITE YOUR CODE HERE
        char mostCommon = 'e';
        
        //get the dictionary
        FileResource di = new FileResource("English");
        HashSet dict = readDictionary(di);
        
        //get the data encrypted
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        breakForLanguage(encrypted, dict);
        
        
        int[] key = tryKeyLength(encrypted,57, mostCommon );
        
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(encrypted));
        
        
        
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        int[] freq = new int[alphabet.length()];
        
        for(String w: dictionary){
            for(int i = 0; i < w.length(); i++){
                if( alphabet.indexOf(w.charAt(i)) != -1){
                    freq[alphabet.indexOf(w.charAt(i))] = freq[alphabet.indexOf(w.charAt(i))] + 1;
                }
            }
        }
        
        int max = 0;
        int pos = 0;
        for(int i = 0; i < freq.length; i++){
            if(max < freq[i]){
                max = freq[i];
                pos = i;
            }
        }
        char letter = alphabet.charAt(pos);
        return letter;
    }
    
    public void breakForAllLanguajes(String encrypted, HashMap<String, HashSet<String>> languages){
        
        for(String lang: languages.keySet()){
            char mostCommonChar = mostCommonCharIn(languages.get(lang));
            
            
            
        }
        
    }
}
