
/**
 * Write a description of class CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File; 

public class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mkey1;
    private int mkey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mkey1 = key1;
        mkey2 = key2;
        
    }
    
    public String encrypt(String input){
        input = input.toUpperCase();
        StringBuilder sb = new StringBuilder(input);
        
        for(int i = 0; i < input.length(); i++){
            int index = alphabet.indexOf(input.charAt(i));
            //encrypt with key1
            if(i % 2 == 0){
                if(index != -1){
                    sb.setCharAt(i,shiftedAlphabet1.charAt(index));
                }
            }
            //encrypt with key2
            else{
                if(index != -1){
                    sb.setCharAt(i,shiftedAlphabet2.charAt(index));
                }
            }
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mkey1, 26 - mkey2);
        
        String result = cc.encrypt(input);
        
        System.out.println(result);
        return result;
    }   
    //for exam purposes only
    
    public void eyeBalling(String input){
        input = input.toUpperCase();
        File f = new File("preguntaExamen.txt");
        for(int i = 0; i < 26; i +=1){
            for(int j = 0; j < 26; j +=1){
                CaesarCipherTwo ccc = new CaesarCipherTwo(i,j);
                //System.out.print("** "+i+" , " + j + "**");
                System.out.print(ccc.encrypt(input)+ "\t");
            }
            System.out.println("");
        
        }
    
    }
    
}
