package web;

import entity.Word;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class AddBean {
    
    String newSource = new String();
    String newTransl = new String();
   
    
    
    
    @Inject
    WordService ws;

    public AddBean() {
    }

    public String getNewSource() {
        return newSource;
    }

    public void setNewSource(String newSource) {
        this.newSource = newSource;
    }

    public String getNewTransl() {
        return newTransl;
    }

    public void setNewTransl(String newTransl) {
        this.newTransl = newTransl;
    }
    
    public List<String> setTrans(){
        ArrayList<String> translations = new ArrayList<>();
        translations.addAll(Arrays.asList(newTransl.split(",")));
        return translations;
    }

    
    public String add(){
        Word word = new Word(newSource, setTrans());
        ws.addWord(word);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Word successfully added!"));
        newSource = null;
        newTransl = null;
        return "add";
    }
       
}
