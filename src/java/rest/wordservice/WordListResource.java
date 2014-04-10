/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest.wordservice;

import entity.Word;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Gábor
 */
@XmlRootElement(name = "words")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class WordListResource {
    
    @XmlElement(name = "word")
    private List<WordResource> words;

    public WordListResource() {
    }

    public WordListResource(List<Word> wordsList) {
        words = new ArrayList<>();
        for (Word word : wordsList) {
            words.add(new WordResource(word));
        }
    }
    
    
    
}
