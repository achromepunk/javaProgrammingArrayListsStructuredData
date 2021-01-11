
/**
 * Write a description of class CodonS here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class Codons {
    private HashMap<String, Integer> codons;
    
    public Codons() {
        codons = new HashMap<String, Integer>();
    }
    
    public HashMap<String, Integer> buildCodonMap(int start, String dna){
        codons.clear();
        String codon = "";
        for(int i = 0; i + start< dna.length(); i++){
            codon = codon + dna.charAt(i + start);
            if(i % 3 == 2){
                if(codons.containsKey(codon)){
                    codons.put(codon, codons.get(codon) + 1);
                }
                else{
                    codons.put(codon, 1);
                }
                codon ="";
            }
        }
        
        return codons;
        
    }
    

}
