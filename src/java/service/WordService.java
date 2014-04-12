package service;

import dao.WordDAO;
import entity.Word;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Gábor
 */
@Stateless
public class WordService {
    
    @Inject
    WordDAO wordDao;
    
    public List<Word> getWordsList(){
        return  wordDao.getWordList();
    }
    
    public void addWord(Word word){
        wordDao.addWord(word);
    }
    
    public void setPriority(Word word, int priority){
        wordDao.setPriority(word, priority);
    }
    
    public Word updateSeen(Word word){
        return wordDao.updateSeen(word);
    }
    
    public Word updateCorrect(Word word){
        return wordDao.updateCorrect(word);
    }
    
    public void removeById(Long id){
        wordDao.removeById(id);
    }
    
    public void update(Word word){
        wordDao.update(word);
    }
    
    public Word findBySource(String source){
        return wordDao.findBySource(source);
    }
    
    public void updateTranslations(List<String> translations, Word word){
        wordDao.updateTranslations(translations,word);
    }
}
