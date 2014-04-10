package dao;

import exceptions.DAOException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;
import statistics.GlobalStatistics;

/**
 *
 * @author Gábor
 */
@Stateless
public class StatisticDAO {

    @PersistenceContext
    EntityManager em;

    public Object getGSKey(GlobalStatistics gs) {

        try {
            PersistenceUnitUtil util = em.getEntityManagerFactory().getPersistenceUnitUtil();
            return util.getIdentifier(gs);
        } catch (Exception ex) {
            throw new DAOException();
        }

    }

    public void persStatistic(GlobalStatistics gs) {

        try {
            Object gskey = getGSKey(gs);
            if (em.find(GlobalStatistics.class, gskey) == null) {
                em.persist(gs);
            }
        } catch (Exception ex) {
            throw new DAOException();
        }

    }

    public GlobalStatistics updateSeen(GlobalStatistics gs) {
        try {
            Object gskey = getGSKey(gs);
            gs = em.getReference(GlobalStatistics.class, gskey);
            em.remove(gs);
            gs.incSeen();
            em.persist(gs);
            return gs;
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public GlobalStatistics updateCorrect(GlobalStatistics gs) {
        try {
            Object gskey = getGSKey(gs);
            gs = em.getReference(GlobalStatistics.class, gskey);
            em.remove(gs);
            gs.incCorrect();
            em.persist(gs);
            return gs;
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public GlobalStatistics findStatistics(GlobalStatistics gs) {
        try{
            Object gskey = getGSKey(gs);
            return em.find(GlobalStatistics.class, gskey);
        }
        catch(Exception ex){
            throw new DAOException();
        }
    }

}
