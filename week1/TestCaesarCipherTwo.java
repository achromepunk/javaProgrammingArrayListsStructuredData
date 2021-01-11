
/**
 * Write a description of class TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    //halfOfString
    public String halfOfString(String message, int start){
        String result = "";
        for(int i = start; i < message.length(); i += 2){
            result = result + message.charAt(i);
        }
        return result;
    }
    
    //countLetters
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
    
    //maxIndex
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
    //simple tests
    public void simpleTests(){
        FileResource fr = new FileResource();
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17, 3);
        //print encrypted text
        String result = cc2.encrypt(fr.asString());
        System.out.println(result);
        //print desencrypted text
        //System.out.println(cc2.decrypt(result));
        System.out.println(breakCaesarCipher(result));
    }
    //breakCaesarCipher
    public String breakCaesarCipher(String input){
        //we get freq of even pos string & odd pos string
        int[] freqs1 = countLetters(halfOfString(input, 0));
        int[] freqs2 = countLetters(halfOfString(input, 1));
        //we search for the most rep character (in Eng it should be "E")
        int freq1 = maxIndex(freqs1);
        int freq2 = maxIndex(freqs2);
        //we determine the decrYpt keys
        int key1 = 0;
        int key2 = 0;
        if (freq1 < 4){
            key1 = freq1 -4 + 26;
        }
        else{
            key1 = freq1 - 4;
        }
        if (freq2 < 4){
            key2 = freq2 -4 + 26;
        }
        else{
            key2 = freq2 - 4;
        }
        //we invoke a new caesarcipherTwo object with the correct keys
        CaesarCipherTwo cc_aux = new CaesarCipherTwo(key1, key2);
        String result = cc_aux.decrypt(input);
        return result;
    }
}
