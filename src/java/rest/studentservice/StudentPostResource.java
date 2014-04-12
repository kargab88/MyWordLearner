package rest.studentservice;

import entity.Student;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Gábor
 */
@XmlRootElement(name = "newStudent")
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StudentPostResource {

    private Student student;

    public StudentPostResource() {
        student = new Student();
    }

    public StudentPostResource(Student student) {
        this.student = student;
    }
    
    @XmlAttribute
    public String getUsername() {
        return student.getUsername();
    }
    
    public void setUsername(String username) {
        student.setUsername(username);
    }

    public Student getStudent() {
        return student;
    }
    
    @XmlTransient
    public void setStudent(Student student) {
        this.student = student;
    }

}
