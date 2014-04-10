package service;

import dao.StatisticDAO;
import javax.ejb.Stateless;
import javax.inject.Inject;
import statistics.GlobalStatistics;

/**
 *
 * @author Gábor
 */
@Stateless
public class StatisticService {
    
    @Inject
    StatisticDAO statisticDao;
    
    public void persStatistic(GlobalStatistics gs){
        statisticDao.persStatistic(gs);
    }
    
    public GlobalStatistics updateSeen(GlobalStatistics gs){
        return statisticDao.updateSeen(gs);
    }
    
    public GlobalStatistics updateCorrect(GlobalStatistics gs){
        return statisticDao.updateCorrect(gs);
    }
    
    public GlobalStatistics findStatistics(GlobalStatistics gs){
        return statisticDao.findStatistics(gs);
    }

}
