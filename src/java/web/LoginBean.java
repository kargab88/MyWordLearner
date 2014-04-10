package web;

import session.UserSession;
import entity.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import service.StudentService;
import statistics.SessionStatistics;

/**
 *
 * @author Gábor
 */
@Model
public class LoginBean implements Serializable {

    private List<Student> studentList;
    private List<String> names;
    private Student selectedStudent;
    private String selectStudentName;
    @Size(min = 3, max = 20,
            message = "Please enter a valid username (3-20 characters)")
    @Pattern(regexp = "[a-zA-Z0-9]+",
            message = "Please don't use special characters")
    private String newStudent;

    @PersistenceContext
    EntityManager em;

    @Inject
    UserSession us;

    @Inject
    StudentService studentService;
    
    @Inject
    SessionStatistics ss;

    public LoginBean() {

    }

    public String getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(String newStudent) {
        this.newStudent = newStudent;
    }

    public String getSelectStudentName() {
        return selectStudentName;
    }

    public void setSelectStudentName(String selectStudentName) {
        this.selectStudentName = selectStudentName;
    }

    public List<String> getNames() {
        names = new ArrayList<>();
        for (Student student : getStudentList()) {
            names.add(student.getUsername());
        }
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public List<Student> getStudentList() {
        return studentService.getStudentsList();
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String login() {
        for (Student student : getStudentList()) {
            if (selectStudentName.equals(student.getUsername())) {
                us.setLoggedIn(student);
            }
        }
        return "menu";
    }
    
    public String logout(){
        us.setLoggedIn(null);
        us.setSeen(0);
        us.setCorrect(0);
        ss.setSeen(0);
        ss.setCorrect(0);
        return "index";
    }

    public String register() {
        if (newStudent.length() > 0) {
            studentService.registerStudent(newStudent);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successfully registered dear " + newStudent));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please give a username"));
        }
        return "index";

    }

}
