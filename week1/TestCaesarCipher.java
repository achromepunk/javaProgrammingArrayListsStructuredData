
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    
    // countLetters
    public int[] countLetters(String encrypted){
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 

        int[] freqs = new int[alphabet.length()];
        
        for(int i = 0; i < encrypted.length(); i++){
            int index = alphabet.indexOf(encrypted.charAt(i));
            if(index != -1){
                freqs[index] += 1; 
            }
        }
        return freqs;
    }
    // maxIndex
    public int maxIndex(int[] freqs){
            int max = 0;
            int index = 0;
            for(int i = 0; i < freqs.length; i++){
                if(max < freqs[i]){
                    max = freqs[i];
                    index = i;
                }
            }
    
            return index;
    }
    
    public void breakCaesarCipher(String input){
        
        int[] freqs = countLetters(input);
        int freqLetter = maxIndex(freqs); 
        int key = 0;
        //la letra mas frecuente es la e (indice 4)
        if (freqLetter < 4){
            key = freqLetter -4 + 26;
        }
        else{
            key = freqLetter - 4;
        }
        
        CaesarCipher cc = new CaesarCipher(key);
        
        System.out.println(cc.decrypt(input));
        
    
    }
    
    public void simpleTest(){
        FileResource fr = new FileResource();
        
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(fr.asString());
        System.out.println(encrypted);
        System.out.println(cc.decrypt(encrypted));
        
        
                       
    
    }
}
