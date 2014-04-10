/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@XmlRootElement(name = "wordStatistic")
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WordStatResource {

    private Word word;

    public WordStatResource() {
        word = new Word();
    }
    
    public WordStatResource(Word word) {
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
    
    @XmlAttribute
    public int getCorrect(){
        return word.getCorrect();
    }
    
    @XmlAttribute
    public int getSeen(){
        return word.getSeen();
    }
    
    @XmlAttribute
    public int getWrong(){
        return word.getSeen()-word.getCorrect();
    }

    @XmlTransient
    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
    
    
    
}