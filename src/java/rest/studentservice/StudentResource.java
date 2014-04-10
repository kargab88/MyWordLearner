
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
@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StudentResource {
    
    private Student student;

    public StudentResource() {
        student = new Student();
    }
    
    public StudentResource(Student student){
        this.student  = student;
    }
    
    @XmlAttribute
    public Long getId(){
        return student.getId();
    }
    
    @XmlAttribute
    public String getUsername(){
        return student.getUsername();
    }
    
    @XmlTransient
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
    
}
