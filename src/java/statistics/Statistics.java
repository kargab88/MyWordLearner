package statistics;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Gábor
 */
@MappedSuperclass
public class Statistics {
    
    
    private int seen=0;
    private int correct=0;
    
    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
    
    public int incSeen(){
        return seen++;
    }
    
    public int incCorrect(){
        return correct++;
    }

}
