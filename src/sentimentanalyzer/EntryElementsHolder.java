/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sentimentanalyzer;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Nikolin
 */
public class EntryElementsHolder {
    
    public LocalDateTime from;
    public LocalDateTime to;
    public int cicleOfData = 0;
    
    public LinkedList<EntryElementsDate> wordsAndCreatedDates_ofHolder;
    public LinkedList<EntryElementsDate> storeCountedWords_ofHolder;
    public LinkedList<EntryElementsDate> holderForCountingProcess;
    
    public EntryElementsHolder(LocalDateTime from, LocalDateTime to){
        this.from = from;
        this.to = to;
        
        this.wordsAndCreatedDates_ofHolder = new LinkedList<>();
        this.storeCountedWords_ofHolder = new LinkedList<>();
        this.holderForCountingProcess = new LinkedList<>();
    }
    
    public EntryElementsHolder(){
           this.wordsAndCreatedDates_ofHolder = new LinkedList<>();
    }
    
    //FUNCTION FOR COUNTING THE OCCURANCE OF EACH WORD IN THE LIST
    //USES SECOND LIST TO STORE THE COUNTED ONCES
    public void counterForTheWords_individualObjectList(){
        this.storeCountedWords_ofHolder.clear();
        
        for(int i=0; i<this.wordsAndCreatedDates_ofHolder.size(); i++){
            if(!this.holderForCountingProcess.contains(this.wordsAndCreatedDates_ofHolder.get(i))){
                int count = frequency_counter(this.wordsAndCreatedDates_ofHolder, this.wordsAndCreatedDates_ofHolder.get(i).word);
                this.holderForCountingProcess.add(this.wordsAndCreatedDates_ofHolder.get(i));
                //System.out.println(this.wordsAndCreatedDates_ofHolder.get(i).word + "  ---------" + count);
                
                EntryElementsDate entry = new EntryElementsDate(this.wordsAndCreatedDates_ofHolder.get(i).word, this.wordsAndCreatedDates_ofHolder.get(i).date, count);
                this.storeCountedWords_ofHolder.add(entry);
            }
        }
    }
    
    //FUNCTION TO SORT EACH LIST ACCOURING TO COUNTING RESULTS
    public void sortForTheWords_individualObjectList(){
        Collections.sort(this.storeCountedWords_ofHolder);
    }
    

    //SAME AS Collections.frequency BUT ADAPTED FOR OUR OWN SCENARIO
    public int frequency_counter(LinkedList<EntryElementsDate> c, Object o) {
        int result = 0;
        
        if (o == null){
                for (EntryElementsDate e : c)
                    if (e.word == null)
                        result++;
        }else{
            for (EntryElementsDate e : c)
                if (o.equals(e.word))
                    result++;
        }
        return result;
    }
}
