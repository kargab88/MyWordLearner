package service;

import dao.StudentDAO;
import entity.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Gábor
 */
@Stateless
public class StudentService {
    
    @Inject
    StudentDAO studentDao;
    
    public List<Student> getStudentsList(){
        return studentDao.getStudentsList();
    }
    
    public void registerStudent(String name){
        studentDao.registerStudent(name);
    }
    
    public Student updateSeen(Student student){
        return studentDao.updateSeen(student);
    }
    
    public Student updateCorrect(Student student){
        return studentDao.updateCorrect(student);
    }
    
    public void removeById(Long id){
        studentDao.removeById(id);
    }
    
}
