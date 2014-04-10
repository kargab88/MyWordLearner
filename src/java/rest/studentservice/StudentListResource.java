package rest.studentservice;

import entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Gábor
 */
@XmlRootElement(name = "students")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentListResource {
    
    @XmlElement(name = "student")
    private List<StudentResource> students;

    public StudentListResource() {
    }

    public StudentListResource(List<Student> studentsList) {
        students = new ArrayList<>();
        for (Student student : studentsList) {
            students.add(new StudentResource(student));
        }
    }
    
    
    
}