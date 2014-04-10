package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import statistics.Statistics;

/**
 *
 * @author Gábor
 */

@Entity
@Named
public class Student extends Statistics implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String username;
    
    public Student() {
    }

    public Student(String username) {
        this.username = username;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return username;
    }
    
    
}
