package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import statistics.Statistics;
/**
 *
 * @author Gábor
 */
@Entity
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement
public class Word extends Statistics implements Serializable {
    
    @Id
    @GeneratedValue
    Long id;
    
    @Column(unique = true)
    String source;
    
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> trans;
    
    int priority=3;
    
    public Word() {
    }

    public Word(String source, List trans) {
        this.source = source;
        this.trans = trans;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getTrans() {
        return trans;
    }

    public void setTrans(List<String> trans) {
        this.trans = trans;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    @Override
    public String toString() {
        return source;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Word other = (Word) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
