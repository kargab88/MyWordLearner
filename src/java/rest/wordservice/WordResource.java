package rest.wordservice;

import entity.Word;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Gábor
 */
@XmlRootElement(name = "word")
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WordResource {

    private Word word;

    public WordResource() {
        word = new Word();
    }
    
    public WordResource(Word word) {
        this.word = word;
    }

    @XmlAttribute
    public Long getId() {
        return word.getId();
    }
    
    @XmlAttribute
    public String getSource(){
        return word.getSource();
    }
    
    @XmlTransient
    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
    
    
    
}