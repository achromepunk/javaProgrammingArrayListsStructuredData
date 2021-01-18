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
        int[] key = tryKeyLength(encrypted,5, mostCommon );
        
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(encrypted));
        
        
    }
    
}
