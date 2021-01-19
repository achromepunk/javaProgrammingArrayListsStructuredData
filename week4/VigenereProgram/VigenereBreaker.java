import java.util.*;
import edu.duke.*;

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
        char mostCommon = 'e';
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
    
    public void breakVigenere () {
        //Select the data
        System.out.println("select data");
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        //Select the dictionary
        System.out.println("select dictionaries");
        FileResource di = new FileResource();
        HashSet<String> dictionary = readDictionary(di);
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println(decrypted);
        
        
    }
    
}
