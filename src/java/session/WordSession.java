package session;

import entity.Word;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Gábor
 */
@SessionScoped
public class WordSession implements Serializable{
    
    private Set<Word> words= new HashSet<>();

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
    
    
    
}
