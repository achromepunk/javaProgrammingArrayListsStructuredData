
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
    
    //maxIndex

}
