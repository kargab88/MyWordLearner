
package statistics;

import entity.Student;
import entity.Word;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gábor
 */
@Entity
@IdClass(GSKey.class)
public class GlobalStatistics extends Statistics implements Serializable{
    
    
    @Id
    @ManyToOne
    Student student;
    
    
    @Id
    @ManyToOne
    Word word;

    public GlobalStatistics() {
    }

    public GlobalStatistics(Student student, Word word) {
        this.student = student;
        this.word = word;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
    
    
    
    
    
}
