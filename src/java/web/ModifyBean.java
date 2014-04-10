package web;

import entity.Word;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import service.WordService;

/**
 *
 * @author Gábor
 */
@Model
public class ModifyBean implements Serializable {

    @Inject
    private WordService wordService;

    private List<Word> words = new ArrayList<>();
    private List<String> wordsources = new ArrayList<>();
    private String modifiedWord;
    private String newTranslations;

    public ModifyBean() {
    }

    @PostConstruct
    private void init() {
    }

    public String getModifiedWord() {
        return modifiedWord;
    }

    public void setModifiedWord(String modifiedWord) {
        this.modifiedWord = modifiedWord;
    }

    public List<Word> getWords() {
        return wordService.getWordsList();
    }

    public List<String> getWordsources() {
        wordsources = new ArrayList<>();
        for (Word word : getWords()) {
            wordsources.add(word.getSource());
        }
        return wordsources;
    }

    public void setWordsources(List<String> wordsources) {
        this.wordsources = wordsources;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public String getNewTranslations() {
        return newTranslations;
    }

    public void setNewTranslations(String newTranslations) {
        this.newTranslations = newTranslations;
    }

    public List<String> setTrans() {
        ArrayList<String> translations = new ArrayList<>();
        translations.addAll(Arrays.asList(newTranslations.split(",")));
        return translations;
    }

    public String modify() {
        Word w = wordService.findBySource(modifiedWord);
        if(newTranslations.length()==0){
            wordService.removeById(w.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Word removed!"));
            return null;
        }
        else{
            wordService.updateTranslations(setTrans(), w);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Word succesfully updated!"));
            return null;
        }
    }

}
