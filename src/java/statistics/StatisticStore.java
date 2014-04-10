package statistics;

import java.io.Serializable;
import java.util.HashSet;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Gábor
 */
@Named
@SessionScoped
public class StatisticStore implements Serializable{
    
    HashSet<SessionStatistics> stat = new HashSet<>();

    public StatisticStore() {
    }

    public HashSet<SessionStatistics> getStat() {
        return stat;
    }

    public void setStat(HashSet<SessionStatistics> stat) {
        this.stat = stat;
    }

}
