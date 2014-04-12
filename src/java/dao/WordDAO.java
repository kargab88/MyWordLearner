/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Word;
import entity.Word_;
import exceptions.DAOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gábor
 */
@Stateless
public class WordDAO {

    @PersistenceContext
    EntityManager em;

    public List<Word> getWordList() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Word> cq = cb.createQuery(Word.class);
            Root<Word> words = cq.from(Word.class);
            cq.select(words);
            return em.createQuery(cq).getResultList();
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public Word findBySource(String source) {
        try {
            return em.createQuery("SELECT w FROM Word w WHERE w.source = :s", Word.class)
                    .setParameter("s", source)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public void addWord(Word word) {
        try {
            em.persist(word);
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public void setPriority(Word word, int priority) {
        try {
            word.setPriority(priority);
            em.merge(word);
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public Word updateSeen(Word word) {
        try {
            Long id = word.getId();
            word = em.find(Word.class, id);
            word.incSeen();
            em.merge(word);
            return word;
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public Word updateCorrect(Word word) {
        try {
            Long id = word.getId();
            word = em.find(Word.class, id);
            word.incCorrect();
            em.merge(word);
            return word;
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public List<Word> searchWord(String part) {
        
        try{
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Word> query = cb.createQuery(Word.class);
        Root<Word> w = query.from(Word.class);

        query.select(w);
        query.where(cb.like(w.get(Word_.source), cb.parameter(String.class, "part")));

        return em.createQuery(query).setParameter("part", "%" + part + "%").getResultList();
        }
        catch(Exception ex){
            throw new DAOException();
        }

    }

    public void removeById(Long id) {
        try{
            Word w = em.getReference(Word.class, id);
            em.remove(w);
        }
        catch(Exception ex){
            throw new DAOException();
        }
    }
    
    public Word findById(Long id){
        try{
            return em.find(Word.class, id);
        }
        catch(Exception ex){
            throw new DAOException();
        }
    }
    

    public void update(Word word) {
        try{
            Long id = word.getId();
            word = em.find(Word.class, id);
            em.merge(word);
            em.flush();
        }
        catch(Exception ex){
            throw new DAOException();
        }
    }
    
    public void updateTranslations(List<String> translations, Word word){
        try {
            Long id = word.getId();
            word = em.find(Word.class, id);
            word.setTrans(translations);
            em.merge(word);
            em.flush();
        } catch (Exception ex) {
            throw new DAOException();
        }
    }
}
