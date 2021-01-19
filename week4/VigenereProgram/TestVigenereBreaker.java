
/**
 * Write a description of class TestVigenereBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestVigenereBreaker {
    
    public void testSlice(){
        
        VigenereBreaker vb = new VigenereBreaker();
        
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
        
    }

    public void testTryKeyLength(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        
        String encrypted = fr.asString();
        String key = "flut";
        
        int[] result = vb.tryKeyLength(encrypted, key.length(), 'e');
        
        for (int i = 0; i < key.length(); i++){
            System.out.println("->" + result[i]);
        }
    }
    
    public void testBreakVigenere(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
    public void testBreakVigenereNoKeyLength(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere2();
    }
    
    public void testMostCommonChar(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        System.out.println(vb.mostCommonCharIn(vb.readDictionary(fr)));
    }
    
}
