package session;

import entity.Student;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import statistics.Statistics;

/**
 *
 * @author Gábor
 */
@SessionScoped
@Named
public class UserSession extends Statistics implements Serializable {

    private Student loggedIn;

    public UserSession() {
    }

    public Student getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Student loggedIn) {
        this.loggedIn = loggedIn;
    }
}
