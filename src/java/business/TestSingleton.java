package business;

import entity.Student;
import entity.Word;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gábor
 */
@Singleton
@Startup
public class TestSingleton {
    
    @Resource
    SessionContext ctx;
    
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    private void init() {
        ctx.getBusinessObject(TestSingleton.class).createDB();
    }
    
    @Asynchronous
    public void createDB() {
        Student s1 = new Student();
        s1.setUsername("Gabor");
        
        Student s2 = new Student();
        s2.setUsername("Bela");
        
        Student s3 = new Student();
        s3.setUsername("Tibi");
        
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        
        List<String> dog= new ArrayList<>();
        dog.add("kutya");
        dog.add("eb");
        List<String> cat= new ArrayList<>();
        cat.add("macska");
        List<String> apple= new ArrayList<>();
        apple.add("alma");
        List<String> potato = new ArrayList<>();
        potato.add("burgonya");
        potato.add("krumpli");
       
        
        Word w1 = new Word("dog",dog);
        Word w2 = new Word("cat",cat);
        Word w3 = new Word("apple",apple);
        Word w4 = new Word("potato",potato);
        
        em.persist(w1);
        em.persist(w2);
        em.persist(w3);
        em.persist(w4);
    }
    
}
