package statistics;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Gábor
 */

public class GSKey implements Serializable{
    
   
    Long student;
    Long word;

    public GSKey() {
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public Long getWord() {
        return word;
    }

    public void setWord(Long word) {
        this.word = word;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.student);
        hash = 47 * hash + Objects.hashCode(this.word);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GSKey other = (GSKey) obj;
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
   
    
    
    

    
    
    
}
