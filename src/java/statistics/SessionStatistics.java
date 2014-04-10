package statistics;

import entity.Student;
import entity.Word;
import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Gábor
 */
@Named
@RequestScoped
public class SessionStatistics extends Statistics implements Serializable{
    
    private Word word;
    private Student student;
    
    public SessionStatistics() {
    }

    public SessionStatistics(Student student,Word word) {
        this.word = word;
        this.student = student;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.word);
        hash = 53 * hash + Objects.hashCode(this.student);
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
        final SessionStatistics other = (SessionStatistics) obj;
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SessionStatistics{" + "word=" + word + ", student=" + student + '}';
    }
    
    
}