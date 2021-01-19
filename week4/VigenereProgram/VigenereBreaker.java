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
    
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String encrypt = fr.asString();
        
        char mostCommon = 'e';
        
        int[] key = tryKeyLength(encrypt, 5, mostCommon);
        VigenereCipher vb = new VigenereCipher(key);
        System.out.println(vb.decrypt(encrypt));
    }
    
}
