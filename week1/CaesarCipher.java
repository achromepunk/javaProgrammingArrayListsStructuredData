
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        input = input.toUpperCase();
        StringBuilder sb = new StringBuilder(input);
        
        for(int i = 0; i < input.length(); i++){
            int index = alphabet.indexOf(input.charAt(i));
            if(index != -1){
                sb.setCharAt(i,shiftedAlphabet.charAt(index));
            }
            
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }

    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        
        String result = cc.encrypt(input);
        
        return result;
    }    
       
}
