package web;

import session.WordSession;
import session.UserSession;
import entity.Word;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.StatisticService;
import service.StudentService;
import service.WordService;
import statistics.GlobalStatistics;
import statistics.SessionStatistics;
import statistics.StatisticStore;

/**
 *
 * @author Gábor
 */
@ViewScoped
@Named
public class LearnBean implements Serializable {

    private Word word;
    private String guess;
    private GlobalStatistics gs;
    private SessionStatistics ss;
    private ArrayList list = new ArrayList();
    private boolean next = true;

    @Inject
    UserSession us;
    @Inject
    WordSession ws;

    @Inject
    StatisticStore statStore;

    @Inject
    StatisticService statService;
    @Inject
    WordService wordService;
    @Inject
    StudentService studentService;

    public LearnBean() {

    }

    @PostConstruct
    private void init() {

        word = findWord();

        us.incSeen();
        us.setLoggedIn(studentService.updateSeen(us.getLoggedIn()));
        word = wordService.updateSeen(word);

        /*Ha a statisztika-tároló tartalmaz bejegyzést a 
         tanuló-szó párosról akkor kikeresi  
         ha nem létrehoz egyet */
        //Session Statistics
        SessionStatistics ssTemp = new SessionStatistics(us.getLoggedIn(), word);
        if (statStore.getStat().contains(ssTemp) == false) {
            ss = ssTemp;
            statStore.getStat().add(ss);
        } else {
            for (SessionStatistics sessionstatistics : statStore.getStat()) {
                if (sessionstatistics.equals(ssTemp)) {
                    ss = sessionstatistics;
                }
            }
        }
        ss.incSeen();
        //Global Statistics
        GlobalStatistics gstemp = new GlobalStatistics(us.getLoggedIn(), word);
        if (statService.findStatistics(gstemp) == null) {
            gs = gstemp;
            statService.persStatistic(gs);
        } else {
            gs = statService.findStatistics(gstemp);
        }
        gs = statService.updateSeen(gs);
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public GlobalStatistics getGs() {
        return gs;
    }

    public void setGs(GlobalStatistics gs) {
        this.gs = gs;
    }

    public SessionStatistics getSs() {
        return ss;
    }

    public void setSs(SessionStatistics ss) {
        this.ss = ss;
    }

    public UserSession getUs() {
        return us;
    }

    public void setUs(UserSession us) {
        this.us = us;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
    
    public String guess() {
        
        List<String> translations = word.getTrans();
        if(guess.length()==0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please type a word!"));
            return null;
        }
        if (translations.contains(guess)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("good"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Translations: " + translations));

            us.incCorrect();
            ss.incCorrect();

            word = wordService.updateCorrect(word);
            us.setLoggedIn(studentService.updateCorrect(us.getLoggedIn()));
            gs = statService.updateCorrect(gs);

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("bad"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Translations: " + translations));
            wordService.setPriority(word, 1);
        }
        next = false;
        return null;
    }

    public Word findWord() {
        List<Word> words = wordService.getWordsList();
        ArrayList indexes = new ArrayList<>();
        Integer index = 0;
        for (Word word1 : words) {
            if (ws.getWords().contains(word1) == false) {
                wordService.setPriority(word1, 4);
            }
            if (word1.getSeen() != 0 && word1.getCorrect() / word1.getSeen() < 0.5) {
                wordService.setPriority(word1, 2);
                ws.getWords().add(word);
            }
            for (int i = 0; i < 5 - word1.getPriority(); i++) {
                indexes.add(index);
            }
            index++;
        }
        list = indexes;
        words = wordService.getWordsList();
        Random rand = new Random();
        int randNum = rand.nextInt(indexes.size() - 1);
        int prio = (Integer) indexes.get(randNum);
        return words.get(prio);

    }

    public String nextWord() {
            word = findWord();
            guess = null;
            next = true;
            return "learn";
    }

}
